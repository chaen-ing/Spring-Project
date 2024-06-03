package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpabook.jpashop.domain.*;
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

    @Autowired
    EntityManager em;

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

    @Test
    void emTest(){
        Member member = new Member();
        member.setName("kim");

        em.persist(member);

    }

}
