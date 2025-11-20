package br.com.movieflix.service;

import br.com.movieflix.DTO.UserDTO;
import br.com.movieflix.entity.User;
import br.com.movieflix.mapper.UserMapper;
import br.com.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDTO save(UserDTO userDTO){
        User user = userMapper.map(userDTO);
        userRepository.save(user);
        return userMapper.map(user);
    }

}
