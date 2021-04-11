package net.gillets.pivo.domain.pivo;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAudit;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAuditListener;

@Entity
@EntityListeners(PivoCreationEntityAuditListener.class)
@Table(name = "pivo_entity_link_type")
@Data
public class PivoEntityLinkType implements Serializable {
    
    private static final long serialVersionUID = 5463218459140900922L;

    @Id
    @Column(name = "pivo_entity_link_type", nullable = false, unique = true, length = 50)
    private String entityLinkType;

    @AttributeOverride(name="createdAtLocalDate",
                           column=@Column(name = "pivo_entity_link_type_created_at_date", columnDefinition = "DATE", nullable = false))
    @AttributeOverride(name="createdAtOffsetTime",
                           column=@Column(name = "pivo_entity_link_type_created_at_time", columnDefinition = "TIME WITH TIME ZONE", nullable = false))
    @Embedded
    protected PivoCreationEntityAudit pivoCreationEntityAudit;

    @Column(name = "pivo_entity_link_type_enable", nullable = false)
    protected boolean enable;

}
