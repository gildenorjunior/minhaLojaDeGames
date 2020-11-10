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

import com.example.minhaLojaDeGames.model.JogosModel;
import com.example.minhaLojaDeGames.repository.JogosRepository;

@RestController
@RequestMapping("/jogos")
public class JogosController {

	@Autowired
	private JogosRepository repository;
	
	//MÉTODO GET ONDE TRAZ TODAS OS JOGOS
	@GetMapping
	public List<JogosModel> buscaTodos(){
		return repository.findAll();
	}
	
	
	//OUTRA FORMA DE FAZER A BUSCA POR ID, PORÉM SE O ID FOR NULO ELE MOSTRA O NULO
	//	@GetMapping("/id/{id}")
	//	public Optional<JogosModel> buscaJogosPorId(@PathVariable Long id){
	//		return repository.findById(id);
	//	}
	
	
	//MÉTODO GET ONDE TRAZ UMA ÚNICA CATEGORIA POR ID
	//USANDO RESPONSE ENTITY PARA O "TRATAMENTO" CASO NÃO ACHE - OK OU NOT FOUND
	@GetMapping("/id/{id}")
	public ResponseEntity<JogosModel> buscaJogosPorId(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)) //SE ACHAR RESPONDE OK 
				.orElse(ResponseEntity.notFound().build());//SE NÃO ACHAR RESPONDE NOT FOUND
	}
	
	//MÉTODO GET ONDE TRAZ UM JOGO POR DESCRICAO
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<JogosModel>> buscaJogoPorDescricao(@PathVariable String descricao) {		
		return ResponseEntity.ok(repository.findByDescricaoContainingIgnoreCase(descricao));
	}
	
	//MÉTODO POST PARA GRAVAR UM NOVO JOGO NO BANCO DE DADOS
	@PostMapping
	public ResponseEntity<JogosModel> post(@RequestBody JogosModel jogos){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(jogos));
	}
	
	//MÉTODO PUT PARA ATUALIZAR UMA CATEGORIA DO BANCO DE DADOS
	@PutMapping
	public ResponseEntity<JogosModel> put(@RequestBody JogosModel jogos){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(jogos));
	}
	
	//MÉTODO PARA DELETAR UMA CATEGORIA DO BANCO DE DADOS
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	
}
