package eu.nnn4.acleaneva.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@MappedSuperclass
public class Audit {
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private long modifiedDate;

//    @Column(name = "created_by")
//    @CreatedBy
//    private String createdBy;
//
//    @Column(name = "modified_by")
//    @LastModifiedBy
//    private String modifiedBy;

}

//https://www.baeldung.com/database-auditing-jpa
//https://examples.javacodegeeks.com/spring-data-jpa-auditing-example/

//https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-data-jpa-query-2/src/main/java/com/baeldung/config/PersistenceConfig.java
//https://stackoverflow.com/questions/221611/creation-timestamp-and-last-update-timestamp-with-hibernate-and-mysql