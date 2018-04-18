DROP TABLE IF EXISTS APPLICATIONS CASCADE;
CREATE TABLE IF NOT EXISTS APPLICATIONS(
   ID   SERIAL PRIMARY KEY       ,
   APPLICATION_NAME        CHAR(50) NOT NULL UNIQUE 
);
ALTER SEQUENCE applications_id_seq RESTART WITH 60;

INSERT INTO  applications (  id ,   application_name ) VALUES
(55, 'AcceptableBO'),
(23, 'APEXMenu'),
(34, 'ApparelSizeCategory'),
(20, 'AutoCurveForecast'),
(16, 'BrandParents'),
(17, 'Brands'),
(19, 'BusinessRuleMaintenance'),
(10, 'ColorParent'),
(1, 'ColorParentMatches'),
(2, 'Colors'),
(3, 'CoopPotential'),
(28, 'CostMasterMaintenance'),
(32, 'Departments'),
(33, 'Discounts'),
(4, 'Fabric'),
(5, 'FabricTexture'),
(12, 'HeelHeightRange'),
(11, 'HeelHeights'),
(25, 'HelpdeskInventory'),
(15, 'HeroColor'),
(35, 'ImageSeqs'),
(36, 'ImageSizes'),
(8, 'ItemColorActual'),
(37, 'Itemgender'),
(14, 'ItemLifestyle'),
(13, 'ItemSubstyles'),
(38, 'ItemXMatrixes'),
(39, 'ItemXParent'),
(40, 'ItemXParentMatches'),
(41, 'ItemXS'),
(42, 'ItemXSumSizes'),
(43, 'ItemYMatrixes'),
(44, 'ItemYParent'),
(53, 'ItemYParentMatches'),
(45, 'ItemYS'),
(46, 'ItemYSumSize'),
(31, 'ListControl-1.2.1'),
(6, 'MarkDownRules'),
(47, 'MatrixLayouts'),
(48, 'MatrixTypes'),
(9, 'OfferExclusions'),
(24, 'OrderTally'),
(27, 'PhotoMaster'),
(7, 'PrimaryUpper'),
(22, 'ProductionMenu'),
(29, 'PromotionalCostsMaintenance'),
(26, 'Run Control'),
(49, 'SalesVentures'),
(50, 'SourceCodeTypes'),
(51, 'StatusCodes'),
(54, 'SwatchSizes'),
(18, 'SylogsReports'),
(30, 'TestMasterUpdate'),
(52, 'TitleTypes'),
(56, 'UPC DATA'),
(21, 'UpdateNetOrigFrcst');

DROP TABLE IF EXISTS   application_groups  CASCADE;
CREATE TABLE IF NOT EXISTS   application_groups  (
    id  SERIAL PRIMARY KEY,
    application_id  int  NOT NULL,
    group_id  int  NOT NULL)  ;


	DROP TABLE IF EXISTS   application_groups ;
	CREATE TABLE IF NOT EXISTS   application_groups  (
	    id  SERIAL PRIMARY KEY,
	    application_id  int  NOT NULL,
	    group_id  int  NOT NULL,
	CONSTRAINT group_app  UNIQUE  (  group_id ,  application_id ) 
	)  ;
	CREATE INDEX application_groups_app_id_idx on application_groups (application_id);
	CREATE INDEX application_groups_group_id_idx on application_groups (group_id);

    ALTER SEQUENCE application_groups_id_seq RESTART WITH 200;

	INSERT INTO  application_groups ( id, application_id,group_id) VALUES
	(130, 26, 1),
	(131, 27, 1),
	(132, 28, 1),
	(133, 29, 1),
	(134, 30, 1),
	(135, 31, 1),
	(121, 9, 2),
	(160, 56, 2),
	(111, 16, 3),
	(112, 17, 3),
	(127, 16, 4),
	(128, 17, 4),
	(161, 1, 5),
	(129, 15, 5),
	(171, 19, 5),
	(113, 1, 6),
	(114, 2, 6),
	(115, 3, 6),
	(116, 4, 6),
	(117, 5, 6),
	(118, 6, 6),
	(119, 7, 6),
	(120, 8, 6),
	(105, 9, 6),
	(106, 10, 6),
	(107, 11, 6),
	(108, 12, 6),
	(109, 13, 6),
	(110, 14, 6),
	(168, 19, 6),
	(122, 10, 7),
	(123, 11, 7),
	(124, 12, 7),
	(125, 13, 7),
	(126, 14, 7),
	(136, 32, 7),
	(137, 33, 7),
	(138, 34, 7),
	(139, 35, 7),
	(140, 36, 7),
	(141, 37, 7),
	(142, 38, 7),
	(143, 39, 7),
	(144, 40, 7),
	(145, 41, 7),
	(146, 42, 7),
	(147, 43, 7),
	(148, 44, 7),
	(149, 45, 7),
	(150, 46, 7),
	(151, 47, 7),
	(152, 48, 7),
	(153, 49, 7),
	(154, 50, 7),
	(155, 51, 7),
	(156, 52, 7),
	(157, 53, 7),
	(158, 54, 7),
	(159, 55, 7),
	(97, 1, 8),
	(98, 2, 8),
	(99, 3, 8),
	(100, 4, 8),
	(101, 5, 8),
	(102, 6, 8),
	(103, 7, 8),
	(104, 8, 8),
	(170, 19, 9);
	
	DROP TABLE IF EXISTS   groups  CASCADE;
	CREATE TABLE IF NOT EXISTS   groups  (
	   ID   SERIAL PRIMARY KEY ,
	   group_name CHAR(250) NOT NULL UNIQUE ,
  
	CONSTRAINT group_name  UNIQUE  (  group_name) 
	) ;

	ALTER SEQUENCE  groups_id_seq RESTART WITH 20;
	--
	-- Dumping data for table  'groups'
	--

	INSERT INTO   groups  (  id ,   group_name ) VALUES
	(8, 'BrandEdit'),
	(6, 'BrandUsers'),
	(9, 'ExclusionsUsers'),
	(5, 'HeroColor'),
	(3, 'ItemUsers'),
	(4, 'Merchandisers'),
	(1, 'PowerBuilder'),
	(7, 'PowerUsers'),
	(2, 'UPC');


	DROP TABLE IF EXISTS  group_assignments CASCADE;
	CREATE TABLE IF NOT EXISTS  group_assignments (
	   id  SERIAL PRIMARY KEY,
           user_id int DEFAULT 5, 
	   group_id  int  NOT NULL ,
 	   CONSTRAINT group_user  UNIQUE  (  group_id ,  user_id ) 
  
	  ) ;
	CREATE INDEX group_assignments_user_id_idx on group_assignments (user_id);
	CREATE INDEX group_assignments_group_id_idx on group_assignments (group_id);
	--
	-- Dumping data for table  group_assignments
	--

        INSERT INTO group_assignments (user_id, group_id) VALUES (1, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (1, 4);
        INSERT INTO group_assignments (user_id, group_id) VALUES (1, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (2, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (2, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (2, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (2, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (2, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (2, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (3, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (3, 5);
        INSERT INTO group_assignments (user_id, group_id) VALUES (3, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (4, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (5, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (6, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (6, 4);
        INSERT INTO group_assignments (user_id, group_id) VALUES (6, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (7, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (8, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (8, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (8, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (8, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (9, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (9, 4);
        INSERT INTO group_assignments (user_id, group_id) VALUES (9, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (10, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (11, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (11, 5);
        INSERT INTO group_assignments (user_id, group_id) VALUES (11, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (12, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (12, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (12, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (12, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (12, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (13, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (13, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (13, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (13, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (13, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (14, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (14, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (14, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (14, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (14, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (14, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (15, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (16, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (17, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (17, 4);
        INSERT INTO group_assignments (user_id, group_id) VALUES (17, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (18, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (19, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (19, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (19, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (19, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (20, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (20, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (21, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (21, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (22, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (22, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (23, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (23, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (23, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (23, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (23, 5);
        INSERT INTO group_assignments (user_id, group_id) VALUES (23, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (23, 2);
        INSERT INTO group_assignments (user_id, group_id) VALUES (24, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (24, 4);
        INSERT INTO group_assignments (user_id, group_id) VALUES (24, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (25, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (25, 4);
        INSERT INTO group_assignments (user_id, group_id) VALUES (25, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (26, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (27, 2);
        INSERT INTO group_assignments (user_id, group_id) VALUES (28, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (28, 5);
        INSERT INTO group_assignments (user_id, group_id) VALUES (28, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (29, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (29, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (29, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (29, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (30, 2);
        INSERT INTO group_assignments (user_id, group_id) VALUES (30, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (31, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (32, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (32, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (32, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (32, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (32, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (32, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (33, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (34, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (35, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (35, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (35, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (36, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (37, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (37, 4);
        INSERT INTO group_assignments (user_id, group_id) VALUES (37, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (38, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (39, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (39, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (39, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (39, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (39, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (39, 1);
        INSERT INTO group_assignments (user_id, group_id) VALUES (40, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (40, 8);
        INSERT INTO group_assignments (user_id, group_id) VALUES (40, 7);
        INSERT INTO group_assignments (user_id, group_id) VALUES (40, 6);
        INSERT INTO group_assignments (user_id, group_id) VALUES (40, 3);
        INSERT INTO group_assignments (user_id, group_id) VALUES (41, 9);
        INSERT INTO group_assignments (user_id, group_id) VALUES (41, 5);
        INSERT INTO group_assignments (user_id, group_id) VALUES (41, 3);

	ALTER SEQUENCE group_assignments_id_seq RESTART WITH 125;
	 
	DROP TABLE IF EXISTS  users CASCADE;
	CREATE TABLE IF NOT EXISTS  users (
	   user_id SERIAL PRIMARY KEY,
           login char(20) NOT NULL,
	   username char(20) DEFAULT NULL 
	);

	--
	-- Dumping data for table  'users'
	--

	INSERT INTO  users (user_id, login,  username) VALUES
	(1,'alb', 'April Jones'),
	(2,'ale', 'Angie Smith'),
	(3,'alm', 'Andrea Thompson'),
	(4,'ayb', 'Alissa Alpha'),
	(5,'blz', 'Brenda Beta'),
	(6,'brw', 'Billy Gamma'),
	(7,'cad', 'Cyndy Delta'),
	(8,'chs', 'Christina Epsilson'),
	(9,'cjj', 'Carol Japell'),
	(10,'clh', 'Carrie Hurley-Marker'),
	(11,'cmb', 'Cathy Becker'),
	(12,'dcj', 'Dawn Powell'),
	(13,'dgk', 'Dave Buster'),
	(14,'djb', 'Dave Blandingsworth'),
	(15,'djl', 'Debra Lawson'),
	(16,'dra', 'Dan Alpaca'),
	(17,'dxj', 'Drew Jewison'),
	(18,'eas', 'Liz Samuels'),
	(19,'gas', 'Gen Spaeth'),
	(20,'gaw', 'George Wolefenstein'),
	(21,'jhm', 'Jane Maryson'),
	(22,'jms', 'Janie Bettysborth'),
	(23,'jpd', 'Joel Deign'),
	(24,'jrk', 'John Kip'),
	(25,'kag', 'Kim Brassworkth'),
	(26,'kjc', 'Kathy Constantine'),
	(27,'lmb', 'Lisa Blossom'),
	(28,'lmn', 'Lana Nebbish'),
	(29,'lmz', 'Luann Zap'),
	(30,'lsb', 'Lucille Betson'),
	(31,'lxz', 'Lynn Ziglier'),
	(32,'lyb', 'Logan Boson'),
	(33,'mab', 'Marietta Ballon'),
	(34,'mkm', 'Mary Mattson'),
	(35,'mnj', 'Michelle Johnson'),
	(36,'oper', 'Olga PE Robeson'),
	(37,'pmg', 'Paula Ganymede'),
	(38,'sgrana', 'Sara Grandstand'),
	(39,'slg', 'Sam Garfnarf'),
	(40,'smg', 'Sue Glendale'),
	(41,'srm', 'Shannon Missouri');


        ALTER SEQUENCE users_user_id_seq RESTART WITH 55;

	--
	-- Constraints for table  'application_groups'
	--
	ALTER TABLE   application_groups 
	  ADD CONSTRAINT   application_groups_ibfk_1  FOREIGN KEY ( group_id ) REFERENCES   groups  (  id ),
	  ADD CONSTRAINT   application_groups_ibfk_2  FOREIGN KEY ( application_id ) REFERENCES   applications  ( id );

	--
	-- Constraints for table  'group_assignments'
	--
	ALTER TABLE   group_assignments 
	  ADD CONSTRAINT   group_assignments_ibfk_1  FOREIGN KEY ( group_id ) REFERENCES   groups  (  id ),
	  ADD CONSTRAINT   group_assignments_ibfk_2  FOREIGN KEY ( user_id ) REFERENCES   users  ( user_id ); 