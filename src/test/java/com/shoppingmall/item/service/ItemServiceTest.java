package com.shoppingmall.item.service;

import com.shoppingmall.item.domain.item.Shoes;
import com.shoppingmall.item.exception.ItemNotFoundException;
import com.shoppingmall.item.repository.ItemRepository;
import com.shoppingmall.item.dto.request.ItemBasicField;
import com.shoppingmall.item.dto.request.PantsSave;
import com.shoppingmall.item.dto.request.ShoesSave;
import com.shoppingmall.item.dto.request.TopSave;
import com.shoppingmall.item.dto.response.ItemResponse;
import com.shoppingmall.item.service.ItemService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.shoppingmall.item.domain.item.size.TopSize.MEDIUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            assertThat(itemRepository.count()).isEqualTo(1);
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
            assertThat(itemRepository.count()).isEqualTo(1);
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
            assertThat(itemRepository.count()).isEqualTo(1);
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
            assertThat(itemRepository.count()).isEqualTo(1);
            assertThat(itemResponse.getItemName()).isEqualTo("상의 상품");
            assertThat(itemResponse.getItemPrice()).isEqualTo(50000);
            assertThat(itemResponse.getItemColor()).isEqualTo("검정색");
            assertThat(itemResponse.getTopSize()).isEqualTo(MEDIUM);
        }
    }
}