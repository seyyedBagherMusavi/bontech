package co.bontech.exam.domain;

import jakarta.persistence.*;

import java.time.LocalTime;


/**
 * A Service.
 */
@Entity
@Table(name = "service")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Service extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private Integer cost;

    private LocalTime lastStartAvailability;

    private LocalTime lastEndAvailability;


}
