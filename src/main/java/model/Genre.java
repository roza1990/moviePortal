package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
private int id;
private String name;


    public Genre(String name) {
        this.name = name;
    }
}
