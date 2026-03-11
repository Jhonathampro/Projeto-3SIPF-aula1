package com.github.jhonathampro.ms_produto.service;

import com.github.jhonathampro.ms_produto.dto.ProdutoDTO;
import com.github.jhonathampro.ms_produto.entites.Categoria;
import com.github.jhonathampro.ms_produto.entites.Produto;
import com.github.jhonathampro.ms_produto.exceptions.DatabaseException;
import com.github.jhonathampro.ms_produto.exceptions.ResourceNotFoundExeption;
import com.github.jhonathampro.ms_produto.repositores.CategoriaRepository;
import com.github.jhonathampro.ms_produto.repositores.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAllProduto() {

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream().map(ProdutoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findProdutoById(Long id){

        Produto produto = produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundExeption("Recurso não encontrado. ID: " + id)
        );

        return new ProdutoDTO(produto);
    }

    @Transactional
    public ProdutoDTO saveProduto(ProdutoDTO produtoDTO){
        Produto produto = new Produto();

        try {
            copyDtoToProduto(produtoDTO, produto);
            produto = produtoRepository.save(produto);
            return new ProdutoDTO(produto);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Não foi possivel salvar esse produto. categoria inesistente" +
                    "(id: " + produtoDTO.getCategoria().getId() + ")" );
        }
    }

    @Transactional
    public ProdutoDTO updateProduto(Long id, ProdutoDTO produtoDTO){

        try {
            Produto produto = produtoRepository.getReferenceById(id);
            copyDtoToProduto(produtoDTO, produto);
            produto = produtoRepository.save(produto);
            return new ProdutoDTO(produto);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundExeption("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional
    public void deleteProdutoByld(Long id){

        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFoundExeption("Recurso não encontrado. ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

    private void copyDtoToProduto(ProdutoDTO produtoDTO, Produto produto){

         produto.setNome(produtoDTO.getNome());
         produto.setDescricao(produtoDTO.getDescricao());
         produto.setValor(produtoDTO.getValor());

         Categoria categoria =  categoriaRepository.getReferenceById(produtoDTO.getCategoria().getId());

         produto.setCategoria(categoria);
    }



}
