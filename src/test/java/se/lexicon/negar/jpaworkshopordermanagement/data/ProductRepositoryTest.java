package se.lexicon.negar.jpaworkshopordermanagement.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.negar.jpaworkshopordermanagement.entity.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
    Product testObject;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        testObject = productRepository.save(new Product("Book", 150));
        testObject = productRepository.save(new Product("T-shirt", 199));
        testObject = productRepository.save(new Product("Car", 1 - 000 - 000));
        testObject = productRepository.save(new Product("The book", 190));
    }

    @Test
    void successfullyCreated() {
        assertEquals(4, productRepository.findAll().size());
    }

    @Test
    void findAllByNameContainingIgnoreCase() {

        //Arrange
        String search = "Boo";

        //Act
        List<Product> foundProductsMatchingSearch = productRepository.findAllByNameContainingIgnoreCase(search);

        //Assert
        assertNotNull(foundProductsMatchingSearch);
        assertFalse(foundProductsMatchingSearch.isEmpty());
        assertEquals(2,foundProductsMatchingSearch.size());
        assertTrue(foundProductsMatchingSearch.contains(testObject));
    }
}