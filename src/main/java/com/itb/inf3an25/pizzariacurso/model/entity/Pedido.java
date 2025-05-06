package com.itb.inf3an25.pizzariacurso.model.entity;

import java.time.LocalDateTime;
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
@Table(name = "pedidos")
@Data
public class Pedido {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(nullable = false, length = 20)
private String numeroPedido;
@Column(nullable = false)
private LocalDateTime dataHoraCompra;
@Column(nullable = true)
private LocalDateTime dataHoraEntrega;
@Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
private double valorTotal;
@Column(nullable = false, length = 20)
private String status;
@JsonIgnore
private boolean codStatus;


@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JsonIgnore
private List<ItemPedido> itemPedidos = new ArrayList<>();


@Transient 
@JsonIgnore        
private String mensagemErro = "";

@Transient
@JsonIgnore
private boolean isValid = true;

public boolean validarPedido() {
    return isValid;
}

    
}