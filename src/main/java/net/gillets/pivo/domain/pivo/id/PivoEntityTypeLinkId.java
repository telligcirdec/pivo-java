package net.gillets.pivo.domain.pivo.id;

import java.io.Serializable;

import lombok.Data;

@Data
public class PivoEntityTypeLinkId implements Serializable {

    private static final long serialVersionUID = -2023951071681018490L;

    private String entityATypeCode;
    private String entityBTypeCode;
    private String entityLinkTypeCode;

}
