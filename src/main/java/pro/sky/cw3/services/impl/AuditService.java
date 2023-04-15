package pro.sky.cw3.services.impl;

import pro.sky.cw3.model.Operation;
import org.springframework.stereotype.Service;
import pro.sky.cw3.model.Socks;
import pro.sky.cw3.model.Type;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pro.sky.cw3.model.Type.ADD;

@Service
public class AuditService {
    private final List<Operation> operations = new ArrayList<>();

    public void recordAddOperation(Socks socks, int count) {
        recordOperation(ADD, socks, count);
    }

    public void recordIssueOperation(Socks socks, int count) {
        recordOperation(Type.ISSUE, socks, count);
    }

    public void recordRemoveDefectedOperation(Socks socks, int count) {
        recordOperation(Type.REMOVE_DEFECTED, socks, count);
    }

    public void recordOperation(Type type, Socks socks, int count) {
        this.operations.add(new Operation(type, LocalDateTime.now(), count, socks));
    }
}
