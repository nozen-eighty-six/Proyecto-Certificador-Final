package idat.Proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringBootSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(getEnecoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers("/administrador").access("hasAnyRole('ADMIN', 'ALMACEN')")
		.antMatchers("/administrador/**").access("hasAnyRole('ADMIN', 'ALMACEN')")

		//.antMatchers("/productos").access("hashRole('ADMIN')")
		//.antMatchers("/productos/**").access("hashRole('ADMIN')")
		//.antMatchers("/marcas").access("hashRole('ADMIN')")
		//.antMatchers("/marcas/**").access("hashRole('ADMIN')")
		//.antMatchers("/categorias").access("hashRole('ADMIN')")
		//.antMatchers("/categorias/**").access("hashRole('ADMIN')")
		//.antMatchers("/proveedores").access("hashRole('ADMIN')")
		//.antMatchers("/proveedores/**").access("hashRole('ADMIN')")
		.and().formLogin().loginPage("/usuario/login")
		.permitAll().defaultSuccessUrl("/usuario/acceder");
		
	}
	
	@Bean(name = "encrypt")
	public BCryptPasswordEncoder getEnecoder() {
		return new BCryptPasswordEncoder();
	}
	
}
