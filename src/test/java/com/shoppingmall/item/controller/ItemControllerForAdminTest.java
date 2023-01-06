package com.shoppingmall.item.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.item.domain.item.Pants;
import com.shoppingmall.item.domain.item.Shoes;
import com.shoppingmall.item.domain.item.Top;
import com.shoppingmall.item.dto.request.*;
import com.shoppingmall.item.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.shoppingmall.item.domain.item.size.TopSize.LARGE;
import static com.shoppingmall.item.domain.item.size.TopSize.MEDIUM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ItemControllerForAdminTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Nested
    @DisplayName("상품 저장 테스트 - ControllerForAdmin")
    class SaveItem {
        @BeforeEach
        void clean() {
            itemRepository.deleteAll();
        }

        @Test
        @DisplayName("하의 상품 저장 테스트 - 성공")
        void savePants() throws Exception {
            // given
            PantsSave pantsSave = PantsSave.builder()
                    .itemName("하의 상품")
                    .itemPrice(50000)
                    .itemColor("검정색")
                    .pantsSize(30)
                    .build();

            String json = objectMapper.writeValueAsString(pantsSave);

            // expected
            mockMvc.perform(post("/admin/item/pants")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.itemName").value("하의 상품"))
                    .andExpect(jsonPath("$.itemPrice").value(50000))
                    .andExpect(jsonPath("$.itemColor").value("검정색"))
                    .andExpect(jsonPath("$.pantsSize").value(30))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("신발 상품 저장 테스트 - 성공")
        void saveShoes() throws Exception {
            // given
            ShoesSave pantsSave = ShoesSave.builder()
                    .itemName("신발 상품")
                    .itemPrice(50000)
                    .itemColor("검정색")
                    .shoesSize(270)
                    .build();

            String json = objectMapper.writeValueAsString(pantsSave);

            // expected
            mockMvc.perform(post("/admin/item/shoes")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.itemName").value("신발 상품"))
                    .andExpect(jsonPath("$.itemPrice").value(50000))
                    .andExpect(jsonPath("$.itemColor").value("검정색"))
                    .andExpect(jsonPath("$.shoesSize").value(270))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("상의 상품 저장 테스트 - 성공")
        void saveTop() throws Exception {
            // given
            TopSave topSave = TopSave.builder()
                    .itemName("상의 상품")
                    .itemPrice(50000)
                    .itemColor("검정색")
                    .topSize(MEDIUM)
                    .build();

            String json = objectMapper.writeValueAsString(topSave);

            // expected
            mockMvc.perform(post("/admin/item/top")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.itemName").value("상의 상품"))
                    .andExpect(jsonPath("$.itemPrice").value(50000))
                    .andExpect(jsonPath("$.itemColor").value("검정색"))
                    .andExpect(jsonPath("$.topSize").value("MEDIUM"))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("하의 상품 저장 테스트 실패 - 조건에 맞지 않으면 오류 메세지를 보여줍니다")
        void savePantsFail() throws Exception {
            // given
            PantsSave pantsSave = PantsSave.builder()
                    .itemName("")
                    .itemPrice(50000)
                    .itemColor("검정색")
                    .pantsSize(30)
                    .build();

            String json = objectMapper.writeValueAsString(pantsSave);

            // expected
            mockMvc.perform(post("/admin/item/pants")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.itemName").value("상품명을 입력해주세요"))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(0);
        }

        @Test
        @DisplayName("신발 상품 저장 테스트 실패 - 조건에 맞지 않으면 오류 메세지를 보여줍니다")
        void saveShoesFail() throws Exception {
            // given
            ShoesSave pantsSave = ShoesSave.builder()
                    .itemName("신발 상품")
                    .itemPrice(-10000)
                    .itemColor("검정색")
                    .shoesSize(270)
                    .build();

            String json = objectMapper.writeValueAsString(pantsSave);

            // expected
            mockMvc.perform(post("/admin/item/shoes")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.itemPrice").value("상품 가격을 입력해주세요"))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(0);
        }

        @Test
        @DisplayName("상의 상품 저장 테스트 실패 - 조건에 맞지 않으면 오류 메세지를 보여줍니다")
        void saveTopFail() throws Exception {
            // given
            TopSave topSave = TopSave.builder()
                    .itemName("상의 상품")
                    .itemPrice(50000)
                    .topSize(MEDIUM)
                    .build();

            String json = objectMapper.writeValueAsString(topSave);

            // expected
            mockMvc.perform(post("/admin/item/top")
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.itemColor").value("상품 색상을 입력해주세요"))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("상품 수정 테스트 - Controller")
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
        void updatePants() throws Exception {
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
            String json = objectMapper.writeValueAsString(pantsUpdate);

            // expected
            mockMvc.perform(patch("/admin/item/pants/{itemId}", pants.getId())
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.itemName").value("수정 상품명"))
                    .andExpect(jsonPath("$.itemPrice").value(50001))
                    .andExpect(jsonPath("$.itemColor").value("하얀색"))
                    .andExpect(jsonPath("$.pantsSize").value(32))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("신발 수정 테스트 - 성공")
        void updateShoes() throws Exception {
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
            String json = objectMapper.writeValueAsString(shoesUpdate);

            // expected
            mockMvc.perform(patch("/admin/item/shoes/{itemId}", shoes.getId())
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.itemName").value("수정 상품명"))
                    .andExpect(jsonPath("$.itemPrice").value(50001))
                    .andExpect(jsonPath("$.itemColor").value("하얀색"))
                    .andExpect(jsonPath("$.shoesSize").value(275))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("상의 수정 테스트 - 성공")
        void updateTop() throws Exception {
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
            String json = objectMapper.writeValueAsString(topUpdate);

            // expected
            mockMvc.perform(patch("/admin/item/top/{itemId}", top.getId())
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.itemName").value("수정 상품명"))
                    .andExpect(jsonPath("$.itemPrice").value(50001))
                    .andExpect(jsonPath("$.itemColor").value("하얀색"))
                    .andExpect(jsonPath("$.topSize").value("LARGE"))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("하의 수정 테스트 - 실패")
        void updatePantsFail() throws Exception {
            // given
            Pants pants = Pants.builder()
                    .itemBasicField(itemBasicField)
                    .pantsSize(30)
                    .build();

            PantsUpdate pantsUpdate = PantsUpdate.builder()
                    .itemName("")
                    .itemPrice(50001)
                    .itemColor("하얀색")
                    .pantsSize(32)
                    .build();

            itemRepository.save(pants);
            String json = objectMapper.writeValueAsString(pantsUpdate);

            // expected
            mockMvc.perform(patch("/admin/item/pants/{itemId}", pants.getId())
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.itemName").value("상품명을 입력해주세요"))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("신발 수정 테스트 - 실패")
        void updateShoesFail() throws Exception {
            // given
            Shoes shoes = Shoes.builder()
                    .itemBasicField(itemBasicField)
                    .shoesSize(270)
                    .build();

            ShoesUpdate shoesUpdate = ShoesUpdate.builder()
                    .itemName("수정 상품명")
                    .itemPrice(-10000)
                    .itemColor("하얀색")
                    .shoesSize(275)
                    .build();

            itemRepository.save(shoes);
            String json = objectMapper.writeValueAsString(shoesUpdate);

            // expected
            mockMvc.perform(patch("/admin/item/shoes/{itemId}", shoes.getId())
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.itemPrice").value("상품 가격을 입력해주세요"))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(1);
        }

        @Test
        @DisplayName("상의 수정 테스트 - 실패")
        void updateTopFail() throws Exception {
            // given
            Top top = Top.builder()
                    .itemBasicField(itemBasicField)
                    .topSize(MEDIUM)
                    .build();

            TopUpdate topUpdate = TopUpdate.builder()
                    .itemName("수정 상품명")
                    .itemPrice(50001)
                    .itemColor("")
                    .topSize(LARGE)
                    .build();

            itemRepository.save(top);
            String json = objectMapper.writeValueAsString(topUpdate);

            // expected
            mockMvc.perform(patch("/admin/item/shoes/{itemId}", top.getId())
                            .contentType(APPLICATION_JSON)
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value("400"))
                    .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                    .andExpect(jsonPath("$.validation.itemColor").value("상품 색상을 입력해주세요"))
                    .andDo(print());

            assertThat(itemRepository.count()).isEqualTo(1);
        }
    }
}