package co.bontech.exam.service.mapper;

import co.bontech.exam.domain.Service;
import co.bontech.exam.service.dto.ServiceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SmsServiceMapper extends EntityMapper<ServiceDTO, Service>{
}
