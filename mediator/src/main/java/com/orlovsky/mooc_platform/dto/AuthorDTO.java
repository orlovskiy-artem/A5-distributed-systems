package com.orlovsky.mooc_platform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO implements Serializable {
//    private static final long serialVersionUID = 6529685098267757690L;
    private UUID id;
    private String firstName;
    private String lastName;
    private String description;
}
