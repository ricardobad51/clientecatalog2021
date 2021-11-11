package com.belemburitiricardo.clicatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belemburitiricardo.clicatalog.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
