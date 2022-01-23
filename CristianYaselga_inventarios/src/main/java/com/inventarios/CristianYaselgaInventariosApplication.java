package com.inventarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.inventarios.dto.ProductoMockDto;
import com.inventarios.dto.ProductosMockDto;
import com.inventarios.jpa.entity.Producto;
import com.inventarios.security.JWTAuthorizationFilter;
import com.inventarios.services.ProductoService;


@SpringBootApplication
public class CristianYaselgaInventariosApplication {

	
	private static final String URL_PRODUCTOS= "https://mocki.io/v1/16ad9e92-209e-44d4-ba35-a3d0f887bdd8";
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(CristianYaselgaInventariosApplication.class, args);
		ProductoService productoService = applicationContext.getBean(ProductoService.class);
		llenarProductosIniciales(productoService);
		
	}
	
	private static void llenarProductosIniciales(ProductoService productoService) {
		
		RestTemplate restTemplate = new RestTemplate();
		ProductosMockDto productos = restTemplate.getForObject(URL_PRODUCTOS, ProductosMockDto.class);
	
		for(ProductoMockDto producto: productos.getProds()) {
			System.out.println(" PROd "+producto);
			productoService.save(castearProducto(producto));
		}
	}
	
	private static Producto castearProducto(ProductoMockDto producto) {
		Producto registro = new Producto();
		registro.setIdProducto(producto.getId());
		registro.setCodigo(producto.getCod());
		registro.setNombre(producto.getName());
		registro.setPrecio(producto.getPrice());
		registro.setStock(producto.getStock());
		return registro;
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/clienteLogin").permitAll()
				.antMatchers(HttpMethod.GET, "/cliente").permitAll()
				.antMatchers(HttpMethod.POST, "/cliente").permitAll()
				.antMatchers(HttpMethod.PUT, "/cliente").permitAll()
				.antMatchers(HttpMethod.DELETE, "/cliente").permitAll()
				.antMatchers(HttpMethod.GET, "/producto").permitAll()
				.antMatchers(HttpMethod.PUT, "/producto").permitAll()
				.antMatchers(HttpMethod.POST, "/tienda-producto").permitAll()
				.antMatchers(HttpMethod.DELETE, "/tienda-producto").permitAll()
				.antMatchers(HttpMethod.GET, "/tienda").permitAll()
				.antMatchers(HttpMethod.GET, "/numero-transacciones").permitAll()
				.antMatchers(HttpMethod.GET, "/monto-vendido").permitAll()
				.anyRequest().authenticated();
		}
	}

}
