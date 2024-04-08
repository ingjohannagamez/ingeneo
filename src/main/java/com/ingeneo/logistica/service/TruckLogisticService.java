package com.ingeneo.logistica.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ingeneo.logistica.api.dto.TruckLogisticDTO;
import com.ingeneo.logistica.api.mapper.TruckLogisticMapper;
import com.ingeneo.logistica.core.CalculateShippingPrice;
import com.ingeneo.logistica.model.TruckLogistic;
import com.ingeneo.logistica.repository.TruckLogisticRepository;

@Service
public class TruckLogisticService {

	private final TruckLogisticRepository repository;

    public TruckLogisticService(TruckLogisticRepository repository) {
        this.repository = repository;
    }    

    public Optional<TruckLogisticDTO> findById(Long id) {
        Optional<TruckLogistic> truckLogistic = repository.findById(id);
        return truckLogistic.map(TruckLogisticMapper::toDTO);
    }

    public TruckLogisticDTO save(TruckLogisticDTO truckLogisticDTO) {
        // Convierte DTO a entidad
        TruckLogistic truckLogistic = TruckLogisticMapper.convertToEntity(truckLogisticDTO);

        // Calcula el nuevo precio de envío con el descuento aplicado
        double newShippingPrice = CalculateShippingPrice.calculateGroundShippingPrice(truckLogistic);
        truckLogistic.setShippingPriceGranted(newShippingPrice);

        // Guarda la entidad en la base de datos
        TruckLogistic savedEntity = repository.save(truckLogistic);

        // Convierte la entidad guardada de vuelta a DTO para devolverlo
        return TruckLogisticMapper.toDTO(savedEntity);
    }
    
    public TruckLogisticDTO update(Long id, TruckLogisticDTO truckLogisticDTO) {
        return repository.findById(id).map(existingTruckLogistic -> {
            
        	existingTruckLogistic.setId(truckLogisticDTO.getId());
        	existingTruckLogistic.setProductType(truckLogisticDTO.getProductType());
        	existingTruckLogistic.setQuantity(truckLogisticDTO.getQuantity());
        	existingTruckLogistic.setRegistrationDate(truckLogisticDTO.getRegistrationDate());
        	existingTruckLogistic.setDeliveryDate(truckLogisticDTO.getDeliveryDate());
        	existingTruckLogistic.setDeliveryWarehouse(truckLogisticDTO.getDeliveryWarehouse());
        	existingTruckLogistic.setShippingPrice(truckLogisticDTO.getShippingPrice());
        	existingTruckLogistic.setVehiclePlate(truckLogisticDTO.getVehiclePlate());
        	existingTruckLogistic.setGuideNumber(truckLogisticDTO.getGuideNumber());
            existingTruckLogistic.setShippingPriceGranted(CalculateShippingPrice.calculateGroundShippingPrice(existingTruckLogistic));

            TruckLogistic updatedTruckLogistic = repository.save(existingTruckLogistic);
            return TruckLogisticMapper.toDTO(updatedTruckLogistic);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Logística de camión no encontrada"));
    }
    
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Logística de camión no encontrada");
        }
        repository.deleteById(id);
    }
    
}