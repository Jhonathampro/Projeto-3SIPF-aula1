package com.github.jhonathampro.ms_produto.controller;

import com.github.jhonathampro.ms_produto.dto.CategoriaDTO;
import com.github.jhonathampro.ms_produto.entites.Categoria;
import com.github.jhonathampro.ms_produto.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id){

        CategoriaDTO categoriaDto = categoriaService.findCategoriaById(id);

        return

    }



}
