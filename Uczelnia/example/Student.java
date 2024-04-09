package org.example;

import lombok.*;
import java.util.*;

@Getter
@Setter
@Builder
public class Student {

    private String name;
    private String surname;
    private int index;
    @Builder.Default
    private List<Group> groups = new ArrayList<>();

    public void addToGroup(Group group){
        groups.add(group);
    }

}
