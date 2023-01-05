package com.shoppingmall.item.dto.response;

import com.shoppingmall.item.domain.item.Pants;
import com.shoppingmall.item.domain.item.Shoes;
import com.shoppingmall.item.domain.item.Top;
import com.shoppingmall.item.domain.item.size.TopSize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemResponse {

    private String itemName;

    private Integer itemPrice;
    private String itemColor;

    private Integer pantsSize;
    private Integer shoesSize;
    private TopSize topSize;

    public ItemResponse(Pants pants) {
        this.itemName = pants.getItemName();
        this.itemPrice = pants.getItemPrice();
        this.itemColor = pants.getItemColor();
        this.pantsSize = pants.getPantsSize();
    }

    public ItemResponse(Shoes shoes) {
        this.itemName = shoes.getItemName();
        this.itemPrice = shoes.getItemPrice();
        this.itemColor = shoes.getItemColor();
        this.shoesSize = shoes.getShoesSize();
    }

    public ItemResponse(Top top) {
        this.itemName = top.getItemName();
        this.itemPrice = top.getItemPrice();
        this.itemColor = top.getItemColor();
        this.topSize = top.getTopSize();
    }
}
