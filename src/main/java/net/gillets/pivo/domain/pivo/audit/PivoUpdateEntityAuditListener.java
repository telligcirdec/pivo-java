package net.gillets.pivo.domain.pivo.audit;

import java.time.LocalDate;
import java.time.OffsetTime;

import javax.persistence.PreUpdate;

public class PivoUpdateEntityAuditListener {

    @PreUpdate
    public void setUpdatedOn(PivoUpdateEntityAuditable auditable) {
        PivoUpdateEntityAudit audit = auditable.getPivoUpdateEntityAudit();

        audit.setUpdatedAtLocalDate(LocalDate.now());
        audit.setUpdatedAtOffsetTime(OffsetTime.now());
    }

}
