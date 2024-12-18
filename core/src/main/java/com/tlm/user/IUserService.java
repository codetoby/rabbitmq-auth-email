package com.tlm.user;

import com.tlm.dto.UserRequestDTO;

public interface IUserService {
    User save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(Long id);
    User createUser(UserRequestDTO userRequest);
    void setVerifiedTrue(Long userId);
}
