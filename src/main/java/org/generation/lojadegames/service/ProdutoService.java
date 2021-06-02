package org.generation.lojadegames.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.lojadegames.model.Produto;
import org.generation.lojadegames.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	public ProdutoRepository produtoRepository;
	
	/**
	 * Metodo para buscar todos os produtos
	 * @return Um ResponseEntity com o status HTTP da requisição e uma lista de todos os produtos
	 */
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> listaTodos = produtoRepository.findAll();
		if (listaTodos.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaTodos);
		}
	}
	
	/**
	 * Metodo para buscar um produto pelo ID
	 * @param id
	 * @return Um ResponseEntity com o status HTTP da requesição e um produto filtrado pelo ID
	 */
	public ResponseEntity<Produto> findById(Long id) {
		return produtoRepository.findById(id)
				.map(produtoPorId -> ResponseEntity.status(200).body(produtoPorId))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para buscar um produto pela descrição
	 * @param descricao
	 * @return Um ResponseEntity com o status HTTP da requisição e um produto filtrado pela descricao
	 */
	public ResponseEntity<Produto> findByDescricao(String descricao) {
		return produtoRepository.findByDescricao(descricao)
				.map(produtoPorDescricao -> ResponseEntity.status(200).body(produtoPorDescricao))
				.orElse(ResponseEntity.status(404).build());
	}
	
	/**
	 * Metodo para salvar/alterar um produto
	 * @param novaCategoria
	 * @return Caso o produto já exista na base de dados ele não será criado e retornara um status HTTP
	 * , caso contrario será salvado e retornara um status HTTP
	 */
	public ResponseEntity<Produto> saveOrUpdateProduto(@Valid Produto novoProduto) {
		if (novoProduto.getDescricao().isBlank()) {
			return ResponseEntity.status(400).build();
		} else {
			Optional<Produto> produtoExiste = produtoRepository.findByDescricao(novoProduto.getDescricao());
			if (produtoExiste.isEmpty()) {
				return ResponseEntity.status(201).body(produtoRepository.save(novoProduto));
			} else {
				return ResponseEntity.status(406).build();
			}
		}
	}
	
	/**
	 * Metodo para deletar um produto
	 * @param id
	 * @return status HTTP
	 */
	public ResponseEntity<Produto> deleteProduto(Long id){
		if(produtoRepository.findById(id).isPresent()) {
			produtoRepository.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}
}
