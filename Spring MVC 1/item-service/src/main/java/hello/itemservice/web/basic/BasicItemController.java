package hello.itemservice.web.basic;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

/**
 * @RequiredArgsConstructor이 아래의 생성자와 같은 역할함
 * @Autowired - 이것도 생성자 하나면 생략 가능
 *        public BasicItemController(ItemRepository itemRepository){
 * 		this.itemRepository = itemRepository;
 *    }
 */
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

	private final ItemRepository itemRepository;

	@GetMapping
	public String items(Model model){
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items",items);
		return "basic/items";
	}

	@GetMapping("/{itemId}")
	public String item(@PathVariable(name = "itemId") Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}

	// 테스트용 데이터
	@PostConstruct
	public void init(){
		itemRepository.save(new Item("itemA",100,100));
		itemRepository.save(new Item("itemB",20,100));
	}
}
