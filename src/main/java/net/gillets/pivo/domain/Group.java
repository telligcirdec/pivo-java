package net.gillets.pivo.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import net.gillets.pivo.domain.data.GroupData;
import net.gillets.pivo.domain.pivo.PivoEntity;

@Entity(name = "group")
@DiscriminatorValue("group")
public class Group extends PivoEntity<GroupData> {

    private static final long serialVersionUID = -4675262521723395902L;
    
}
