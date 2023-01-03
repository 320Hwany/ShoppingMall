package com.shoppingmall.controller;

import com.shoppingmall.response.ItemResponse;
import com.shoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/item/{itemId}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long itemId) {
        ItemResponse itemResponse = itemService.getItem(itemId);
        return ResponseEntity.ok(itemResponse);
    }
}
