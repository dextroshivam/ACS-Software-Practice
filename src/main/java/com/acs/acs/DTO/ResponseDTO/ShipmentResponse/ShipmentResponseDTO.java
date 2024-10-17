package com.acs.acs.DTO.ResponseDTO.ShipmentResponse;

import lombok.Data;
import java.util.List;

@Data
public class ShipmentResponseDTO {
    private int shipmentId;
    private int rateId;
    private String shipmentTrackingNumber;
    private String confirmation;
    private List<LabelDTO> labels;

    @Data
    public static class LabelDTO {
        private int id;
        private String labelUrl;
        private double cost;
        private String finalMileCarrier;
        private String trackingUrl;
        private String trackingNumber;
        private String labelFormat;
    }
}
