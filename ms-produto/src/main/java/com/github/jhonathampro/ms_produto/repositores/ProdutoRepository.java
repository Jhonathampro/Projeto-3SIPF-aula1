package com.github.jhonathampro.ms_produto.repositores;

import com.github.jhonathampro.ms_produto.entites.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
