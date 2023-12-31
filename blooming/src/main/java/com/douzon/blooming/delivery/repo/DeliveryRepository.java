package com.douzon.blooming.delivery.repo;

import com.douzon.blooming.delivery.dto.DeliveryStatus;
import com.douzon.blooming.delivery.dto.request.DeliverySearchDto;
import com.douzon.blooming.delivery.dto.request.RequestDeliveryDto;
import com.douzon.blooming.delivery.dto.request.UpdateDeliveryDto;
import com.douzon.blooming.delivery.dto.response.GetDeliveryDto;
import com.douzon.blooming.delivery.dto.response.ResponseMyDeliveryDto;
import com.douzon.blooming.delivery_instruction.dto.response.DeliveryListInstructionDto;
import com.douzon.blooming.main.dto.BarGraphDto;
import com.douzon.blooming.main.dto.CircleGraphDeliveryDto;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeliveryRepository {

  void insertDelivery(Long employeeNo, @Param("dto") RequestDeliveryDto dto, DeliveryStatus status);

  String getDeliveryNo();

  int deleteDelivery(String deliveryNo);

  List<DeliveryListInstructionDto> findDeliveries(@Param("dto") DeliverySearchDto dto,
      Integer start, Integer pageSize);

  int getCountDeliveries(@Param("dto") DeliverySearchDto searchDto);

  Optional<GetDeliveryDto> findDelivery(String deliveryNo);

  int updateDelivery(String deliveryNo, @Param("dto") UpdateDeliveryDto dto);

  void changeStatus(String deliveryNo);

  Long findDeliveryCount();

  Long findThisMonthDeliveryCount();

  List<ResponseMyDeliveryDto> findMyDelivery(Long employeeNo);

  List<BarGraphDto> findMainPageBarGraphData(String type);

  List<CircleGraphDeliveryDto> findMainPageCircleGraphData(String type);
}
