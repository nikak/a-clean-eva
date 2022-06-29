package eu.nnn4.acleaneva.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Embeddable
public class EmbeddableMoney {
    @Basic
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private ECurrencyCode unit;


}
