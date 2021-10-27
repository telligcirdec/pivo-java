package net.gillets.pivo.domain.entity.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.gillets.pivo.domain.entity.Group;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GroupData implements Serializable {
    
    @Column(name = "groupname", table = Group.EMBEDDABLE_DATA_ENTITY_NAME)
    private String groupname;

}
