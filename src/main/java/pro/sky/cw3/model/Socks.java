package pro.sky.cw3.model;

import java.util.Objects;

public class Socks {
    private final Colors colors;
    private final Size size;
    private final int containsCotton;

    public Socks(Colors colors, Size size, int containsCotton) {
        this.colors = colors;
        this.size = size;
        this.containsCotton = containsCotton;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return containsCotton == socks.containsCotton && colors == socks.colors && size == socks.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colors, size, containsCotton);
    }
}
