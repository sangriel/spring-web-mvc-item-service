package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item itemA = new Item("itemA", 1000, 10);

        //when
        Item savedItem = itemRepository.save(itemA);

        //then

        Item foundItem = itemRepository.findById(itemA.getId());

        assertThat(foundItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item itemA = new Item("itemA", 1000, 10);
        Item itemb = new Item("itemB", 1000, 10);

        itemRepository.save(itemA);
        itemRepository.save(itemb);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA,itemb);
    }

    @Test
    void update() {
        //given
        Item itemA = new Item("itemA", 1000, 10);

        itemRepository.save(itemA);


        //when
        Item updateItem = new Item("itemAUpdated", 2000, 20);
        itemRepository.update(itemA.getId(),updateItem);

        //then
        Item result = itemRepository.findById(itemA.getId());
        assertThat(result.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(result.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(result.getQuantity()).isEqualTo(updateItem.getQuantity());
    }

    @Test
    void findById() {
    }

    @Test
    void clearStore() {
    }
}