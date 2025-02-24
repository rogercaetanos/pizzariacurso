package com.itb.inf3an25.pizzariacurso.model.entity;

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
@Table(name = "produtos")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = true, length = 255)
    private String descricao;
    @Column(nullable = true, length = 45)
    private String tipo;
    @Column(nullable = true)
    private int quantidadeEstoque;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    private double precoVenda;
    @Column(nullable = true, columnDefinition = "DECIMAL(5,2)")
    private double precoCompra;
    private boolean codStatus;

    // Relacionamento entre classes
    
    //  @ManyToOne:  Muitos pra Um (Muitos produtos para Uma Categoria).
    // CascadeType:  Define como as operações de persistência (INSERT, UPDATE, DELETE) serão
    //               propagadas para uma entidade filha no banco de dados.
    // MERGE:        Propaga operações de atualização da entidade pai para as filhas
    //               O MERGE é usado para atualizar uma entidade desaclopada no banco de dados.
    //               Se a entidade não existe no banco, o MERGE a insere
    //               Se já existe, o MERGE atualiza os dados com os novos valores
    //               Além do MERGE, temos: ALL, PERSIST, REMOVE, REFRESH e DETACH  
    // fetch:        Define como os dados relacionados serão carregados do banco de dados quando a entidade 
    //               for consultada
    // FetchType.LAZY : Os dados só serão carregados quando forem acessados explicitamente no código
    // FetchType.EAGER: Join automático os dados relacionados serão carregados juntamente com a classe pai
    // @JoinColumn: Caracteriza o nome e a referência da FK (Chave Estrangeira)           

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = true)
    private Categoria categoria;

    // Atributos de apoio

    @Transient         // Informa que o atributo NÃO É COLUNA DE BANCO DE DADOS  
    private String mensagemErro = "";
    @Transient
    private boolean isValid = true;

    public boolean validarProduto() {
        if(nome == null || nome.isEmpty()) {
            mensagemErro += "O nome do produto é obrigatório:";
            isValid = false;
        }
        if(precoVenda < 0){
            precoVenda = 0;
            mensagemErro += "O preço de venda do produto deve ser maior que zero:"; 
            isValid = false;
        }
        if(precoCompra < 0){
            precoCompra = 0;
            mensagemErro += "O preço de compra do produto deve ser maior que zero:"; 
            isValid = false;
        }
        
        return isValid;
    }
}
