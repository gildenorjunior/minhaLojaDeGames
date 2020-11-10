package com.example.minhaLojaDeGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minhaLojaDeGames.model.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long>{

	public List<CategoriaModel> findByDescricaoContainingIgnoreCase(String descricao);
}
