package com.github.jhonathampro.ms_produto.controller;


import com.github.jhonathampro.ms_produto.dto.ProdutoDTO;
import com.github.jhonathampro.ms_produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

  @Autowired
    private ProdutoService produtoService;

  @Profile("test")
  @GetMapping("/-demor/500")
  public String force500(){
      throw new RuntimeException("Erro 500 forçado por demostração");
  }

  @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getALLProdutos(){

      List<ProdutoDTO> list = produtoService.findAllProduto();

      return ResponseEntity.ok(list);
  }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getProdutoById(@PathVariable Long id){

        ProdutoDTO produtoDTO = produtoService.findProdutoById(id);

        return ResponseEntity.ok(produtoDTO);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> createProduto(@RequestBody @Valid ProdutoDTO produtoDTO){
          produtoDTO = produtoService.saveProduto(produtoDTO);

          URI uri = ServletUriComponentsBuilder
                  .fromCurrentRequestUri()
                  .path("/{id}")
                  .buildAndExpand(produtoDTO.getId())
                  .toUri();

          return  ResponseEntity.created(uri).body(produtoDTO);
    }

    @PutMapping("/{id}")
   public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable long id,@RequestBody @Valid ProdutoDTO produtoDTO){

      produtoDTO = produtoService.updateProduto(id, produtoDTO);

      return  ResponseEntity.ok(produtoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable long id){

       produtoService.deleteProdutoByld(id);

       return ResponseEntity.noContent().build();
    }
}
