package com.shoppingmall.item.service;

import com.shoppingmall.item.domain.item.Item;
import com.shoppingmall.item.exception.ItemNotFoundException;
import com.shoppingmall.item.repository.ItemRepository;
import com.shoppingmall.item.dto.request.ItemSearch;
import com.shoppingmall.item.dto.request.PantsSave;
import com.shoppingmall.item.dto.request.ShoesSave;
import com.shoppingmall.item.dto.request.TopSave;
import com.shoppingmall.item.dto.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.shoppingmall.item.domain.item.Item.getItemResponse;
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
