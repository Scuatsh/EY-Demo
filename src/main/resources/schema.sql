CREATE USER IF NOT EXISTS "SA" SALT 'ffb639ce38318af5' HASH '6c940bda6cce6dd9846346ddbd70b9f1a82b2801005a80b9a0f16f3171ca2180' ADMIN;          
CREATE MEMORY TABLE "PUBLIC"."PHONES"(
    "ID" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "CITYCODE" INTEGER,
    "CONTRYCODE" INTEGER,
    "NUMBER" INTEGER
);      
ALTER TABLE "PUBLIC"."PHONES" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_8" PRIMARY KEY("ID");        
 
CREATE MEMORY TABLE "PUBLIC"."USERS"(
    "ID" UUID NOT NULL,
    "CREATED" TIMESTAMP(6),
    "EMAIL" CHARACTER VARYING(255),
    "ISACTIVE" BOOLEAN,
    "LAST_LOGIN" TIMESTAMP(6),
    "MODIFIED" TIMESTAMP(6),
    "NAME" CHARACTER VARYING(255),
    "PASSWORD" CHARACTER VARYING(255),
    "TOKEN" CHARACTER VARYING(255)
);              
ALTER TABLE "PUBLIC"."USERS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_4" PRIMARY KEY("ID");         
  
CREATE MEMORY TABLE "PUBLIC"."USERS_PHONES"(
    "USER_ID" UUID NOT NULL,
    "PHONES_ID" BIGINT NOT NULL
); 
            
ALTER TABLE "PUBLIC"."USERS_PHONES" ADD CONSTRAINT "PUBLIC"."UK_8EM9X6DVSSPRISQGHWWE0DGEE" UNIQUE("PHONES_ID");
ALTER TABLE "PUBLIC"."USERS_PHONES" ADD CONSTRAINT "PUBLIC"."FK36DPKFHW8EHRYMJANEBPNKGML" FOREIGN KEY("USER_ID") REFERENCES "PUBLIC"."USERS"("ID") NOCHECK;    
ALTER TABLE "PUBLIC"."USERS_PHONES" ADD CONSTRAINT "PUBLIC"."FK2H8LRW8NCHQT19WJ7LQGDJFPO" FOREIGN KEY("PHONES_ID") REFERENCES "PUBLIC"."PHONES"("ID") NOCHECK; 