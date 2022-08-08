BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "dop" (
	"_id"	INTEGER NOT NULL UNIQUE,
	"Akkum"	TEXT,
	"Controller"	TEXT,
	"Invertor"	TEXT,
	"Price"	TEXT,
	"KolovoDop"	TEXT,
	"idkomplect"	TEXT,
	PRIMARY KEY("_id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "service" (
	"_id"	INTEGER NOT NULL UNIQUE,
	"NameService"	TEXT NOT NULL,
	"StaffService"	TEXT NOT NULL,
	"AddressService"	TEXT NOT NULL,
	"PhoneService"	TEXT NOT NULL,
	"MailService"	TEXT NOT NULL,
	PRIMARY KEY("_id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "poslug" (
	"_id"	INTEGER NOT NULL UNIQUE,
	"TypePoslug"	TEXT,
	"PricePoslug"	TEXT,
	"TimeWork"	TEXT,
	"idServices"	TEXT,
	PRIMARY KEY("_id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "client" (
	"_id"	INTEGER NOT NULL UNIQUE,
	"NameClient"	TEXT NOT NULL,
	"SecondNameClient"	TEXT NOT NULL,
	"PatronymicClient"	TEXT NOT NULL,
	"PhoneClient"	TEXT NOT NULL,
	"AddressClient"	TEXT NOT NULL,
	"MailClient"	TEXT NOT NULL,
	"Password"	INTEGER NOT NULL,
	PRIMARY KEY("_id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "garant" (
	"_id"	INTEGER NOT NULL UNIQUE,
	"TypeGarant"	TEXT NOT NULL,
	"TimeGarant"	TEXT NOT NULL,
	PRIMARY KEY("_id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "panel" (
	"_id"	INTEGER NOT NULL UNIQUE,
	"NamePanel"	TEXT,
	"PricePanel"	TEXT,
	"SizePanel"	TEXT,
	"TypePanel"	TEXT,
	"KKDPanel"	TEXT,
	"SpecificPanel"	TEXT,
	"IdPanel"	TEXT,
	"KolovoPanel"	TEXT,
	PRIMARY KEY("_id" AUTOINCREMENT)
);
INSERT INTO "dop" VALUES (1,'gdg','dfgd','fgdfg','6456','dfgdfg','dfg');
INSERT INTO "service" VALUES (1,'csczx','cxzczx','cxzczx','czxczxc','zxcxz');
INSERT INTO "poslug" VALUES (1,'bnvb','345','vbnbv','bvnv');
INSERT INTO "client" VALUES (1,'Ярослав','Рева','Евгениевич','0983580772','ИванаСирка 24','egouscz@gmail.com',1234);
INSERT INTO "garant" VALUES (1,'vxcv','vxcv');
INSERT INTO "panel" VALUES (1,'f','43','dsfsd','fsdfsd','fsdf','sdfsd','323','4');
COMMIT;
