package org.example;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter
@Builder
public class Group {
    @NotBlank
    private String name;
    private Subject subject;
    @Builder.Default
    private List<Student> students = new ArrayList<>();

    public void addStudent(@NotNull Student student) {
        Objects.requireNonNull(student, "Student must not be null");

        if(students.contains(student)) {
            throw new IllegalArgumentException("Student " + student.getName() + " already exists in the group " + this.name + "\n");
        }
        students.add(student);
        student.addToGroup(this);
    }

}
