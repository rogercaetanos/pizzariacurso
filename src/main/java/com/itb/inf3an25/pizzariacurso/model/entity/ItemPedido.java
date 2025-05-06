package com.itb.inf3an25.pizzariacurso.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;



@Entity
@Table(name = "itens_pedido")
@Data
public class ItemPedido {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(nullable = false)
private int quantidadeItem;
@Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
private double precoUnitario;
@JsonIgnore
private boolean codStatus;

// Relacionamento - Esta é uma entidade associativa entre Produto e Pedido

@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name="pedido_id", referencedColumnName = "id", nullable = false)
private Pedido pedido;

@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
@JoinColumn(name="produto_id", referencedColumnName = "id", nullable = false)
private Produto produto;



@Transient
@JsonIgnore         
private String mensagemErro = "";

@Transient
@JsonIgnore
private boolean isValid = true;

public boolean validarItemPedido() {
    return isValid;
}


    
}
