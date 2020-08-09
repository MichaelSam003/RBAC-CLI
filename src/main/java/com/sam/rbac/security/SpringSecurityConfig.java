package com.sam.rbac.security;

import com.sam.rbac.security.authentication.MyAuthenctiationFailureHandler;
import com.sam.rbac.security.authentication.MyAuthenticationSuccessHandler;
import com.sam.rbac.security.authentication.MyLogoutSuccessHandler;
import com.sam.rbac.security.authentication.RestAuthenticationAccessDeniedHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/login.html",
                        "/my/**",
                        "/treetable-lay/**",
                        "/xadmin/**",
                        "/ztree/**",
                        "/statics/**"
                        )
                .permitAll()
                .anyRequest()
                .authenticated()
                ;
        //解决X-Frame-Options DENY问题
        httpSecurity.headers().frameOptions().sameOrigin();
        httpSecurity.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenctiationFailureHandler)
        .and().logout().permitAll().invalidateHttpSession(true).
                deleteCookies("JSESSIONID").logoutSuccessHandler(myLogoutSuccessHandler)
        ;
        //异常处理
        httpSecurity.exceptionHandling().accessDeniedHandler(restAuthenticationAccessDeniedHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
