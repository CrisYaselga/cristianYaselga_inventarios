package com.inventarios;

import org.assertj.core.api.Fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.inventarios.jpa.dao.ClienteDao;
import com.inventarios.jpa.entity.Cliente;
import com.inventarios.rest.api.ClienteRestApi;
import com.inventarios.services.ClienteService;

@RunWith(MockitoJUnitRunner.class)
public class ClientesTest {
	
	@Mock
	private ClienteService clienteservice;
	
	
	@Mock
	private ClienteDao clientedao;
	
	@InjectMocks
	private ClienteRestApi clienteApi;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(ClientesTest.class);
	}
	

	@Test
	public void  registrarCliente() {
	
		Cliente cliente = new Cliente();
		cliente.setIdentificacion("100145426");
		cliente.setNombre("Arturo Reyes");
		cliente.setPassword("artud");
		
	
	}
}
