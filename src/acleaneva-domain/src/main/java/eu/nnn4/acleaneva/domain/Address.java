package eu.nnn4.acleaneva.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor

//@DomainLayer
@AllArgsConstructor //(access = AccessLevel.PRIVATE)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id", nullable = false)
    private Long addressId;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotBlank
    @Size(message = "Max 255 characters", max = 255)
    private String fullName;

    @Column(columnDefinition = "VARCHAR(255)")
    @NotBlank
    @Size(message = "Max 255 characters", max = 255)
    private String street;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotBlank
    @Size(message = "Max 255 characters", max = 255)
    private String city;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotBlank
    @Size(message = "Max 255 characters", max = 255)
    private String zipCode;
    @Column(columnDefinition = "VARCHAR(255)")
    @NotBlank
    @Size(message = "Max 255 characters", max = 255)
    private String country;
    @Size(message = "Max 255 characters", max = 255)
    private String additional;

}
