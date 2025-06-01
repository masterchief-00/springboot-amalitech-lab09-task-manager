package com.kwizera.springbootamalitechlab09taskmanager.domain.dto;

public class TaskUpdateRequestDTO {
    String field;
    Object newValue;

    public TaskUpdateRequestDTO(String field, Object newValue) {
        this.field = field;
        this.newValue = newValue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getNewValue() {
        return newValue;
    }

    public void setNewValue(Object newValue) {
        this.newValue = newValue;
    }
}
