package com.kurikulum;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		  .antMatchers("/").hasRole("ADMIN")
		  .antMatchers("/kurikulum/**").hasRole("ADMIN")
		  .antMatchers("/api/**").permitAll()
		  .antMatchers("/img/**").permitAll()
		  .antMatchers("/css/**").permitAll()
		  .antMatchers("/js/**").permitAll()
		  .anyRequest().authenticated()
		  .and()
		  .formLogin()
		  .loginPage("/login")
		  .defaultSuccessUrl("/", true)
		  .permitAll()
		  .and()
		  .logout()
          .invalidateHttpSession(true)
          .clearAuthentication(true)
          .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
          .logoutSuccessUrl("/login")
          .permitAll().and().csrf().disable();
}
		
//		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/rest/**").permitAll().antMatchers("/course/**").hasRole("ADMIN")
//		.antMatchers("/student/**").hasAnyRole("USER", "ADMIN").anyRequest().authenticated().and().formLogin()
//		.loginPage("/login").permitAll().and().logout().permitAll();
	

		
		/* @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
		  throws Exception {
		  auth.inMemoryAuthentication().withUser("user").password("user").roles(
		  "USER");
		  auth.inMemoryAuthentication().withUser("admin").password("admin").roles(
		  "ADMIN"); }*/
		 

		@Autowired
		DataSource dataSource;
		 
		@Autowired
		public void configAuthentication (AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(
					"select username,password,enabled from user where username=?")
			.authoritiesByUsernameQuery(
					"select username, role from user_roles where username=?");
			
			
		}
		
	
}