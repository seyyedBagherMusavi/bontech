package co.bontech.exam.service.mapper;

import co.bontech.exam.domain.UserSmsServices;
import co.bontech.exam.service.dto.UserSmsServiceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserSmsServiceMapper extends EntityMapper<UserSmsServiceDTO, UserSmsServices> {

}
