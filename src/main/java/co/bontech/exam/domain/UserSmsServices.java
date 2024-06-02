package co.bontech.exam.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * this is join table with some good things
 * to manage which services user has access and how many times user use servic
 * it's so important to reset the counter and one of the good moement to reset is
 * when you enabled the service
 */
@Entity
@Table(name = "user_services")
// TODO we can create audit table for userSms service and based on that we can generate report
//@Audited
@Getter@Setter
public class UserSmsServices extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "service_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Service.class, fetch = FetchType.EAGER)
    private Service service;

    @Column(name = "service_id")
    private Long serviceId;


    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    @Column(name = "user_id")
    private Long userId;

    int useCounter;


}
