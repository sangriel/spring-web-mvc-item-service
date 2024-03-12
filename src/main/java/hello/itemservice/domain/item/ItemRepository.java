package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    //실환경에서는 HashMap대신 concurrentHashmap을 사용해야됨 몇번째 나오는지 모르겠네
    private static final Map<Long,Item> store = new HashMap<>();
    //이것도 atomic long
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }


    //정석으로는 ItemParameterDTO 같은 식의 객체를 따로 만들어두는게 맞음
    public void update(Long id, Item updateParam) {
        Item foundItem = findById(id);
        foundItem.setItemName(updateParam.getItemName());
        foundItem.setPrice(updateParam.getPrice());
        foundItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
