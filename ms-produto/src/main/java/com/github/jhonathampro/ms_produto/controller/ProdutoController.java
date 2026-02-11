package com.github.jhonathampro.ms_produto.controller;


import com.github.jhonathampro.ms_produto.dto.ProdutoInputDTO;
import com.github.jhonathampro.ms_produto.dto.ProdutoResponseDTO;
import com.github.jhonathampro.ms_produto.entites.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> createProduto(
            @RequestBody ProdutoInputDTO inputDTO) {

        ProdutoResponseDTO dto = new ProdutoResponseDTO(1L,
                inputDTO.getNome(), inputDTO.getDescricao(), inputDTO.getValor());

        return ResponseEntity.created(null).body(dto);
    }

    /*
        List <Produto> produtos = new ArrayList<>();
        produtos.add( new Produto(1L, "Smart TV", "Smart TV LED 50 polegadas", 2285.0));
        produtos.add( new Produto(2L, "Mouse", "Mouse sem fio", 250.0));
        produtos.add( new Produto(3L, "Teclado", "Teclado sem fio", 1350.0));
        return ResponseEntity.ok(produtos);

     */
}
