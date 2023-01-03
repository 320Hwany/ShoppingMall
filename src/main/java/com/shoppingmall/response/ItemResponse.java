package com.shoppingmall.response;

import com.shoppingmall.domain.item.Pants;
import com.shoppingmall.domain.item.Shoes;
import com.shoppingmall.domain.item.Top;
import com.shoppingmall.domain.item.size.TopSize;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemResponse {

    private String itemName;

    private int itemPrice;
    private String itemColor;

    private int pantsSize;
    private int shoesSize;
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
