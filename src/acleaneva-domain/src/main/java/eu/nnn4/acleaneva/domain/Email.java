package eu.nnn4.acleaneva.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
@NoArgsConstructor
public class Email {
    private String email;
    @Enumerated(EnumType.STRING)
    private EInfoType type;
}
