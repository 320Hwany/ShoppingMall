package com.shoppingmall.item.dto.request;

import com.shoppingmall.item.domain.item.Item;
import com.shoppingmall.item.domain.item.Pants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class PantsSave {

    @NotBlank(message = "상품명을 입력해주세요")
    private String itemName;

    @Min(value = 0, message = "상품 가격을 입력해주세요")
    private Integer itemPrice;

    @NotBlank(message = "상품 색상을 입력해주세요")
    private String itemColor;

    @Min(value = 20, message = "최소 20이상의 수를 입력해주세요")
    private Integer pantsSize;

    public Item toEntity() {
        ItemBasicField itemBasicField = ItemBasicField.builder()
                .itemName(itemName)
                .itemPrice(itemPrice)
                .itemColor(itemColor)
                .build();

        return new Pants(itemBasicField, pantsSize);
    }

    @Builder
    public PantsSave(String itemName, Integer itemPrice, String itemColor, Integer pantsSize) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemColor = itemColor;
        this.pantsSize = pantsSize;
    }
}
