-- Create schema
CREATE SCHEMA IF NOT EXISTS ${flyway:defaultSchema};
CREATE SCHEMA IF NOT EXISTS ${audit_schema};

-- Create Tables
CREATE TABLE ${flyway:defaultSchema}.pivo_entity_link_type ( 
	pivo_entity_link_type           		varchar(50)  NOT NULL ,
	pivo_entity_link_type_enable    		boolean DEFAULT false ,
	pivo_entity_link_type_created_at_date   date DEFAULT NOW() NOT NULL ,
	pivo_entity_link_type_created_at_time   time with time zone DEFAULT NOW() NOT NULL  ,
	CONSTRAINT pk_pivo_entity_link_type_pivo_entity_link_type PRIMARY KEY ( pivo_entity_link_type )
 );

CREATE TABLE ${flyway:defaultSchema}.pivo_entity_type ( 
	pivo_entity_type                  varchar(50)  NOT NULL ,
	pivo_entity_type_enable           boolean DEFAULT false ,
	pivo_entity_type_created_at_date  date DEFAULT NOW() NOT NULL ,
	pivo_entity_type_created_at_time  time with time zone DEFAULT NOW() NOT NULL  ,
	CONSTRAINT pk_pivo_entity_type_pivo_entity_type PRIMARY KEY ( pivo_entity_type )
 );

CREATE TABLE ${flyway:defaultSchema}.pivo_entity_type_link ( 
	pivo_entity_a_type                     varchar(50)  NOT NULL ,
	pivo_entity_b_type                     varchar(50)  NOT NULL ,
	pivo_entity_link_type                  varchar(50)  NOT NULL ,
	pivo_entity_type_link_enable           boolean DEFAULT false ,
	pivo_entity_type_link_created_at_date  date DEFAULT NOW() NOT NULL ,
	pivo_entity_type_link_created_at_time  time with time zone DEFAULT NOW() NOT NULL , 
	CONSTRAINT idx_pivo_entity_type_link PRIMARY KEY ( pivo_entity_a_type, pivo_entity_b_type, pivo_entity_link_type ),
	CONSTRAINT fk_pivo_entity_type_link_pivo_entity_a_type FOREIGN KEY ( pivo_entity_a_type ) REFERENCES ${flyway:defaultSchema}.pivo_entity_type( pivo_entity_type )   ,
	CONSTRAINT fk_pivo_entity_type_link_pivo_entity_b_type FOREIGN KEY ( pivo_entity_b_type ) REFERENCES ${flyway:defaultSchema}.pivo_entity_type( pivo_entity_type )   ,
	CONSTRAINT fk_pivo_entity_type_link_pivo_entity_link_type FOREIGN KEY ( pivo_entity_link_type ) REFERENCES ${flyway:defaultSchema}.pivo_entity_link_type( pivo_entity_link_type )   
 );

CREATE TABLE ${flyway:defaultSchema}.pivo_entity ( 
	pivo_entity_uuid              uuid DEFAULT ${uuid_default_value} NOT NULL ,
	pivo_entity_type              varchar(50)  NOT NULL ,
	pivo_entity_enable            boolean DEFAULT false ,
	pivo_entity_created_at_date   date DEFAULT NOW() NOT NULL  ,
	pivo_entity_created_at_time   time with time zone DEFAULT NOW() NOT NULL  ,     
	pivo_entity_updated_at_date   date ,
	pivo_entity_updated_at_time   time with time zone ,     
	pivo_entity_version           bigint DEFAULT 0 NOT NULL ,
	CONSTRAINT pk_pivo_entity_pivo_entity_uuid PRIMARY KEY ( pivo_entity_uuid ),
	CONSTRAINT fk_pivo_entity_pivo_entity_type FOREIGN KEY ( pivo_entity_type ) REFERENCES ${flyway:defaultSchema}.pivo_entity_type( pivo_entity_type )   
 );

CREATE TABLE ${flyway:defaultSchema}.pivo_entity_link ( 
	pivo_entity_a_uuid                uuid  NOT NULL ,
	pivo_entity_b_uuid                uuid  NOT NULL ,
	pivo_entity_link_type             varchar(50)  NOT NULL ,
	pivo_entity_link_enable           boolean DEFAULT false ,
	pivo_entity_link_created_at_date  date DEFAULT NOW() NOT NULL  ,
	pivo_entity_link_created_at_time  time with time zone DEFAULT NOW() NOT NULL  , 
	CONSTRAINT pk_pivo_entity_link_pivo_entity_a_id PRIMARY KEY ( pivo_entity_a_uuid, pivo_entity_b_uuid, pivo_entity_link_type ),
	CONSTRAINT fk_pivo_entity_a_link_pivo_entity FOREIGN KEY ( pivo_entity_a_uuid ) REFERENCES ${flyway:defaultSchema}.pivo_entity( pivo_entity_uuid )   ,
	CONSTRAINT fk_pivo_entity_b_link_pivo_entity FOREIGN KEY ( pivo_entity_b_uuid ) REFERENCES ${flyway:defaultSchema}.pivo_entity( pivo_entity_uuid )   ,
	CONSTRAINT fk_pivo_entity_link_pivo_entity_link_type FOREIGN KEY ( pivo_entity_link_type ) REFERENCES ${flyway:defaultSchema}.pivo_entity_link_type( pivo_entity_link_type )   
 );