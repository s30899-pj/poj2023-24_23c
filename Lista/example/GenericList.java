package org.example;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GenericList<N> {
    private final List<N> list;

    public GenericList() {
        list = new ArrayList<>();
    }

    public void add(N n) {
        if(n != null && !n.toString().trim().isEmpty()) {
            list.add(n);
        }
    }

    @SafeVarargs
    public final void addAll(N... nElements) {
        for (N n : nElements) {
            add(n);
        }
    }
    public void removeFromList(N n) {
        list.remove(n);
    }
}
