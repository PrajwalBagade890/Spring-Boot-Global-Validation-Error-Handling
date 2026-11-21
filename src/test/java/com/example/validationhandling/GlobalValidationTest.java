package com.example.validationhandling;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GlobalValidationTest {
    @Autowired private MockMvc mockMvc;

    @Test
    void invalidRequestReturnsBadRequestAndFieldErrors() throws Exception {
        String json = "{\"name\":\"\",\"email\":\"wrong-email\",\"age\":15,\"password\":\"123\"}";
        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.fieldErrors.name").exists())
            .andExpect(jsonPath("$.fieldErrors.email").exists())
            .andExpect(jsonPath("$.fieldErrors.age").exists())
            .andExpect(jsonPath("$.fieldErrors.password").exists());
    }

    @Test
    void validRequestReturnsCreated() throws Exception {
        String json = "{\"name\":\"Prajwal\",\"email\":\"prajwal@example.com\",\"age\":22,\"password\":\"password123\"}";
        mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.message").value("User data is valid and received successfully"));
    }
}
