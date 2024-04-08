package com.ingeneo.logistica.api.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckLogisticDTO {

	private Long id;
    private String productType;
    private Integer quantity;
    private LocalDate registrationDate;
    private LocalDate deliveryDate;
    private String deliveryWarehouse;
    private Double shippingPrice;
    private Double shippingPriceGranted;
    @Pattern(regexp = "^[A-Z]{3}\\d{3}$", message = "La placa del vehículo debe tener 3 letras seguidas de 3 números")
    private String vehiclePlate;
    private String guideNumber;
    private ClientDTO client;
}
