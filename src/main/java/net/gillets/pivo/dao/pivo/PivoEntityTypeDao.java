package net.gillets.pivo.dao.pivo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gillets.pivo.domain.pivo.PivoEntityType;

@Repository
public interface PivoEntityTypeDao extends JpaRepository<PivoEntityType, String> {

}