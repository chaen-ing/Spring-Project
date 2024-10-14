package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	/**
	 * 메시지 소스 설정 직접 등록
	 * @Bean
	 * public MessageSource messageSource(){
	 * 		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	 * 		messageSource.setBasenames("messages", "errors");
	 * 		messageSource.setDefaultEncoding("utf-8");
	 * 		return messageSource;
	 * }
	 * MessageSource는 인터페이스 이므로 ResouceBundelMessageSource를 빈으로 등록
	 * basenames : 설정 파일의 이름 지정 -> messages면 messages.properties찾아서 사용
	 * 파일 위치는 /resouces//messages.properties
	 */

}
