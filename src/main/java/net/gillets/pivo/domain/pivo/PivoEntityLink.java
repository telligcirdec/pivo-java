package net.gillets.pivo.domain.pivo;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAudit;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAuditListener;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAuditable;
import net.gillets.pivo.domain.pivo.id.PivoEntityLinkId;

@Entity
@EntityListeners(PivoCreationEntityAuditListener.class)
@Table(name = "pivo_entity_link")
@Data
@IdClass(PivoEntityLinkId.class)
public abstract class PivoEntityLink implements Serializable, PivoCreationEntityAuditable {

    private static final long serialVersionUID = 5108471401967046983L;

    @Id
    @Column(name = "pivo_entity_a_uuid", nullable = false, insertable = false, updatable = false)
    private UUID entityAUuid;

    @Id
    @Column(name = "pivo_entity_b_uuid", nullable = false, insertable = false, updatable = false)
    private UUID entityBUuid;
    
    @Id
    @Column(name = "pivo_entity_link_type", nullable = false, insertable = false, updatable = false, length = 50)
    private String entityLinkTypeCode;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="pivo_entity_a_uuid")
    private PivoEntity<?> entityA;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="pivo_entity_b_uuid")
    private PivoEntity<?> entityB;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="pivo_entity_link_type")
    private PivoEntityLinkType entityLinkType;

    @Column(name = "pivo_entity_link_enable", nullable = false)
    protected boolean enable;

    @AttributeOverride(name="createdAtLocalDate",
                            column=@Column(name = "pivo_entity_link_created_at_date", columnDefinition = "DATE", nullable = false))
    @AttributeOverride(name="createdAtOffsetTime",
                            column=@Column(name = "pivo_entity_link_created_at_time", columnDefinition = "TIME WITH TIME ZONE", nullable = false))
    @Embedded
    private PivoCreationEntityAudit pivoCreationEntityAudit;  

}
