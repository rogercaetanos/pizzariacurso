package com.itb.inf3an25.pizzariacurso.exceptions;

// RuntimeException: Exceptions que podem ser ou não tratadas pelo programador, normalmente acontecem
//                   em tempo de execução, ou seja, seu tratamento não é obrigatório
public class NotFound extends RuntimeException {

    public NotFound(String message) {
        super(message); // acessando o construtor da classe pai
    }

}
