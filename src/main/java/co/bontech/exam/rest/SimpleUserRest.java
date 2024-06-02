package co.bontech.exam.rest;

import co.bontech.exam.security.MyPrincipal;
import co.bontech.exam.service.SmsServiceImp;
import co.bontech.exam.service.dto.ServiceDTO;
import co.bontech.exam.service.dto.UserSmsServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class SimpleUserRest {
    private final SmsServiceImp smsServiceImp;
    @GetMapping("active-services")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<ServiceDTO>> getALLServices(){
        Long userId = ((MyPrincipal) (SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())).getUserId();
        return  ResponseEntity.ok(smsServiceImp.findAllUserServices(userId));
    }

    @GetMapping("enable-services")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<ServiceDTO>> getALLEnableServices(){
        return  ResponseEntity.ok(smsServiceImp.findAllEnabled());
    }

    @PutMapping("use-service/{serviceId}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<ServiceDTO>> useService(@PathVariable Long serviceId){
        smsServiceImp.useService(serviceId);
        return  ResponseEntity.ok().build();
    }


    @GetMapping("uses-services")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<UserSmsServiceDTO>> getServicesReport(){
        return  ResponseEntity.ok(smsServiceImp.findUserServicesReport());
    }
}
