/**
 * Schema creation for CarShop
 *
 */

CREATE SEQUENCE car_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
END;

CREATE TABLE "car" (
    "id"                         BIGINT DEFAULT nextval('car_id_seq' :: REGCLASS) NOT NULL,
    "uuid"                       UUID                                                        NOT NULL DEFAULT uuid_generate_v4(),
    "create_date"                TIMESTAMP WITH TIME ZONE                                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "update_date"                TIMESTAMP WITH TIME ZONE                                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "version"                    BIGINT                                                      NOT NULL,
    "make"                       VARCHAR(200)                                                NOT NULL,
    "model"                      VARCHAR(200)                                                NOT NULL,
    "year"                       BIGINT                                                      NOT NULL,

    PRIMARY KEY ("id")
);
END;

CREATE UNIQUE INDEX "car_idx01"
    ON "car" ("uuid");

CREATE INDEX "car_idx02"
    ON "car" ("make", "model", "year");


------------------------------------------------------------------------------------------------------------------------


CREATE SEQUENCE garage_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
END;

CREATE TABLE "garage" (
    "id"                         BIGINT DEFAULT nextval('garage_id_seq' :: REGCLASS) NOT NULL,
    "uuid"                       UUID                                                NOT NULL DEFAULT uuid_generate_v4(),
    "create_date"                TIMESTAMP WITH TIME ZONE                            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "update_date"                TIMESTAMP WITH TIME ZONE                            NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "version"                    BIGINT                                              NOT NULL,
    "status"                     VARCHAR(70)                                         NOT NULL DEFAULT 'FREE', -- FREE, OCCUPIED
    "door"                       BIGINT                                              NOT NULL,

    PRIMARY KEY ("id")
);
END;

CREATE UNIQUE INDEX "garage_idx01"
    ON "garage" ("uuid");

CREATE INDEX "garage_idx02"
    ON "garage" ("status");

CREATE INDEX "garage_idx03"
    ON "garage" ("door");


------------------------------------------------------------------------------------------------------------------------


CREATE SEQUENCE appointment_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
END;

CREATE TABLE "appointment" (
    "id"                          BIGINT DEFAULT nextval('appointment_id_seq' :: REGCLASS) NOT NULL,
    "uuid"                        UUID                                                        NOT NULL DEFAULT uuid_generate_v4(),
    "create_date"                 TIMESTAMP WITH TIME ZONE                                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "update_date"                 TIMESTAMP WITH TIME ZONE                                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "version"                     BIGINT                                                      NOT NULL,
    "car_id"                      BIGINT                                                      NOT NULL,
    "garage_id"                   BIGINT                                                      NOT NULL,
    "schedule_date"               TIMESTAMP WITH TIME ZONE                                    NOT NULL,

    PRIMARY KEY ("id")
);
END;

CREATE UNIQUE INDEX "appointment_idx01"
    ON "appointment" ("uuid");

CREATE INDEX "appointment_idx02"
    ON "appointment" ("car_id");

CREATE INDEX "appointment_idx03"
    ON "appointment" ("garage_id");


------------------------------------------------------------------------------------------------------------------------


CREATE SEQUENCE service_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;
END;

CREATE TABLE "service" (
    "id"                          BIGINT DEFAULT nextval('service_id_seq' :: REGCLASS) NOT NULL,
    "uuid"                        UUID                                                        NOT NULL DEFAULT uuid_generate_v4(),
    "create_date"                 TIMESTAMP WITH TIME ZONE                                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "update_date"                 TIMESTAMP WITH TIME ZONE                                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "version"                     BIGINT                                                      NOT NULL,
    "appointment_id"              BIGINT                                                      NOT NULL,
    "type"                        VARCHAR(200)                                                NOT NULL, -- CHECK_TIRES, CLEAN_VEHICLE, CHANGE_OIL
    "status"                      VARCHAR(70)                                                 NOT NULL DEFAULT 'PENDING', -- PENDING, DONE, CANCELLED

    PRIMARY KEY ("id")
);
END;

CREATE UNIQUE INDEX "service_idx01"
    ON "service" ("uuid");

CREATE INDEX "service_idx02"
    ON "service" ("appointment_id");

CREATE INDEX "service_idx03"
    ON "service" ("status");
