package com.github.jhonathampro.ms_produto.controller;

import com.github.jhonathampro.ms_produto.dto.CategoriaDTO;
import com.github.jhonathampro.ms_produto.entites.Categoria;
import com.github.jhonathampro.ms_produto.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategoria(){

        List<CategoriaDTO> categorias = categoriaService.findAllCategoria();

        return ResponseEntity.ok(categorias);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id){

        CategoriaDTO categoriaDto = categoriaService.findCategoriaById(id);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand()
                .toUri();

        return   ResponseEntity.created(uri).body(categoriaDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Long id,
                                                        @RequestBody @Valid CategoriaDTO categoriaDTO){

        categoriaDTO = categoriaService.updateCategoria(id, categoriaDTO);
        return ResponseEntity.ok(categoriaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoiraById(@PathVariable Long id){

        categoriaService.deleteCategoiraById(id);
        return ResponseEntity.noContent().build();
    }



}
