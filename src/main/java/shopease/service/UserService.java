package shopease.service;

import shopease.dto.RegistrationDto;
import shopease.entity.User;

public interface UserService {

    User registerNewUser(RegistrationDto dto) throws IllegalArgumentException;
}