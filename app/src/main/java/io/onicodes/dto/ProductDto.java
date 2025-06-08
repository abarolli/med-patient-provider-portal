package io.onicodes.dto;

import io.onicodes.entity.Product;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;

    public static ProductDto fromProduct(Product product) {
        return new ProductDto(product.getId(), product.getName());
    }
}
