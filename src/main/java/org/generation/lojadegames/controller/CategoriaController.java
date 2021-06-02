package org.generation.lojadegames.controller;

import java.util.List;

import org.generation.lojadegames.model.Categoria;
import org.generation.lojadegames.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
		return categoriaService.findAll();
	}

	@GetMapping(params = "id")
	public ResponseEntity<Categoria> getById(@RequestParam Long id) {
		return categoriaService.findById(id);
	}

	@GetMapping(params = "descricao")
	public ResponseEntity<Categoria> getByDescricao(@RequestParam String descricao) {
		return categoriaService.findByDescricao(descricao);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria){
			return categoriaService.saveOrUpdateCategoria(categoria);
	}
	
	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@RequestBody Categoria categoria) {
		return categoriaService.saveOrUpdateCategoria(categoria);
	}
	
	@DeleteMapping(params = "id")
	public ResponseEntity<Categoria> deleteCategoria(@RequestParam Long id){
		return categoriaService.deleteCategoria(id);
	}
}
