package hello.itemservice.web.basic;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //여기에서 원래 @Pathvariable 변수이름이 name과 동일할 경우 name = ""을 생략해도 되는데,
    //추가를 안하면 안되는 상황 -> test.text 읽어볼것
    @GetMapping("/{itemId}")
    public String item(@PathVariable(name = "itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }


//    @PostMapping("/add")
//    public String save(@RequestParam(name = "itemName") String itemName,
//                       @RequestParam(name = "price") int price,
//                       @RequestParam(name = "quantity") Integer quantity,
//                       Model model) {
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setQuantity(quantity);
//        item.setPrice(price);
//        Item saved = itemRepository.save(item);
//
//
//        model.addAttribute("item",item);
//        return "basic/item";
//    }

//    @PostMapping("/add")
//    public String save(@ModelAttribute("item") Item item) {
//        //@ModelAttribute가 알아서 Item setter 다 해줌
//        itemRepository.save(item);
////        model.addAttribute("item",item);
//        // 이것도 주석 처리해도 됨 @ModelAttribute가 보통 뷰에서도 data를 쓴다고 판단하고,
//        // 알아서 모델에다 넣어줌
//        // 이떄 이름은 @ModelAttribute("item")에서 item으로 들어감
//        return "basic/item";
//    }

    @PostMapping("/add")
    public String save(Item item) {
        //우리가 만든 객체는 왠만하면 생략해도 @ModelAttribute가 붙은걸로 인지됨
        itemRepository.save(item);
        return "basic/item";
    }
}
