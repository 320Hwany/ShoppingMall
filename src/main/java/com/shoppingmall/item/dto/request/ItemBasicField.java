package com.shoppingmall.item.dto.request;

import com.shoppingmall.item.domain.item.Top;
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

    public ItemBasicField(PantsUpdate pantsUpdate) {
        this.itemName = pantsUpdate.getItemName();
        this.itemPrice = pantsUpdate.getItemPrice();
        this.itemColor = pantsUpdate.getItemColor();
    }

    public ItemBasicField(ShoesUpdate shoesUpdate) {
        this.itemName = shoesUpdate.getItemName();
        this.itemPrice = shoesUpdate.getItemPrice();
        this.itemColor = shoesUpdate.getItemColor();
    }

    public ItemBasicField(TopUpdate topUpdate) {
        this.itemName = topUpdate.getItemName();
        this.itemPrice = topUpdate.getItemPrice();
        this.itemColor = topUpdate.getItemColor();
    }
}
