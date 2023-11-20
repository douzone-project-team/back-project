package com.douzon.blooming.delivery.service;

import com.douzon.blooming.PageDto;
import com.douzon.blooming.delivery.dto.request.DeliverySearchDto;
import com.douzon.blooming.delivery.dto.request.RequestDeliveryDto;
import com.douzon.blooming.delivery.dto.request.UpdateDeliveryDto;
import com.douzon.blooming.delivery.dto.response.*;
import com.douzon.blooming.delivery.exception.NotFoundDeliveryException;
import com.douzon.blooming.delivery.repo.DeliveryRepository;
import com.douzon.blooming.delivery_instruction.dto.response.DeliveryListInstructionDto;
import com.douzon.blooming.delivery_instruction.dto.response.GetInstructionDetailDto;
import com.douzon.blooming.delivery_instruction.repo.DeliveryInstructionRepository;
import com.douzon.blooming.product_instruction.exception.UnsupportedProductStatusException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService{

    private final DeliveryRepository deliveryRepository;
    private final DeliveryInstructionRepository deliveryInstructionRepository;

    @Override
    public ResponseDeliveryDto addDelivery(RequestDeliveryDto dto) {
//        EmployeeDetails employeeDetails = (EmployeeDetails) SecurityContextHolder.getContext()
//                .getAuthentication().getPrincipal();
//        deliveryRepository.insertDelivery(employeeDetails.getEmployeeNo(), dto);
        deliveryRepository.insertDelivery(200012L, dto);
        return new ResponseDeliveryDto(deliveryRepository.getDeliveryNo());
    }

    @Override
    public GetDeliveryDto findDelivery(String deliveryNo) {
        GetDeliveryDto getDeliveryDto = deliveryRepository.findDelivery(deliveryNo)
                .orElseThrow(NotFoundDeliveryException::new);
        getDeliveryDto.setInstructions(deliveryInstructionRepository.findInstructionsByDeliverNo(deliveryNo));
        return getDeliveryDto;
    }

    @Override
    public PageDto<DeliveryListInstructionDto> findDeliveries(DeliverySearchDto searchDto) {
        int start = (searchDto.getPage()) * searchDto.getPageSize();
        List<DeliveryListInstructionDto> deliveries = deliveryRepository.findDeliveries(searchDto, start, searchDto.getPageSize());
        deliveries.forEach(delivery -> {
            delivery.setInstructionCount(deliveryInstructionRepository.getInstructionCount(delivery.getDeliveryNo()));
        });

        int searchInstructionCount = deliveryRepository.getCountDeliveries(searchDto);

        return PageDto.<DeliveryListInstructionDto>builder().list(deliveries).currentPage(searchDto.getPage() + 1)
                .hasNextPage(start + searchDto.getPageSize() < searchInstructionCount)
                .hasPreviousPage(start > 0)
                .build();
    }

    @Override
    public void updateDelivery(String deliveryNo, UpdateDeliveryDto dto) {
        if(deliveryRepository.updateDelivery(deliveryNo, dto) <= 0 ){
            throw new NotFoundDeliveryException();
        }
    }

    @Override
    public void removeDelivery(String deliveryNo) {
        if(deliveryRepository.deleteDelivery(deliveryNo) <= 0){
            throw new NotFoundDeliveryException();
        }
    }

}
