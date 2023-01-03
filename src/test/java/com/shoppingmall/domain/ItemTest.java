package com.shoppingmall.domain;

import com.shoppingmall.domain.item.Item;
import com.shoppingmall.domain.item.Shoes;
import com.shoppingmall.request.item.ItemRequest;
import com.shoppingmall.response.ItemResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ItemTest {

    @Test
    @DisplayName("각 상품 종류 찾기 테스트")
    void getItemResponse() {
        // given
        ItemRequest itemRequest = ItemRequest.builder()
                .itemName("상품명")
                .itemPrice(50000)
                .itemColor("검정색")
                .build();

        Shoes shoes = Shoes.builder()
                .itemRequest(itemRequest)
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
