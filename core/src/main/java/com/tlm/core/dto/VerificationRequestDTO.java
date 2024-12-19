package com.tlm.core.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VerificationRequestDTO implements Serializable {

    private String confirmUrl;
    private String email;
    private String username;
    
}
