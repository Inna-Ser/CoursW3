package pro.sky.cw3.dto;

import pro.sky.cw3.model.Colors;
import pro.sky.cw3.model.Size;
import pro.sky.cw3.model.Socks;

public class SocksDTO {
    private Colors colors;
    private Size size;
    private int containsCotton;
    private int count;

    public SocksDTO() {
        this.colors = colors;
        this.size = size;
        this.containsCotton = containsCotton;
        this.count = count;
    }

    public Colors getColors() {
        return colors;
    }

    public Size getSize() {
        return size;
    }

    public int getContainsCotton() {
        return containsCotton;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setContainsCotton(int containsCotton) {
        this.containsCotton = containsCotton;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static SocksDTO from(Socks socks) {
        var socksDTO = new SocksDTO();
        return socksDTO;
    }
}
