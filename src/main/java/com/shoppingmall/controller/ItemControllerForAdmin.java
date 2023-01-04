package com.shoppingmall.controller;

import com.shoppingmall.request.item.PantsSave;
import com.shoppingmall.request.item.ShoesSave;
import com.shoppingmall.request.item.TopSave;
import com.shoppingmall.response.ItemResponse;
import com.shoppingmall.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ItemControllerForAdmin {

    private final ItemService itemService;

    @PostMapping("/admin/item/pants")
    public ResponseEntity<ItemResponse> savePants(@RequestBody @Valid PantsSave pantsSave) {
        ItemResponse itemResponse = itemService.savePants(pantsSave);
        return ResponseEntity.ok(itemResponse);
    }

    @PostMapping("/admin/item/shoes")
    public ResponseEntity<ItemResponse> saveShoes(@RequestBody @Valid ShoesSave shoesSave) {
        ItemResponse itemResponse = itemService.saveShoes(shoesSave);
        return ResponseEntity.ok(itemResponse);
    }

    @PostMapping("/admin/item/top")
    public ResponseEntity<ItemResponse> saveTop(@RequestBody @Valid TopSave topSave) {
        ItemResponse itemResponse = itemService.saveTop(topSave);
        return ResponseEntity.ok(itemResponse);
    }
}
