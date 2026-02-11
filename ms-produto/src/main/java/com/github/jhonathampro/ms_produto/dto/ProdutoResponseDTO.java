package com.github.jhonathampro.ms_produto.dto;

import java.util.List;

public class ProdutoResponseDTO {
        private long id;
        private String nome;
        private String descricao;
        private double valor;

    public ProdutoResponseDTO(long id, String nome, String descricao, double valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public static List<ProdutoResponseDTO> createMock(){

        return List.of(
            new ProdutoResponseDTO(1L, "Smart TV", "Smart TV LED 50 polegadas", 2285.0),
            new ProdutoResponseDTO(2L, "Mouse", "Mouse sem fio", 250.0),
            new ProdutoResponseDTO(3L, "Teclado", "Teclado sem fio", 1350.0)
        );


    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }
}
