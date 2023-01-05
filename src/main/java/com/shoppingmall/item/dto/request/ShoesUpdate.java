package com.shoppingmall.item.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class ShoesUpdate {

    @NotBlank(message = "상품명을 입력해주세요")
    private String itemName;

    @Min(value = 0, message = "상품 가격을 입력해주세요")
    private Integer itemPrice;

    @NotBlank(message = "상품 색상을 입력해주세요")
    private String itemColor;

    @Min(value = 220, message = "최소 220 이상의 수를 입력해주세요")
    private Integer shoesSize;

    @Builder
    public ShoesUpdate(String itemName, Integer itemPrice, String itemColor, Integer shoesSize) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemColor = itemColor;
        this.shoesSize = shoesSize;
    }
}
