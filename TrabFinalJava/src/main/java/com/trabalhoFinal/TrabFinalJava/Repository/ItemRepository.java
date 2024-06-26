package com.trabalhoFinal.TrabFinalJava.Repository;


import com.trabalhoFinal.TrabFinalJava.Models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAll();

    Optional<Item> findById(Long id);

    Item save(Item item);

    void deleteById(Long id);


}

