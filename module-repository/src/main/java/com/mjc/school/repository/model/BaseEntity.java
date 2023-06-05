package com.mjc.school.repository.model;

import java.io.Serializable;

public interface BaseEntity<T> {

    T getId();

    void setId(T id);
}
