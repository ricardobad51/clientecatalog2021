package com.belemburitiricardo.clicatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belemburitiricardo.clicatalog.dto.ClientDTO;
import com.belemburitiricardo.clicatalog.entity.Client;
import com.belemburitiricardo.clicatalog.repositories.ClientRepository;
import com.belemburitiricardo.clicatalog.services.exception.DataBaseException;
import com.belemburitiricardo.clicatalog.services.exception.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll(){
		
		List<Client> list = repository.findAll();

		return list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
		//return repository.findAll();
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ClientDTO(entity);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		entity.setName(dto.getName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		
		return new ClientDTO(entity);
		
	}
	
	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {

		try {
		Client entity = repository.getOne(id);//não toca no banco, só instacia provisorio, e só qdo salva ele mexe no banco
		entity.setName(dto.getName());

		entity= repository.save(entity);

		return new ClientDTO(entity);
		}catch(ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Id no found " + id);
		}
		//return null;
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
			}catch(EmptyResultDataAccessException e){
				throw new ResourceNotFoundException("Id not found " + id);
			}
			catch(DataIntegrityViolationException di) {
				throw new DataBaseException("Integrity violation ");
			}
		
	}
	
}
