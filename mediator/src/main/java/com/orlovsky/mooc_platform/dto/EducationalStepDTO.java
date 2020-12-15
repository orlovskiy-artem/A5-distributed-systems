package com.orlovsky.mooc_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.net.URI;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalStepDTO implements Serializable {
//    private static final long serialVersionUID = 6529685098267757690L;

    private UUID id;
    private UUID courseId;
    private URI eduMaterialUri;
    private int position;
}
