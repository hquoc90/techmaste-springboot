package vn.techmaster.buoi2.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private String manufacturer;
    private int price;
}
