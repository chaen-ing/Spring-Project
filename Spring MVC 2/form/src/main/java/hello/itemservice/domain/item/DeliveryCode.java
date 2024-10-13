package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FAST : 빠른 배송
 * NORMAL : 일반 배송
 * SLOW : 느린 배송
 */
@Data
@AllArgsConstructor
public class DeliveryCode {

	private String code;	// FAST같은 시스템 전달 값
	private String displayName;	// 빠른배송 같은 사용자에게 보여주는 값
}
