package net.gillets.pivo.dao.entity;

import org.javers.spring.annotation.JaversSpringDataAuditable;

import net.gillets.pivo.dao.pivo.PivoEntityDao;
import net.gillets.pivo.domain.entity.Domain;
import net.gillets.pivo.domain.entity.data.DomainData;
import net.gillets.pivo.views.pivo.PivoViewableRepository;

@JaversSpringDataAuditable
@PivoViewableRepository(entityName = "domain")
public interface DomainDao extends PivoEntityDao<Domain, DomainData> {
    
}