package org.example;

import lombok.*;
import java.util.*;

@Getter
@Setter
@Builder
public class Group {

    private String name;
    private Subject subject;
    @Builder.Default
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
        student.addToGroup(this);
    }

}
