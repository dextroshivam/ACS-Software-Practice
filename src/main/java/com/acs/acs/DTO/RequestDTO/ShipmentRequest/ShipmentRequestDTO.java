package com.acs.acs.DTO.RequestDTO.ShipmentRequest;

import lombok.Data;
import java.util.List;

@Data
public class ShipmentRequestDTO {
    private ShipmentDTO shipment;

    @Data
    public static class ShipmentDTO {
        private String external_reference;
        private AddressDTO address_to;
        private AddressDTO address_from;
        private List<ParcelDTO> parcels;
    }

    @Data
    public static class AddressDTO {
        private String name;
        private String company;
        private boolean residential;
        private String street1;
        private String street2;
        private String city;
        private String state;
        private String country;
        private String postal_code;
        private String phone;
        private String email;
    }

    @Data
    public static class ParcelDTO {
        private DimensionDTO dimensions;
        private WeightDTO weight;
    }

    @Data
    public static class DimensionDTO {
        private double length;
        private double width;
        private double height;
    }

    @Data
    public static class WeightDTO {
        private double value;
        private String unit;
    }
}
