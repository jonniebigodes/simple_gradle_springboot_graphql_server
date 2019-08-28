package com.graphqljava.examplemutations.orders;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;

    private Long productId;

    private Integer quantity;

    private String status;

    private LocalDate created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, productId, quantity, status, created);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel that = (OrderModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(customerId, that.customerId) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(status, that.status) &&
                Objects.equals(created, that.created);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("OrderModel{");
        stringBuilder.append("id=");
        stringBuilder.append(id);
        stringBuilder.append(", customerId=");
        stringBuilder.append(customerId);
        stringBuilder.append(", productId=");
        stringBuilder.append(productId);
        stringBuilder.append(", quantity=");
        stringBuilder.append(quantity);
        stringBuilder.append(", status='");
        stringBuilder.append(status);
        stringBuilder.append('\'');
        stringBuilder.append(", created=");
        stringBuilder.append(created);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
