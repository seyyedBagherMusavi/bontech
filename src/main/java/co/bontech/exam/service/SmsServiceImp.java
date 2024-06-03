package co.bontech.exam.service;

import co.bontech.exam.domain.UserSmsServices;
import co.bontech.exam.repository.ServiceRepository;
import co.bontech.exam.repository.UserSmsServiceRepository;
import co.bontech.exam.repository.projection.ServiceCredit;
import co.bontech.exam.rest.vm.ServiceVM;
import co.bontech.exam.security.MyPrincipal;
import co.bontech.exam.service.dto.ServiceCreditReportDTO;
import co.bontech.exam.service.dto.ServiceDTO;
import co.bontech.exam.service.dto.UserSmsServiceDTO;
import co.bontech.exam.service.mapper.ServiceCreditMapper;
import co.bontech.exam.service.mapper.SmsServiceMapper;
import co.bontech.exam.service.mapper.UserSmsServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SmsServiceImp {
    private final ServiceRepository serviceRepository;

    private final UserSmsServiceRepository userSmsServiceRepository;
    private final UserSmsServiceMapper userSmsServiceMapper;
    private final ServiceCreditMapper serviceCreditMapper;
    private final SmsServiceMapper smsServiceMapper;
    @Transactional
    public void enableService(ServiceVM serviceVM) {
        co.bontech.exam.domain.Service service = serviceRepository.findById(serviceVM.getServiceId())
                .orElseThrow(()-> new RuntimeException("service with this id not found"));
        if(Objects.nonNull(service.getLastStartAvailability())
                && Objects.equals(service.getLastStartAvailability().toLocalDate(),serviceVM.getStartTime().toLocalDate()))
            throw new RuntimeException("you can't enable twice a day");
        service.setLastStartAvailability(serviceVM.getStartTime());
        service.setLastEndAvailability(serviceVM.getEndTime());
        serviceRepository.save(service);
    }

    public List<ServiceCreditReportDTO> reportServices() {
        List< ServiceCredit> serviceCredits =userSmsServiceRepository.report();
        List<ServiceCreditReportDTO> serviceCreditsDTOs =serviceCreditMapper.toDto(serviceCredits);
        List<co.bontech.exam.domain.Service> services = serviceRepository.findAll();
        serviceCreditsDTOs.forEach(i->{
            co.bontech.exam.domain.Service service = services.stream().filter(serv -> Objects.equals(serv.getId(), i.getId())).findFirst().orElseThrow();
            i.setSumCredit(i.getSumCredit()*service.getCost());
        });
        return serviceCreditsDTOs;
    }

    @Transactional
    public void addUserToService(Long userId, Long serviceId) {
        UserSmsServices userServ = new UserSmsServices();
        userServ.setVersion(0);
        userServ.setUserId(userId);
        userServ.setServiceId(serviceId);
        userServ.setUseCounter(0);
        userSmsServiceRepository.save(userServ);
    }

    public List<ServiceDTO> findAllUserServices(Long userId) {
        List<UserSmsServices> allByUserId = userSmsServiceRepository.findAllByUserId(userId);
        List<ServiceDTO> collect = allByUserId.stream().map(item -> smsServiceMapper.toDto(item.getService()))
                .collect(Collectors.toList());
        return collect;
    }
    public List<UserSmsServiceDTO> findUserServicesReport() {
        Long userId = ((MyPrincipal) (SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal())).getUserId();
        List<UserSmsServices> allByUserId = userSmsServiceRepository.findAllByUserId(userId);
        List<UserSmsServiceDTO> userSmsServiceDTOS = userSmsServiceMapper.toDto(allByUserId);

        return userSmsServiceDTOS;
    }

    public List<ServiceDTO> findAllEnabled() {
        List<ServiceDTO> serviceDTOS = smsServiceMapper.toDto(serviceRepository.findAllAvailableNow());
        return serviceDTOS;
    }
    @Transactional
    public void useService(Long serviceId) {
        co.bontech.exam.domain.Service service = serviceRepository.findById(serviceId).orElseThrow();
        if(service.getLastStartAvailability().isAfter(LocalDateTime.now())
                || service.getLastEndAvailability().isBefore(LocalDateTime.now()))
            throw new RuntimeException("service not enable");
        Long userId = ((MyPrincipal)(SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())).getUserId();
        UserSmsServices userSmsServices = userSmsServiceRepository.findOneByUserIdAndServiceId(userId, serviceId)
                .orElseThrow(() -> new RuntimeException("this service not available for user"));
        if(userSmsServices.getUser().getInventory()>service.getCost()){
            userSmsServices.getUser().setInventory(userSmsServices.getUser().getInventory()-service.getCost());
            userSmsServices.setUseCounter(userSmsServices.getUseCounter()+1);
        }
        else throw new RuntimeException(" not enough credit");
    }

    @Transactional
    public void save(ServiceDTO serviceDTO) {
        co.bontech.exam.domain.Service service = smsServiceMapper.toEntity(serviceDTO);
        serviceRepository.save(service);
    }
}
