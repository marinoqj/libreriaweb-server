package es.golemdr.libreriaweb.server.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class BasicControllerTest {
	
	
	@Autowired
    MockMvc mockMvc; 
	
    @Autowired
    ObjectMapper objectMapper;
    
    
    @Test
    public void checkApp() throws Exception {

    	
    // Opción 1
        mockMvc.perform(get("/check-app").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(content().string("STATUS: UP"));
        
        
     // Opción 2
        MvcResult resultado = mockMvc.perform(get("/check-app").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();        
        
        assertTrue(resultado.getResponse().getContentAsString().equals("STATUS: UP"));
        
    	
    }

}