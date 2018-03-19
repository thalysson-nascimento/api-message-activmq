package br.com.ampq.v1.controller;

import javax.validation.Valid;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ampq.v1.model.Usuario;
import br.com.ampq.v1.util.ClientConsumer;
import br.com.ampq.v1.util.ClientProduce;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@PostMapping
	@RequestMapping("/producer")
	public ResponseEntity<Usuario> userProduce(@Valid @RequestBody Usuario usuario, HttpServletResponse response){
		ClientProduce produce = new ClientProduce();
		produce.createProduce(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
	}
	
	@PostMapping
	@RequestMapping("/consumer")
	public ResponseEntity<Usuario> userConsume(@Valid @RequestBody Usuario usuario, HttpServletResponse response){
		ClientConsumer consume = new ClientConsumer();
		consume.userConsume(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
}
