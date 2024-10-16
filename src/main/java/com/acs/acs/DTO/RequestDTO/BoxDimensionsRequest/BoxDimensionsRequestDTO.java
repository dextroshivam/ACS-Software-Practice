package com.acs.acs.DTO.RequestDTO.BoxDimensionsRequest;

import lombok.Data;

@Data
public class BoxDimensionsRequestDTO {
    private String orderNumber;
    private Long boxId;
    private Double boxWeight;
    private BoxDimensions boxDimensions;

}

