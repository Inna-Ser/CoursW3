package pro.sky.cw3.model;

import java.time.LocalDateTime;

public class Operation {
    private final Type type;
    private final LocalDateTime dateTime;
    private final int count;
    private final Socks socks;

    public Operation(Type type, LocalDateTime dateTime, int count, Socks socks) {
        this.type = type;
        this.dateTime = dateTime;
        this.count = count;
        this.socks = socks;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getCount() {
        return count;
    }

    public Socks getSocks() {
        return socks;
    }
}
