package com.zandu.readingList.config;

import com.zandu.readingList.model.Reader;
import com.zandu.readingList.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/").access("hasRole(READER)")//require an authenticated user with the READER role.
                    .antMatchers("/**").permitAll()//for open access to all users.
                .and()

                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error=true");///login as the path
        //for the login page as well as the login failure page (along with an error attribute)
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(username->readerRepository.findById(username)
                                                            .orElseThrow(()->new UsernameNotFoundException("user with username "+username+" not found")));
    }

}
