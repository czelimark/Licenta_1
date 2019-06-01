package web_layer.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] staticResources = {
                "/css/**",
                "/images/**",
                "/**",
                "/images/**",
                "/fonts/**",
                "/angularJS/**",
                "/dependencies/**",
                "/templates/**"
        };
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(staticResources).permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/login**").permitAll()
                .anyRequest().authenticated()
                .and();
                /*.formLogin().permitAll().loginPage("/loginView.html").loginProcessingUrl("/").defaultSuccessUrl("/homeView.html").failureUrl("/loginView.html?error=true")
                .and()
                .logout().logoutSuccessUrl("/loginView.html");*/
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
