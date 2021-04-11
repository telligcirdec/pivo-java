package net.gillets.pivo.domain.pivo.audit;

public interface PivoUpdateEntityAuditable {

    PivoUpdateEntityAudit getPivoUpdateEntityAudit();

    void setPivoUpdateEntityAudit(PivoUpdateEntityAudit audit);
}
