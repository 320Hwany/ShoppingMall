package com.shoppingmall.domain;

import com.shoppingmall.item.domain.item.Item;
import com.shoppingmall.item.domain.item.Shoes;
import com.shoppingmall.item.dto.request.ItemBasicField;
import com.shoppingmall.item.dto.response.ItemResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ItemTest {

    @Test
    @DisplayName("각 상품 종류 찾기 테스트")
    void getItemResponse() {
        // given
        ItemBasicField itemBasicField = ItemBasicField.builder()
                .itemName("상품명")
                .itemPrice(50000)
                .itemColor("검정색")
                .build();

        Shoes shoes = Shoes.builder()
                .itemBasicField(itemBasicField)
                .shoesSize(270)
                .build();

        // when
        ItemResponse itemResponse = Item.getItemResponse(shoes);

        // then
        assertThat(itemResponse.getItemName()).isEqualTo("상품명");
        assertThat(itemResponse.getItemPrice()).isEqualTo(50000);
        assertThat(itemResponse.getItemColor()).isEqualTo("검정색");
        assertThat(itemResponse.getShoesSize()).isEqualTo(270);
    }

}
