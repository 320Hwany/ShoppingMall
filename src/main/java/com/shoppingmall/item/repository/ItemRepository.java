package com.shoppingmall.item.repository;

import com.shoppingmall.item.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
}
