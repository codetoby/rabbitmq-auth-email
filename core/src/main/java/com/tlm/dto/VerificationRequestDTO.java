package com.tlm.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VerificationRequestDTO implements Serializable {

    private String confirmUrl;
    private String email;
    private String username;
    
}
