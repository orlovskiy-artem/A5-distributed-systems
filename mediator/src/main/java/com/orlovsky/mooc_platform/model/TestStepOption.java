package com.orlovsky.mooc_platform.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestStepOption implements Serializable {
    private UUID id;
    private String optionText;
    private boolean isCorrect;
    @JsonBackReference
    private TestStep testStep;
}