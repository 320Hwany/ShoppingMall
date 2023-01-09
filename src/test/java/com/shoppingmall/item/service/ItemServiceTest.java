package com.shoppingmall.item.service;

import com.shoppingmall.item.domain.item.Pants;
import com.shoppingmall.item.domain.item.Shoes;
import com.shoppingmall.item.domain.item.Top;
import com.shoppingmall.item.dto.request.*;
import com.shoppingmall.item.exception.ItemNotFoundException;
import com.shoppingmall.item.repository.ItemRepository;
import com.shoppingmall.item.dto.response.ItemResponse;
import com.shoppingmall.item.service.ItemService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.shoppingmall.item.domain.item.size.TopSize.LARGE;
import static com.shoppingmall.item.domain.item.size.TopSize.MEDIUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Nested
    @DisplayName("상품 조회 테스트 - Service")
    class GetItem {
        @BeforeEach
        void clean() {
            itemRepository.deleteAll();
        }

        @Test
        @DisplayName("상품이 존재하면 상품을 조회합니다.")
        void getItemSuccess() {
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

            itemRepository.save(shoes);

            // when
            ItemResponse itemResponse = itemService.getItem(shoes.getId());

            // then
            assertThat(itemResponse.getItemName()).isEqualTo("상품명");
            assertThat(itemResponse.getItemPrice()).isEqualTo(50000);
            assertThat(itemResponse.getItemColor()).isEqualTo("검정색");
            assertThat(itemResponse.getShoesSize()).isEqualTo(270);
        }

        @Test
        @DisplayName("상품이 존재하지 않으면 오류 메세지를 출력합니다.")
        void getItemFail() {
            // expected
            assertThrows(ItemNotFoundException.class,
                    () -> itemService.getItem(1L));
        }
    }

    @Nested
    @DisplayName("상품 저장 테스트 - Service")
    class saveItem {
        @BeforeEach
        void clean() {
            itemRepository.deleteAll();
        }

        @Test
        @DisplayName("하의 상품 저장")
        void savePants() {
            // given
            PantsSave pantsSave = PantsSave.builder()
                    .itemName("하의 상품")
                    .itemPrice(60000)
                    .itemColor("검정색")
                    .pantsSize(30)
                    .build();

            // when
            ItemResponse itemResponse = itemService.savePants(pantsSave);

            // then
            assertThat(itemResponse.getItemName()).isEqualTo("하의 상품");
            assertThat(itemResponse.getItemPrice()).isEqualTo(60000);
            assertThat(itemResponse.getItemColor()).isEqualTo("검정색");
            assertThat(itemResponse.getPantsSize()).isEqualTo(30);
        }

        @Test
        @DisplayName("신발 상품 저장")
        void saveShoes() {
            // given
            ShoesSave shoesSave = ShoesSave.builder()
                    .itemName("신발 상품")
                    .itemPrice(70000)
                    .itemColor("하얀색")
                    .shoesSize(270)
                    .build();

            // when
            ItemResponse itemResponse = itemService.saveShoes(shoesSave);

            // then
            assertThat(itemResponse.getItemName()).isEqualTo("신발 상품");
            assertThat(itemResponse.getItemPrice()).isEqualTo(70000);
            assertThat(itemResponse.getItemColor()).isEqualTo("하얀색");
            assertThat(itemResponse.getShoesSize()).isEqualTo(270);
        }

        @Test
        @DisplayName("상의 상품 저장")
        void saveTop() {
            // given
            TopSave topSave = TopSave.builder()
                    .itemName("상의 상품")
                    .itemPrice(50000)
                    .itemColor("검정색")
                    .topSize(MEDIUM)
                    .build();

            // when
            ItemResponse itemResponse = itemService.saveTop(topSave);

            // then
            assertThat(itemResponse.getItemName()).isEqualTo("상의 상품");
            assertThat(itemResponse.getItemPrice()).isEqualTo(50000);
            assertThat(itemResponse.getItemColor()).isEqualTo("검정색");
            assertThat(itemResponse.getTopSize()).isEqualTo(MEDIUM);
        }
    }

    @Nested
    @DisplayName("상품 수정 테스트 - Service")
    class UpdateItem {

        private ItemBasicField itemBasicField = ItemBasicField.builder()
                .itemName("상품명")
                .itemPrice(50000)
                .itemColor("검정색")
                .build();

        @BeforeEach
        void clean() {
            itemRepository.deleteAll();
        }

        @Test
        @DisplayName("하의 수정 테스트 - 성공")
        void updatePants() {
            // given
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

            itemRepository.save(pants);

            // when
            ItemResponse itemResponse = itemService.updatePants(pants.getId(), pantsUpdate);

            // then
            assertThat(itemResponse.getItemName()).isEqualTo("수정 상품명");
            assertThat(itemResponse.getItemPrice()).isEqualTo(50001);
            assertThat(itemResponse.getItemColor()).isEqualTo("하얀색");
            assertThat(itemResponse.getPantsSize()).isEqualTo(32);
        }

        @Test
        @DisplayName("신발 수정 테스트 - 성공")
        void updateShoes() {
            // given
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

            itemRepository.save(shoes);

            // when
            ItemResponse itemResponse = itemService.updateShoes(shoes.getId(), shoesUpdate);

            // then
            assertThat(itemResponse.getItemName()).isEqualTo("수정 상품명");
            assertThat(itemResponse.getItemPrice()).isEqualTo(50001);
            assertThat(itemResponse.getItemColor()).isEqualTo("하얀색");
            assertThat(itemResponse.getShoesSize()).isEqualTo(275);
        }

        @Test
        @DisplayName("상의 수정 테스트 - 성공")
        void updateTop() {
            // given
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

            itemRepository.save(top);

            // when
            ItemResponse itemResponse = itemService.updateTop(top.getId(), topUpdate);

            // then
            assertThat(itemResponse.getItemName()).isEqualTo("수정 상품명");
            assertThat(itemResponse.getItemPrice()).isEqualTo(50001);
            assertThat(itemResponse.getItemColor()).isEqualTo("하얀색");
            assertThat(itemResponse.getTopSize()).isEqualTo(LARGE);
        }

        @Test
        @DisplayName("수정 전에 하의 상품을 찾을 수 없으면 오류를 발생시킵니다 - 실패")
        void updatePantsFail() {
            // given
            PantsUpdate pantsUpdate = PantsUpdate.builder()
                    .itemName("수정 상품명")
                    .itemPrice(50001)
                    .itemColor("하얀색")
                    .pantsSize(32)
                    .build();

            // expected
            assertThrows(ItemNotFoundException.class,
                    () -> itemService.updatePants(1L, pantsUpdate));
        }

        @Test
        @DisplayName("수정 전에 신발 상품을 찾을 수 없으면 오류를 발생시킵니다 - 실패")
        void updateShoesFail() {
            // given
            ShoesUpdate shoesUpdate = ShoesUpdate.builder()
                    .itemName("수정 상품명")
                    .itemPrice(50001)
                    .itemColor("하얀색")
                    .shoesSize(275)
                    .build();

            // expected
            assertThrows(ItemNotFoundException.class,
                    () -> itemService.updateShoes(1L, shoesUpdate));
        }

        @Test
        @DisplayName("수정 전에 상의 상품을 찾을 수 없으면 오류를 발생시킵니다 - 실패")
        void updateTopFail() {
            // given
            TopUpdate topUpdate = TopUpdate.builder()
                    .itemName("수정 상품명")
                    .itemPrice(50001)
                    .itemColor("하얀색")
                    .topSize(LARGE)
                    .build();

            // expected
            assertThrows(ItemNotFoundException.class,
                    () -> itemService.updateTop(1L, topUpdate));
        }
    }

    @Nested
    @DisplayName("상품 삭제 테스트 - Service")
    class DeleteItem {

        private ItemBasicField itemBasicField = ItemBasicField.builder()
                .itemName("상품명")
                .itemPrice(50000)
                .itemColor("검정색")
                .build();

        @BeforeEach
        void clean() {
            itemRepository.deleteAll();
        }

        @Test
        @DisplayName("상품 삭제 테스트 - 성공")
        void deleteItem() {
            // given
            Pants pants = Pants.builder()
                    .itemBasicField(itemBasicField)
                    .pantsSize(30)
                    .build();

            itemRepository.save(pants);

            // expected
            itemService.deletePants(pants.getId());
        }

        @Test
        @DisplayName("삭제하려는 상품이 없으면 오류가 발생합니다 - 실패")
        void deleteItemFail() {
            // expected
            assertThrows(ItemNotFoundException.class,
                    () -> itemService.deletePants(1L));
        }
    }
}