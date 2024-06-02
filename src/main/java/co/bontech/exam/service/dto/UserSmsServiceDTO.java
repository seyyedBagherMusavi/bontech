package co.bontech.exam.service.dto;

import co.bontech.exam.domain.Service;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UserSmsServiceDTO {
    private Long id;

    private Service service;

    int useCounter;
}
