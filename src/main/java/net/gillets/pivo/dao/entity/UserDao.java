package net.gillets.pivo.dao.entity;

import org.javers.spring.annotation.JaversSpringDataAuditable;

import net.gillets.pivo.dao.pivo.PivoEntityDao;
import net.gillets.pivo.domain.entity.User;
import net.gillets.pivo.domain.entity.data.UserData;
import net.gillets.pivo.views.pivo.PivoViewableRepository;


@JaversSpringDataAuditable
@PivoViewableRepository(entityName = "user")
public interface UserDao extends PivoEntityDao<User, UserData> {

}
