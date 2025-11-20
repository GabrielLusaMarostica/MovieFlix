package br.com.movieflix.mapper;

import br.com.movieflix.DTO.UserDTO;
import br.com.movieflix.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User map(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return user;
    }

    public UserDTO map(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());

        return userDTO;
    }

}
