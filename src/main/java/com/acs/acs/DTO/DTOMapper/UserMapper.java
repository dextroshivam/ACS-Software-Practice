package com.acs.acs.DTO.DTOMapper;

import com.acs.acs.DTO.ResponseDTO.UserResponse.UserResponseDTO;
import com.acs.acs.Enitities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO userToUserResponseDTO(User AUser);
}
