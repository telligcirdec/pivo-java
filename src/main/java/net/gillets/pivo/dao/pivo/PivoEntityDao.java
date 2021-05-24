package net.gillets.pivo.dao.pivo;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import net.gillets.pivo.domain.pivo.PivoEntity;

@NoRepositoryBean
public interface PivoEntityDao<ENTITY extends PivoEntity<ENTITY, DATA>, DATA extends Serializable>
        extends JpaRepository<ENTITY, UUID> {

    ENTITY saveWithHistory(ENTITY entity);

}
