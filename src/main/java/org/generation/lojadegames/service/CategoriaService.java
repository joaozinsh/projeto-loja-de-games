package org.generation.lojadegames.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.lojadegames.model.Categoria;
import org.generation.lojadegames.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository categoriaRepository;
	
	/**
	 * Metodo para buscar todas as categorias
	 * @return Um ResponseEntity com o status HTTP da requisição e uma lista de todas as categorias 
	 */
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> listaTodos = categoriaRepository.findAll();
		if (listaTodos.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaTodos);
		}
	}
	
	/**
	 * Metodo para buscar uma categoria pelo ID
	 * @param id
	 * @return Um ResponseEntity com o status HTTP da requesição euma categoria filtrada pelo ID
	 */
	public ResponseEntity<Categoria> findById(Long id) {
		return categoriaRepository.findById(id)
				.map(categoriaPorId -> ResponseEntity.status(200).body(categoriaPorId))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para buscar uma categoria pela descrição
	 * @param descricao
	 * @return Um ResponseEntity com o status HTTP da requisição e uma categoria filtrada pela descricao
	 */
	public ResponseEntity<Categoria> findByDescricao(String descricao) {
		return categoriaRepository.findByDescricao(descricao)
				.map(categoriaPorDescricao -> ResponseEntity.status(200).body(categoriaPorDescricao))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para salvar/alterar uma categoria
	 * @param novaCategoria
	 * @return Caso a categoria já exista na base de dados ela não será criada e retornara um status HTTP
	 * , caso contrario será salvada e retornara um status HTTP
	 */
	public ResponseEntity<Categoria> saveOrUpdateCategoria(@Valid Categoria novaCategoria) {
		if (novaCategoria.getDescricao().isBlank()) {
			return ResponseEntity.status(400).build();
		} else {
			Optional<Categoria> categoriaExiste = categoriaRepository.findByDescricao(novaCategoria.getDescricao());
			if (categoriaExiste.isEmpty()) {
				return ResponseEntity.status(201).body(categoriaRepository.save(novaCategoria));
			} else {
				return ResponseEntity.status(406).build();
			}
		}
	}
	
	/**
	 * Metodo para remover uma categoria e por consequência, todos os produtos relacionados
	 * @param id
	 * @return status HTTP
	 */
	public ResponseEntity<Categoria> deleteCategoria(Long id){
		if(categoriaRepository.findById(id).isPresent()) {
			categoriaRepository.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}
}
