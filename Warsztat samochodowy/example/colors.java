package org.example;

import lombok.*;

@Getter
@AllArgsConstructor
public enum colors {
    BLACK("000000"),
    RED("#FF0000"),
    WHITE("#FFFFFF");

    private final String hexValue;

}
