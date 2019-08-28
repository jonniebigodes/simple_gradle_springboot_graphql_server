package com.graphqljava.examplemutations.GraphQL.Resolvers;
import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphqljava.examplemutations.customers.Customer;
import com.graphqljava.examplemutations.orders.OrderModel;
import com.graphqljava.examplemutations.orders.OrderRepository;
import com.graphqljava.examplemutations.orders.Order;
import com.graphqljava.examplemutations.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerResolver implements GraphQLResolver<Customer> {
    private OrderRepository orderRepository;

    @Autowired
    public CustomerResolver(OrderRepository orderrepo){
        this.orderRepository= orderrepo;
    }
    public List<Order> orders(Customer customer) {
        return orderRepository.findByCustomerId(customer.getId())
                .stream()
                .map(this::OrderToGraphql)
                .collect(Collectors.toList());
    }

    private Order OrderToGraphql(OrderModel value) {
        Order newOrder = new Order();
        newOrder.setId(value.getId());
        newOrder.setStatus(value.getStatus());
        newOrder.setQuantity(value.getQuantity());
        newOrder.setCreated(value.getCreated());

        Product product = new Product();
        product.setId(value.getProductId());
        newOrder.setProduct(product);

        Customer customer = new Customer();
        customer.setId(value.getCustomerId());
        newOrder.setCustomer(customer);
        return newOrder;
    }
}
