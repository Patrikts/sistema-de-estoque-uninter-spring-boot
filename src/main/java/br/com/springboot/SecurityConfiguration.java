package br.com.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("admin")
				.password(passwordEncoder().encode("12345"))
				.roles("ADMINISTRADOR")
			.and()
				.withUser("user")
				.password(passwordEncoder().encode("12345"))
				.roles("USUARIO");				
	}
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/api/**").permitAll()
				.antMatchers(HttpMethod.GET, "/nota-entrada").hasRole("ADMINISTRADOR")
				.antMatchers(HttpMethod.GET, "/nota-saida").hasRole("ADMINISTRADOR")
				.antMatchers(HttpMethod.GET, "/estoque").hasRole("ADMINISTRADOR")
			.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
			.and()
				.cors()
			.and()
				.csrf()
				.disable();
	}
}
