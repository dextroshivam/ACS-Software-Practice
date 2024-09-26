package com.acs.acs.DTO.DTOMapper;

import com.acs.acs.DTO.ResponseDTO.UserResponse.RoleResponseDTO;
import com.acs.acs.Enitities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponseDTO roleToRoleResponseDTO(Role role);
}
