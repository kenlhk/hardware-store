package io.recruitment.assessment.api.data;

import com.github.javafaker.Faker;
import io.recruitment.assessment.api.model.Product;
import io.recruitment.assessment.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {

        Random random = new Random();

        // create products
        if (productRepository.count() < 10) {
            List<Product> products = LongStream.rangeClosed(1, 1000).mapToObj(i -> new Product(
                    null,
                    faker.funnyName().name(),
                    faker.lorem().characters(100, 200),
                    faker.internet().image(),
                    IntStream.rangeClosed(1, 4).mapToObj(j -> faker.internet().image()).collect(Collectors.toList()),
                    random.nextDouble() * 100,
                    random.nextDouble()
            )).collect(Collectors.toList());
            productRepository.saveAll(products);
        }
    }
}
