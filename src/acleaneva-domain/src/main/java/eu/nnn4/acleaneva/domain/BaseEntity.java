package eu.nnn4.acleaneva.domain;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.annotation.Version;
//import org.springframework.data.domain.Persistable;
import org.springframework.lang.Nullable;
//import org.jmolecules.architecture.layered.*;
//@DomainLayer

@MappedSuperclass
public abstract class BaseEntity<BaseId extends Serializable>  { // extends Persistable<BaseId>

    @Version
    private Long version;

    public @NotNull Optional<Long> getVersion() {
        return Optional.ofNullable(version);
    }

    protected void setVersion(@Nullable Long version) {
        this.version = version;
    }
}