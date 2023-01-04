package com.shoppingmall.request.item;

import com.shoppingmall.domain.item.Item;
import com.shoppingmall.domain.item.Pants;
import com.shoppingmall.domain.item.Shoes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ShoesSave {

    @NotBlank(message = "상품명을 입력해주세요")
    private String itemName;

    @Min(value = 0, message = "상품 가격을 입력해주세요")
    private Integer itemPrice;

    @NotBlank(message = "상품 색상을 입력해주세요")
    private String itemColor;

    @Min(value = 220, message = "최소 220 이상의 수를 입력해주세요")
    private Integer shoesSize;

    public Item toEntity() {
        ItemBasicField itemBasicField = ItemBasicField.builder()
                .itemName(itemName)
                .itemPrice(itemPrice)
                .itemColor(itemColor)
                .build();

        return new Shoes(itemBasicField, shoesSize);
    }

    @Builder
    public ShoesSave(String itemName, Integer itemPrice, String itemColor, Integer shoesSize) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemColor = itemColor;
        this.shoesSize = shoesSize;
    }
}
