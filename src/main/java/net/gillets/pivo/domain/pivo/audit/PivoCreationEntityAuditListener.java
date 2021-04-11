package net.gillets.pivo.domain.pivo.audit;

import java.time.LocalDate;
import java.time.OffsetTime;

import javax.persistence.PrePersist;

public class PivoCreationEntityAuditListener {

    @PrePersist
    public void setCreatedOn(PivoCreationEntityAuditable auditable) {
        PivoCreationEntityAudit audit = auditable.getPivoCreationEntityAudit();

        if (audit == null) {
            audit = new PivoCreationEntityAudit();
            auditable.setPivoCreationEntityAudit(audit);
        }

        audit.setCreatedAtLocalDate(LocalDate.now());
        audit.setCreatedAtOffsetTime(OffsetTime.now());
    }
}
