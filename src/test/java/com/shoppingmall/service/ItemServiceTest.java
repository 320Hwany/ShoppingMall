package com.shoppingmall.service;

import com.shoppingmall.domain.item.Shoes;
import com.shoppingmall.exception.e404.ItemNotFoundException;
import com.shoppingmall.repository.item.ItemRepository;
import com.shoppingmall.request.item.ItemRequest;
import com.shoppingmall.response.ItemResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
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
            ItemRequest itemRequest = ItemRequest.builder()
                    .itemName("상품명")
                    .itemPrice(50000)
                    .itemColor("검정색")
                    .build();

            Shoes shoes = Shoes.builder()
                    .itemRequest(itemRequest)
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
}