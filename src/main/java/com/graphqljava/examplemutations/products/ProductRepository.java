package com.graphqljava.examplemutations.products;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductModel,Long> {
}
