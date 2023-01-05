package com.shoppingmall.item.controller;

import com.shoppingmall.item.dto.request.ItemSearch;
import com.shoppingmall.item.dto.response.ItemResponse;
import com.shoppingmall.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/item/{itemId}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long itemId) {
        ItemResponse itemResponse = itemService.getItem(itemId);
        return ResponseEntity.ok(itemResponse);
    }

    @GetMapping("/items-latest")
    public ResponseEntity<List<ItemResponse>> getItemsByLatest(@ModelAttribute ItemSearch itemSearch) {
        List<ItemResponse> itemsResponseByLatest = itemService.getItemsByLatest(itemSearch);
        return ResponseEntity.ok(itemsResponseByLatest);
    }

    @GetMapping("/items-lowPrice")
    public ResponseEntity<List<ItemResponse>> getItemsByLowPrice(@ModelAttribute ItemSearch itemSearch) {
        List<ItemResponse> itemsResponseByLatest = itemService.getItemsByLowPrice(itemSearch);
        return ResponseEntity.ok(itemsResponseByLatest);
    }

    @GetMapping("/items-highPrice")
    public ResponseEntity<List<ItemResponse>> getItemsByHighPrice(@ModelAttribute ItemSearch itemSearch) {
        List<ItemResponse> itemsResponseByLatest = itemService.getItemsByHighPrice(itemSearch);
        return ResponseEntity.ok(itemsResponseByLatest);
    }
}
