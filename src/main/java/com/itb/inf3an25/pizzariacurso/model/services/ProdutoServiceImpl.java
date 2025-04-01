package com.itb.inf3an25.pizzariacurso.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itb.inf3an25.pizzariacurso.exceptions.BadRequest;
import com.itb.inf3an25.pizzariacurso.exceptions.NotFound;
import com.itb.inf3an25.pizzariacurso.model.entity.Produto;
import com.itb.inf3an25.pizzariacurso.model.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto findById(Long id) {
        try {
            Produto produto = produtoRepository.findById(id).get();
            return produto;
        } catch (Exception e) {
            throw new NotFound("Produto não encontrado com o id " + id);
        }
    }

    @Override
    @Transactional
    public Produto save(Produto produto) {
        produto.setCodStatus(true);
        if (!produto.validarProduto()) {
            throw new BadRequest(produto.getMensagemErro());
        }
        return produtoRepository.save(produto);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new NotFound("Produto não encontrado com o id " + id);
        }
        produtoRepository.deleteById(id);
        return true;
    }

    @Override
    public Produto update(Produto produto, Long id) {

     if(!produto.validarProduto()) {
       throw new BadRequest(produto.getMensagemErro());
     }
     if(!produtoRepository.existsById(id)){
       throw new NotFound("Produto não encontrado com o id " + id);
     }
     // Agora posso atualizar o produto

     Produto produtoDb = produtoRepository.findById(id).get();
     produtoDb.setNome(produto.getNome());
     produtoDb.setDescricao(produto.getDescricao());
     produtoDb.setPrecoVenda(produto.getPrecoVenda());
     produtoDb.setPrecoCompra(produto.getPrecoCompra());
     produtoDb.setQuantidadeEstoque(produto.getQuantidadeEstoque());
     produtoDb.setTipo(produto.getTipo());
     
     if(produto.getCategoria() != null) {
      //  Categoria categoriaDb = categoriaService.findById(produto.getCategoria().getId());
      //  if(categoriaDb == null ){
      //      throw new NotFound("Categoria não encontrada com o id " + id);
      //  } 
      // produtoDb.setCategoria(categoriaDb);

     }
     return produtoRepository.save(produtoDb);
    }

    @Override
    public List<Produto> findAll() {

        return produtoRepository.findAll();
    }

}
