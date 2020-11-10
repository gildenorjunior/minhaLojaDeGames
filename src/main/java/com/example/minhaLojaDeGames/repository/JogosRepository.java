package com.example.minhaLojaDeGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minhaLojaDeGames.model.JogosModel;

public interface JogosRepository extends JpaRepository<JogosModel, Long>{

	public List<JogosModel> findByDescricaoContainingIgnoreCase(String descricao);

}
