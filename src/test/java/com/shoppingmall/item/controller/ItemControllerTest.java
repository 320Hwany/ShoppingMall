package com.shoppingmall.item.controller;

import com.shoppingmall.item.domain.item.Shoes;
import com.shoppingmall.item.repository.ItemRepository;
import com.shoppingmall.item.dto.request.ItemBasicField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.is;
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
    private ItemRepository itemRepository;

    @Nested
    @DisplayName("상품 조회 테스트 - Controller")
    class GetItem {

        @BeforeEach
        void clean() {
            itemRepository.deleteAll();
        }

        @DisplayName("상품이 존재하면 상품을 조회합니다. - 단건 조회")
        void getItemSuccess() throws Exception {
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

            // expected
            mockMvc.perform(get("/item/{itemId}", shoes.getId())
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.itemName").value("상품명"))
                    .andExpect(jsonPath("$.itemPrice").value(50000))
                    .andExpect(jsonPath("$.itemColor").value("검정색"))
                    .andExpect(jsonPath("$.shoesSize").value(270))
                    .andDo(print());
        }

        @Test
        @DisplayName("상품이 존재하지 않으면 오류메세지를 보여줍니다. - 단건조회")
        void getItemFail() throws Exception {
            // expected
            mockMvc.perform(get("/item/{itemId}", 1L)
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value("404"))
                    .andExpect(jsonPath("$.message").value("상품을 찾을 수 없습니다."))
                    .andDo(print());
        }

        @Test
        @DisplayName("상품의 한 페이지를 보여줍니다. - 최신순")
        void getItemsByLatest() throws Exception {
            // given
            List<ItemBasicField> itemBasicFieldList = IntStream.range(1, 31)
                    .mapToObj(i -> ItemBasicField.builder()
                            .itemName("상품명 " + i)
                            .itemPrice(10000 + i)
                            .itemColor("검정색 " + i)
                            .build())
                    .collect(toList());

            List<Shoes> shoesList = IntStream.range(1, 31)
                    .mapToObj(i -> Shoes.builder()
                            .itemBasicField(itemBasicFieldList.get(i - 1))
                            .shoesSize(220 + i)
                            .build())
                    .collect(toList());

            itemRepository.saveAll(shoesList);
            // expected
            mockMvc.perform(get("/items-latest?page=2"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()", is(10)))
                    .andExpect(jsonPath("$[0].itemName").value("상품명 20"))
                    .andExpect(jsonPath("$[0].itemPrice").value(10020))
                    .andExpect(jsonPath("$[0].itemColor").value("검정색 20"))
                    .andExpect(jsonPath("$[0].shoesSize").value(240))
                    .andDo(print());
        }

        @Test
        @DisplayName("상품의 한 페이지를 보여줍니다. - 가격 낮은 순")
        void getItemsByLowPrice() throws Exception {
            // given
            List<ItemBasicField> itemBasicFieldList = IntStream.range(1, 31)
                    .mapToObj(i -> ItemBasicField.builder()
                            .itemName("상품명 " + i)
                            .itemPrice(10000 + i)
                            .itemColor("검정색 " + i)
                            .build())
                    .collect(toList());

            List<Shoes> shoesList = IntStream.range(1, 31)
                    .mapToObj(i -> Shoes.builder()
                            .itemBasicField(itemBasicFieldList.get(i - 1))
                            .shoesSize(220 + i)
                            .build())
                    .collect(toList());

            itemRepository.saveAll(shoesList);
            // expected
            mockMvc.perform(get("/items-lowPrice?page=2"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()", is(10)))
                    .andExpect(jsonPath("$[0].itemName").value("상품명 11"))
                    .andExpect(jsonPath("$[0].itemPrice").value(10011))
                    .andExpect(jsonPath("$[0].itemColor").value("검정색 11"))
                    .andExpect(jsonPath("$[0].shoesSize").value(231))
                    .andDo(print());
        }

        @Test
        @DisplayName("상품의 한 페이지를 보여줍니다. - 가격 높은 순")
        void getItemsByHighPrice() throws Exception {
            // given
            List<ItemBasicField> itemBasicFieldList = IntStream.range(1, 31)
                    .mapToObj(i -> ItemBasicField.builder()
                            .itemName("상품명 " + i)
                            .itemPrice(10000 + i)
                            .itemColor("검정색 " + i)
                            .build())
                    .collect(toList());

            List<Shoes> shoesList = IntStream.range(1, 31)
                    .mapToObj(i -> Shoes.builder()
                            .itemBasicField(itemBasicFieldList.get(i - 1))
                            .shoesSize(220 + i)
                            .build())
                    .collect(toList());

            itemRepository.saveAll(shoesList);
            // expected
            mockMvc.perform(get("/items-highPrice?page=2"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()", is(10)))
                    .andExpect(jsonPath("$[0].itemName").value("상품명 20"))
                    .andExpect(jsonPath("$[0].itemPrice").value(10020))
                    .andExpect(jsonPath("$[0].itemColor").value("검정색 20"))
                    .andExpect(jsonPath("$[0].shoesSize").value(240))
                    .andDo(print());
        }
    }
}