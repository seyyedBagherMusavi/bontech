package co.bontech.exam.service.mapper;

import co.bontech.exam.domain.User;
import co.bontech.exam.service.dto.UserDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct
 * support is still in beta, and requires a manual step with an IDE.
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO,User>{



}
