package io.core.app.springbase2022.repositories;


import io.core.app.springbase2022.models.items.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {


    @Query("select p from Item p where p.name like %?1%")
    List<Item> findByName(String term);

    List<Item> findByNameContainingIgnoreCase(String term);

    Boolean deleteById(long id);

    List<Item> findByNameStartingWithIgnoreCase(String term);

}
