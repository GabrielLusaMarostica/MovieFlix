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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Diz ao Spring: "Essa classe cria Beans e configura o sistema."
@Configuration
//Liga o Spring Security.
@EnableWebSecurity
//Gera um construtor com os atributos final.
//Isso é usado para injetar o SecurityFilter automaticamente.
@RequiredArgsConstructor
//Essa classe define como a segurança da sua aplicação funciona, tudo passa por aqui
public class SecurityConfig {

    //Esse é o filtro que valida o JWT.
    private final SecurityFilter securityFilter;

    //Esse metodo diz
    //Como proteger rotas
    //Quais endpoints são públicos
    //Como lidar com sessões
    //Quais filtros devem rodar
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                //O Spring Security HABILITA CSRF por padrão.
                //Mas CSRF só faz sentido quando há sessão e cookies.
                .csrf(csrf -> csrf.disable())
                //aqui diz: "Eu não vou usar sessão para guardar o usuário.
                //Cada requisição deve trazer o JWT."
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //define as rotas publicas e privadas, todos sao autorizados a usar o registro e login, todos os outros requests, é necessario estar autenticado
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/movieflix/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/movieflix/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                //"Executa os filtros securityFilter ANTES do filtro padrão do Spring."
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    //BCrypt é o algoritmo recomendado pela Spring Security.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
