package pro.sky.cw3.dto;

import pro.sky.cw3.Colors;
import pro.sky.cw3.Size;
import pro.sky.cw3.model.Socks;

public class SocksDTO {
    private final Colors colors;
    private final Size size;
    private final int containsCotton;
    private final int count;

    public SocksDTO(Colors colors, Size size, int containsCotton, int count) {
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

    public int getCount() {
        return count;
    }

    public static SocksDTO from(Socks socks) {
        return new SocksDTO(socks.getColors(), socks.getSize(), socks.getContainsCotton(), socks.getCount());
    }
}
