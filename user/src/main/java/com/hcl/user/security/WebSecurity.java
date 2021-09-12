package com.hcl.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hcl.user.filter.JwtFilter;
import com.hcl.user.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
	   @Autowired
	    private UserServiceImpl userServiceImpl;

	    @Autowired
	    private JwtFilter jwtFilter;

		@Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	    
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userServiceImpl);
	    }
//	    @Bean
//	    public BCryptPasswordEncoder encoder(){
//	        return new BCryptPasswordEncoder();
//	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder(){
	        return NoOpPasswordEncoder.getInstance();
	    }

	    @Override
		protected AuthenticationManager authenticationManager() throws Exception {
			// TODO Auto-generated method stub
			return super.authenticationManager();
		}


	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable().cors().disable().authorizeRequests().antMatchers("/authenticate")
	                .permitAll().anyRequest().authenticated()
	                .and().exceptionHandling().and().sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
	    }
}