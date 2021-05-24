package net.gillets.pivo.domain.entity.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.gillets.pivo.domain.entity.Domain;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class DomainData implements Serializable {
    
    @Column(name = "domain_name", table = Domain.EMBEDDABLE_DATA_ENTITY_NAME)
    private String domainName;

}