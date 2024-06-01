package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.repository.OrderItemRepository;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpashopApplicationTests {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    @Test
    void jpaSaveItem(){
        Book book = new Book();
        book.setAuthor("김영한");
        book.setName("JPA");

        itemRepository.save(book);
    }

    @Test
    void jpaCascadeTest(){
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setCount(2);
        OrderItem orderItem2 = new OrderItem();

        Order order = new Order();
        order.addOrderItem(orderItem1);
        order.addOrderItem(orderItem2);

        orderRepository.save(order);

    }

}
