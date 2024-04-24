package org.example;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter
@Builder
public class Student {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    private int index;
    @Builder.Default
    private List<Group> groups = new ArrayList<>();

    public void addToGroup(@NotNull Group group){
        Objects.requireNonNull(group, "Group cannot be null");

        groups.add(group);
    }

}
