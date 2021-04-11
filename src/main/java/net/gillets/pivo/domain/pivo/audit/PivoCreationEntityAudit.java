package net.gillets.pivo.domain.pivo.audit;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetTime;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable 
@Data
public class PivoCreationEntityAudit implements Serializable {
    
    private static final long serialVersionUID = 2045909430118966774L;

    protected LocalDate createdAtLocalDate;
    protected OffsetTime createdAtOffsetTime;

}
