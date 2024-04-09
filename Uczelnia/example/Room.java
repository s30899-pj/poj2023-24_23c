package org.example;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public abstract class Room {
    private TypeRoom type;

    public abstract void cleanRoom ();

    public abstract void onElectricity();

    public  abstract void  offElectricity();

}
