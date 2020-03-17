package com.github.hcsp.stream;

import java.util.*;
import java.util.stream.Collectors;

public class Problem1 {
    static class User {
        private String name;
        private int age;

        User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public static boolean isSurnameZhang(User user) {
            return user.getName().startsWith("张");
        }
    }

    // 编写一个方法，统计"年龄大于等于60的用户中，名字是两个字的用户数量"
    public static int countUsers(List<User> users) {

        return (int) users.stream().filter(user -> user.getAge() >= 60).filter(user -> user.getName().length() == 2).count();
    }

    // 编写一个方法，筛选出年龄大于等于60的用户，然后将他们按照年龄从大到小排序，将他们的名字放在一个LinkedList中返回
    public static LinkedList<String> collectNames(List<User> users) {

        return (LinkedList) users.stream()
                .filter(user -> user.getAge() >= 60)
                .sorted(Comparator.comparing(User::getAge).reversed())
                .map(User::getName)
                .collect(Collectors.toCollection(LinkedList::new));
    }


    public static void main(String[] args) {
        System.out.println(
                countUsers(
                        Arrays.asList(
                                new User("张三", 60),
                                new User("李四", 61),
                                new User("张三丰", 300),
                                new User("王五", 12))));

        System.out.println(
                collectNames(
                        Arrays.asList(
                                new User("张三", 60),
                                new User("李四", 61),
                                new User("张三丰", 300),
                                new User("王五", 12))));

        List<User> users = Arrays.asList(new User("三", 60),
                new User("李四", 61));


        Optional<User> optionalUser = users.stream().filter(User::isSurnameZhang).findAny();
        optionalUser.orElseThrow(IllegalAccessError::new);
        optionalUser.ifPresent(System.out::println);

    }
}
