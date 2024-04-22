package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfasd");

        String name = helloLombok.getName();
        System.out.println("name1 = " + name);

        System.out.println("helloLombok = " + helloLombok);
    }
}