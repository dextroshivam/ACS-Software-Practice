package com.acs.acs.DTO.DTOMapper;

import com.acs.acs.DTO.RequestDTO.RoleRequestDTO;
import com.acs.acs.DTO.ResponseDTO.RoleResponseDTO;
import com.acs.acs.Enitities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role roleRequestDTOToRole(RoleRequestDTO roleRequestDTO);
    RoleResponseDTO roleToRoleResponseDTO(Role role);
}
