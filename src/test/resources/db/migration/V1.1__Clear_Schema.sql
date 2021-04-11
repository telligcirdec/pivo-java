-- Drop constraints pivo_entity_link for testing
ALTER TABLE IF EXISTS pivo_entity_link DROP CONSTRAINT IF EXISTS fk_pivo_entity_a_link_pivo_entity;
ALTER TABLE IF EXISTS pivo_entity_link DROP CONSTRAINT IF EXISTS fk_pivo_entity_b_link_pivo_entity;
ALTER TABLE IF EXISTS pivo_entity_link DROP CONSTRAINT IF EXISTS fk_pivo_entity_link_pivo_entity_link_type;

-- Drop constraints pivo_entity for testing
ALTER TABLE IF EXISTS pivo_entity DROP CONSTRAINT IF EXISTS fk_pivo_entity_pivo_entity_type;

-- Drop constraints pivo_entity_type_link for testing
ALTER TABLE IF EXISTS pivo_entity_type_link DROP CONSTRAINT IF EXISTS fk_pivo_entity_type_link_pivo_entity_a_type;
ALTER TABLE IF EXISTS pivo_entity_type_link DROP CONSTRAINT IF EXISTS fk_pivo_entity_type_link_pivo_entity_b_type;
ALTER TABLE IF EXISTS pivo_entity_type_link DROP CONSTRAINT IF EXISTS fk_pivo_entity_type_link_pivo_entity_link_type;

-- Drop Tables
DROP TABLE IF EXISTS pivo_entity_link;
DROP TABLE IF EXISTS pivo_entity;
DROP TABLE IF EXISTS pivo_entity_type_link;
DROP TABLE IF EXISTS pivo_entity_type;
DROP TABLE IF EXISTS pivo_entity_link_type;

TRUNCATE flyway_schema_history;