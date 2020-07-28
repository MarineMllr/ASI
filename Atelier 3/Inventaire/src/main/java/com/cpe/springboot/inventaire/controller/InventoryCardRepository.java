package com.cpe.springboot.inventaire.controller;

import com.cpe.springboot.inventaire.model.InventoryCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryCardRepository extends CrudRepository<InventoryCard, Integer> {

    List<InventoryCard> findAllByIdUser(int idUser);

    Optional<Object> findById(int id);
}
