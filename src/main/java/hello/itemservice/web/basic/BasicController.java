package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor //
public class BasicController {
//    @Autowired //스프링에서 이렇게 생성자가 딱 하나만 있으면 생략가능
//    public BasicController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }
    //RequiredArgsConstructor 쓰면 final이 붙은 애들을 생성자를 그냥 만들어줌 따라서 전체 코드 생략 가능

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items",items);
        return "basic/items";
    }

    /*
    테스트용 데이터
     */
    @PostConstruct
    public void init() {
        itemRepository.save( new Item("itemA",100,10));
        itemRepository.save( new Item("itemB",200,20));
    }
}
