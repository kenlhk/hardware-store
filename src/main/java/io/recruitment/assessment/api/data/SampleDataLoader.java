package io.recruitment.assessment.api.data;

import com.github.javafaker.Faker;
import io.recruitment.assessment.api.model.*;
import io.recruitment.assessment.api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {

        Random random = new Random();
        DecimalFormat df = new DecimalFormat("###.##");

        // create 1000 products
        if (productRepository.count() == 0 && userRepository.count() == 0) {
            List<Product> products = LongStream.rangeClosed(1, 1000).mapToObj(i -> new Product(
                    null,
                    faker.commerce().productName(),
                    faker.lorem().sentence(20),
                    faker.internet().image(),
                    IntStream.rangeClosed(1, 4).mapToObj(j -> faker.internet().image()).collect(Collectors.toList()),
                    Double.parseDouble(df.format(random.nextDouble() * 100)),
                    Double.parseDouble(df.format(random.nextDouble()))
            )).collect(Collectors.toList());
            productRepository.saveAll(products);

        // create items
        Item item1 = new Item();
        item1.setProduct(productRepository.findById(1L).orElseThrow());
        item1.setQuantity(10L);
        itemRepository.save(item1);
        Item item2 = new Item();
        item2.setProduct(productRepository.findById(2L).orElseThrow());
        item2.setQuantity(5L);
        itemRepository.save(item2);
        Item item3 = new Item();
        item3.setProduct(productRepository.findById(3L).orElseThrow());
        item3.setQuantity(30L);
        itemRepository.save(item3);

        // create order
        Order order = new Order();
        order.getItems().add(item1);
        order.getItems().add(item2);
        orderRepository.save(order);

        // create cart
        Cart cart = new Cart();
        cart.getItems().add(item3);
        cartRepository.save(cart);

        // create admin and customer
        User admin = new User();
        admin.setEmail("admin@hardvare.com");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.getRoles().add(Role.ADMIN);
        User customer1 = new User();
        customer1.setEmail("customer1@gmail.com");
        customer1.setPassword(passwordEncoder.encode("customer1"));
        customer1.getRoles().add(Role.CUSTOMER);
        customer1.getOrders().add(order);
        customer1.setCart(cart);
        userRepository.save(admin);
        userRepository.save(customer1);
        }
    }
}
