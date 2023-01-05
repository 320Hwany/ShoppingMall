package com.shoppingmall.item.controller;

import com.shoppingmall.item.dto.request.PantsSave;
import com.shoppingmall.item.dto.request.ShoesSave;
import com.shoppingmall.item.dto.request.TopSave;
import com.shoppingmall.item.dto.response.ItemResponse;
import com.shoppingmall.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/admin/item")
@RestController
public class ItemControllerForAdmin {

    private final ItemService itemService;

    @PostMapping("/pants")
    public ResponseEntity<ItemResponse> savePants(@RequestBody @Valid PantsSave pantsSave) {
        ItemResponse itemResponse = itemService.savePants(pantsSave);
        return ResponseEntity.ok(itemResponse);
    }

    @PostMapping("/shoes")
    public ResponseEntity<ItemResponse> saveShoes(@RequestBody @Valid ShoesSave shoesSave) {
        ItemResponse itemResponse = itemService.saveShoes(shoesSave);
        return ResponseEntity.ok(itemResponse);
    }

    @PostMapping("/top")
    public ResponseEntity<ItemResponse> saveTop(@RequestBody @Valid TopSave topSave) {
        ItemResponse itemResponse = itemService.saveTop(topSave);
        return ResponseEntity.ok(itemResponse);
    }
}
