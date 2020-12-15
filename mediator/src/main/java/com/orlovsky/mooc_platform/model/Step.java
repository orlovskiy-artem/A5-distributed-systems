package com.orlovsky.mooc_platform.model;

import java.io.Serializable;
import java.util.UUID;

public interface Step extends Serializable {
    UUID getId();
    int getPosition();
}
