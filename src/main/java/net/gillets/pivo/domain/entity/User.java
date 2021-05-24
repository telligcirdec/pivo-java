package net.gillets.pivo.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.gillets.pivo.domain.entity.data.UserData;
import net.gillets.pivo.domain.pivo.PivoEntity;

@Entity
@DiscriminatorValue(User.ENTITY_NAME)
@EqualsAndHashCode(callSuper = true)
@Data
@SecondaryTable(name = User.EMBEDDABLE_DATA_ENTITY_NAME, pkJoinColumns = @PrimaryKeyJoinColumn(name = "pivo_entity_uuid"))
public class User extends PivoEntity<User, UserData> {

    private static final long serialVersionUID = 7216169196967961130L;

    public static final String ENTITY_NAME = "user";
    public static final String EMBEDDABLE_DATA_ENTITY_NAME = PivoEntity.DATA_PREFIX + ENTITY_NAME;

    @Embedded
    private UserData data;

    

}
