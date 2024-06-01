package co.bontech.exam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;


/**
 * A Service.
 */
@Entity
@Table(name = "service")
@Getter
@Setter
public class Service extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private Integer cost;

    private LocalDateTime lastStartAvailability;

    private LocalDateTime lastEndAvailability;


}
