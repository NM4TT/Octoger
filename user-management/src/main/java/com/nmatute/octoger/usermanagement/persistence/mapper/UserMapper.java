package com.nmatute.octoger.usermanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nmatute.octoger.usermanagement.domain.dto.UserDTO;
import com.nmatute.octoger.usermanagement.persistence.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

    List<UserDTO> toUserDTOs(List<User> users);

    List<User> toUsers(List<UserDTO> userDTOs);

}
