package com.github.jhonathampro.ms_produto.service;

import com.github.jhonathampro.ms_produto.dto.CategoriaDTO;
import com.github.jhonathampro.ms_produto.entites.Categoria;
import com.github.jhonathampro.ms_produto.exceptions.DatabaseException;
import com.github.jhonathampro.ms_produto.exceptions.ResourceNotFoundExeption;
import com.github.jhonathampro.ms_produto.repositores.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAllCategoria(){

        return categoriaRepository.findAll().stream()
                .map(CategoriaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findCategoriaById(long id){

        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundExeption("Recurso não encontrado. ID: " + id)
        );
        return new CategoriaDTO(categoria);
    }

    @Transactional
    public CategoriaDTO saveCategoria(CategoriaDTO inputDTO){
        Categoria categoria = new Categoria();
        copyDtoToCategoria(inputDTO, categoria);
        categoria = categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria);

    }
    private void copyDtoToCategoria(CategoriaDTO inputDTO, Categoria categoria){
        categoria.setNome(inputDTO.getNome());
    }

    @Transactional
    public CategoriaDTO updateCategoria(Long id, CategoriaDTO inputDto){

        try{
            Categoria categoria = categoriaRepository.getReferenceById(id);
            copyDtoToCategoria(inputDto, categoria);
            categoria = categoriaRepository.save(categoria);
            return new CategoriaDTO(categoria);
        } catch (EntityNotFoundException ex){
            throw new ResourceNotFoundExeption("Recurso não encontrado. id " + id);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteCategoiraById(Long id){
        if(!categoriaRepository.existsById(id)){
            throw new ResourceNotFoundExeption("Recurso não encontrado. ID: " + id);

        }

        try {
            categoriaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Não foi possivel excluir a catgoria" +
                   "Exitem produtos associados a ela." );
        }


    }
}
