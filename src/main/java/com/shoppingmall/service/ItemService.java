package com.shoppingmall.service;

import com.shoppingmall.domain.item.Item;
import com.shoppingmall.exception.e404.ItemNotFoundException;
import com.shoppingmall.repository.item.ItemRepository;
import com.shoppingmall.response.ItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.shoppingmall.domain.item.Item.getItemResponse;

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
}
