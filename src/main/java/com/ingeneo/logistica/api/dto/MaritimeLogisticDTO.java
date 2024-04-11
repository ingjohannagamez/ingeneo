package com.ingeneo.logistica.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^[A-Z]{3}\\d{4}[A-Z]$", message = "La placa del vehículo debe tener 3 letras seguidas de 4 números y terminar con una letra")
    private String fleetNumber;
    private String guideNumber;
    private ClientDTO client;
}
