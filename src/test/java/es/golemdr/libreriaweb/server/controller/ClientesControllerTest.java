package es.golemdr.libreriaweb.server.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.golemdr.libreriaweb.server.domain.Cliente;
import es.golemdr.libreriaweb.server.ext.Constantes;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientesControllerTest {
	
	
	private static final String DNI = "1234567C";
	
	@Autowired
    MockMvc mockMvc; 
	
    @Autowired
    ObjectMapper objectMapper;
    
    
    @Test
    public void bucarClienteDNI() throws Exception {
    	    	
        mockMvc.perform(get("/clientes/buscar/" + DNI.toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.nombre", is("Isabel")))
        .andExpect(jsonPath("$.apellidos", is("Antón Pinz")));
        
    }
    
    @Test
    public void buscarClientes() throws Exception {
    	
    	Cliente cliente = new Cliente();
    	cliente.setNombre("Is");
    	
		String clienteJson = objectMapper.writeValueAsString(cliente);
		
        mockMvc.perform(post("/clientes/buscar")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content(clienteJson))
                .andExpect(status().isOk())                
		        .andExpect(content().contentType(MediaType.APPLICATION_JSON))	       
		        .andExpect(jsonPath("$[0].nombre", is("Isabel")))
		        .andExpect(jsonPath("$[0].apellidos", is("Antón Pinz")));
    	
    }
    
    @Test
    public void listadoClientesPaginado() throws Exception {
    	
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(Constantes.PAGINACION_INICIO, "0");
		headers.set(Constantes.PAGINACION_ELEMENTOS_PAGINA, "5");
    	
    	
        mockMvc.perform(get("/clientes/paginado").accept(MediaType.APPLICATION_JSON).headers(headers))	        	   
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(MediaType.APPLICATION_JSON))	       
		        .andExpect(jsonPath("$[0].nombre", is("Isabel")))
		        .andExpect(jsonPath("$[0].apellidos", is("Antón Pinz")));
    	
    }

}
