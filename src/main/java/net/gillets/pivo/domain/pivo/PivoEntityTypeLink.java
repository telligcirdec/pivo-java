package net.gillets.pivo.domain.pivo;

import java.io.Serializable;

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
import net.gillets.pivo.domain.pivo.id.PivoEntityTypeLinkId;

@Entity
@EntityListeners(PivoCreationEntityAuditListener.class)
@Table(name = "pivo_entity_type_link")
@Data
@IdClass(PivoEntityTypeLinkId.class)
public class PivoEntityTypeLink implements Serializable {

    private static final long serialVersionUID = 196100985095968753L;

    @Id
    @Column(name = "pivo_entity_a_type", nullable = false, insertable = false, updatable = false, length = 50)
    private String entityATypeCode;

    @Id
    @Column(name = "pivo_entity_b_type", nullable = false, insertable = false, updatable = false, length = 50)
    private String entityBTypeCode;

    @Id
    @Column(name = "pivo_entity_link_type", nullable = false, insertable = false, updatable = false, length = 50)
    private String entityLinkTypeCode;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pivo_entity_a_type")
    private PivoEntityType entityAType;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pivo_entity_b_type")
    private PivoEntityType entityBType;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pivo_entity_link_type")
    private PivoEntityLinkType entityLinkType;

    @Column(name = "pivo_entity_type_link_enable", nullable = false)
    private boolean enable;

    @AttributeOverride(name = "createdAtLocalDate", column = @Column(name = "pivo_entity_type_link_created_at_date", columnDefinition = "DATE", nullable = false))
    @AttributeOverride(name = "createdAtOffsetTime", column = @Column(name = "pivo_entity_type_link_created_at_time", columnDefinition = "TIME WITH TIME ZONE", nullable = false))
    @Embedded
    private PivoCreationEntityAudit pivoCreationEntityAudit;

}
