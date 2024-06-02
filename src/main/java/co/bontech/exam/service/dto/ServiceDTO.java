package co.bontech.exam.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
public class ServiceDTO
{
    private Long id;

    private String name;

    private Integer cost;

    private LocalDateTime lastStartAvailability;

    private LocalDateTime lastEndAvailability;
}
