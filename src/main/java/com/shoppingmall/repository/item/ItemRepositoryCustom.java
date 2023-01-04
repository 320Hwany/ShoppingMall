package com.shoppingmall.repository.item;

import com.shoppingmall.domain.item.Item;
import com.shoppingmall.request.item.ItemSearch;

import java.util.List;

public interface ItemRepositoryCustom {

    List<Item> getItemsByLatest(ItemSearch itemSearch);

    List<Item> getItemsByLowPrice(ItemSearch itemSearch);

    List<Item> getItemsByHighPrice(ItemSearch itemSearch);
}
