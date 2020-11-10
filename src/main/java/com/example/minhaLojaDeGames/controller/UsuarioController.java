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

import com.example.minhaLojaDeGames.model.UsuarioModel;
import com.example.minhaLojaDeGames.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;

	// MÉTODO GET ONDE TRAZ TODAS OS USUARIOS
	@GetMapping
	public List<UsuarioModel> buscaTodos() {
		return repository.findAll();
	}

	// OUTRA FORMA DE FAZER A BUSCA POR ID, PORÉM SE O ID FOR NULO ELE MOSTRA O NULO
	// @GetMapping("/id/{id}")
	// public Optional<UsuarioModel> buscaUsuarioPorId(@PathVariable Long id){
	// return repository.findById(id);
	// }

	// MÉTODO GET ONDE TRAZ UM UNICO USUARIO POR ID
	// USANDO RESPONSE ENTITY PARA O "TRATAMENTO" CASO NÃO ACHE - OK OU NOT FOUND
	@GetMapping("/id/{id}")
	public ResponseEntity<UsuarioModel> buscaJogosPorId(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)) // SE ACHAR RESPONDE OK
				.orElse(ResponseEntity.notFound().build());// SE NÃO ACHAR RESPONDE NOT FOUND
	}

	// MÉTODO POST PARA GRAVAR UM NOVO USUARIO NO BANCO DE DADOS
	@PostMapping
	public ResponseEntity<UsuarioModel> post(@RequestBody UsuarioModel usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}

	// MÉTODO PUT PARA ATUALIZAR UM USUARIO DO BANCO DE DADOS
	@PutMapping
	public ResponseEntity<UsuarioModel> put(@RequestBody UsuarioModel usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}

	// MÉTODO PARA DELETAR UM USUARIO DO BANCO DE DADOS
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
