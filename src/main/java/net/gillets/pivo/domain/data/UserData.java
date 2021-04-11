package net.gillets.pivo.domain.data;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserData implements Serializable{

    private static final long serialVersionUID = 7603098810591054491L;

    private final String username;
    
}
