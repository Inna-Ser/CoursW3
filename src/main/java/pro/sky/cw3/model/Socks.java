package pro.sky.cw3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.sky.cw3.Colors;
import pro.sky.cw3.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    private Colors colors;
    private Size size;
    private int containsCotton;
    private int count;


}
