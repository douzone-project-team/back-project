package com.douzon.blooming.product.controller;

import static com.douzon.blooming.restdocs.RestDocsConfig.field;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.douzon.blooming.product.dto.request.RequestProductDto;
import com.douzon.blooming.restdocs.RestDocsConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@Disabled
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
@Import(RestDocsConfig.class)
class ProductControllerTest {

  @Autowired
  protected RestDocumentationResultHandler restDocs;

  private final ObjectMapper objectMapper = new ObjectMapper();
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp(WebApplicationContext webApplicationContext,
      RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(documentationConfiguration(restDocumentation))
        .alwaysDo(MockMvcResultHandlers.print()) // andDo(print()) 코드 포함 -> 3번 문제 해결
        .alwaysDo(restDocs) // pretty 패턴과 문서 디렉토리 명 정해준것 적용
        .addFilters(new CharacterEncodingFilter("UTF-8", true)) // 한글 깨짐 방지
        .build();
  }

  @Test
  void getProduct() throws Exception {
    mockMvc.perform(get("/products/{productCode}", "VV0001")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(restDocs.document(
            pathParameters(
                parameterWithName("productCode").description("제품 코드").attributes(field("constraints","길이 6 이하"))
            ),
            responseFields(
                fieldWithPath("productNo").type(JsonFieldType.NUMBER).description("상품 PK"),
                fieldWithPath("productCode").type(JsonFieldType.STRING).description("픔목의 코드"),
                fieldWithPath("designation").type(JsonFieldType.STRING).description("명칭"),
                fieldWithPath("standard").type(JsonFieldType.STRING).description("규격"),
                fieldWithPath("unit").type(JsonFieldType.NUMBER).description("단위")
            )))
        .andReturn();
  }

  @Test
  void addProduct() throws Exception {
    RequestProductDto requestProductDto =
        new RequestProductDto(null, "TT0001", "Test Product", "standard : X", 10);

    mockMvc.perform(post("/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(requestProductDto)))
        .andExpect(status().isNoContent())
        .andDo(restDocs.document(
        )).andReturn();
  }
}