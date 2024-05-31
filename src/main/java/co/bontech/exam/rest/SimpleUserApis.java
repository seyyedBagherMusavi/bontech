package co.bontech.exam.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ap")
public class SimpleUserApis {

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> reportAvailableServices(){
        return  ResponseEntity.ok().build();
    }

}
