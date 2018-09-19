package com.ahorasw.edge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    protected ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {
        return new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(HttpSecurity http) throws Exception {

                http
                .anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/auth/*").hasAnyAuthority("admin","userweb")
                .antMatchers("/admin/*").hasAuthority("admin")
                .antMatchers("/pub/*").permitAll()
                .antMatchers("/api/vendas/auth/admin/*").hasAuthority("admin")
                .antMatchers("/api/vendas/auth/*").hasAnyAuthority("admin","userweb")
                .antMatchers("/api/vendas/pub/*").permitAll()
                .antMatchers("/api/logistica/admin/*").hasAuthority("admin")
                .antMatchers("/api/logistica/auth/*").hasAnyAuthority("admin","userweb")
                .antMatchers("/api/logistica/pub/*").permitAll();
                
            }

        };
    }       
    
}

