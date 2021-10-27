package net.gillets.pivo.dao.pivo;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import net.gillets.pivo.domain.pivo.PivoEntity;
import net.gillets.pivo.domain.pivo.PivoEntityLink;
import net.gillets.pivo.domain.pivo.id.PivoEntityLinkId;

public class PivoEntityLinkDaoImpl<A_ENTITY extends PivoEntity<A_ENTITY,?>, B_ENTITY extends PivoEntity<B_ENTITY,?>, LINK extends PivoEntityLink<A_ENTITY, B_ENTITY>>
        extends SimpleJpaRepository<LINK, PivoEntityLinkId> implements PivoEntityLinkDao<A_ENTITY, B_ENTITY, LINK> {

    public PivoEntityLinkDaoImpl(JpaEntityInformation<LINK, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
    }

}
