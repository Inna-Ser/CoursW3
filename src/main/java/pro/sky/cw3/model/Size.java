package pro.sky.cw3.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Size {
    XS(35),
    S(37),
    M(39),
    L(42),
    XL(45),
    XXL(47);
    private final int shoeSize;

    Size(int shoeSize) {
        this.shoeSize = shoeSize;
    }

    @JsonCreator
    public static Size convertSize(int value) {
        for (Size size : Size.values()) {
            if (value == size.shoeSize) {
                return size;
            }
        }
        throw new RuntimeException("This size was not found");
    }
}
