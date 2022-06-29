DROP
DATABASE IF EXISTS acleaneva;
go

CREATE
DATABASE acleaneva
CHARACTER SET "utf8"
COLLATE "utf8_unicode_ci";
go

use acleaneva

-- charset utf8 collate utf8_unicode_ci;
go
SHOW COLLATION LIKE 'utf8%';

-- group all objects under "schema1" for the user="dbowner"
CREATE
schema1 AUTHORIZATION dbowner;