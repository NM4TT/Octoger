package com.nmatute.octoger.typemanagement.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.nmatute.octoger.typemanagement.domain.dto.UserDTO;
import com.nmatute.octoger.typemanagement.persistence.entity.User;

@Mapper(componentModel = "spring", uses = TypeMapper.class)
public interface UserMapper {
    
    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

    List<UserDTO> toUserDTOs(List<User> users);

    List<User> toUsers(List<UserDTO> userDTOs);

}
