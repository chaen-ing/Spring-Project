package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

	/**
	 *
	 * @param paramMap
	 * @param model
	 * @return viewname
	 */

	String process(Map<String, String> paramMap, Map<String, Object> model);
	// ModelView 반환에서 String 반환으로 변경
}
