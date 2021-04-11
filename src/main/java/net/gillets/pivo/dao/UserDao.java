package net.gillets.pivo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.gillets.pivo.domain.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {
    
}
