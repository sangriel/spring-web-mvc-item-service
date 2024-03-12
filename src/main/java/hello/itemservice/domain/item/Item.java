package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

//@Data //Getter setter 다 만들어주는데 위험하다고 함
@Getter @Setter //그래서 이렇게 따로 빼서 쓰라고 함
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
