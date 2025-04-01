package com.itb.inf3an25.pizzariacurso.model.services;

import java.util.List;

import com.itb.inf3an25.pizzariacurso.model.entity.Produto;

public interface ProdutoService {

    public Produto findById(Long id);
    public Produto save(Produto produto);
    public boolean delete(Long id);
    public Produto update(Produto produto, Long id);
    public List<Produto> findAll();

}
