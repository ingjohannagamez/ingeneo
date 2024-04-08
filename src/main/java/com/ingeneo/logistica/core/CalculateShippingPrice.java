package com.ingeneo.logistica.core;

import com.ingeneo.logistica.api.dto.TruckLogisticDTO;
import com.ingeneo.logistica.model.MaritimeLogistic;
import com.ingeneo.logistica.model.TruckLogistic;

public class CalculateShippingPrice {

	public static double calculateMaritimeShippingPrice(MaritimeLogistic maritimeLogistic) {
        double discountRate = maritimeLogistic.getQuantity() > 10 ? 0.03 : 0.0;
        return maritimeLogistic.getShippingPrice() * (1 - discountRate);
    }
    
    public static double calculateGroundShippingPrice(TruckLogistic truckLogistic) {
        double discountRate = truckLogistic.getQuantity() > 10 ? 0.05 : 0.0;
        return truckLogistic.getShippingPrice() * (1 - discountRate);
    }
    
    public static boolean isValidShippingPriceForElectronics(TruckLogisticDTO truckLogisticDTO) {
        double shippingPrice = truckLogisticDTO.getShippingPrice();
        return "Electrónicos".equals(truckLogisticDTO.getProductType()) && (shippingPrice >= 2000.0 && shippingPrice <= 5000.0);
    }
    
}
