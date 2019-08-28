package com.graphqljava.examplemutations.GraphQL.Resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphqljava.examplemutations.GraphQL.Types.CreateProductInput;
import com.graphqljava.examplemutations.orders.Order;
import com.graphqljava.examplemutations.orders.OrderModel;
import com.graphqljava.examplemutations.orders.OrderRepository;
import com.graphqljava.examplemutations.products.Product;
import com.graphqljava.examplemutations.products.ProductModel;
import com.graphqljava.examplemutations.products.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMutationResolver implements GraphQLMutationResolver {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    public ProductMutationResolver(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository= orderRepository;
    }

    public String deleteProduct(Long value){
        StringBuilder result= new StringBuilder();
        result.append("Product with id ");
        boolean tmpProduct= productRepository.existsById(value);
        if (tmpProduct==false){
            result.append(value);
            result.append(" does not exist");
            return result.toString();
        }
        productRepository.deleteById(value);
        orderRepository.findByProductId(value).forEach(order->{
            orderRepository.deleteById(order.getId());
        });

        result.append(value);
        result.append(" was deleted");
        return result.toString();
    }
    public Product createProduct(CreateProductInput value){

        ProductModel newProduct= new ProductModel();
        newProduct.setDescription(value.getDescription());
        newProduct.setName(value.getName());

        newProduct.setPrice(Double.parseDouble(value.getPrice()));

        productRepository.save(newProduct);
        return productToGraphql(newProduct);
    }

    private Product productToGraphql(ProductModel value) {
        Product product= new Product();
        product.setId(value.getId());
        product.setDescription(value.getDescription());
        product.setPrice(value.getPrice());
        product.setName(value.getName());
        return product;
    }
}
