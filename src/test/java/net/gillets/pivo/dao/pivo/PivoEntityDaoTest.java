package net.gillets.pivo.dao.pivo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.time.OffsetTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import net.gillets.pivo.domain.pivo.PivoEntity;
import net.gillets.pivo.domain.pivo.audit.PivoCreationEntityAudit;

public abstract class PivoEntityDaoTest<DAO extends PivoEntityDao<ENTITY, DATA>, ENTITY extends PivoEntity<ENTITY, DATA>, DATA extends Serializable> {

    private DAO pivoEntityDao;

    protected PivoEntityDaoTest(DAO pivoEntityDao) {
        this.pivoEntityDao = pivoEntityDao;
    }

    @Test
    public void testSaveEntityWithData() {

        // Init entity and data
        DATA dataToSave = getData(false);
        ENTITY savedEntity = pivoEntityDao.save(getEntityToSave(dataToSave));

        // Assertions
        checkAllNewEnableSavedEntity(savedEntity, dataToSave);
    }

    @Test
    public void testUpdateEntityWithSameData() {

        /** First save a new entity with enable=true */

        // Init entity and data
        DATA dataToSave = getData(false);
        ENTITY savedEntity = pivoEntityDao.save(getEntityToSave(dataToSave));

        // Assertions
        checkAllNewEnableSavedEntity(savedEntity, dataToSave);

        /** Second disable entity and update it */
        savedEntity.setEnable(false);
        ENTITY updatedEntity = pivoEntityDao.save(savedEntity);

        // Assertions
        checkUpdatedEntityWithoutPreviousState(updatedEntity, dataToSave, false, savedEntity.getUuid(), 1L,
                savedEntity.getPivoCreationEntityAudit());
    }

    @Test
    public void testUpdateEntityWithDataUpdate() {

        /** First save a new entity with enable=true */

        // Init entity and data
        DATA dataToSave = getData(false);
        ENTITY savedEntity = pivoEntityDao.save(getEntityToSave(dataToSave));

        // Assertions
        checkAllNewEnableSavedEntity(savedEntity, dataToSave);

        /** Second disable entity and update it */
        DATA dataToUpdate = getData(false);
        savedEntity.setData(dataToUpdate);
        ENTITY updatedEntity = pivoEntityDao.save(savedEntity);

        // Assertions
        checkUpdatedEntityWithoutPreviousState(updatedEntity, dataToUpdate, true, savedEntity.getUuid(), 1L,
                savedEntity.getPivoCreationEntityAudit());
    }

    protected abstract Class<ENTITY> getEntityClass();

    protected abstract String getEntityTypeCode();

    protected abstract DATA getData(boolean testFailure);

    protected ENTITY getEntityToSave(DATA dataToSave) {
        ENTITY entityToSave = null;
        try {
            entityToSave = getEntityClass().getDeclaredConstructor().newInstance();
            entityToSave.setEnable(true);
            entityToSave.setData(dataToSave);

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return entityToSave;
    }

    /**
     * Check new enable entity saved value.
     * 
     * @param savedEntity Entity saved to check.
     * @param dataToSave  Data that should be insert.
     */
    private void checkAllNewEnableSavedEntity(ENTITY savedEntity, DATA dataToSave) {
        checkAllNewSavedEntity(savedEntity, dataToSave, true);
    }

    /**
     * Check new disable entity saved value.
     * 
     * @param savedEntity Entity saved to check.
     * @param dataToSave  Data that should be insert.
     */
    /*private void checkAllNewDisableSavedEntity(Optional<ENTITY> savedEntity, DATA dataToSave) {
        checkAllNewSavedEntity(savedEntity, dataToSave, false);
    }*/

    /**
     * Check new entity saved value.
     * 
     * @param savedEntity Entity saved to check.
     * @param dataToSave  Data that should be insert.
     * @param enable      Entity enable state.
     */
    private void checkAllNewSavedEntity(ENTITY savedEntity, DATA dataToSave, boolean enable) {
        // Check saved ENTITY not null
        assertThat(savedEntity).isNotNull();

        // Check all insert
        assertThat(savedEntity.getUuid()).isNotNull();
        assertThat(savedEntity.getVersion()).isZero();
        assertThat(savedEntity.getPivoUpdateEntityAudit()).isNull();
        assertThat(savedEntity.isEnable()).isEqualTo(enable);
        assertThat(savedEntity.getPivoCreationEntityAudit().getCreatedAtLocalDate()).isToday();
        assertThat(savedEntity.getPivoCreationEntityAudit().getCreatedAtOffsetTime()).isCloseTo(OffsetTime.now(),
                within(1, ChronoUnit.SECONDS));
        assertThat(savedEntity.getData()).isEqualTo(dataToSave);
    }

    private void checkUpdatedEntityWithoutPreviousState(ENTITY updatedEntity, DATA dataToUpdate,
            boolean enable, UUID expectedID, Long expectedVersion, PivoCreationEntityAudit pivoCreationEntityAudit) {
        // Check updated ENTITY not null
        assertThat(updatedEntity).isNotNull();

        // Check all update
        assertThat(updatedEntity.getUuid()).isEqualTo(expectedID);
        assertThat(updatedEntity.getVersion()).isEqualTo(expectedVersion);
        assertThat(updatedEntity.isEnable()).isEqualTo(enable);
        assertThat(updatedEntity.getPivoCreationEntityAudit().getCreatedAtLocalDate())
                .isEqualTo(pivoCreationEntityAudit.getCreatedAtLocalDate());
        assertThat(updatedEntity.getPivoCreationEntityAudit().getCreatedAtOffsetTime())
                .isEqualTo(pivoCreationEntityAudit.getCreatedAtOffsetTime());
        assertThat(updatedEntity.getPivoUpdateEntityAudit().getUpdatedAtLocalDate()).isToday();
        assertThat(updatedEntity.getPivoUpdateEntityAudit().getUpdatedAtOffsetTime()).isCloseTo(OffsetTime.now(),
                within(1, ChronoUnit.SECONDS));
        assertThat(updatedEntity.getData()).isEqualTo(dataToUpdate);
    }

}
