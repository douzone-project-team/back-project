package com.douzon.blooming.delivery.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDeliveryDto {
    @DateTimeFormat(fallbackPatterns = "yyyy-MM-dd")
    private LocalDate deliveryDate;
}
