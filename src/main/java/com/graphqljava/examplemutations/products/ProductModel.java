package com.graphqljava.examplemutations.products;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel productModel = (ProductModel) o;
        return Objects.equals(id, productModel.id) &&
                Objects.equals(name, productModel.name) &&
                Objects.equals(description, productModel.description) &&
                Objects.equals(price, productModel.price);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ProductModel{");
        stringBuilder.append("id=");
        stringBuilder.append(id);
        stringBuilder.append(", name='");
        stringBuilder.append(name);
        stringBuilder.append('\'');
        stringBuilder.append(", description='");
        stringBuilder.append(description);
        stringBuilder.append('\'');
        stringBuilder.append(", price=");
        stringBuilder.append(price);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
