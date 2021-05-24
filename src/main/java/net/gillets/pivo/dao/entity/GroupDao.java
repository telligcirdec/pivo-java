package net.gillets.pivo.dao.entity;

import org.javers.spring.annotation.JaversSpringDataAuditable;

import net.gillets.pivo.dao.pivo.PivoEntityDao;
import net.gillets.pivo.domain.entity.Group;
import net.gillets.pivo.domain.entity.data.GroupData;

@JaversSpringDataAuditable
public interface GroupDao extends PivoEntityDao<Group, GroupData> {
    
}
