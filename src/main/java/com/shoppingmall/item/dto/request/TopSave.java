package com.shoppingmall.item.dto.request;

import com.shoppingmall.item.domain.item.Item;
import com.shoppingmall.item.domain.item.Top;
import com.shoppingmall.item.domain.item.size.TopSize;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class TopSave {

    @NotBlank(message = "상품명을 입력해주세요")
    private String itemName;

    @Min(value = 0, message = "상품 가격을 입력해주세요")
    private Integer itemPrice;

    @NotBlank(message = "상품 색상을 입력해주세요")
    private String itemColor;

    private TopSize topSize;

    public Item toEntity() {
        ItemBasicField itemBasicField = ItemBasicField.builder()
                .itemName(itemName)
                .itemPrice(itemPrice)
                .itemColor(itemColor)
                .build();

        return new Top(itemBasicField, topSize);
    }


    @Builder
    public TopSave(String itemName, Integer itemPrice, String itemColor, TopSize topSize) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemColor = itemColor;
        this.topSize = topSize;
    }
}
