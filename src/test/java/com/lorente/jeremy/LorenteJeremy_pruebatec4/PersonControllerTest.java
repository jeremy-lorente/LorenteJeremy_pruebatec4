package com.lorente.jeremy.LorenteJeremy_pruebatec4;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getPersons() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/agency/persons")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());


    }

}
