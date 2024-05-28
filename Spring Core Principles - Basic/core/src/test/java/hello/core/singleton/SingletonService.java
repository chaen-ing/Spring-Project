package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성해둔다.
    // 자기자신을 내부의 private으로 가지고 있음
    // static이라서 class레벨에 올라가서 딱 하나만 생성
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용
    // 조회시 사용
    public static SingletonService getInstance(){
        return instance;
    }

    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성 못하게 막는다
    // 밖에서 못쓰게 막아놓음
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
