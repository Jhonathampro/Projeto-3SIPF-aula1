package com.github.jhonathampro.ms_produto.repositores;

import com.github.jhonathampro.ms_produto.entites.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
