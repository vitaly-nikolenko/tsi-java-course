package lv.tsi.student.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Identity implements Serializable {
    private final String id;

    public Identity() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity identity = (Identity) o;
        return Objects.equals(id, identity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
