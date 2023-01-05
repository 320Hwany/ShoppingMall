package com.shoppingmall.item.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemBasicField {

    private String itemName;

    private Integer itemPrice;

    private String itemColor;

    @Builder
    public ItemBasicField(String itemName, Integer itemPrice, String itemColor) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemColor = itemColor;
    }
}
