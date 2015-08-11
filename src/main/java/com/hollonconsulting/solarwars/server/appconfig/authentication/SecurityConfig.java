package com.hollonconsulting.solarwars.server.appconfig.authentication;

import com.hollonconsulting.solarwars.server.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource solarWarDataSource;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private AuthSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthFailureHandler authFailureHandler;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(solarWarDataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT username, password, enabled FROM player WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT p.username, r.role FROM roles r LEFT JOIN player p ON p.id = r.player_id WHERE p.username = ?")
                .rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "**").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/logout").authenticated()
                .antMatchers(HttpMethod.POST, "/api/player/register").permitAll()
                .antMatchers("/*").permitAll()
                .antMatchers("/api/**").hasRole(Integer.toString(Roles.Role.USER.ordinal()))
                .and()
            .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authFailureHandler)
            .and()
            .rememberMe()
                .useSecureCookie(false) //TODO: Change this when we get SSL
            .and()
            .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
