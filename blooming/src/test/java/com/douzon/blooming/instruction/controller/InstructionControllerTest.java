package com.douzon.blooming.instruction.controller;


import com.douzon.blooming.instruction.dto.request.RequestInstructionDto;
import com.douzon.blooming.instruction.dto.request.UpdateInstructionDto;
import com.douzon.blooming.product_instruction.dto.request.ProductInstructionDto;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
@Import(RestDocsConfig.class)
public class InstructionControllerTest {
    @Autowired
    protected RestDocumentationResultHandler restDocs;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(MockMvcResultHandlers.print())
                .alwaysDo(restDocs)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    public void insertInstruction() throws Exception {
        List<ProductInstructionDto> productList = new ArrayList<>();
        productList.add(new ProductInstructionDto(1L, 15, null));
        productList.add(new ProductInstructionDto(2L,  30, null));

        RequestInstructionDto dto = new RequestInstructionDto(
                15L, 1L, productList, "2023-10-30", "2023-11-30", 1
        );

        mockMvc.perform(post("/instructions/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andDo(restDocs.document(

                ))
                .andReturn();
    }

    @Test
    public void getInstruction() throws Exception {
        mockMvc.perform(get("/instructions/{instructionNo}", "WO2310000001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("instructionNo").description("지시 번호")
                        ),
                        responseFields(
                                fieldWithPath("productNo").type(JsonFieldType.NUMBER).description("상품 PK"),
                                fieldWithPath("productCode").type(JsonFieldType.STRING).description("픔목의 코드"),
                                fieldWithPath("designation").type(JsonFieldType.STRING).description("명칭"),
                                fieldWithPath("standard").type(JsonFieldType.STRING).description("규격"),
                                fieldWithPath("unit").type(JsonFieldType.NUMBER).description("단위")
                        ))).andReturn();
    }

    @Test
    public void getInstructions() throws Exception {
//        SearchDto dto = new SearchDto(1L, "jonson", "2023-11-24", "2023-11-24", 1, 8);
        mockMvc.perform(get("/instructions/list")
                .contentType(MediaType.APPLICATION_JSON))
//                        .param("progressStatus", "1")
//                        .param("employeeName", "jonson")
//                        .param("startDate", "2023-10-24")
//                        .param("endDate", "2023-10-24"))
                .andExpect(status().isOk())
                .andDo(restDocs.document(

                )).andReturn();
    }

    @Test
    public void updateInstruction() throws Exception {
        List<ProductInstructionDto> products = new ArrayList<>();
        products.add(new ProductInstructionDto(1L, 15, "updated"));
        products.add(new ProductInstructionDto(3L, 10, "added"));
        UpdateInstructionDto dto = new UpdateInstructionDto(
            3L, products, "2023-10-22", "2023-11-21");

        mockMvc.perform(put("/instructions/{instructionNo}", "WO2310000001")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andDo(restDocs.document(

                )).andReturn();
    }

    @Test
    public void deleteInstruction() throws Exception {

    }
}
