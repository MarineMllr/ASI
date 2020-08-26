package com.sp.repository;

import com.sp.model.InventoryCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryCardRepository extends CrudRepository<InventoryCard, Integer> {

    List<InventoryCard> findAllByIdUser(int idUser);

    InventoryCard findById(int id);
}
