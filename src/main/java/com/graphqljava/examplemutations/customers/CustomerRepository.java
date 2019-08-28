package com.graphqljava.examplemutations.customers;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerModel,Long> {
}
