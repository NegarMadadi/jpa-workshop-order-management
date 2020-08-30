package se.lexicon.negar.jpaworkshopordermanagement.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.negar.jpaworkshopordermanagement.entity.OrderItem;
import se.lexicon.negar.jpaworkshopordermanagement.entity.Product;
import se.lexicon.negar.jpaworkshopordermanagement.entity.ProductOrder;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderItemRepositoryTest {

    OrderItem testObject;

    @Autowired
    OrderItemRepository orderItemRepository;

    @BeforeEach
    void setUp() {
        Product car = new Product("Car", 1-000-000);
        testObject = orderItemRepository.save(new OrderItem(2, car, null));
    }

    @Test
    void successfullyCreated(){
       List<OrderItem> found = orderItemRepository.findAll();

       assertFalse(found.isEmpty());
       assertTrue(found.contains(testObject));

    }
}