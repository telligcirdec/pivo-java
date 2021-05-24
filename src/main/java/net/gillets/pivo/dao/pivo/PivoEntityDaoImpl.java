package net.gillets.pivo.dao.pivo;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import net.gillets.pivo.domain.pivo.PivoEntity;

public class PivoEntityDaoImpl<ENTITY extends PivoEntity<ENTITY, DATA>, DATA extends Serializable>
        extends SimpleJpaRepository<ENTITY, UUID> implements PivoEntityDao<ENTITY, DATA> {

    private final EntityManager em;

    public PivoEntityDaoImpl(JpaEntityInformation<ENTITY, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.em = em;
    }

    @Transactional
    public ENTITY saveWithHistory(ENTITY entity) {
        return save(entity);
    }

    public List<ENTITY> findAllEnable() {
        return null;
    }

}
