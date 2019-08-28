package com.graphqljava.examplemutations.GraphQL.Resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphqljava.examplemutations.customers.Customer;
import com.graphqljava.examplemutations.customers.CustomerModel;
import com.graphqljava.examplemutations.customers.CustomerRepository;
import com.graphqljava.examplemutations.products.ProductModel;
import com.graphqljava.examplemutations.products.ProductRepository;
import com.graphqljava.examplemutations.orders.Order;
import com.graphqljava.examplemutations.products.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderResolver implements GraphQLResolver<Order> {

    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    public OrderResolver(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    public Customer customer(Order order) {
        return customerRepository.findById(order.getCustomer().getId())
                .map(this::modelToGraphQL)
                .orElse(null);
    }

    public Product product(Order order) {
        return productRepository.findById(order.getProduct().getId())
                .map(this::modelToGraphQL)
                .orElse(null);
    }

    private Product modelToGraphQL(ProductModel productModel) {
        Product product = new Product();
        product.setDescription(productModel.getDescription());
        product.setName(productModel.getName());
        product.setId(productModel.getId());
        product.setPrice(productModel.getPrice());
        return product;
    }

    private Customer modelToGraphQL(CustomerModel customerModel) {
        Customer customer = new Customer();
        customer.setEmail(customerModel.getEmail());
        customer.setId(customerModel.getId());
        customer.setName(customerModel.getName());
        return customer;
    }

}
