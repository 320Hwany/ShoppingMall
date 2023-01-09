package com.shoppingmall.item.repository;


import com.shoppingmall.item.domain.item.Item;
import com.shoppingmall.item.domain.item.Shoes;
import com.shoppingmall.item.dto.request.ItemSearch;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository {

    List<Item> getItemsByLatest(ItemSearch itemSearch);
    List<Item> getItemsByLowPrice(ItemSearch itemSearch);
    List<Item> getItemsByHighPrice(ItemSearch itemSearch);

    void save(Item item);

    Optional<Item> findById(Long id);

    void delete(Item item);

    void saveAll(List<Shoes> shoesList);

    void deleteAll();
}
