package com.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.domain.item.Shoes;
import com.shoppingmall.repository.item.ItemRepository;
import com.shoppingmall.request.item.ItemRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Nested
    @DisplayName("상품 조회 테스트 - Controller")
    class GetItem {

        @BeforeEach
        void clean() {
            itemRepository.deleteAll();
        }

        @Test
        @DisplayName("상품이 존재하면 상품을 조회합니다.")
        void getItem() throws Exception {
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

            String json = objectMapper.writeValueAsString(shoes);

            // expected
            mockMvc.perform(get("/item/{itemId}", shoes.getId())
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.itemName").value("상품명"))
                    .andExpect(jsonPath("$.itemPrice").value(50000))
                    .andExpect(jsonPath("$.itemColor").value("검정색"))
                    .andExpect(jsonPath("$.shoesSize").value(270))
                    .andDo(print());
        }
    }
}