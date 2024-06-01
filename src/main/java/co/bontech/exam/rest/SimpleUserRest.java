package co.bontech.exam.rest;

import co.bontech.exam.rest.vm.ServiceVM;
import co.bontech.exam.service.SmsServiceImp;
import co.bontech.exam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class SimpleUserRest {

    private final UserService userService;
    private final SmsServiceImp smsServiceImp;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> reportAvailableServices(){
        return  ResponseEntity.ok().build();
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> addUserInv(@RequestParam(name = "userId") Long userId,
                                           @RequestParam(name = "debit") Integer debit,
                                           @RequestParam(name = "version") int version){
        userService.addUserInv(userId,debit,version);
        return  ResponseEntity.ok().build();
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> enableService(@Validated @RequestBody ServiceVM serviceVM){
        smsServiceImp.enableService(serviceVM);
        return  ResponseEntity.ok().build();
    }




}
