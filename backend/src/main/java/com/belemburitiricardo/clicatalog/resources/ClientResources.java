package com.belemburitiricardo.clicatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belemburitiricardo.clicatalog.entity.Client;

@RestController
@RequestMapping(value = "/clients")
public class ClientResources {

	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		List<Client> list = new ArrayList<>(); 

		list.add(new Client());

		return ResponseEntity.ok().body(list);

	}
	
}
