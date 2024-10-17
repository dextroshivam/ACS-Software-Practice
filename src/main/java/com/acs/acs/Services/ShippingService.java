package com.acs.acs.Services;

import com.acs.acs.DTO.RequestDTO.BoxDimensionsRequest.BoxDimensionsRequestDTO;
import com.acs.acs.DTO.RequestDTO.ShipmentRequest.ShipmentRequestDTO;
import com.acs.acs.DTO.ResponseDTO.ScanBoxLableResponse.ScanBoxLableResponse;
import com.acs.acs.DTO.ResponseDTO.ShipmentResponse.ShipmentResponseDTO;
import com.acs.acs.ENUM.OrderStatus;
import com.acs.acs.ENUM.ServiceType;
import com.acs.acs.Enitities.*;
import com.acs.acs.Repository.*;
import io.swagger.v3.oas.models.headers.Header;
import lombok.Data;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShippingService {

  @Autowired OrderContainerAssignRespository orderContainerAssignRespository;
  @Autowired WarehouseRepository warehouseRepository;
  @Autowired WarehouseBoxRepository warehouseBoxRepository;
  @Autowired WarehouseOrdersInfoRepository warehouseOrdersInfoRepository;
  @Autowired PartnersRepository partnersRepository;
  @Autowired CityRepository cityRepository;
  @Autowired StateRepository stateRepository;
  @Autowired CountryRepository countryRepository;

  public ScanBoxLableResponse scanBoxLabel(Long boxLabel) {
    ScanBoxLableResponse scanBoxLableResponse = new ScanBoxLableResponse();
    //        String orderNumber;
    //        Address shipFromAddress;
    //        Address shipToAddress;
    //        String carrierName;
    //        ServiceType serviceType;
    //        --> change status to ready to ship
    OrderContainerAssign packedOrder =
        orderContainerAssignRespository.findFirstByBoxIdAndStatus(boxLabel, OrderStatus.PACKED);

    //    scanBoxLableResponse.setOrderNumber(packedOrder.getOrderNumber());
    Address shipFromAddress = new Address();
    //      String addressLine1;
    //      String addressLine2;
    //      Long stateId;
    //      Long cityId;
    //      Long countyId;
    //      String zipCode;
    Long warehouseId = warehouseBoxRepository.findById(boxLabel).get().getWarehouseId();

    Warehouse warehouse = warehouseRepository.findById(warehouseId).get();
    shipFromAddress.setAddressLine1(warehouse.getAddressLine1());
    shipFromAddress.setAddressLine2(warehouse.getAddressLine2());
    shipFromAddress.setStateId(warehouse.getStateId());
    shipFromAddress.setZipCode(warehouse.getZipCode());
    shipFromAddress.setCityId(warehouse.getCityId());
    shipFromAddress.setCountyId(warehouse.getCountyId());

    scanBoxLableResponse.setShipFromAddress(shipFromAddress);
    Address shipToAddress = new Address();

    String orderNumber = packedOrder.getOrderNumber();
    WarehouseOrdersInfo warehouseOrdersInfo =
        warehouseOrdersInfoRepository.findByOrderNumber(orderNumber).get();
    scanBoxLableResponse.setOrderNumber(orderNumber);
    shipToAddress.setAddressLine1(warehouseOrdersInfo.getAddressLine1());
    shipToAddress.setAddressLine2(warehouseOrdersInfo.getAddressLine2());
    shipToAddress.setCityId(warehouseOrdersInfo.getShipToCityId());
    shipToAddress.setStateId(warehouseOrdersInfo.getShipToStateId());
    shipToAddress.setCountyId(warehouseOrdersInfo.getShipToCountryId());
    shipToAddress.setZipCode(warehouseOrdersInfo.getShipToZipCode());

    scanBoxLableResponse.setShipToAddress(shipToAddress);
    Partners partners = partnersRepository.findByName(warehouseOrdersInfo.getCarrierName());
    scanBoxLableResponse.setCarrierName(partners.getName());
    scanBoxLableResponse.setServiceType(partners.getServiceType());
    return scanBoxLableResponse;
  }

  public ResponseEntity<String> setBoxDimensions(BoxDimensionsRequestDTO boxDimensionsRequestDTO)
      throws URISyntaxException {
    OrderContainerAssign presentOrderContainerAssign =
        orderContainerAssignRespository.findByOrderNumberAndBoxIdAndStatus(
            boxDimensionsRequestDTO.getOrderNumber(),
            boxDimensionsRequestDTO.getBoxId(),
            OrderStatus.PACKED);
    // set presentOrderContainerAssign
    presentOrderContainerAssign.setBoxWeight(boxDimensionsRequestDTO.getBoxWeight());
    presentOrderContainerAssign.setBoxLength(
        boxDimensionsRequestDTO.getBoxDimensions().getBoxLength());
    presentOrderContainerAssign.setBoxHeight(
        boxDimensionsRequestDTO.getBoxDimensions().getBoxHeight());
    presentOrderContainerAssign.setBoxWidth(
        boxDimensionsRequestDTO.getBoxDimensions().getBoxWidth());
    presentOrderContainerAssign.setStatus(OrderStatus.PACKED);
    orderContainerAssignRespository.save(presentOrderContainerAssign);


    System.out.println("Box id is : " + boxDimensionsRequestDTO.getBoxId());


    // create shipment
    ShipmentRequestDTO shipmentRequestDTO =
        generateShipment(
            boxDimensionsRequestDTO.getOrderNumber(), boxDimensionsRequestDTO.getBoxId());

    // call resttemplate
    URI uri = new URI("https://apisandbox.tusklogistics.com/v1/labels");
    HttpHeaders headers = new HttpHeaders();
    headers.set("content-type", "application/json");
    headers.set("x-api-key", "7Du28nKx66p6PloG9iGz9Vbg9PZINZCuIUXdahH5");
    RestTemplate restTemplate=new RestTemplate();
    HttpEntity<ShipmentRequestDTO> httpEntity = new HttpEntity<>(shipmentRequestDTO, headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri,httpEntity, String.class);
    return result;
  }
//  {
//    "shipment": {
//    "external_reference": "Order1",
//            "address_to": {
//      "name": "test person2",
//              "company": "addressToCompany",
//              "street1": "235 Cattle Ranch Drive",
//              "street2": "street",
//              "city": "Las Vegas",
//              "state": "NV",
//              "country": "US",
//              "postal_code": "89129",
//              "phone": "9999999999",
//              "email": "example@example.com",
//              "residential": true
//    },
//    "address_from": {
//      "name": "addressFromName",
//              "company": "addressFromCompany",
//              "street1": "110 Freeport Pkwy",
//              "street2": "street",
//              "city": "Coppell",
//              "state": "TX",
//              "country": "INDIA",
//              "postal_code": "75019",
//              "phone": "0000000000",
//              "email": "support@company.com",
//              "residential": false
//    },
//    "parcels": [
//    {
//      "dimensions": {
//      "length": 10.0,
//              "width": 10.0,
//              "height": 20.0
//    },
//      "weight": {
//      "value": 10.0,
//              "unit": "Ounce"
//    }
//    }
//        ]
//  }
//  }
  public ShipmentRequestDTO generateShipment(String orderNumber, Long boxId) {

    ShipmentRequestDTO shipmentRequestDTO = new ShipmentRequestDTO();
    ShipmentRequestDTO.ShipmentDTO shipmentDTO = new ShipmentRequestDTO.ShipmentDTO();
    //    private ShipmentRequestDTO.ShipmentDTO shipment;
    //
    //    @Data
    //    public static class ShipmentDTO {
    //      private String externalReference; -- auto
    //      private ShipmentRequestDTO.AddressDTO addressTo; --> make --> done
    //      private ShipmentRequestDTO.AddressDTO addressFrom; --> make --> done
    //      private List<ShipmentRequestDTO.ParcelDTO> parcels; --> make --> done
    //    }
    //
    //    @Data
    //    public static class AddressDTO {
    //      private String name;
    //      private String company;
    //      private boolean isResidential;
    //      private String street1;
    //      private String street2;
    //      private String city;
    //      private String state;
    //      private String country;
    //      private String postalCode;
    //      private String phone;
    //      private String email;
    //    }
    //
    //    @Data
    //    public static class ParcelDTO {
    //      private ShipmentRequestDTO.DimensionDTO dimensions;
    //      private ShipmentRequestDTO.WeightDTO weight;
    //    }
    //
    //    @Data
    //    public static class DimensionDTO {
    //      private double length;
    //      private double width;
    //      private double height;
    //    }
    //
    //    @Data
    //    public static class WeightDTO {
    //      private double value;
    //      private String unit;
    //    }

    // get OrderInfo for address
    Optional<WarehouseOrdersInfo> warehouseOrdersInfo =
        warehouseOrdersInfoRepository.findByOrderNumber(orderNumber);
    System.out.println(warehouseOrdersInfo);
    Warehouse warehouse =
        warehouseRepository.findById(warehouseOrdersInfo.get().getWarehouseId()).get();
    List<OrderContainerAssign> presentParcels =
        orderContainerAssignRespository.findByBoxIdAndStatus(boxId, OrderStatus.PACKED);
    // Create AddressTo
    ShipmentRequestDTO.AddressDTO addressTo = createAddressTo(warehouseOrdersInfo);

    // Create AddressFrom
    ShipmentRequestDTO.AddressDTO addressFrom = createAddressFrom(warehouse);

    //    create private List<ShipmentRequestDTO.ParcelDTO> parcels
    List<ShipmentRequestDTO.ParcelDTO> parcels = getParcels(presentParcels);

    // set values shipment
    shipmentDTO.setExternal_reference(orderNumber);
    shipmentDTO.setAddress_to(addressTo);
    shipmentDTO.setAddress_from(addressFrom);
    shipmentDTO.setParcels(parcels);

    // set shipment req dto
    shipmentRequestDTO.setShipment(shipmentDTO);
    return shipmentRequestDTO;
  }

  private List<ShipmentRequestDTO.ParcelDTO> getParcels(List<OrderContainerAssign> presentParcels) {
    List<ShipmentRequestDTO.ParcelDTO> parcelDTOList = new ArrayList<>();

    for (OrderContainerAssign presentParcel : presentParcels) {
      ShipmentRequestDTO.ParcelDTO parcelDTO = new ShipmentRequestDTO.ParcelDTO();
      //    @Data
      //    public static class ParcelDTO {
      //        private DimensionDTO dimensions; --make --> done
      //        private WeightDTO weight; -- make --> done
      //    }
      //
      //    @Data
      //    public static class DimensionDTO {
      //        private double length;
      //        private double width;
      //        private double height;
      //    }
      //
      //    @Data
      //    public static class WeightDTO {
      //        private double value;
      //        private String unit;
      //    }

      // create dimensions

      ShipmentRequestDTO.DimensionDTO dimensions = new ShipmentRequestDTO.DimensionDTO();
      //    private double length;
      //    private double width;
      //    private double height;
      dimensions.setLength((double) presentParcel.getBoxLength());
      dimensions.setWidth((double) presentParcel.getBoxWidth());
      dimensions.setHeight((double) presentParcel.getBoxHeight());

      // create weight
      ShipmentRequestDTO.WeightDTO weight = new ShipmentRequestDTO.WeightDTO();
      //        private double value;
      //        private String unit;

      weight.setValue(presentParcel.getBoxWeight());
      weight.setUnit("Ounce");

      // set parent properties
      parcelDTO.setDimensions(dimensions);
      parcelDTO.setWeight(weight);

      // add in list
      parcelDTOList.add(parcelDTO);
    }
    return parcelDTOList;
  }

  private ShipmentRequestDTO.AddressDTO createAddressFrom(Warehouse warehouse) {
    ShipmentRequestDTO.AddressDTO addressFrom = new ShipmentRequestDTO.AddressDTO();
    //    private String name;
    //    private String company;
    //    private boolean isResidential;
    //    private String street1;
    //    private String street2;
    //    private String city;
    //    private String state;
    //    private String country;
    //    private String postalCode;
    //    private String phone;
    //    private String email;
    addressFrom.setName("addressFromName");
    addressFrom.setCompany("addressFromCompany");
    addressFrom.setResidential(false);
//    addressFrom.setStreet1(warehouse.getAddressLine1());
//    addressFrom.setStreet2(warehouse.getAddressLine2());
    addressFrom.setStreet1("110 Freeport Pkwy");
    addressFrom.setStreet2("street");
//    // fetch address data from repositories
//    String city = String.valueOf(cityRepository.findById(warehouse.getCityId()));
//    String state = String.valueOf(stateRepository.findById(warehouse.getStateId()));
//    String country = String.valueOf(countryRepository.findById(warehouse.getCountyId()));
// fetch address data from repositories
    String city =
            (cityRepository.findById(warehouse.getCityId()).get().getName());
    String state =
            (stateRepository.findById(warehouse.getStateId()).get().getState().name());
    String country =
            (countryRepository.findById(warehouse.getCountyId()).get().getCountry().name());

    addressFrom.setCity("Coppel");
    addressFrom.setState("TX");
    addressFrom.setCountry("INDIA");
    addressFrom.setPostal_code("75019");
    addressFrom.setPhone("9248928298");
    addressFrom.setEmail("email@email.com");
//    addressFrom.setCity(city);
//    addressFrom.setState(state);
//    addressFrom.setCountry(country);
//    addressFrom.setPostal_code(warehouse.getZipCode());
//    addressFrom.setPhone("addressFromPhone");
//    addressFrom.setEmail("addressFromEmail");
    return addressFrom;
  }

  private ShipmentRequestDTO.AddressDTO createAddressTo(Optional<WarehouseOrdersInfo> warehouseOrdersInfo) {
    ShipmentRequestDTO.AddressDTO addressTo = new ShipmentRequestDTO.AddressDTO();
    //    private String name;
    //    private String company;
    //    private boolean isResidential;
    //    private String street1;
    //    private String street2;
    //    private String city;
    //    private String state;
    //    private String country;
    //    private String postalCode;
    //    private String phone;
    //    private String email;
    addressTo.setName("test person2");
    addressTo.setCompany("addressToCompany");
    addressTo.setResidential(true);
//    addressTo.setStreet1(warehouseOrdersInfo.get().getAddressLine1());
//    addressTo.setStreet2(warehouseOrdersInfo.get().getAddressLine2());
    addressTo.setStreet1("235 Cattle Ranch Drive");
    addressTo.setStreet2("");
    // fetch address data from repositories
    String city =
        (cityRepository.findById(warehouseOrdersInfo.get().getShipToCityId()).get().getName());
    String state =
        (stateRepository.findById(warehouseOrdersInfo.get().getShipToStateId()).get().getState().name());
    String country =
        (countryRepository.findById(warehouseOrdersInfo.get().getShipToCountryId()).get().getCountry().name());

//    addressTo.setCity(city);
//    addressTo.setState(state);
//    addressTo.setCountry(country);
//    addressTo.setPostal_code(warehouseOrdersInfo.get().getShipToZipCode());
//    addressTo.setPhone("AddressToPhone");
//    addressTo.setEmail("AddressToEmail");
    addressTo.setCity("Las Vegas");
    addressTo.setState("NV");
    addressTo.setCountry("US");
    addressTo.setPostal_code("89129");
    addressTo.setPhone("9898329892");
    addressTo.setEmail("example@gmail.com");
    return addressTo;
  }
}
