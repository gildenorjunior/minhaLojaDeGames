package com.example.minhaLojaDeGames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.minhaLojaDeGames.model.CategoriaModel;
import com.example.minhaLojaDeGames.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	//INJETANDO O JPA REPOSITORY
	@Autowired
	private CategoriaRepository repository;
	
	//MÉTODO GET ONDE TRAZ TODAS AS CATEGORIAS
	@GetMapping
	public List<CategoriaModel> buscaTodos(){
		return repository.findAll();
	}
	
	//MÉTODO GET ONDE TRAZ UMA ÚNICA CATEGORIA POR ID
	//USANDO RESPONSE ENTITY PARA O "TRATAMENTO" CASO NÃO ACHE - OK OU NOT FOUND 
	@GetMapping("/id/{id}")
	public ResponseEntity<CategoriaModel> buscaCategoriaPorId(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)) //SE ACHAR RESPONDE OK 
				.orElse(ResponseEntity.notFound().build());//SE NÃO ACHAR RESPONDE NOT FOUND
	}
	
	//MÉTODO GET ONDE TRAZ UMA CATEGORIA POR DESCRICAO
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<CategoriaModel>> buscarCategoriaPorDescricao(@PathVariable String descricao) {		
		return ResponseEntity.ok(repository.findByDescricaoContainingIgnoreCase(descricao));
	}
	
	//MÉTODO POST PARA GRAVAR UMA NOVA CATEGORIA NO BANCO DE DADOS
	@PostMapping
	public ResponseEntity<CategoriaModel> post(@RequestBody CategoriaModel categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	//MÉTODO PUT PARA ATUALIZAR UMA CATEGORIA DO BANCO DE DADOS
	@PutMapping
	public ResponseEntity<CategoriaModel> put(@RequestBody CategoriaModel categoria){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));
	}
	
	//MÉTODO PARA DELETAR UMA CATEGORIA DO BANCO DE DADOS
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	
}
