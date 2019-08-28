package com.graphqljava.examplemutations.GraphQL.Resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.graphqljava.examplemutations.customers.Customer;
import com.graphqljava.examplemutations.customers.CustomerModel;
import com.graphqljava.examplemutations.customers.CustomerRepository;
import com.graphqljava.examplemutations.orders.Order;
import com.graphqljava.examplemutations.orders.OrderModel;
import com.graphqljava.examplemutations.orders.OrderRepository;
import com.graphqljava.examplemutations.products.Product;
import com.graphqljava.examplemutations.products.ProductModel;
import com.graphqljava.examplemutations.products.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public QueryResolver(CustomerRepository customerrepo, ProductRepository prodrepo,OrderRepository ordrepo){
        this.customerRepository= customerrepo;
        this.productRepository= prodrepo;
        this.orderRepository= ordrepo;

    }
    public Customer customerById(Long id){
        return customerRepository
                .findById(id)
                .map(this::modelToGraphQL)
                .orElse(null);
    }
    public List<Customer> allCustomers(){
       Iterable<CustomerModel> allData= customerRepository.findAll();
       List<Customer> result = new ArrayList<Customer>();
        allData.forEach(customer ->{
            result.add(modelToGraphQL(customer));
        } );
        return result;
    }
    public List<Product> allProducts(){
        Iterable<ProductModel> allProducts=productRepository.findAll();
        List<Product> result= new ArrayList<Product>();
        allProducts.forEach(product->{
            result.add(productmodelToGraphQL(product));
        });
        return result;
    }
    public List<Order> allOrders(){
        Iterable<OrderModel> allOrders= orderRepository.findAll();

        List<Order> result= new ArrayList<Order>();
        allOrders.forEach(order->{
            ProductModel tmpProduct= productRepository.findById(order.getProductId()).orElse(null);
            CustomerModel tmpCustomer= customerRepository.findById(order.getCustomerId()).orElse(null);
            result.add(ordermodelToGraphQL(order,tmpProduct,tmpCustomer));
        });
        return result;
    }
    private Order ordermodelToGraphQL(OrderModel value, ProductModel valueProduct,CustomerModel valueCustomer){
        Order result= new Order();
        result.setId(value.getId());
        result.setStatus(value.getStatus());
        result.setQuantity(value.getQuantity());
        result.setProduct(new Product(valueProduct.getId(),valueProduct.getName(),valueProduct.getDescription(),valueProduct.getPrice()));
        result.setCustomer(new Customer(valueCustomer.getId(),valueCustomer.getName(),valueCustomer.getEmail()));
        return result;
    }
    private Product productmodelToGraphQL(ProductModel product) {
        Product newProduct= new Product();
        newProduct.setId(product.getId());
        newProduct.setDescription(product.getDescription());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        return newProduct;
    }

    private Customer modelToGraphQL(CustomerModel customerModel) {
        Customer customer = new Customer();
        customer.setId(customerModel.getId());
        customer.setName(customerModel.getName());
        customer.setEmail(customerModel.getEmail());

        return customer;
    }
}
