package com.tlm.core.user;

import org.springframework.stereotype.Component;

import com.tlm.core.dto.UserRequestDTO;

@Component
public interface IUserService {
    User save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(Long id);
    User createUser(UserRequestDTO userRequest);
    void setVerifiedTrue(Long userId);
}
