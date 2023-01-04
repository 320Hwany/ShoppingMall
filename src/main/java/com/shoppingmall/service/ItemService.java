package com.shoppingmall.service;

import com.shoppingmall.domain.item.Item;
import com.shoppingmall.exception.e404.ItemNotFoundException;
import com.shoppingmall.repository.item.ItemRepository;
import com.shoppingmall.request.item.ItemSearch;
import com.shoppingmall.request.item.PantsSave;
import com.shoppingmall.request.item.ShoesSave;
import com.shoppingmall.request.item.TopSave;
import com.shoppingmall.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.shoppingmall.domain.item.Item.getItemResponse;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemResponse getItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);
        return getItemResponse(item);
    }

    public List<ItemResponse> getItemsByLatest(ItemSearch itemSearch) {
        return itemRepository.getItemsByLatest(itemSearch).stream()
                .map(Item::getItemResponse)
                .collect(toList());
    }

    public List<ItemResponse> getItemsByLowPrice(ItemSearch itemSearch) {
        return itemRepository.getItemsByLowPrice(itemSearch).stream()
                .map(Item::getItemResponse)
                .collect(toList());
    }

    public List<ItemResponse> getItemsByHighPrice(ItemSearch itemSearch) {
        return itemRepository.getItemsByHighPrice(itemSearch).stream()
                .map(Item::getItemResponse)
                .collect(toList());
    }

    @Transactional
    public ItemResponse savePants(PantsSave pantsSave) {
        Item item = pantsSave.toEntity();
        itemRepository.save(item);
        return getItemResponse(item);
    }

    @Transactional
    public ItemResponse saveShoes(ShoesSave shoesSave) {
        Item item = shoesSave.toEntity();
        itemRepository.save(item);
        return getItemResponse(item);
    }

    @Transactional
    public ItemResponse saveTop(TopSave topSave) {
        Item item = topSave.toEntity();
        itemRepository.save(item);
        return getItemResponse(item);
    }
}
