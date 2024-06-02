package co.bontech.exam.rest;

import co.bontech.exam.rest.vm.ServiceVM;
import co.bontech.exam.service.SmsServiceImp;
import co.bontech.exam.service.UserService;
import co.bontech.exam.service.dto.ServiceCreditReportDTO;
import co.bontech.exam.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminRest {

    private final UserService userService;
    private final SmsServiceImp smsServiceImp;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> isAdmin(){
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/add-user-inv")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> addUserInv(@RequestParam(name = "userId") Long userId,
                                           @RequestParam(name = "debit") Integer debit,
                                           @RequestParam(name = "version") int version){
        userService.addUserInv(userId,debit,version);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/enable-service")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> enableService(@Validated @RequestBody ServiceVM serviceVM){
        smsServiceImp.enableService(serviceVM);
        return  ResponseEntity.ok().build();
    }


    @PostMapping("/add-service-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> addServiceToUser(@RequestParam(name = "userId") Long userId,
                                                 @RequestParam(name = "serviceId") Long serviceId){
        smsServiceImp.addUserToService(userId,serviceId);
        return  ResponseEntity.ok().build();
    }
    @PostMapping("create-user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> createUser(@Validated @RequestBody UserDTO userDTO){
        userService.save(userDTO);
        return  ResponseEntity.ok().build();
    }

    @GetMapping("service-report")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ServiceCreditReportDTO>> getServiceCredit(){
        return  ResponseEntity.ok(smsServiceImp.reportServices());
    }



}
