package net.gillets.pivo.domain.pivo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import lombok.Data;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAudit;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAuditListener;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAuditable;
import net.gillets.pivo.domain.pivo.audit.PivoUpdateEntityAudit;
import net.gillets.pivo.domain.pivo.audit.PivoUpdateEntityAuditListener;
import net.gillets.pivo.domain.pivo.audit.PivoUpdateEntityAuditable;

@Entity
@EntityListeners({PivoCreationEntityAuditListener.class, PivoUpdateEntityAuditListener.class})
@Table(name = "pivo_entity")
@TypeDef(name = "json", typeClass = JsonStringType.class)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pivo_entity_type")
public abstract class PivoEntity<T extends Serializable> implements Serializable, PivoCreationEntityAuditable, PivoUpdateEntityAuditable {

    private static final long serialVersionUID = -475811309159406143L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pivo_entity_uuid", updatable = false, nullable = false)
    protected UUID uuid;

    @Column(name = "pivo_entity_previous_uuid", nullable = true, insertable = false, updatable = false)
    protected UUID uuidPreviousState;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name ="pivo_entity_previous_uuid")
    private PivoEntity<T> previousState;
    
    @Column(name = "pivo_entity_type", length = 50, insertable = false, updatable = false)
    protected String entityTypeCode;

    @Type(type = "jsonb")
    @Column(name = "pivo_entity_data", columnDefinition = "jsonb")
    protected T data;
    
    @AttributeOverride(name="createdAtLocalDate",
                           column=@Column(name = "pivo_entity_created_at_date", columnDefinition = "DATE", nullable = false))
    @AttributeOverride(name="createdAtOffsetTime",
                           column=@Column(name = "pivo_entity_created_at_time", columnDefinition = "TIME WITH TIME ZONE", nullable = false))
    @Embedded
    protected PivoCreationEntityAudit pivoCreationEntityAudit;

    @AttributeOverride(name="updatedAtLocalDate",
                           column=@Column(name = "pivo_entity_updated_at_date", columnDefinition = "DATE"))
    @AttributeOverride(name="updatedAtOffsetTime",
                           column=@Column(name = "pivo_entity_updated_at_time", columnDefinition = "TIME WITH TIME ZONE"))
    @Embedded
    protected PivoUpdateEntityAudit pivoUpdateEntityAudit;

    @Column(name = "pivo_entity_enable", nullable = false)
    protected boolean enable;

    @Version
    @Column(name = "pivo_entity_version")
    protected Long version;

}
