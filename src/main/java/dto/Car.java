package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.Fuel;

@Setter
@Getter
@Builder
@ToString
public class Car {
    private String serialNumber;
    private String manufacture;
    private String model;
    private String year;
    private String fuel;
    // private Fuel fuel;   wrong
    private Integer seats;
    private String carClass;
    private Double pricePerDay;
    private String about;
    private String city;
    private String image;
}