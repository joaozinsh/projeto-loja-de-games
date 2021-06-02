package org.generation.lojadegames.repository;

import java.util.Optional;

import org.generation.lojadegames.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public Optional<Categoria> findByDescricao(String descricao);
}
