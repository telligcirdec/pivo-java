package net.gillets.pivo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import net.gillets.pivo.domain.data.UserData;
import net.gillets.pivo.domain.pivo.PivoEntity;

@Entity(name = "user")
@DiscriminatorValue("user")
public class User extends PivoEntity<UserData> {

    private static final long serialVersionUID = 7216169196967961130L;
    
    public User(UserData data, boolean enable){
        this.data = data;
        this.enable = enable;
    }

}
