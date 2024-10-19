package hello.itemservice.web.validation.form;

import java.io.PipedReader;
import java.security.Principal;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class ItemUpdateForm {

	@NotNull
	private Long id;

	@NotBlank
	private String itemName;

	@NotNull
	@Range(min = 1000, max = 1000000)
	private Integer price;

	// 수정에서 수량 자유롭게 변경가능. null도 허용
	private Integer quantity;
}
