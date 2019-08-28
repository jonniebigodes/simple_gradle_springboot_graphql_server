package com.graphqljava.examplemutations.GraphQL.Resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphqljava.examplemutations.GraphQL.Types.CreateOrderInput;
import com.graphqljava.examplemutations.customers.Customer;
import com.graphqljava.examplemutations.orders.Order;
import com.graphqljava.examplemutations.orders.OrderModel;
import com.graphqljava.examplemutations.orders.OrderRepository;
import com.graphqljava.examplemutations.products.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderMutationResolver implements GraphQLMutationResolver {
    private OrderRepository orderRepository;

    public OrderMutationResolver(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(CreateOrderInput value){
        OrderModel newOrder= new OrderModel();
        newOrder.setCustomerId(value.getCustomerId());
        newOrder.setProductId(value.getProductId());
        newOrder.setQuantity(value.getQuantity());
        newOrder.setStatus("PENDING");
        orderRepository.save(newOrder);

        return orderToGraphQL(newOrder);
    }
    public String deleteOrder(Long value){
        StringBuilder result= new StringBuilder();
        result.append("Order with id ");
        boolean tmpOrder=orderRepository.existsById(value);
        if (tmpOrder==false){
            result.append(value);
            result.append(" does not exist");
            return result.toString();
        }
        orderRepository.deleteById(value);
        result.append(value);
        result.append(" was deleted");
        return result.toString();
    }
    private Order orderToGraphQL(OrderModel orderModel) {
        Order order = new Order();
        order.setId(orderModel.getId());
        order.setStatus(orderModel.getStatus());
        order.setQuantity(orderModel.getQuantity());
        order.setCreated(orderModel.getCreated());

        Product product = new Product();
        product.setId(orderModel.getProductId());
        order.setProduct(product);

        Customer customer = new Customer();
        customer.setId(orderModel.getCustomerId());
        order.setCustomer(customer);

        return order;
    }
}
