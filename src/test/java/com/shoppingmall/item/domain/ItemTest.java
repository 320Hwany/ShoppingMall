package com.shoppingmall.item.domain;

import com.shoppingmall.item.domain.item.Item;
import com.shoppingmall.item.domain.item.Pants;
import com.shoppingmall.item.domain.item.Shoes;
import com.shoppingmall.item.domain.item.Top;
import com.shoppingmall.item.domain.item.size.TopSize;
import com.shoppingmall.item.dto.request.ItemBasicField;
import com.shoppingmall.item.dto.request.PantsUpdate;
import com.shoppingmall.item.dto.request.ShoesUpdate;
import com.shoppingmall.item.dto.request.TopUpdate;
import com.shoppingmall.item.dto.response.ItemResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.shoppingmall.item.domain.item.size.TopSize.LARGE;
import static com.shoppingmall.item.domain.item.size.TopSize.MEDIUM;
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

    @Test
    @DisplayName("상품 공통 필드 업데이트")
    void updateBasicField() {
        // given
        ItemBasicField beforeItemBasicField = ItemBasicField.builder()
                .itemName("상품명")
                .itemPrice(50000)
                .itemColor("검정색")
                .build();

        ItemBasicField afterItemBasicField = ItemBasicField.builder()
                .itemName("수정 상품명")
                .itemPrice(50001)
                .itemColor("하얀색")
                .build();

        Pants pants = Pants.builder()
                .itemBasicField(beforeItemBasicField)
                .pantsSize(30)
                .build();

        // when
        pants.updateBasicField(afterItemBasicField);

        // then
        assertThat(pants.getItemName()).isEqualTo("수정 상품명");
        assertThat(pants.getItemPrice()).isEqualTo(50001);
        assertThat(pants.getItemColor()).isEqualTo("하얀색");
        assertThat(pants.getPantsSize()).isEqualTo(30);
    }

    @Test
    @DisplayName("하의 상품 개별 필드 업데이트")
    void updatePantsEachField() {
        // given
        ItemBasicField itemBasicField = ItemBasicField.builder()
                .itemName("상품명")
                .itemPrice(50000)
                .itemColor("검정색")
                .build();

        Pants pants = Pants.builder()
                .itemBasicField(itemBasicField)
                .pantsSize(30)
                .build();

        PantsUpdate pantsUpdate = PantsUpdate.builder()
                .itemName("수정 상품명")
                .itemPrice(50001)
                .itemColor("하얀색")
                .pantsSize(32)
                .build();

        // when
        pants.updatePantsEachField(pantsUpdate);

        // then
        assertThat(pants.getItemName()).isEqualTo("상품명");
        assertThat(pants.getItemPrice()).isEqualTo(50000);
        assertThat(pants.getItemColor()).isEqualTo("검정색");
        assertThat(pants.getPantsSize()).isEqualTo(32);
    }

    @Test
    @DisplayName("신발 상품 개별 필드 업데이트")
    void updateShoesEachField() {
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

        ShoesUpdate shoesUpdate = ShoesUpdate.builder()
                .itemName("수정 상품명")
                .itemPrice(50001)
                .itemColor("하얀색")
                .shoesSize(275)
                .build();

        // when
        shoes.updateShoesEachField(shoesUpdate);

        // then
        assertThat(shoes.getItemName()).isEqualTo("상품명");
        assertThat(shoes.getItemPrice()).isEqualTo(50000);
        assertThat(shoes.getItemColor()).isEqualTo("검정색");
        assertThat(shoes.getShoesSize()).isEqualTo(275);
    }

    @Test
    @DisplayName("상의 상품 개별 필드 업데이트")
    void updateTopEachField() {
        // given
        ItemBasicField itemBasicField = ItemBasicField.builder()
                .itemName("상품명")
                .itemPrice(50000)
                .itemColor("검정색")
                .build();

        Top top = Top.builder()
                .itemBasicField(itemBasicField)
                .topSize(MEDIUM)
                .build();

        TopUpdate topUpdate = TopUpdate.builder()
                .itemName("수정 상품명")
                .itemPrice(50001)
                .itemColor("하얀색")
                .topSize(LARGE)
                .build();

        // when
        top.updateTopEachField(topUpdate);

        // then
        assertThat(top.getItemName()).isEqualTo("상품명");
        assertThat(top.getItemPrice()).isEqualTo(50000);
        assertThat(top.getItemColor()).isEqualTo("검정색");
        assertThat(top.getTopSize()).isEqualTo(LARGE);
    }
}
