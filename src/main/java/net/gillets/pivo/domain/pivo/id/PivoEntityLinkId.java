package net.gillets.pivo.domain.pivo.id;

import java.io.Serializable;

import lombok.Data;

@Data
public class PivoEntityLinkId implements Serializable {

    private static final long serialVersionUID = -5181233002663327716L;
    
    private String entityAUuid;
    private String entityBUuid;
    private String entityLinkTypeCode;

}