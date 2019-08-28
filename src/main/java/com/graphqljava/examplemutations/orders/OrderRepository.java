package com.graphqljava.examplemutations.orders;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderModel,Long> {
    List<OrderModel>findByCustomerId(Long customerId);
    List<OrderModel>findByProductId(Long productId);
}
