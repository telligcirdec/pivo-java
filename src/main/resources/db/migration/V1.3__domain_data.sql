INSERT INTO ${flyway:defaultSchema}.pivo_entity_type(pivo_entity_type) VALUES ('domain');

CREATE TABLE ${flyway:defaultSchema}.${data_prefix}domain ( 
	pivo_entity_uuid              uuid NOT NULL ,
	domain_name                     varchar(50)  NOT NULL ,
	CONSTRAINT pk_domain_data_pivo_pivo_entity_data_uuid PRIMARY KEY ( pivo_entity_uuid ),
	CONSTRAINT fk_domain_data_pivo_pivo_entity_data_uuid FOREIGN KEY ( pivo_entity_uuid ) REFERENCES ${flyway:defaultSchema}.pivo_entity( pivo_entity_uuid )
 );