package com.graphqljava.examplemutations.GraphQL.Types;

import java.util.Objects;

public class CreateProductInput {

    private String name;
    private String description;
    private String price;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,description,price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null || getClass()!=o.getClass()) return false;
        CreateProductInput that=(CreateProductInput) o;
        return Objects.equals(description, that.description) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price,that.price);
    }
}
