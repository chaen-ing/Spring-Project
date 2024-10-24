package hello.login.web.session;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * 세션 관리
 */
@Component
public class SessionManager {

	public static final String SESSION_COOKIE_NAME = "mySessionId";
	private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

	/**
	 * 세션 생성
	 * - sessionId 생성 (임의의 추정 불가능한 랜덤 값)
	 * - 세션 저장소에 sessionId와 보관할 값 저장
	 * - sessionId로 응답 쿠키를 생성해서 클라이언트에 전달
	 */
	public void createSession(Object value, HttpServletResponse response){
		// 세션 id 생성하고, 값을 세션에 저장
		String sessionId = UUID.randomUUID().toString();
		sessionStore.put(sessionId,value);

		//쿠키 생성
		Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
		response.addCookie(mySessionCookie);
	}

	/**
	 * 세션 조회
	 */
	public Object getSession(HttpServletRequest request){
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		if(sessionCookie == null){
			return null;
		}
		return sessionStore.get(sessionCookie.getValue());
		// 찾은 쿠키의 value를 통해 sessionId(UUID) 찾음
	}

	/**
	 * 세션만료
	 */
	public void expire(HttpServletRequest request){
		Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
		if(sessionCookie != null){
			sessionStore.remove(sessionCookie.getValue());
		}
	}

	public Cookie findCookie(HttpServletRequest request, String cookieName){
		if(request.getCookies() == null){
			return null;
		}

		/**
		 * 쿠키 배열 -> stream
		 * 쿠키의 name을 통해 찾고 있으면 쿠키리턴, 없으면 null 리턴
		 */
		return Arrays.stream(request.getCookies())
			.filter(cookie -> cookie.getName().equals(cookieName))
			.findAny()
			.orElse(null);
	}



}

