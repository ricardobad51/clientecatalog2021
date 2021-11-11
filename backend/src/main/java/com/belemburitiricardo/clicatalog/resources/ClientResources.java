package com.belemburitiricardo.clicatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belemburitiricardo.clicatalog.dto.ClientDTO;
import com.belemburitiricardo.clicatalog.entity.Client;
import com.belemburitiricardo.clicatalog.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResources {

	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(){
		List<ClientDTO> list = service.findAll();

		return ResponseEntity.ok().body(list);

	}
	
}
