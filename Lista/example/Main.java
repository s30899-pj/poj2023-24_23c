package org.example;

public class Main {
    public static void main(String[] args) {
        GenericList<String> stringList = new GenericList<>();
        stringList.add("Hello");
        stringList.add("guys");
        stringList.add("");
        stringList.addAll("this","","is", "a", "list");
        System.out.println(stringList.getList());

        stringList.removeFromList("this");
        System.out.println("List after removal " + stringList.getList());

        String result = stringList.getList().stream()
                .map(String::toUpperCase)
                .reduce((s1, s2) -> s1 + " " + s2)
                .orElse("");
        System.out.println(result);
    }
}