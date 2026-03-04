package com.github.jhonathampro.ms_produto.dto;

import com.github.jhonathampro.ms_produto.entites.Categoria;
import com.github.jhonathampro.ms_produto.entites.Produto;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoriaDTO {

    private Long id;

    @NotBlank(message =  "Campo nome não pode ser vazio, nulo ou em branco")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 a 100 caracteres")
    private String nome;

    public CategoriaDTO(Categoria categoria){
        id = categoria.getId();
        nome = categoria.getNome();
    }


}
