package org.generation.lojadegames.controller;

import java.util.List;

import org.generation.lojadegames.model.Produto;
import org.generation.lojadegames.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return produtoService.findAll();
	}

	@GetMapping(params = "id")
	public ResponseEntity<Produto> getById(@RequestParam Long id) {
		return produtoService.findById(id);
	}

	@GetMapping(params = "descricao")
	public ResponseEntity<Produto> getByDescricao(@RequestParam String descricao) {
		return produtoService.findByDescricao(descricao);
	}
	
	@PostMapping
	public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
			return produtoService.saveOrUpdateProduto(produto);
	}
	
	@PutMapping
	public ResponseEntity<Produto> putProduto(@RequestBody Produto produto) {
		return produtoService.saveOrUpdateProduto(produto);
	}
	
	@DeleteMapping(params = "id")
	public ResponseEntity<Produto> deleteProduto(@RequestParam Long id){
		return produtoService.deleteProduto(id);
	}
}
