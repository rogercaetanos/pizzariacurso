package com.itb.inf3an25.pizzariacurso.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration : Normalmente é um dos primeiros recursos a serem carregados toda vez que o spring é executado
// Em outras palavras, ela transforma  a classe em uma classe de configuração

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        
       registry.addMapping("/**")
       .allowedOrigins("http://localhost/5173", "http://localhost/8686")
       .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

    

}


// OPTIONS:  Verifica quais métodos estão disponíveis na api
// HEAD:     Igual ao GET, mas só retorna os cabeçalhos sem o corpo da resposta
// TRACE:    O servidor reflete de volta a requisição recebida, permitindo ao cliente ver como ela foi processada
// CONNECT:  Estabelece um túnel de comunicação com o servidor, normalmente para conexões seguras (HTTPS) via proxies
//           Não é comum em API´S REST e sim em NAVEGADORES