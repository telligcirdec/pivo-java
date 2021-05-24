package net.gillets.pivo.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.gillets.pivo.domain.entity.data.DomainData;
import net.gillets.pivo.domain.pivo.PivoEntity;

@Entity
@DiscriminatorValue(Domain.ENTITY_NAME)
@EqualsAndHashCode(callSuper = true)
@Data
@SecondaryTable(name = Domain.EMBEDDABLE_DATA_ENTITY_NAME, pkJoinColumns = @PrimaryKeyJoinColumn(name = "pivo_entity_uuid"))
public class Domain extends PivoEntity<Domain, DomainData> {

    private static final long serialVersionUID = 7216169196967961130L;

    public static final String ENTITY_NAME = "domain";
    public static final String EMBEDDABLE_DATA_ENTITY_NAME = PivoEntity.DATA_PREFIX + ENTITY_NAME;

    @Embedded
    private DomainData data;

}
