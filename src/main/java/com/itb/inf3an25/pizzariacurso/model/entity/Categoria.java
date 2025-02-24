package com.itb.inf3an25.pizzariacurso.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;


@Entity
@Table(name = "categorias")
@Data
public class Categoria {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = true, length = 255)
    private String descricao;
    private boolean codStatus;

   // Relacionamento entre classes
   
   // @OneToMany: Um para muitos (Uma Categoria para Muitos Produtos)

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();


    @Transient      
    private String mensagemErro = "";
    @Transient
    private boolean isValid = true;

    public boolean validarCategoria() {
        if(nome == null || nome.isEmpty()) {
            mensagemErro += "O nome da categoria é obrigatório:";
            isValid = false;
        }
        return isValid;
    }

}
