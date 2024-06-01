package co.bontech.exam.service;

import co.bontech.exam.repository.ServiceRepository;
import co.bontech.exam.rest.vm.ServiceVM;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SmsServiceImp {
    private final ServiceRepository serviceRepository;
    @Transactional
    public void enableService(ServiceVM serviceVM) {
        co.bontech.exam.domain.Service service = serviceRepository.findById(serviceVM.getServiceId())
                .orElseThrow(()-> new RuntimeException("service with this id not found"));
        if(service.getLastStartAvailability().toLocalDate().equals(serviceVM.getStartTime().toLocalDate()))
            throw new RuntimeException("you can't enable twice a day");
        service.setLastStartAvailability(serviceVM.getStartTime());
        service.setLastEndAvailability(serviceVM.getEndTime());
        serviceRepository.save(service);
    }
}
