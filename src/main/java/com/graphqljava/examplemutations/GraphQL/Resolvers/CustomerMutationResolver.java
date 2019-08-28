package com.graphqljava.examplemutations.GraphQL.Resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphqljava.examplemutations.GraphQL.Types.CreateCustomerInput;
import com.graphqljava.examplemutations.customers.Customer;
import com.graphqljava.examplemutations.customers.CustomerModel;
import com.graphqljava.examplemutations.customers.CustomerRepository;
import com.graphqljava.examplemutations.orders.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class CustomerMutationResolver implements GraphQLMutationResolver {
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;

    public CustomerMutationResolver(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository= orderRepository;
    }

    public String deleteCustomer(Long value){
        StringBuilder result= new StringBuilder();
        result.append("Customer with id ");
        boolean customerExists= customerRepository.existsById(value);
        if (customerExists==false){
            result.append(value);
            result.append(" does not exist");
            return result.toString();
        }
        orderRepository.findByCustomerId(value).forEach(order->{
            orderRepository.deleteById(order.getId());
        });
        customerRepository.deleteById(value);
        result.append(value);
        result.append(" was deleted");
        return result.toString();
    }
    public Customer createCustomer(CreateCustomerInput value){
        CustomerModel newCustomer= new CustomerModel();
        newCustomer.setName(value.getName());
        newCustomer.setEmail(value.getEmail());
        customerRepository.save(newCustomer);

        return customerToGraphQL(newCustomer);
    }

    private Customer customerToGraphQL(CustomerModel value) {
        Customer newcustomer= new Customer();
        newcustomer.setEmail(value.getEmail());
        newcustomer.setName(value.getName());
        newcustomer.setId(value.getId());
        return newcustomer;
    }
}
