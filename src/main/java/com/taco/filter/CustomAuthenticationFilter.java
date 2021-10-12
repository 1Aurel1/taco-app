
package com.taco.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    //kjo klase do te bej autentikimin e userit
    private final AuthenticationManager authenticationManager;

    //tek kjo klase do te krijojme filtrat qe do vendosim per appin


    @Override
    //kjo metode do te therritet sa her qe useri do te tentoj te logohet
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //me kto metoda marrim nga kerkesa username & password
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        // ktu i themi authenticationManager qe te bej autentikimin e userit ne app dhe me pas te kalohet ne autentikimin token
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    //kjo metod do te therritet ne momentin qe useri do te logohet me sukses, pra do kontrollohet nese useri eshte loguar me sukses ose jo, dhe ne rast se
    //eshte loguar me sukses ateher gjenerohet nje token me kto info qe na kan ardhur nga kerkesa
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
       //metoda Principle eshte Useri qe eshte loguar me sukses, tani ne kemi informacione per userin e loguar tek objecti user
        User user = (User) authentication.getPrincipal();
        // do te percaktohet algoritmi qe do te perdoret nga token per te gjeneruar kodin e enkriptuar
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        //ktu po krijojme tokenin e aksesit
        String access_token = JWT.create()
                //tek subject vendosim nje vlere unike qe ka ky perdorues psh id, username ne menyre qe te identifikohet
                .withSubject(user.getUsername())
                //ktu percaktojme se per sa koh duam qe token te jet i vlefshem
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                //issuer percaktojme emrin e kompanis ose krijuesin e tokenit
                .withIssuer(request.getRequestURL().toString())
                //me Claims do te listojme te gjitha rolet per nje user dhe akseset qe do te ket
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                //
                .sign(algorithm);

        //
        //ktu po krijojme tokenin e refreshit
       /* String refresh_token = JWT.create()
                //tek subject vendosim nje vlere unike qe ka ky perdorues psh id, username ne menyre qe te identifikohet
                .withSubject(user.getUsername())
                //ktu percaktojme se per sa koh duam qe token te jet i vlefshem
                .withExpiresAt(new Date(System.currentTimeMillis() + 20 * 60 * 1000))
                //issuer percaktojme emrin e kompanis ose krijuesin e tokenit
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);*/

        //me ane te Response, pasi useri eshte loguar me sukses, i dergojme tokenin ne header qe dhe useri kur te dergoj kerkesat e tij drejt appit tone
        //te perdori tokenin si identifikues
        response.addHeader("access_token",access_token);
       // response.addHeader("refresh_token",refresh_token);
    }
}
