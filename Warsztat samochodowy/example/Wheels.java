package org.example;

import lombok.*;

@Setter
@AllArgsConstructor
public class Wheels {

    private int VMax;

    @Override
    public String toString() {
        return "Wheels: " +
                "VMax-" + VMax;
    }
}
