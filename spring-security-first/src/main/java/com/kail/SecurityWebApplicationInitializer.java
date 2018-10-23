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
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityWebApplicationInitializer extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(getFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .authenticationProvider(preAuthenticationProvider())
                .authorizeRequests()
                .antMatchers("/noauthen").permitAll()
                .antMatchers("/authen").authenticated()
        .and().sessionManagement().
        ;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(preAuthenticationProvider());
        super.configure(auth);

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
        preAuthenticationProvider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper(userDetailsService));
        return preAuthenticationProvider;
    }
}
