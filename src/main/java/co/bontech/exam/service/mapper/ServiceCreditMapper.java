package co.bontech.exam.service.mapper;

import co.bontech.exam.repository.projection.ServiceCredit;
import co.bontech.exam.service.dto.ServiceCreditReportDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceCreditMapper {

    ServiceCreditReportDTO toDto(ServiceCredit entity);


    List<ServiceCreditReportDTO> toDto(List<ServiceCredit> entityList);
}
