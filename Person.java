package sample;

import java.time.LocalDateTime;


public class Person {
    static String name;
    static int id;

    public Person(int value,String initname) {
        id = value;
        //System.out.println(value);
        name = initname;
        //System.out.println(initname);
        var time = LocalDateTime.now();
        System.out.println("Welcome " + name + ". Badge #: " + id + ". Entered at time: " + time);
    }

}