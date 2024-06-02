package co.bontech.exam.service.dto;


import co.bontech.exam.domain.Authority;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * A DTO representing a user, with only the public attributes.
 */
@Getter@Setter
public class UserDTO {

    @Size(max = 50)
    @NotNull
    private String userName;
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    private int inventory=0;

    private Authority authority;

}
