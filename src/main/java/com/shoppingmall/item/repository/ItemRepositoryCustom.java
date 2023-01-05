package com.shoppingmall.item.repository;

import com.shoppingmall.item.domain.item.Item;
import com.shoppingmall.item.dto.request.ItemSearch;

import java.util.List;

public interface ItemRepositoryCustom {

    List<Item> getItemsByLatest(ItemSearch itemSearch);

    List<Item> getItemsByLowPrice(ItemSearch itemSearch);

    List<Item> getItemsByHighPrice(ItemSearch itemSearch);
}
