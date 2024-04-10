package com.ingeneo.logistica.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaritimeLogisticDTO {

	private Long id;
    private String productType;
    private Integer quantity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date registrationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date deliveryDate;
    private String deliveryPort;
    private Double shippingPrice;
    private Double shippingPriceGranted;
    private String fleetNumber;
    private String guideNumber;
    private ClientDTO client;
}
