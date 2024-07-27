package org.example.filmratev2simplev.mappers;

import org.example.filmratev2simplev.dto.UserDTO;
import org.example.filmratev2simplev.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
