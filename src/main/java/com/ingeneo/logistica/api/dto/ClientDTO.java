package com.ingeneo.logistica.api.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {

	private Long id;
    private String name;
    private Set<TruckLogisticDTO> truckLogistics;
    private Set<MaritimeLogisticDTO> maritimeLogistics;
}
