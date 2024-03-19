package org.example;

import lombok.*;

@Setter
@AllArgsConstructor
public class Body {

    private String type;

    private colors color;

    @Override
    public String toString() {
        return "Body: " +
                "type-" + type +
                ", color-" + color;
    }
}
