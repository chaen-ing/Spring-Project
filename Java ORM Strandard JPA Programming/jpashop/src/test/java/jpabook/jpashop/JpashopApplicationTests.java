package jpabook.jpashop;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Book;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpashopApplicationTests {
    @Autowired
    ItemRepository itemRepository;

    @Test
    void jpaSaveItem(){
        Book book = new Book();
        book.setAuthor("김영한");
        book.setName("JPA");

        itemRepository.save(book);
    }

}
