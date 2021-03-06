package com.orlovsky.mooc_platform.rabbitMessages.educationalMaterialServerMessages;

import com.orlovsky.mooc_platform.dto.TestStepDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddTestStepMessageBody implements Serializable {
    private UUID courseId;
    private TestStepDTO testStepDTO;
}
