package org.stackdrive.audit.dto;

import java.util.Objects;

public class SimpleObject {

    private Object key;

    private Object value;

    public SimpleObject() {
    }

    public SimpleObject(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleObject that = (SimpleObject) o;
        return Objects.equals(getKey(), that.getKey()) &&
                Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }
}