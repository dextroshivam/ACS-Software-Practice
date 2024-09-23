package com.acs.acs.DTO.DTOMapper;

import com.acs.acs.DTO.RequestDTO.UserRequestDTO;
import com.acs.acs.DTO.ResponseDTO.UserResponseDTO;
import com.acs.acs.Enitities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRequestDTOToUser(UserRequestDTO userRequestDTO);
    UserResponseDTO userToUserResponseDTO(User user);
}
