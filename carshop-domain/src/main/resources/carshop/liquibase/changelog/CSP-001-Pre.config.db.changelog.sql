/**
 * Schema Pre-configuration
 *
 */

CREATE EXTENSION IF NOT EXISTS pgcrypto; /* Enables encryption */

CREATE EXTENSION IF NOT EXISTS "uuid-ossp"; /* Creates UUID usage for db */

SET TIME ZONE 'America/Montreal';

SET TIMEZONE TO 'America/Montreal';

ALTER DATABASE carshop SET timezone TO 'America/Montreal'

