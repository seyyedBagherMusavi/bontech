package co.bontech.exam.rest;

import co.bontech.exam.security.MyPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class Home {
    @GetMapping()
    public ResponseEntity<String> isAdmin(){
        String username = ((MyPrincipal) (SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())).getUsername();
        return  ResponseEntity.ok("hello "+username);
    }
}
