--------------------------------------------------------
-- Archivo creado  - s�bado-mayo-12-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Type CLIENTE
--------------------------------------------------------

  CREATE OR REPLACE TYPE "ARAN"."CLIENTE" AS OBJECT 
(
NUMERO NUMBER(11),
NIF VARCHAR(12),
NOMBRE VARCHAR(45),
DIRECCION VARCHAR(80),
TELEFONO VARCHAR(15),
EMAIL VARCHAR(45)
);

/
--------------------------------------------------------
--  DDL for Type FACTURA
--------------------------------------------------------

  CREATE OR REPLACE TYPE "ARAN"."FACTURA" AS OBJECT
(
NUMERO VARCHAR(12),
FECHA DATE,
ESTADO VARCHAR(15),
CLIENTE NUMBER(11), 
CONCEPTO VARCHAR(180), 
TOTAL FLOAT
);

/
--------------------------------------------------------
--  DDL for Table CLIENTES
--------------------------------------------------------

  CREATE TABLE "ARAN"."CLIENTES" OF "ARAN"."CLIENTE" 
 OIDINDEX  ( PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ) 
 PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table FACTURAS
--------------------------------------------------------

  CREATE TABLE "ARAN"."FACTURAS" OF "ARAN"."FACTURA" 
 OIDINDEX  ( PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ) 
 PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into ARAN.CLIENTES
SET DEFINE OFF;
Insert into ARAN.CLIENTES (NUMERO,NIF,NOMBRE,DIRECCION,TELEFONO,EMAIL) values ('3','B676899','MITSHUBISHI ELECTRIC','ASTINTZE 6, DERIO','902456789','info@mitshubishi.com');
REM INSERTING into ARAN.FACTURAS
SET DEFINE OFF;
Insert into ARAN.FACTURAS (NUMERO,FECHA,ESTADO,CLIENTE,CONCEPTO,TOTAL) values ('11/18',to_date('12/05/18','DD/MM/RR'),'no pagada','3','Dise�o de cat�logos','450');
--------------------------------------------------------
--  Constraints for Table CLIENTES
--------------------------------------------------------

  ALTER TABLE "ARAN"."CLIENTES" ADD UNIQUE ("SYS_NC_OID$")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ARAN"."CLIENTES" ADD PRIMARY KEY ("NUMERO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table FACTURAS
--------------------------------------------------------

  ALTER TABLE "ARAN"."FACTURAS" ADD UNIQUE ("SYS_NC_OID$")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
  ALTER TABLE "ARAN"."FACTURAS" ADD PRIMARY KEY ("NUMERO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;