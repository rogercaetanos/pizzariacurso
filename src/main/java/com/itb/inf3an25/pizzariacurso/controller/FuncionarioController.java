package com.itb.inf3an25.pizzariacurso.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itb.inf3an25.pizzariacurso.model.services.ProdutoService;
import com.itb.inf3an25.pizzariacurso.exceptions.BadRequest;
import com.itb.inf3an25.pizzariacurso.model.entity.Produto;

@RestController
@RequestMapping("/api/v1/funcionario")
public class FuncionarioController {

    private final ProdutoService produtoService;  // final: Não aceita alteração após atribuição

    public FuncionarioController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    } 

    // Salvar produto

    @PostMapping("/produto")
    public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/funcionario/produto").toUriString());
        return ResponseEntity.created(uri).body(produtoService.save(produto));
    }

    // Listar todos os produtos
    @GetMapping("/produto")
    public ResponseEntity<List<Produto>> findAllProdutos() {
       return ResponseEntity.ok().body(produtoService.findAll());

    }
    
    // Listar produto por id
    @GetMapping("/produto/{id}")
    public ResponseEntity<Produto> findByIdProduto(@PathVariable (value = "id") String id) {
      try {
        Long idLong = Long.parseLong(id);
        return ResponseEntity.ok().body(produtoService.findById(idLong));   
      } catch (NumberFormatException e) {
        throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 17.");
      }

    }

    // Excluir por id

    @DeleteMapping("/produto/{id}")
    public ResponseEntity<Object> deleteByIdProduto(@PathVariable (value = "id") String id) {
      try {
        Long idLong = Long.parseLong(id);
        if(produtoService.delete(idLong)){
            return ResponseEntity.ok().body("Produto com o id " + id + " excluído com sucesso"); 
        }
      } catch (NumberFormatException e) {
        throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 17.");
      }
        return ResponseEntity.ok().body("Não foi possível a exclusão do produto com o id " + id); 
    }

    // Atualizar produto

    @PutMapping("/produto/{id}")
    public ResponseEntity<Produto> updateByIdProduto(@RequestBody Produto produto, @PathVariable (value = "id") String id) {
       try {
        Long idLong = Long.parseLong(id);
        return ResponseEntity.ok().body(produtoService.update(produto, idLong)); 
       } catch (NumberFormatException e) {
            throw new BadRequest("'" + id + "' não é um número inteiro válido. Por favor, forneça um valor inteiro, como 17.");
       } 

    }

}
