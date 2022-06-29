package eu.nnn4.acleaneva.domain;

import lombok.*;
import org.hibernate.validator.internal.util.stereotypes.Immutable;
import org.joda.money.Money;
//import org.jmolecules.architecture.layered.DomainLayer;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;
@Getter @Setter //@RequiredArgsConstructor
@ToString @EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "account")
//@Data
@NoArgsConstructor

//@DomainLayer
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account extends Audit{

    @Id
    @GeneratedValue
    private Long accountId;

    @Version
    private Long version;

    @NotBlank @Max(message = "Max 255 characters", value = 255)
    private String firstName;
    @NotBlank @Max(message = "Max 255 characters", value = 255)
    private String secondName;

    @Transient
    public final static int MAX_ELEMENTS = 4;

    @Size(min = 1, max = MAX_ELEMENTS)
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL
    )
    @OrderColumn
    Set<Address> addresses = new LinkedHashSet<>();

    @Immutable
    @Size(min = 1, max = MAX_ELEMENTS)
    @ElementCollection(fetch=FetchType.EAGER,targetClass = Email.class)
    @OrderColumn
    Set<Email> emails = new LinkedHashSet<>();

    @Size(min = 1, max = MAX_ELEMENTS)
    @ElementCollection(fetch=FetchType.EAGER)
    @OrderColumn
    Set<Phone> phones;

    public void addAddress(Address setElement) {
        if (this.addresses.size() == MAX_ELEMENTS) {
            Iterator<Address> it = this.addresses.iterator();
            it.next();
            it.remove();
        }
        this.addresses.add(setElement);
    }

    public void removeAddress(Address setElement) {
        if (this.addresses.size() > 0) {
            this.addresses.remove(setElement);
        }
    }

    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(this.addresses);
    }

//    @Convert(converter = EmbeddableMoney.class)
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "value", column = @Column(name = "account_amount_value")),
//            @AttributeOverride(name = "unit", column = @Column(name = "account_amount_unit"))
//    })
//    private Money amount;

    @Convert(converter = AccountTypeConverter.class)
    private EAccountType accountType;

}
