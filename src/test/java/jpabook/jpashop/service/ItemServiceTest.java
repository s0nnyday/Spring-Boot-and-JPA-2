package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 상품_저장() throws Exception {
        //given
        Book book = new Book();
        book.setName("Spring Boot in Action");
        book.setPrice(30000);
        book.setStockQuantity(10);
        book.setAuthor("Craig Walls");
        book.setIsbn("123456789");

        //when
        itemService.saveItem(book);
        em.flush();
        em.clear();

        //then
        Item savedItem = itemRepository.findOne(book.getId());
        assertNotNull(savedItem);
        assertEquals(book.getName(), savedItem.getName());
        assertEquals(book.getPrice(), savedItem.getPrice());
        assertEquals(book.getStockQuantity(), savedItem.getStockQuantity());
    }

    @Test
    public void 상품_조회() throws Exception {
        //given
        Book book = new Book();
        book.setName("Clean Code");
        book.setPrice(40000);
        book.setStockQuantity(15);
        book.setAuthor("Robert C. Martin");
        book.setIsbn("987654321");

        itemService.saveItem(book);
        em.flush();
        em.clear();

        //when
        Item foundItem = itemService.findOne(book.getId());

        //then
        assertNotNull(foundItem);
        assertEquals(book.getName(), foundItem.getName());
        assertEquals(book.getPrice(), foundItem.getPrice());
        assertEquals(book.getStockQuantity(), foundItem.getStockQuantity());
    }

    @Test
    public void 전체_상품_조회() throws Exception {
        //given
        Book book = new Book();
        book.setName("Clean Code");
        book.setPrice(40000);
        book.setStockQuantity(15);
        book.setAuthor("Robert C. Martin");
        book.setIsbn("987654321");

        Book book1 = new Book();
        book1.setName("Spring Boot in Action");
        book1.setPrice(30000);
        book1.setStockQuantity(10);
        book1.setAuthor("Craig Walls");
        book1.setIsbn("123456789");

        itemService.saveItem(book);
        itemService.saveItem(book1);
        em.flush();
        em.clear();

        //when
        List<Item> items = itemService.findItems();

        //then
        assertEquals(2, items.size());
    }
}
