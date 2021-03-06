package com.orlovsky.mooc_platform.rabbitMessages.educationalMaterialServerMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAuthorMessageBody implements Serializable {
    private UUID courseId;
    private UUID authorId;
}
