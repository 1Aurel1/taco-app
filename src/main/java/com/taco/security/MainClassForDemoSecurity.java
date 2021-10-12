package com.taco.security;

import com.taco.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

//kjo do jet klasa jone e konfigurimit
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class MainClassForDemoSecurity extends WebSecurityConfigurerAdapter {
    // me ane te ksaj i themi springut se si te kerkoj dhe te load-oj  userat
    private final UserDetailsService userDetailsService;
    // eshte nje klas e ofruar nga Spring per te Encripuar passwordet
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    //kjo metod ben konfigurimin e token qe ne momentin qe useri do te logohet, atij do i jepet nje token qe sa here ai te bej
    // kerkes ne web, me ane te ktij token te behet identifikimi i tij
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // sessionCreationPolicy specifikon krijimin e sessioneve per Spring securityn
        // ka disa tipe, psh : STATELESS na tregon qe spring security nuk do te krijoj nje HttpSession
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        // ktu po u japim te drejten qe appin tone te aksesohet nga te gjitha kerkesat
        http.authorizeRequests()
                .antMatchers("/tacos").permitAll()
                .anyRequest().permitAll();
        // me pas vendosim filtrat qe duam per appin, therrasim klasen ku kemi krijuar filtrat
        http.addFilter(new CustomAuthenticationFilter(authenticationManager()));

    }

    //ky bean na kthen token e gjeneruar per userin qe eshte loguar
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
}
