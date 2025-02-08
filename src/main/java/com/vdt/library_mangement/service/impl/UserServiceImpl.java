package com.vdt.library_mangement.service.impl;

import com.vdt.library_mangement.dto.UserDto;
import com.vdt.library_mangement.entity.User;
import com.vdt.library_mangement.repository.UserRepository;
import com.vdt.library_mangement.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    // @Override
    // public UserDto createUser(UserDto userDto) {
    //     User user = UserMapper.toEntity(userDto);

    //     User savedUser = userRepository.save(user);

    //     // UserDto savedUserDto = UserMapper.toDTO(savedUser);

    //     return UserMapper.toDTO(savedUser);
    // }
}
