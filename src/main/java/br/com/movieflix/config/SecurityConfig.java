package br.com.movieflix.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    //private final SecurityFilter securityFilter;

    @Bean //digo que o spring para que ele gerencia o metodo
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //todos requests que serao feitos, será verificado se essa chamada vem de algum user valido, ou se ele está acessando algo que possa ter acesso
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //configura para que as rotas de register e login sejam abertas para todos, e qualquer outro request, necessita que o usuario esteja autenticado
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/movieflix/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/movieflix/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    //quando alguem fizer uma soliticao de um passwordencoder, ele retornara o passwordencorder criptografado
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
