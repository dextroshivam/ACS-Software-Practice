package com.acs.acs.DTO.DTOMapper;

import com.acs.acs.DTO.RequestDTO.PermissionRequestDTO;
import com.acs.acs.DTO.ResponseDTO.PermissionResponseDTO;
import com.acs.acs.Enitities.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission permissionRequestDTOToPermission(PermissionRequestDTO permissionRequestDTO);
    PermissionResponseDTO permissionToPermissionResponseDTO(Permission permission);
}
