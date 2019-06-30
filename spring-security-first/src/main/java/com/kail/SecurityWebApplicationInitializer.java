package com.kail;
import com.kail.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

@EnableWebSecurity
public class SecurityWebApplicationInitializer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(getFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                    .authenticationProvider(preAuthenticationProvider())
                .authorizeRequests()
                    .antMatchers("/noauthen").permitAll()
                    .antMatchers("/authen").hasRole("ADMIN").and()
                .csrf().disable()//disabled, ssid/password as properties name.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
                .httpBasic().authenticationEntryPoint(forbidden())
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("kail").password("1234qwer").roles("ADMIN");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(preAuthenticationProvider());
    }

    @Bean
    public CustomPreAuthenticationFilter getFilter() throws Exception {
        CustomPreAuthenticationFilter filter = new CustomPreAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthenticationProvider(){
        PreAuthenticatedAuthenticationProvider preAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
        preAuthenticationProvider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper(userDetailsService()));
        return preAuthenticationProvider;
    }

    @Bean
    public CustomUserDetailsService userDetailsService(){
        return new CustomUserDetailsService();
    }

    @Bean
    public Http403ForbiddenEntryPoint forbidden(){
        return new Http403ForbiddenEntryPoint();
    }
}
