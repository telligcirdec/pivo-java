package net.gillets.pivo.domain.entity.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.gillets.pivo.domain.entity.User;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class UserData implements Serializable {

    @Column(name = "username", table = User.EMBEDDABLE_DATA_ENTITY_NAME)
    private String username;

}
