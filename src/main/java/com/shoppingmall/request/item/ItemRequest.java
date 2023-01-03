package com.shoppingmall.request.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRequest {

    private String itemName;

    private int itemPrice;

    private String itemColor;

    @Builder
    public ItemRequest(String itemName, int itemPrice, String itemColor) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemColor = itemColor;
    }
}
