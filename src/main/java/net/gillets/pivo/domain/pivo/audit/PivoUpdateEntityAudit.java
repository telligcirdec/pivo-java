package net.gillets.pivo.domain.pivo.audit;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetTime;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable 
@Data
public class PivoUpdateEntityAudit implements Serializable {
    
    private static final long serialVersionUID = -6338869550224013045L;

    protected LocalDate updatedAtLocalDate;
    protected OffsetTime updatedAtOffsetTime;

}
