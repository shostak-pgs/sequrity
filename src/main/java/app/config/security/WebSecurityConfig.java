package app.config.security;

import app.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@ComponentScan(basePackages = "app.config.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

   // private CustomUserDetailsService userDetailsService;

   // @Autowired
    //public WebSecurityConfig(CustomUserDetailsService userDetailsService) {
     //   this.userDetailsService = userDetailsService;
   // }

    /**
     * Used to configure AuthenticationManager
     *
     * @param auth the{@link AuthenticationManagerBuilder}
     * @throws Exception throws when enable to apply userDetailsService
     */
   // @Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userDetailsService);
   // }

    /**
     * Configure Spring security
     *
     * @param http allows configuring web based security for specific http requests
     * @throws Exception can be thrown when cors configured
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors()
                .disable()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/", "login")
                .permitAll()
                .antMatchers("/initialize/createUserServlet/**", "/add/**/")
                .hasRole(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureForwardUrl("/initialize/createUserServlet")
                .defaultSuccessUrl("/initialize/createUserServlet")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/logout")
                .logoutUrl("/j_spring_security_logout")
                .permitAll()
                .and()
                .sessionManagement()
                .invalidSessionUrl("/login")
                .maximumSessions(1)
                .expiredUrl("/login");
    }
}