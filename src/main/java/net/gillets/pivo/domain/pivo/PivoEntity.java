package net.gillets.pivo.domain.pivo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAudit;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAuditListener;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAuditable;
import net.gillets.pivo.domain.pivo.audit.PivoUpdateEntityAudit;
import net.gillets.pivo.domain.pivo.audit.PivoUpdateEntityAuditListener;
import net.gillets.pivo.domain.pivo.audit.PivoUpdateEntityAuditable;

@Entity
@EntityListeners({ PivoCreationEntityAuditListener.class, PivoUpdateEntityAuditListener.class })
@Table(name = "pivo_entity")
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pivo_entity_type")
public abstract class PivoEntity<ENTITY extends PivoEntity<ENTITY, DATA>, DATA extends Serializable>
        implements Serializable, PivoCreationEntityAuditable, PivoUpdateEntityAuditable {

    private static final long serialVersionUID = -475811309159406143L;
    public static final String DATA_PREFIX = "pivo_entity_data_";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pivo_entity_uuid", updatable = false, nullable = false)
    private UUID uuid;

    @AttributeOverride(name = "createdAtLocalDate", column = @Column(name = "pivo_entity_created_at_date", columnDefinition = "DATE", nullable = false))
    @AttributeOverride(name = "createdAtOffsetTime", column = @Column(name = "pivo_entity_created_at_time", columnDefinition = "TIME WITH TIME ZONE", nullable = false))
    @Embedded
    private PivoCreationEntityAudit pivoCreationEntityAudit;

    @AttributeOverride(name = "updatedAtLocalDate", column = @Column(name = "pivo_entity_updated_at_date", columnDefinition = "DATE"))
    @AttributeOverride(name = "updatedAtOffsetTime", column = @Column(name = "pivo_entity_updated_at_time", columnDefinition = "TIME WITH TIME ZONE"))
    @Embedded
    private PivoUpdateEntityAudit pivoUpdateEntityAudit;

    @Column(name = "pivo_entity_enable", nullable = false)
    private boolean enable;

    @Version
    @Column(name = "pivo_entity_version")
    private Long version;

    public abstract DATA getData();

    public abstract void setData(DATA data);

}
