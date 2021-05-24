package net.gillets.pivo.dao.pivo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import net.gillets.pivo.domain.pivo.PivoEntity;
import net.gillets.pivo.domain.pivo.PivoEntityLink;
import net.gillets.pivo.domain.pivo.id.PivoEntityLinkId;

@NoRepositoryBean
public interface PivoEntityLinkDao<A_ENTITY extends PivoEntity<A_ENTITY,?>, B_ENTITY extends PivoEntity<B_ENTITY,?>, LINK extends PivoEntityLink<A_ENTITY, B_ENTITY>>
                extends JpaRepository<LINK, PivoEntityLinkId> {

}
