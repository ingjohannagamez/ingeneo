package com.ingeneo.logistica.api.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaritimeLogisticDTO {

	private Long id;
    private String productType;
    private Integer quantity;
    private LocalDate registrationDate;
    private LocalDate deliveryDate;
    private String deliveryPort;
    private Double shippingPrice;
    private String fleetNumber;
    private String guideNumber;
}
