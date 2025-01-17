/* Header line. Object: applications. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `applications` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`version` varchar(255) NOT NULL,
	`instdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: attribute. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `attribute` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: attributeinstance. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `attributeinstance` (
	`id` varchar(255) NOT NULL,
	`attributesetinstance_id` varchar(255) NOT NULL,
	`attribute_id` varchar(255) NOT NULL,
	`value` varchar(255) default NULL,
	KEY `attinst_att` ( `attribute_id` ),
	KEY `attinst_set` ( `attributesetinstance_id` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: attributeset. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `attributeset` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: attributesetinstance. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `attributesetinstance` (
	`id` varchar(255) NOT NULL,
	`attributeset_id` varchar(255) NOT NULL,
	`description` varchar(255) default NULL,
	KEY `attsetinst_set` ( `attributeset_id` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: attributeuse. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `attributeuse` (
	`id` varchar(255) NOT NULL,
	`attributeset_id` varchar(255) NOT NULL,
	`attribute_id` varchar(255) NOT NULL,
	`lineno` int(11) default NULL,
	KEY `attuse_att` ( `attribute_id` ),
	UNIQUE INDEX `attuse_line` ( `attributeset_id`, `lineno` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: attributevalue. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `attributevalue` (
	`id` varchar(255) NOT NULL,
	`attribute_id` varchar(255) NOT NULL,
	`value` varchar(255) default NULL,
	KEY `attval_att` ( `attribute_id` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: breaks. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `breaks` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`visible` tinyint(1) NOT NULL default '1',
	`notes` varchar(255) default NULL,
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: categories. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `categories` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`parentid` varchar(255) default NULL,
	`image` mediumblob default NULL,
	`texttip` varchar(255) default NULL,
	`catshowname` smallint(6) NOT NULL default '1',
	`catorder` varchar(255) default NULL,
	KEY `categories_fk_1` ( `parentid` ),
	UNIQUE INDEX `categories_name_inx` ( `name` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: closedcash. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `closedcash` (
	`money` varchar(255) NOT NULL,
	`host` varchar(255) NOT NULL,
	`hostsequence` int(11) NOT NULL,
	`datestart` datetime NOT NULL,
	`dateend` datetime default NULL,
	`nosales` int(11) NOT NULL default '0',
	KEY `closedcash_inx_1` ( `datestart` ),
	UNIQUE INDEX `closedcash_inx_seq` ( `host`, `hostsequence` ),
	PRIMARY KEY  ( `money` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: csvimport. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `csvimport` (
	`id` varchar(255) NOT NULL,
	`rownumber` varchar(255) default NULL,
	`csverror` varchar(255) default NULL,
	`reference` varchar(255) default NULL,
	`code` varchar(255) default NULL,
	`name` varchar(255) default NULL,
	`pricebuy` double default NULL,
	`pricesell` double default NULL,
	`previousbuy` double default NULL,
	`previoussell` double default NULL,
	`category` varchar(255) default NULL,
	`tax` varchar(255) default NULL,
	`searchkey` varchar(255) default NULL,
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: customers. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `customers` (
	`id` varchar(255) NOT NULL,
	`searchkey` varchar(255) NOT NULL,
	`taxid` varchar(255) default NULL,
	`name` varchar(255) NOT NULL,
	`taxcategory` varchar(255) default NULL,
	`card` varchar(255) default NULL,
	`maxdebt` double NOT NULL default '0',
	`address` varchar(255) default NULL,
	`address2` varchar(255) default NULL,
	`postal` varchar(255) default NULL,
	`city` varchar(255) default NULL,
	`region` varchar(255) default NULL,
	`country` varchar(255) default NULL,
	`firstname` varchar(255) default NULL,
	`lastname` varchar(255) default NULL,
	`email` varchar(255) default NULL,
	`phone` varchar(255) default NULL,
	`phone2` varchar(255) default NULL,
	`fax` varchar(255) default NULL,
	`notes` varchar(255) default NULL,
	`visible` bit(1) NOT NULL default b'1',
	`curdate` datetime default NULL,
	`curdebt` double default '0',
	`image` mediumblob default NULL,
	`isvip` bit(1) NOT NULL default b'0',
	`discount` double default '0',
	`memodate` datetime default '2000-01-01 00:00:01',
        `taxid_type` varchar(90) not null,
	KEY `customers_card_inx` ( `card` ),
	KEY `customers_name_inx` ( `name` ),
	UNIQUE INDEX `customers_skey_inx` ( `searchkey` ),
	KEY `customers_taxcat` ( `taxcategory` ),
	KEY `customers_taxid_inx` ( `taxid` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: draweropened. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `draweropened` (
	`opendate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`name` varchar(255) default NULL,
	`ticketid` varchar(255) default NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: floors. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `floors` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`image` mediumblob default NULL,
	UNIQUE INDEX `floors_name_inx` ( `name` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: leaves. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `leaves` (
	`id` varchar(255) NOT NULL,
	`pplid` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`startdate` datetime NOT NULL,
	`enddate` datetime NOT NULL,
	`notes` varchar(255) default NULL,
	KEY `leaves_pplid` ( `pplid` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: lineremoved. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `lineremoved` (
	`removeddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`name` varchar(255) default NULL,
	`ticketid` varchar(255) default NULL,
	`productid` varchar(255) default NULL,
	`productname` varchar(255) default NULL,
	`units` double NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: locations. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `locations` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`address` varchar(255) default NULL,
	UNIQUE INDEX `locations_name_inx` ( `name` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: moorers. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `moorers` (
	`vesselname` varchar(255) default NULL,
	`size` int(11) default NULL,
	`days` int(11) default NULL,
	`power` bit(1) NOT NULL default b'0'
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS `orders` (
    `id` MEDIUMINT NOT NULL AUTO_INCREMENT,
    `orderid` varchar(50) DEFAULT NULL,
    `qty` int(11) DEFAULT '1',
    `details` varchar(255) DEFAULT NULL,
    `attributes` varchar(255) DEFAULT NULL,
    `notes` varchar(255) DEFAULT NULL,
    `ticketid` varchar(50) DEFAULT NULL,
    `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `displayid` int(11) DEFAULT '1',
    `auxiliary` int(11) DEFAULT NULL,
    `completetime` timestamp NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: payments. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `payments` (
	`id` varchar(255) NOT NULL,
	`receipt` varchar(255) NOT NULL,
	`payment` varchar(255) NOT NULL,
	`total` double NOT NULL default '0',
	`tip` double default '0',
	`transid` varchar(255) default NULL,
	`isprocessed` bit(1) default b'0',
	`returnmsg` mediumblob default NULL,
	`notes` varchar(255) default NULL,
	`tendered` double default NULL,
	`cardname` varchar(255) default NULL,
        `voucher` varchar(255) default NULL,
	KEY `payments_fk_receipt` ( `receipt` ),
	KEY `payments_inx_1` ( `payment` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: people. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `people` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`apppassword` varchar(255) default NULL,
	`card` varchar(255) default NULL,
	`role` varchar(255) NOT NULL,
	`visible` bit(1) NOT NULL,
	`image` mediumblob default NULL,
	KEY `people_card_inx` ( `card` ),
	KEY `people_fk_1` ( `role` ),
	UNIQUE INDEX `people_name_inx` ( `name` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: pickup_number. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `pickup_number` (
	`id` int(11) NOT NULL default '0'
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: places. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `places` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`seats` varchar(6) NOT NULL DEFAULT '1',
	`x` int(11) NOT NULL,
	`y` int(11) NOT NULL,
	`floor` varchar(255) NOT NULL,
	`customer` varchar(255) default NULL,
	`waiter` varchar(255) default NULL,
	`ticketid` varchar(255) default NULL,
	`tablemoved` smallint(6) NOT NULL default '0',
	`width` int(11) NOT NULL,
	`height` int(11) NOT NULL,
    `guests` int(11) DEFAULT 0,
    `occupied` datetime DEFAULT NULL,
	UNIQUE INDEX `places_name_inx` ( `name` ),
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: products. Script date: 02/04/2016 10:53:00. */
CREATE TABLE `products` (
	`id` varchar(255) NOT NULL,
	`reference` varchar(255) NOT NULL,
	`code` varchar(255) NOT NULL,
	`codetype` varchar(255) default NULL,
	`name` varchar(255) NOT NULL,
	`pricebuy` double NOT NULL default '0',
	`pricesell` double NOT NULL default '0',
	`category` varchar(255) NOT NULL,
	`taxcat` varchar(255) NOT NULL,
	`attributeset_id` varchar(255) default NULL,
	`stockcost` double NOT NULL default '0',
	`stockvolume` double NOT NULL default '0',
	`image` mediumblob default NULL,
	`iscom` bit(1) NOT NULL default b'0',
	`isscale` bit(1) NOT NULL default b'0',
	`isconstant` bit(1) NOT NULL default b'0',
	`printkb` bit(1) NOT NULL default b'0',
	`sendstatus` bit(1) NOT NULL default b'0',
	`isservice` bit(1) NOT NULL default b'0',
	`attributes` mediumblob default NULL,
	`display` varchar(255) default NULL,
	`isvprice` smallint(6) NOT NULL default '0',
	`isverpatrib` smallint(6) NOT NULL default '0',
	`texttip` varchar(255) default NULL,
	`warranty` smallint(6) NOT NULL default '0',
	`stockunits` double NOT NULL default '0',
	`printto` varchar(255) default '1',
	`supplier` varchar(255) default NULL,
        `uom` varchar(255) default '0',
	`memodate` datetime default '2018-01-01 00:00:01',

	PRIMARY KEY  ( `id` ),
	KEY `products_attrset_fx` ( `attributeset_id` ),
	KEY `products_fk_1` ( `category` ),
	UNIQUE INDEX `products_inx_0` ( `reference` ),
	UNIQUE INDEX `products_inx_1` ( `code` ),
	INDEX `products_name_inx` ( `name` ),
	KEY `products_taxcat_fk` ( `taxcat` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: products_cat. Script date: 11/05/2016 05:25:00. */
CREATE TABLE `products_bundle` (
    `id` varchar(255) NOT NULL,
    `product` VARCHAR(255) NOT NULL,
    `product_bundle` VARCHAR(255) NOT NULL,
    `quantity` DOUBLE NOT NULL,
    PRIMARY KEY ( `id` ),
    UNIQUE INDEX `pbundle_inx_prod` ( `product` , `product_bundle` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: products_cat. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `products_cat` (
	`product` varchar(255) NOT NULL,
	`catorder` int(11) default NULL,
	PRIMARY KEY  ( `product` ),
	KEY `products_cat_inx_1` ( `catorder` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: products_com. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `products_com` (
	`id` varchar(255) NOT NULL,
	`product` varchar(255) NOT NULL,
	`product2` varchar(255) NOT NULL,
	UNIQUE INDEX `pcom_inx_prod` ( `product`, `product2` ),
	PRIMARY KEY  ( `id` ),
	KEY `products_com_fk_2` ( `product2` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: receipts. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `receipts` (
	`id` varchar(255) NOT NULL,
	`money` varchar(255) NOT NULL,
	`datenew` datetime NOT NULL,
	`attributes` mediumblob default NULL,
	`person` varchar(255) default NULL,
	PRIMARY KEY  ( `id` ),
	KEY `receipts_fk_money` ( `money` ),
	KEY `receipts_inx_1` ( `datenew` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: reservation_customers. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `reservation_customers` (
	`id` varchar(255) NOT NULL,
	`customer` varchar(255) NOT NULL,
	PRIMARY KEY  ( `id` ),
	KEY `res_cust_fk_2` ( `customer` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: reservations. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `reservations` (
	`id` varchar(255) NOT NULL,
	`created` datetime NOT NULL,
	`datenew` datetime NOT NULL default '2018-01-01 00:00:00',
	`title` varchar(255) NOT NULL,
	`chairs` int(11) NOT NULL,
	`isdone` bit(1) NOT NULL,
	`description` varchar(255) default NULL,
	PRIMARY KEY  ( `id` ),
	KEY `reservations_inx_1` ( `datenew` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: resources. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `resources` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`restype` int(11) NOT NULL,
	`content` mediumblob default NULL,
        `version` varchar(10) default NULL,
	PRIMARY KEY  ( `id` ),
	UNIQUE INDEX `resources_name_inx` ( `name` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: roles. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `roles` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`permissions` mediumblob default NULL,
	PRIMARY KEY  ( `id` ),
	UNIQUE INDEX `roles_name_inx` ( `name` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: sharedtickets. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `sharedtickets` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`content` mediumblob default NULL,
	`appuser` varchar(255) default NULL,
	`pickupid` int(11) NOT NULL default '0',
	`locked` varchar(20) default NULL,
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: shift_breaks. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `shift_breaks` (
	`id` varchar(255) NOT NULL,
	`shiftid` varchar(255) NOT NULL,
	`breakid` varchar(255) NOT NULL,
	`starttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`endtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY  ( `id` ),
	KEY `shift_breaks_breakid` ( `breakid` ),
	KEY `shift_breaks_shiftid` ( `shiftid` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: shifts. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `shifts` (
	`id` varchar(255) NOT NULL,
	`startshift` datetime NOT NULL,
	`endshift` datetime default NULL,
	`pplid` varchar(255) NOT NULL,
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: stockcurrent. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `stockcurrent` (
	`location` varchar(255) NOT NULL,
	`product` varchar(255) NOT NULL,
	`attributesetinstance_id` varchar(255) default NULL,
	`units` double NOT NULL,
	KEY `stockcurrent_attsetinst` ( `attributesetinstance_id` ),
	KEY `stockcurrent_fk_1` ( `product` ),
	UNIQUE INDEX `stockcurrent_inx` ( `location`, `product`, `attributesetinstance_id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: stockdiary. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `stockdiary` (
	`id` varchar(255) NOT NULL,
	`datenew` datetime NOT NULL,
	`reason` int(11) NOT NULL,
	`location` varchar(255) NOT NULL,
	`product` varchar(255) NOT NULL,
	`attributesetinstance_id` varchar(255) default NULL,
	`units` double NOT NULL,
	`price` double NOT NULL,
	`appuser` varchar(255) default NULL,
	`supplier` varchar(255) default NULL,
	`supplierdoc` varchar(255) default NULL,
	PRIMARY KEY  ( `id` ),
	KEY `stockdiary_attsetinst` ( `attributesetinstance_id` ),
	KEY `stockdiary_fk_1` ( `product` ),
	KEY `stockdiary_fk_2` ( `location` ),
	KEY `stockdiary_inx_1` ( `datenew` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: stocklevel. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `stocklevel` (
	`id` varchar(255) NOT NULL,
	`location` varchar(255) NOT NULL,
	`product` varchar(255) NOT NULL,
	`stocksecurity` double default NULL,
	`stockmaximum` double default NULL,
	PRIMARY KEY  ( `id` ),
	KEY `stocklevel_location` ( `location` ),
	KEY `stocklevel_product` ( `product` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: suppliers. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `suppliers` (
	`id` varchar(255) NOT NULL,
	`searchkey` varchar(255) NOT NULL,
	`taxid` varchar(255) default NULL,
	`name` varchar(255) NOT NULL,
	`maxdebt` double NOT NULL default '0',
	`address` varchar(255) default NULL,
	`address2` varchar(255) default NULL,
	`postal` varchar(255) default NULL,
	`city` varchar(255) default NULL,
	`region` varchar(255) default NULL,
	`country` varchar(255) default NULL,
	`firstname` varchar(255) default NULL,
	`lastname` varchar(255) default NULL,
	`email` varchar(255) default NULL,
	`phone` varchar(255) default NULL,
	`phone2` varchar(255) default NULL,
	`fax` varchar(255) default NULL,
	`notes` varchar(255) default NULL,
	`visible` bit(1) NOT NULL default b'1',
	`curdate` datetime default NULL,
	`curdebt` double default '0',
	`vatid` varchar(255) default NULL,
        `taxid_type` varchar(90) not null default 'CF',
	PRIMARY KEY  ( `id` ),
	KEY `suppliers_name_inx` ( `name` ),
	UNIQUE INDEX `suppliers_skey_inx` ( `searchkey` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: taxcategories. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `taxcategories` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	PRIMARY KEY  ( `id` ),
	UNIQUE INDEX `taxcat_name_inx` ( `name` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: taxcustcategories. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `taxcustcategories` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	PRIMARY KEY  ( `id` ),
	UNIQUE INDEX `taxcustcat_name_inx` ( `name` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: taxes. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `taxes` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`category` varchar(255) NOT NULL,
	`custcategory` varchar(255) default NULL,
	`parentid` varchar(255) default NULL,
	`rate` double NOT NULL default '0',
	`ratecascade` bit(1) NOT NULL default b'0',
	`rateorder` int(11) default NULL,
        `legalcode` varchar(6) default '0' NOT NULL,
        `datestart` datetime DEFAULT NULL,
        `status` boolean DEFAULT true,
	PRIMARY KEY  ( `id` ),
	KEY `taxes_cat_fk` ( `category` ),
	KEY `taxes_custcat_fk` ( `custcategory` ),
	UNIQUE INDEX `taxes_name_inx` ( `name` ),
	KEY `taxes_taxes_fk` ( `parentid` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: taxlines. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `taxlines` (
	`id` varchar(255) NOT NULL,
	`receipt` varchar(255) NOT NULL,
	`taxid` varchar(255) NOT NULL,
	`base` double NOT NULL default '0',
	`amount` double NOT NULL default '0',
	PRIMARY KEY  ( `id` ),
	KEY `taxlines_receipt` ( `receipt` ),
	KEY `taxlines_tax` ( `taxid` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: taxsuppcategories. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `taxsuppcategories` (
	`id` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: thirdparties. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `thirdparties` (
	`id` varchar(255) NOT NULL,
	`cif` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`address` varchar(255) default NULL,
	`contactcomm` varchar(255) default NULL,
	`contactfact` varchar(255) default NULL,
	`payrule` varchar(255) default NULL,
	`faxnumber` varchar(255) default NULL,
	`phonenumber` varchar(255) default NULL,
	`mobilenumber` varchar(255) default NULL,
	`email` varchar(255) default NULL,
	`webpage` varchar(255) default NULL,
	`notes` varchar(255) default NULL,
	PRIMARY KEY  ( `id` ),
	UNIQUE INDEX `thirdparties_cif_inx` ( `cif` ),
	UNIQUE INDEX `thirdparties_name_inx` ( `name` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: ticketlines. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `ticketlines` (
	`ticket` varchar(255) NOT NULL,
	`line` int(11) NOT NULL,
	`product` varchar(255) default NULL,
	`attributesetinstance_id` varchar(255) default NULL,
	`units` double NOT NULL,
	`price` double NOT NULL,
	`taxid` varchar(255) NOT NULL,
	`attributes` mediumblob default NULL,
	PRIMARY KEY  ( `ticket`, `line` ),
	KEY `ticketlines_attsetinst` ( `attributesetinstance_id` ),
	KEY `ticketlines_fk_2` ( `product` ),
	KEY `ticketlines_fk_3` ( `taxid` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: tickets. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `tickets` (
	`id` varchar(255) NOT NULL,
	`tickettype` int(11) NOT NULL default '0',
	`ticketid` int(11) NOT NULL,
	`person` varchar(255) NOT NULL,
	`customer` varchar(255) default NULL,
	`status` int(11) NOT NULL default '0',
        `serie_number` varchar(100),
	`code` varchar(10),
        `access_key` varchar(180),
	`tickets_id` varchar(255) null comment 'Property for the recursive relation, the purpose is for the refund, is for to save the original ticket',
	PRIMARY KEY  ( `id` ),
	KEY `tickets_customers_fk` ( `customer` ),
	KEY `tickets_fk_2` ( `person` ),
	KEY `tickets_ticketid` ( `tickettype`, `ticketid` ),
        KEY `tickets_tickets_fk` ( `tickets_id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: ticketsnum. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `ticketsnum` (
    `code`      varchar(10) not null,
    `people_id` varchar(255) not null,
    `serie`     varchar(100) not null,
    `id`        int(11) NOT NULL,    
    `priority`  varchar(20) not null,
    `status`    varchar(10) not null,
    primary key (`code`, `people_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: ticketsnum_payment. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `ticketsnum_payment` (
	`id` int(11) NOT NULL,
	PRIMARY KEY  ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: ticketsnum_refund. Script date: 23/01/2018 08:00:00 */

CREATE TABLE `ticketsnum_refund` (
    `code`      varchar(10) not null,
    `people_id` varchar(255) not null,
    `serie`     varchar(100) not null,
    `id` int(11) NOT NULL,
    `priority`  varchar(20) not null,
    `status`    varchar(10) not null,
    primary key (`code`, `people_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: uom. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `uom` (
    `id` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/* Header line. Object: vouchers. Script date: 23/01/2018 08:00:00 */
CREATE TABLE `vouchers` (
   `id` VARCHAR(100) NOT NULL,
   `voucher_number` VARCHAR(100) DEFAULT NULL,
   `customer` VARCHAR(100) DEFAULT NULL,
   `amount` DOUBLE DEFAULT NULL,
   `status` CHAR(1) DEFAULT 'A',
  PRIMARY KEY ( `id` )
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

CREATE TABLE `identification_type` (
    `code`          VARCHAR(90) not null,
    `name`          VARCHAR(100) not null,
    `legal_code`    VARCHAR(100) null,
    `status`        BOOLEAN not null default true,
    primary key (`code`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE `taxpayer` (
    `id`                int(11) not null,
    `identification`    varchar(50) not null,
    `legal_name`        varchar(500) not null,
    `forced_accounting` varchar(5) not null,
    `special_taxpayer`  varchar(50),
    `retention_agent`   varchar(50),
    `other`             varchar(100),
    primary key (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE `establishments` (
    `id` varchar(11) not null,
    `comercial_name` varchar(500),
    `city` varchar(200) not null,
    `address` varchar(500) not null,
    `phone` varchar(500) not null,
    `email` varchar(500) not null,
    `principal` varchar(50) not null,    
    `status` boolean not null,
    primary key (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE `holidays` (
	`id` INT auto_increment NOT NULL,
	`name` varchar(180) NOT NULL,
	`date` DATE NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8 ;

-- Update foreign keys of attributeinstance
ALTER TABLE `attributeinstance` ADD CONSTRAINT `attinst_att`
	FOREIGN KEY ( `attribute_id` ) REFERENCES `attribute` ( `id` );

ALTER TABLE `attributeinstance` ADD CONSTRAINT `attinst_set`
	FOREIGN KEY ( `attributesetinstance_id` ) REFERENCES `attributesetinstance` ( `id` ) ON DELETE CASCADE;

-- Update foreign keys of attributesetinstance
ALTER TABLE `attributesetinstance` ADD CONSTRAINT `attsetinst_set`
	FOREIGN KEY ( `attributeset_id` ) REFERENCES `attributeset` ( `id` ) ON DELETE CASCADE;

-- Update foreign keys of attributeuse
ALTER TABLE `attributeuse` ADD CONSTRAINT `attuse_att`
	FOREIGN KEY ( `attribute_id` ) REFERENCES `attribute` ( `id` );

ALTER TABLE `attributeuse` ADD CONSTRAINT `attuse_set`
	FOREIGN KEY ( `attributeset_id` ) REFERENCES `attributeset` ( `id` ) ON DELETE CASCADE;

-- Update foreign keys of attributevalue
ALTER TABLE `attributevalue` ADD CONSTRAINT `attval_att`
	FOREIGN KEY ( `attribute_id` ) REFERENCES `attribute` ( `id` ) ON DELETE CASCADE;

-- Update foreign keys of categories
ALTER TABLE `categories` ADD CONSTRAINT `categories_fk_1`
	FOREIGN KEY ( `parentid` ) REFERENCES `categories` ( `id` );

-- Update foreign keys of customers
 ALTER TABLE `customers` ADD CONSTRAINT `customers_taxcat`
	FOREIGN KEY ( `taxcategory` ) REFERENCES `taxcustcategories` ( `id` );

-- Update foreign keys of leaves
ALTER TABLE `leaves` ADD CONSTRAINT `leaves_pplid`
	FOREIGN KEY ( `pplid` ) REFERENCES `people` ( `id` );

-- Update foreign keys of payments
ALTER TABLE `payments` ADD CONSTRAINT `payments_fk_receipt`
	FOREIGN KEY ( `receipt` ) REFERENCES `receipts` ( `id` );

-- Update foreign keys of people
ALTER TABLE `people` ADD CONSTRAINT `people_fk_1`
	FOREIGN KEY ( `role` ) REFERENCES `roles` ( `id` );

-- Update foreign keys of products
ALTER TABLE `products` ADD CONSTRAINT `products_attrset_fk`
	FOREIGN KEY ( `attributeset_id` ) REFERENCES `attributeset` ( `id` );

ALTER TABLE `products` ADD CONSTRAINT `products_fk_1`
	FOREIGN KEY ( `category` ) REFERENCES `categories` ( `id` );

ALTER TABLE `products` ADD CONSTRAINT `products_taxcat_fk`
	FOREIGN KEY ( `taxcat` ) REFERENCES `taxcategories` ( `id` );

-- Update foreign keys of product_bundle
ALTER TABLE `products_bundle` ADD CONSTRAINT `products_bundle_fk_1` 
        FOREIGN KEY ( `product` ) REFERENCES `products`( `id` );

ALTER TABLE `products_bundle` ADD CONSTRAINT `products_bundle_fk_2`     
        FOREIGN KEY ( `product_bundle` ) REFERENCES `products`( `id` );

-- Update foreign keys of products_cat
ALTER TABLE `products_cat` ADD CONSTRAINT `products_cat_fk_1`
	FOREIGN KEY ( `product` ) REFERENCES `products` ( `id` );

-- Update foreign keys of products_com
ALTER TABLE `products_com` ADD CONSTRAINT `products_com_fk_1`
	FOREIGN KEY ( `product` ) REFERENCES `products` ( `id` );

ALTER TABLE `products_com` ADD CONSTRAINT `products_com_fk_2`
	FOREIGN KEY ( `product2` ) REFERENCES `products` ( `id` );

-- Update foreign keys of receipts
ALTER TABLE `receipts` ADD CONSTRAINT `receipts_fk_money`
	FOREIGN KEY ( `money` ) REFERENCES `closedcash` ( `money` );

-- Update foreign keys of reservation_customers
ALTER TABLE `reservation_customers` ADD CONSTRAINT `res_cust_fk_1`
	FOREIGN KEY ( `id` ) REFERENCES `reservations` ( `id` );

ALTER TABLE `reservation_customers` ADD CONSTRAINT `res_cust_fk_2`
	FOREIGN KEY ( `customer` ) REFERENCES `customers` ( `id` );

-- Update foreign keys of shift_breaks
ALTER TABLE `shift_breaks` ADD CONSTRAINT `shift_breaks_breakid`
	FOREIGN KEY ( `breakid` ) REFERENCES `breaks` ( `id` );

ALTER TABLE `shift_breaks` ADD CONSTRAINT `shift_breaks_shiftid`
	FOREIGN KEY ( `shiftid` ) REFERENCES `shifts` ( `id` );

-- Update foreign keys of stockcurrent
ALTER TABLE `stockcurrent` ADD CONSTRAINT `stockcurrent_attsetinst`
	FOREIGN KEY ( `attributesetinstance_id` ) REFERENCES `attributesetinstance` ( `id` );

ALTER TABLE `stockcurrent` ADD CONSTRAINT `stockcurrent_fk_1`
	FOREIGN KEY ( `product` ) REFERENCES `products` ( `id` );

ALTER TABLE `stockcurrent` ADD CONSTRAINT `stockcurrent_fk_2`
	FOREIGN KEY ( `location` ) REFERENCES `locations` ( `id` );

-- Update foreign keys of stockdiary
ALTER TABLE `stockdiary` ADD CONSTRAINT `stockdiary_attsetinst`
	FOREIGN KEY ( `attributesetinstance_id` ) REFERENCES `attributesetinstance` ( `id` );

ALTER TABLE `stockdiary` ADD CONSTRAINT `stockdiary_fk_1`
	FOREIGN KEY ( `product` ) REFERENCES `products` ( `id` );

ALTER TABLE `stockdiary` ADD CONSTRAINT `stockdiary_fk_2`
	FOREIGN KEY ( `location` ) REFERENCES `locations` ( `id` );

-- Update foreign keys of stocklevel
ALTER TABLE `stocklevel` ADD CONSTRAINT `stocklevel_location`
	FOREIGN KEY ( `location` ) REFERENCES `locations` ( `id` );

ALTER TABLE `stocklevel` ADD CONSTRAINT `stocklevel_product`
	FOREIGN KEY ( `product` ) REFERENCES `products` ( `id` );

-- Update foreign keys of taxes
ALTER TABLE `taxes` ADD CONSTRAINT `taxes_cat_fk`
	FOREIGN KEY ( `category` ) REFERENCES `taxcategories` ( `id` );

ALTER TABLE `taxes` ADD CONSTRAINT `taxes_custcat_fk`
	FOREIGN KEY ( `custcategory` ) REFERENCES `taxcustcategories` ( `id` );

ALTER TABLE `taxes` ADD CONSTRAINT `taxes_taxes_fk`
	FOREIGN KEY ( `parentid` ) REFERENCES `taxes` ( `id` );

-- Update foreign keys of taxlines
ALTER TABLE `taxlines` ADD CONSTRAINT `taxlines_receipt`
	FOREIGN KEY ( `receipt` ) REFERENCES `receipts` ( `id` );

ALTER TABLE `taxlines` ADD CONSTRAINT `taxlines_tax`
	FOREIGN KEY ( `taxid` ) REFERENCES `taxes` ( `id` );

-- Update foreign keys of ticketlines
ALTER TABLE `ticketlines` ADD CONSTRAINT `ticketlines_attsetinst`
	FOREIGN KEY ( `attributesetinstance_id` ) REFERENCES `attributesetinstance` ( `id` );

ALTER TABLE `ticketlines` ADD CONSTRAINT `ticketlines_fk_2`
	FOREIGN KEY ( `product` ) REFERENCES `products` ( `id` );

ALTER TABLE `ticketlines` ADD CONSTRAINT `ticketlines_fk_3`
	FOREIGN KEY ( `taxid` ) REFERENCES `taxes` ( `id` );

ALTER TABLE `ticketlines` ADD CONSTRAINT `ticketlines_fk_ticket`
	FOREIGN KEY ( `ticket` ) REFERENCES `tickets` ( `id` );

-- Update foreign keys of tickets
ALTER TABLE `tickets` ADD CONSTRAINT `tickets_customers_fk`
	FOREIGN KEY ( `customer` ) REFERENCES `customers` ( `id` );

ALTER TABLE `tickets` ADD CONSTRAINT `tickets_fk_2`
	FOREIGN KEY ( `person` ) REFERENCES `people` ( `id` );

ALTER TABLE `tickets` ADD CONSTRAINT `tickets_fk_id`
	FOREIGN KEY ( `id` ) REFERENCES `receipts` ( `id` );

ALTER TABLE `tickets`
ADD INDEX `idx_document` (`code`, `serie_number` desc) ;

-- Update foreign keys of customer to identification_type
ALTER TABLE `customers` 
ADD CONSTRAINT `taxid_type`
  FOREIGN KEY (`taxid_type`)
  REFERENCES `identification_type` (`code`);

alter table `ticketsnum` add constraint `ticketsnum_people_fk`
    foreign key ( `people_id` ) references `people` ( `id` );

alter table `tickets` add constraint `tickets_tickets_fk`
    foreign key ( `tickets_id` ) references `tickets` ( `id` );
 
-- *****************************************************************************

-- ADD roles
INSERT INTO roles(id, name, permissions) VALUES('0', 'Administrator role', $FILE{/com/unicenta/pos/templates/Role.Administrator.xml} );
INSERT INTO roles(id, name, permissions) VALUES('1', 'Manager role', $FILE{/com/unicenta/pos/templates/Role.Manager.xml} );
INSERT INTO roles(id, name, permissions) VALUES('2', 'Employee role', $FILE{/com/unicenta/pos/templates/Role.Employee.xml} );
INSERT INTO roles(id, name, permissions) VALUES('3', 'Guest role', $FILE{/com/unicenta/pos/templates/Role.Guest.xml} );

-- ADD people
INSERT INTO people(id, name, apppassword, role, visible, image) VALUES ('0', 'Administrator', NULL, '0', TRUE, NULL);
INSERT INTO people(id, name, apppassword, role, visible, image) VALUES ('1', 'Manager', NULL, '1', TRUE, NULL);
INSERT INTO people(id, name, apppassword, role, visible, image) VALUES ('2', 'Employee', NULL, '2', TRUE, NULL);
INSERT INTO people(id, name, apppassword, role, visible, image) VALUES ('3', 'Guest', NULL, '3', TRUE, NULL);

-- ADD resources --
-- SYSTEM
INSERT INTO resources(id, name, restype, content) VALUES('00', 'Menu.Root', 0, $FILE{/com/unicenta/pos/templates/Menu.Root.txt});
INSERT INTO resources(id, name, restype, content) VALUES('02', 'Cash.Close', 0, $FILE{/com/unicenta/pos/templates/Cash.Close.xml});
INSERT INTO resources(ID, name, restype, content) VALUES('03', 'Customer.Created', 0, $FILE{/com/unicenta/pos/templates/customer.created.xml});
INSERT INTO resources(ID, name, restype, content) VALUES('04', 'Customer.Deleted', 0, $FILE{/com/unicenta/pos/templates/customer.deleted.xml});
INSERT INTO resources(ID, name, restype, content) VALUES('05', 'Customer.Updated', 0, $FILE{/com/unicenta/pos/templates/customer.updated.xml});
INSERT INTO resources(id, name, restype, content) VALUES('06', 'payment.cash', 0, $FILE{/com/unicenta/pos/templates/payment.cash.txt});
INSERT INTO resources(id, name, restype, content) VALUES('07', 'Ticket.Buttons', 0, $FILE{/com/unicenta/pos/templates/Ticket.Buttons.xml});
INSERT INTO resources(id, name, restype, content) VALUES('08', 'Ticket.Close', 0, $FILE{/com/unicenta/pos/templates/Ticket.Close.xml});
INSERT INTO resources(id, name, restype, content) VALUES('09', 'Ticket.Line', 0, $FILE{/com/unicenta/pos/templates/Ticket.Line.xml});
INSERT INTO resources(id, name, restype, content) VALUES('10', 'Window.Title', 0, $FILE{/com/unicenta/pos/templates/Window.Title.txt});

-- IMAGES
INSERT INTO resources(id, name, restype, content) VALUES('11', 'img.001', 1, $FILE{/com/unicenta/images/.01.png});
INSERT INTO resources(id, name, restype, content) VALUES('12', 'img.002', 1, $FILE{/com/unicenta/images/.02.png});
INSERT INTO resources(id, name, restype, content) VALUES('13', 'img.005', 1, $FILE{/com/unicenta/images/.05.png});
INSERT INTO resources(id, name, restype, content) VALUES('14', 'img.010', 1, $FILE{/com/unicenta/images/.10.png});
INSERT INTO resources(id, name, restype, content) VALUES('15', 'img.020', 1, $FILE{/com/unicenta/images/.20.png});
INSERT INTO resources(id, name, restype, content) VALUES('16', 'img.025', 1, $FILE{/com/unicenta/images/.25.png});
INSERT INTO resources(id, name, restype, content) VALUES('17', 'img.050', 1, $FILE{/com/unicenta/images/.50.png});
INSERT INTO resources(id, name, restype, content) VALUES('18', 'img.1', 1, $FILE{/com/unicenta/images/1.00.png});
INSERT INTO resources(id, name, restype, content) VALUES('19', 'img.2', 1, $FILE{/com/unicenta/images/2.00.png});
INSERT INTO resources(id, name, restype, content) VALUES('20', 'img.5', 1, $FILE{/com/unicenta/images/5.00.png});
INSERT INTO resources(id, name, restype, content) VALUES('21', 'img.10', 1, $FILE{/com/unicenta/images/10.00.png});
INSERT INTO resources(id, name, restype, content) VALUES('22', 'img.20', 1, $FILE{/com/unicenta/images/20.00.png});
INSERT INTO resources(id, name, restype, content) VALUES('23', 'img.50', 1, $FILE{/com/unicenta/images/50.00.png});
INSERT INTO resources(id, name, restype, content) VALUES('24', 'img.100', 1, $FILE{/com/unicenta/images/100.00.png});
INSERT INTO resources(id, name, restype, content) VALUES('25', 'img.200', 1, $FILE{/com/unicenta/images/200.00.png});
INSERT INTO resources(id, name, restype, content) VALUES('26', 'img.500', 1, $FILE{/com/unicenta/images/500.00.png});
INSERT INTO resources(id, name, restype, content) VALUES('27', 'img.1000', 1, $FILE{/com/unicenta/images/1000.00.png});
INSERT INTO resources(id, name, restype, content) VALUES('28', 'img.cash', 1, $FILE{/com/unicenta/images/cash.png});
INSERT INTO resources(id, name, restype, content) VALUES('29', 'img.cashdrawer', 1, $FILE{/com/unicenta/images/cashdrawer.png});
INSERT INTO resources(id, name, restype, content) VALUES('30', 'img.discount', 1, $FILE{/com/unicenta/images/discount.png});
INSERT INTO resources(id, name, restype, content) VALUES('31', 'img.discount_b', 1, $FILE{/com/unicenta/images/discount_b.png});
INSERT INTO resources(id, name, restype, content) VALUES('32', 'img.heart', 1, $FILE{/com/unicenta/images/heart.png});
INSERT INTO resources(id, name, restype, content) VALUES('33', 'img.keyboard_48', 1, $FILE{/com/unicenta/images/keyboard_48.png});
INSERT INTO resources(id, name, restype, content) VALUES('34', 'img.kit_print', 1, $FILE{/com/unicenta/images/kit_print.png});
INSERT INTO resources(id, name, restype, content) VALUES('35', 'img.no_photo', 1, $FILE{/com/unicenta/images/no_photo.png});
INSERT INTO resources(id, name, restype, content) VALUES('36', 'img.refundit', 1, $FILE{/com/unicenta/images/refundit.png});
INSERT INTO resources(id, name, restype, content) VALUES('37', 'img.run_script', 1, $FILE{/com/unicenta/images/run_script.png});
INSERT INTO resources(id, name, restype, content) VALUES('38', 'img.ticket_print', 1, $FILE{/com/unicenta/images/ticket_print.png});
INSERT INTO resources(id, name, restype, content) VALUES('39', 'Printer.Ticket.Logo', 1, $FILE{/com/unicenta/images/printer.ticket.logo.jpg});

-- PRINTER
INSERT INTO resources(id, name, restype, content) VALUES('41', 'Printer.CloseCash.Preview', 0, $FILE{/com/unicenta/pos/templates/Printer.CloseCash.Preview.xml});
INSERT INTO resources(id, name, restype, content) VALUES('42', 'Printer.CloseCash', 0, $FILE{/com/unicenta/pos/templates/Printer.CloseCash.xml});
INSERT INTO resources(id, name, restype, content) VALUES('43', 'Printer.CustomerPaid', 0, $FILE{/com/unicenta/pos/templates/Printer.CustomerPaid.xml});
INSERT INTO resources(id, name, restype, content) VALUES('44', 'Printer.CustomerPaid2', 0, $FILE{/com/unicenta/pos/templates/Printer.CustomerPaid2.xml});
INSERT INTO resources(id, name, restype, content) VALUES('45', 'Printer.FiscalTicket', 0, $FILE{/com/unicenta/pos/templates/Printer.FiscalTicket.xml});
INSERT INTO resources(id, name, restype, content) VALUES('46', 'Printer.Inventory', 0, $FILE{/com/unicenta/pos/templates/Printer.Inventory.xml});
INSERT INTO resources(id, name, restype, content) VALUES('47', 'Printer.OpenDrawer', 0, $FILE{/com/unicenta/pos/templates/Printer.OpenDrawer.xml});
INSERT INTO resources(id, name, restype, content) VALUES('48', 'Printer.PartialCash', 0, $FILE{/com/unicenta/pos/templates/Printer.PartialCash.xml});
INSERT INTO resources(id, name, restype, content) VALUES('49', 'Printer.PrintLastTicket', 0, $FILE{/com/unicenta/pos/templates/Printer.PrintLastTicket.xml});
INSERT INTO resources(id, name, restype, content) VALUES('50', 'Printer.Product', 0, $FILE{/com/unicenta/pos/templates/Printer.Product.xml});
INSERT INTO resources(id, name, restype, content) VALUES('52', 'Printer.ReprintTicket', 0, $FILE{/com/unicenta/pos/templates/Printer.ReprintTicket.xml});
INSERT INTO resources(id, name, restype, content) VALUES('53', 'Printer.Start', 0, $FILE{/com/unicenta/pos/templates/Printer.Start.xml});
INSERT INTO resources(id, name, restype, content) VALUES('54', 'Printer.Ticket.P1', 0, $FILE{/com/unicenta/pos/templates/Printer.Ticket.P1.xml});
INSERT INTO resources(id, name, restype, content) VALUES('55', 'Printer.Ticket.P2', 0, $FILE{/com/unicenta/pos/templates/Printer.Ticket.P2.xml});
INSERT INTO resources(id, name, restype, content) VALUES('56', 'Printer.Ticket.P3', 0, $FILE{/com/unicenta/pos/templates/Printer.Ticket.P3.xml});
INSERT INTO resources(id, name, restype, content) VALUES('57', 'Printer.Ticket.P4', 0, $FILE{/com/unicenta/pos/templates/Printer.Ticket.P4.xml});
INSERT INTO resources(id, name, restype, content) VALUES('58', 'Printer.Ticket.P5', 0, $FILE{/com/unicenta/pos/templates/Printer.Ticket.P5.xml});
INSERT INTO resources(id, name, restype, content) VALUES('59', 'Printer.Ticket.P6', 0, $FILE{/com/unicenta/pos/templates/Printer.Ticket.P6.xml});
INSERT INTO resources(id, name, restype, content) VALUES('60', 'Printer.Ticket', 0, $FILE{/com/unicenta/pos/templates/Printer.Ticket.xml});
INSERT INTO resources(id, name, restype, content) VALUES('61', 'Printer.Ticket2', 0, $FILE{/com/unicenta/pos/templates/Printer.Ticket2.xml});
INSERT INTO resources(id, name, restype, content) VALUES('62', 'Printer.TicketClose', 0, $FILE{/com/unicenta/pos/templates/Printer.TicketClose.xml});
INSERT INTO resources(id, name, restype, content) VALUES('63', 'Printer.TicketLine', 0, $FILE{/com/unicenta/pos/templates/Printer.TicketLine.xml});
INSERT INTO resources(id, name, restype, content) VALUES('64', 'Printer.TicketNew', 0, $FILE{/com/unicenta/pos/templates/Printer.TicketLine.xml});
INSERT INTO resources(id, name, restype, content) VALUES('65', 'Printer.TicketPreview', 0, $FILE{/com/unicenta/pos/templates/Printer.TicketPreview.xml});
INSERT INTO resources(id, name, restype, content) VALUES('66', 'Printer.Ticket_A4', 0, $FILE{/com/unicenta/pos/templates/Printer.Ticket_A4.xml});
INSERT INTO resources(id, name, restype, content) VALUES('67', 'Printer.TicketPreview_A4', 0, $FILE{/com/unicenta/pos/templates/Printer.TicketPreview_A4.xml});
INSERT INTO resources(id, name, restype, content) VALUES('68', 'Printer.TicketRemote', 0, $FILE{/com/unicenta/pos/templates/Printer.TicketRemote.xml});
INSERT INTO resources(id, name, restype, content) VALUES('69', 'Printer.TicketTotal', 0, $FILE{/com/unicenta/pos/templates/Printer.TicketTotal.xml});

-- SCRIPTS
INSERT INTO resources(id, name, restype, content) VALUES('70', 'script.Keyboard', 0, $FILE{/com/unicenta/pos/templates/script.Keyboard.txt});
INSERT INTO resources(id, name, restype, content) VALUES('71', 'script.Linediscount', 0, $FILE{/com/unicenta/pos/templates/script.Linediscount.txt});
INSERT INTO resources(id, name, restype, content) VALUES('72', 'script.SendOrder', 0, $FILE{/com/unicenta/pos/templates/script.SendOrder.txt});
INSERT INTO resources(id, name, restype, content) VALUES('73', 'script.Totaldiscount', 0, $FILE{/com/unicenta/pos/templates/script.Totaldiscount.txt});
INSERT INTO resources(id, name, restype, content) VALUES('74', 'script.multibuy', 0, $FILE{/com/unicenta/pos/templates/script.multibuy.txt});

-- Customer default
INSERT INTO resources(id, name, restype, content) VALUES('90', 'Customer.Default', 0, $FILE{/com/unicenta/pos/templates/Customer.Default.txt});

-- Customer default
INSERT INTO resources(id, name, restype, content) VALUES('91', 'FormatTicket.NumberDigits', 0, $FILE{/com/unicenta/pos/templates/FormatTicket.NumberDigits.txt});

-- ADD Ambiente de facturación electrónica: Test -> 1; Production -> 2
INSERT INTO resources(id, name, restype, content) VALUES('92', 'Electronic.Environment', 0, $FILE{/com/unicenta/pos/templates/Electronic.Environment.txt});

-- Tax category for holidays
INSERT INTO resources(id, name, restype, content) VALUES('93', 'Tax.Holidays', 0, $FILE{/com/unicenta/pos/templates/Tax.Holidays.txt});
INSERT INTO resources(id, name, restype, content) VALUES('94', 'Tax.Normal.Days', 0, $FILE{/com/unicenta/pos/templates/Tax.Normal.Days.txt});

-- Subscription Manager URL
INSERT INTO resources(id, name, restype, content) VALUES('108', 'Subscription.txt', 0, $FILE{/com/unicenta/pos/templates/Subscription.txt});

-- ADD CATEGORIES
INSERT INTO categories(id, name) VALUES ('000', 'Category Standard');
INSERT INTO categories(id, name) VALUES ('001', 'Category Ecuador');

-- ADD TAXCATEGORIES
/* 002 added 31/01/2017 00:00:00. */
INSERT INTO taxcategories(id, name) VALUES ('000', 'IVA 0');
INSERT INTO taxcategories(id, name) VALUES ('012', 'IVA 12');
INSERT INTO taxcategories(id, name) VALUES ('008', 'IVA 8');
INSERT INTO taxcategories(id, name) VALUES ('015', 'IVA 15');
INSERT INTO taxcategories(id, name) VALUES ('013', 'IVA 13');
INSERT INTO taxcategories(id, name) VALUES ('005', 'IVA 5');

-- ADD TAXES
/* 002 added 31/01/2017 00:00:00. */
INSERT INTO taxes(id, name, category, custcategory, parentid, rate, ratecascade, rateorder, legalcode) VALUES ('000', 'IVA 0', '000', NULL, NULL, 0, FALSE, NULL, '0');
INSERT INTO taxes(id, name, category, custcategory, parentid, rate, ratecascade, rateorder, legalcode) VALUES ('012', 'IVA 12', '012', NULL, NULL, 0.12, FALSE, NULL, '2');
INSERT INTO taxes(id, name, category, custcategory, parentid, rate, ratecascade, rateorder, legalcode) VALUES ('008', 'IVA 8', '008', NULL, NULL, 0.08, FALSE, NULL, '8');
INSERT INTO taxes(id, name, category, custcategory, parentid, rate, ratecascade, rateorder, legalcode) VALUES ('015', 'IVA 15', '015', NULL, NULL, 0.15, FALSE, NULL, '4');
INSERT INTO taxes(id, name, category, custcategory, parentid, rate, ratecascade, rateorder, legalcode) VALUES ('013', 'IVA 13', '013', NULL, NULL, 0.13, FALSE, NULL, '10');
INSERT INTO taxes(id, name, category, custcategory, parentid, rate, ratecascade, rateorder, legalcode) VALUES ('005', 'IVA 5', '005', NULL, NULL, 0.05, FALSE, NULL, '5');

-- ADD PRODUCTS
INSERT INTO products(id, reference, code, name, category, taxcat, isservice, display, printto) 
VALUES ('xxx999_999xxx_x9x9x9', 'xxx999', 'xxx999', 'Free Line entry', '000', '000', 1, '<html><center>Free Line entry', '1');
INSERT INTO products(id, reference, code, name, category, taxcat, isservice, display, printto) 
VALUES ('xxx998_998xxx_x8x8x8', 'xxx998', 'xxx998', 'Service Charge', '000', '000', 1, '<html><center>Service Charge', '1');

-- ADD PRODUCTS_CAT
INSERT INTO products_cat(product) VALUES ('xxx999_999xxx_x9x9x9');
INSERT INTO products_cat(product) VALUES ('xxx998_998xxx_x8x8x8');

-- ADD PRODUCTS Ecuador
INSERT INTO products(id, reference, code, name, pricesell, category, taxcat, isservice, display, printto, uom) 
VALUES ('1', '1', '1', 'Producto 0%', 1, '001', '000', 0, '<html><center>Producto 0%', '1', 'u');
-- INSERT INTO products(id, reference, code, name, pricesell, category, taxcat, isservice, display, printto, uom) 
-- VALUES ('2', '2', '2', 'Producto 12%', 1, '001', '012', 0, '<html><center>Producto 12%', '1', 'u');
-- INSERT INTO products(id, reference, code, name, pricesell, category, taxcat, isservice, display, printto, uom) 
-- VALUES ('3', '3', '3', 'Producto 13%', 1, '001', '013', 0, '<html><center>Producto 13%', '1', 'u');
INSERT INTO products(id, reference, code, name, pricesell, category, taxcat, isservice, display, printto, uom) 
VALUES ('4', '4', '4', 'Producto 15%', 1, '001', '015', 0, '<html><center>Producto 15%', '1', 'u');
INSERT INTO products(id, reference, code, name, pricesell, category, taxcat, isservice, display, printto, uom) 
VALUES ('5', '5', '5', 'Producto 5%', 1, '001', '005', 0, '<html><center>Producto 5%', '1', 'u');


-- ADD PRODUCTS_CAT
INSERT INTO products_cat(product) VALUES ('1');
-- INSERT INTO products_cat(product) VALUES ('2');
-- INSERT INTO products_cat(product) VALUES ('3');
INSERT INTO products_cat(product) VALUES ('4');
INSERT INTO products_cat(product) VALUES ('5');

-- ADD LOCATION
INSERT INTO locations(id, name, address) VALUES ('0','Location 1','Local');

-- ADD SUPPLIERS
INSERT INTO suppliers(id, searchkey, taxid, taxid_type, name) VALUES ('9999999999999','9999999999999', '9999999999999', 'CF', 'Otros Proveedores');

-- ADD UOM
INSERT INTO uom(id, name) VALUES ('u','Unidad');

-- ADD FLOORS
INSERT INTO floors(id, name, image) VALUES ('0', 'Restaurant floor', $FILE{/com/unicenta/images/paperboard960_600.png});

-- ADD PLACES
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('1', 'Table 1', 100, 50, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('2', 'Table 2', 250, 50, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('3', 'Table 3', 400, 50, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('4', 'Table 4', 550, 50, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('5', 'Table 5', 700, 50, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('6', 'Table 6', 850, 50, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('7', 'Table 7', 100, 150, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('8', 'Table 8', 250, 150, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('9', 'Table 9', 400, 150, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('10', 'Table 10', 550, 150, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('11', 'Table 11', 700, 150, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('12', 'Table 12', 850, 150, '0', '1', 90, 45);

INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('13', 'Table 13', 100, 250, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('14', 'Table 14', 250, 250, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('15', 'Table 15', 400, 250, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('16', 'Table 16', 550, 250, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('17', 'Table 17', 700, 250, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('18', 'Table 18', 850, 250, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('19', 'Table 19', 100, 350, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('20', 'Table 20', 250, 350, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('21', 'Table 21', 400, 350, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('22', 'Table 22', 550, 350, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('23', 'Table 23', 700, 350, '0', '1', 90, 45);
INSERT INTO places(id, name, x, y, floor, seats, width, height) VALUES ('24', 'Table 24', 850, 350, '0', '1', 90, 45);

-- ADD SHIFTS
INSERT INTO shifts(id, startshift, endshift, pplid) VALUES ('0', '2018-01-01 00:00:00.001', '2018-01-01 00:00:00.002','0');

-- ADD BREAKS
INSERT INTO breaks(id, name, visible, notes) VALUES ('0', 'Lunch Break', TRUE, NULL);
INSERT INTO breaks(id, name, visible, notes) VALUES ('1', 'Tea Break', TRUE, NULL);
INSERT INTO breaks(id, name, visible, notes) VALUES ('2', 'Mid Break', TRUE, NULL);

-- ADD SHIFT_BREAKS
INSERT INTO shift_breaks(id, shiftid, breakid, starttime, endtime) VALUES ('0', '0', '0', '2018-01-01 00:00:00.003', '2018-01-01 00:00:00.004');

-- ADD SEQUENCES
INSERT INTO pickup_number VALUES(1);

INSERT INTO ticketsnum VALUES('FV', '0', '001201', 0, 'primary', 'Active');
INSERT INTO ticketsnum VALUES('BV', '0', '001201', 0, 'alternative', 'Active');
INSERT INTO ticketsnum VALUES('EV', '0', '001201', 0, 'alternative', 'Active');

INSERT INTO ticketsnum VALUES('FV', '1', '001301', 0, 'primary', 'Active');
INSERT INTO ticketsnum VALUES('BV', '1', '001301', 0, 'alternative', 'Active');
INSERT INTO ticketsnum VALUES('EV', '1', '001301', 0, 'alternative', 'Active');

INSERT INTO ticketsnum VALUES('FV', '2', '001401', 0, 'primary', 'Active');
INSERT INTO ticketsnum VALUES('BV', '2', '001401', 0, 'alternative', 'Active');
INSERT INTO ticketsnum VALUES('EV', '2', '001401', 0, 'alternative', 'Active');


INSERT INTO ticketsnum_refund VALUES('DV', '0', '001201', 0, 'primary', 'Active');
INSERT INTO ticketsnum_refund VALUES('DE', '0', '001201', 0, 'alternative', 'Active');

INSERT INTO ticketsnum_refund VALUES('DV', '1', '001301', 0, 'primary', 'Active');

INSERT INTO ticketsnum_refund VALUES('DV', '2', '001401', 0, 'primary', 'Active');
INSERT INTO ticketsnum_refund VALUES('DE', '2', '001401', 0, 'alternative', 'Active');

INSERT INTO ticketsnum_payment VALUES(1);

-- ADD IDENTIFICATION TYPES FOR ECUADOR
INSERT INTO identification_type(code, name, legal_code) VALUES ('C', 'Cédula', '05');
INSERT INTO identification_type(code, name, legal_code) VALUES ('R', 'RUC', '04');
INSERT INTO identification_type(code, name, legal_code) VALUES ('P', 'Pasaporte', '06');
INSERT INTO identification_type(code, name, legal_code) VALUES ('CF', 'Consumidor Final', '07');
INSERT INTO identification_type(code, name, legal_code) VALUES ('IE', 'Identificación del Exterior', '08');

-- ADD final consumer for Ecuador
INSERT INTO customers (id,searchkey,taxid,name,maxdebt,address,address2,taxid_type,firstname,lastname,notes,visible,isvip,discount) 
VALUES ('9999999999999','9999999999999','9999999999999','Consumidor Final',49.99,NULL,NULL,'CF','Consumidor','Final','',1,0,0);

-- ADD taxpayer
INSERT INTO taxpayer (id, identification, legal_name, forced_accounting, special_taxpayer, retention_agent, other)
VALUES (1, '9999999999999', 'Mi Empresa', 'SI', '12345', '1', 'RIMPE');

-- ADD establishments
INSERT INTO establishments (id, comercial_name, city, address, phone, email, principal, status) 
VALUES ('001', 'Principal', 'Mi ciudad', 'Mi dirección principal', '0999 999 999', 'info@pri.com', 'Principal', true);

INSERT INTO establishments (id, comercial_name, city, address, phone, email, principal, status) 
VALUES ('002', 'Sucursal', 'Mi otra ciudad', 'Mi otra dirección', '0988 888 888', 'info@sucu.com', 'BranchOffice', true);

-- ADD APPLICATION VERSION
INSERT INTO applications(id, name, version) VALUES($APP_ID{}, $APP_NAME{}, $APP_VERSION{});

CREATE TABLE `ele_parameters` (
  `id` bigint(18) NOT NULL,
  `name` varchar(300) DEFAULT NULL,
  `value` varchar(300) DEFAULT NULL,
  `observation` varchar(300) DEFAULT NULL,
  `type` varchar(300) DEFAULT NULL,
  `status` boolean DEFAULT true,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

Insert into ele_parameters (ID,name,value,observation,type) 
values (0,'Base Directory','/app/RoQui','Base directory for files','SRI');
Insert into ele_parameters (ID,name,value,observation,type) 
values (1,'Generated','/app/RoQui/receipt/generated','URL generated xml files','SRI');
Insert into ele_parameters (ID,name,value,observation,type) 
values (2,'Signed','/app/RoQui/receipt/signed','URL signed xml files','SRI');
Insert into ele_parameters (ID,name,value,observation,type) 
values (3,'Sended','/app/RoQui/receipt/sended','URL sended xml files','SRI');
Insert into ele_parameters (ID,name,value,observation,type) 
values (4,'Authorized','/app/RoQui/receipt/authorized','URL authorized xml files','SRI');
Insert into ele_parameters (ID,name,value,observation,type) 
values (5,'Unauthorized','/app/RoQui/receipt/unauthorized','URL unauthorized xml files','SRI');
Insert into ele_parameters (ID,name,value,observation,type) 
values (6,'Refused','/app/RoQui/receipt/refused','URL refused xml files','SRI');
Insert into ele_parameters (ID,name,value,observation,type) 
values (7,'PDF','/app/RoQui/receipt/pdf','URL pdf files','SRI');
Insert into ele_parameters (ID,name,value,observation,type) 
values (8,'Certificate','/app/RoQui/Certificate/Certificate.p12','URL certificate','Certificate');
Insert into ele_parameters (ID,name,value,observation,type) 
values (9,'Certificate Password','***************==','Certificate Password','Certificate');
Insert into ele_parameters (ID,name,value,observation,type) 
values (10,'Logo','/app/RoQui/Resources/images/logo.jpeg','URL logo','SRI');
Insert into ele_parameters (ID,name,value,observation,type) 
values (11,'Email Server','smtp.googlemail.com','Email Server','Email');
Insert into ele_parameters (ID,name,value,observation,type) 
values (12,'Email Server Port','465','Email Server Port','Email');
Insert into ele_parameters (ID,name,value,observation,type) 
values (13,'Email','jl@gmail.com','Email address','Email');
Insert into ele_parameters (ID,name,value,observation,type) 
values (14,'Email Password','***************==','Email Password','Email');
Insert into ele_parameters (ID,name,value,observation,type) 
values (15,'Subscription','**************==','Subscription','Subscription');


CREATE TABLE `ele_documents` (
    `id` BIGINT(18) NOT NULL AUTO_INCREMENT,
    `code` VARCHAR(18) NOT NULL,
    `number` VARCHAR(18) NOT NULL,
    `authorization` VARCHAR(90) DEFAULT NULL,
    `authorization_date` DATETIME DEFAULT NULL,
    `observation` VARCHAR(3000) DEFAULT NULL,
    `status` VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_ele` (`code` , `number`) USING BTREE,
    KEY `inx_fecha` (`code` , `number` , `status`) USING BTREE
)  ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE VIEW `v_ele_taxpayer` AS SELECT 
        `id`,
        `identification`,
        `legal_name`,
        `forced_accounting`,
        `special_taxpayer`,
        `retention_agent`,
        `other` AS `rimpe`
    FROM
        `taxpayer`;

CREATE VIEW `v_ele_establishments` as SELECT 
    (cast(`t`.`id` as unsigned)) AS `id`,
    `t`.`identification`,
    `e`.`id` AS `code`,
    `e`.`comercial_name` as `business_name`,
    `e`.`address`,
    `e`.`principal`
from
    `taxpayer` `t` join `establishments` `e`;

CREATE VIEW `v_ele_invoices` AS select
    (cast(ROWNUM() as unsigned)) AS `id`,
    `t`.`code` AS `code`,
    `t`.`serie_number` AS `number`,
    cast('01' as char) AS `code_document`,
    substr(`t`.`serie_number`, 1, 3) AS `establishment`,
    substr(`t`.`serie_number`, 4, 3) AS `emission_point`,
    cast(lpad(`t`.`ticketid`, 9, '0') as char) AS `sequence`,
    cast(`r`.`datenew` as date) AS `date`,
    round(sum(cast(`tl`.`units` * `tl`.`price` as decimal(19, 2))), 2) as `total_without_taxes`,
    round(sum(cast(`tl`.`units` * `tl`.`price` + if(`tx`.`rate` > 0, `tl`.`units` * `tl`.`price` * `tx`.`rate`, 0) as decimal(19, 2))), 2) AS `total`,
    `i`.`legal_code` AS `identification_type`,
    `c`.`taxid` AS `identification`,
    `c`.`name` AS `legal_name`,
    `c`.`address` AS `address`,
    cast(NULL as char(20)) AS `delivery_note`,
    (
    select
        `e`.`address`
    from
        `establishments` `e`
    where
        `e`.`id` = substr(`t`.`serie_number`, 1, 3)) AS `establishment_address`,
    `t`.`access_key` as `access_key`
from
    (`tickets` `t`
join `receipts` `r` on
    `t`.`id` = `r`.`id`
join `customers` `c` on
    `c`.`id` = `t`.`customer`
join `identification_type` `i` on
    `i`.`code` = `c`.`taxid_type`
join `ticketlines` `tl` on
    `t`.`id` = `tl`.`ticket`
join `taxes` `tx` on
    `tx`.`category` = `tl`.`taxid`)
where
    (`t`.`code` = 'FV')
    and (`t`.`tickettype` = 0)
group by
    (cast(`t`.`ticketid` as unsigned)),
    `t`.`code`,
    `t`.`serie_number`,
    cast('01' as char),
    substr(`t`.`serie_number`, 1, 3),
    substr(`t`.`serie_number`, 4, 3),
    cast(lpad(`t`.`ticketid`, 9, '0') as char),
    cast(`r`.`datenew` as date),
    `i`.`legal_code`,
    `c`.`taxid`,
    `c`.`name`,
    `c`.`address`,
    cast(NULL as char(20)),
    cast('' as char(10));

CREATE VIEW `v_ele_invoices_detail` as SELECT
    (CAST(CONCAT(`t`.`TICKETID`, `tl`.`LINE`) AS UNSIGNED)) AS `id`,
    t.code,
    `t`.`serie_number` AS `number`,
    `p`.`REFERENCE` AS `principal_code`,
    CAST(`tl`.`line` AS UNSIGNED) AS `line`,
    `p`.`name`,
    CAST(`tl`.`units` AS DECIMAL (19 , 2 )) AS `quantity`,
    CONVERT('UN', CHAR) AS `unit`,
    CAST(`tl`.`PRICE` AS DECIMAL (19 , 2 )) AS `unit_price`,
    CONVERT(`tx`.`legalcode`, CHAR) AS `tax_code`,
    CAST((`tx`.`RATE` * 100) AS DECIMAL (19 , 2 )) AS `tax_iva`,
    CAST(((`tl`.`UNITS` * `tl`.`PRICE`) * `tx`.`RATE`)
        AS DECIMAL (19 , 2 )) AS `value_iva`,
    CAST(0 AS DECIMAL (19 , 2 )) AS `discount`,
    CAST((`tl`.`UNITS` * `tl`.`PRICE`) AS DECIMAL (19 , 2 )) AS `total_price_without_tax`
FROM
    (((`tickets` `t`
    JOIN `ticketlines` `tl` ON ((`t`.`ID` = `tl`.`TICKET`)))
    JOIN `taxes` `tx` ON ((`tx`.`CATEGORY` = `tl`.`TAXID`)))
    JOIN `products` `p` ON ((`p`.`ID` = `tl`.`PRODUCT`)))
WHERE
    (`t`.`TICKETTYPE` = 0);

CREATE VIEW `v_ele_taxes_detail` as SELECT 
        (CAST(CONCAT(`t`.`TICKETID`, `tl`.`LINE`, '2') AS UNSIGNED)) AS `id`,
        t.code AS `code`,
        `t`.`serie_number` AS `number`,
        `p`.`REFERENCE` AS `principal_code`,
        CAST(`tl`.`LINE` AS UNSIGNED) AS `line`,
        CONVERT( '2' , CHAR) AS `tax_code`,
        CONVERT( `tx`.`legalcode` , CHAR) AS `percentage_code`,
        ABS(CAST((`tl`.`UNITS` * `tl`.`PRICE`) AS DECIMAL (19 , 2 ))) AS `tax_base`,
        CAST((`tx`.`RATE` * 100) AS DECIMAL (19 , 2 )) AS `tax_iva`,
        ABS(CAST(((`tl`.`UNITS` * `tl`.`PRICE`) * `tx`.`RATE`)
                    AS DECIMAL (19 , 2 ))) AS `value`
    FROM
        (((`tickets` `t`
        JOIN `ticketlines` `tl` ON ((`t`.`ID` = `tl`.`TICKET`)))
        JOIN `taxes` `tx` ON ((`tx`.`CATEGORY` = `tl`.`TAXID`)))
        JOIN `products` `p` ON ((`p`.`ID` = `tl`.`PRODUCT`)));

CREATE VIEW v_ele_information as WITH information AS 
   (SELECT `TAXID` AS `identification`,
	'Email' AS `name`,
	`EMAIL` AS `value`
FROM `customers`
WHERE
	((`EMAIL` IS NOT NULL)
		AND (`EMAIL` <> ''))
UNION all SELECT
	`TAXID`,
	'Dirección',
	`ADDRESS`
FROM
	`customers`
WHERE
	((`ADDRESS` IS NOT NULL)
		AND (`ADDRESS` <> ''))
UNION all SELECT
	`TAXID`,
	'Teléfono',
	`PHONE`
FROM
	`customers`
WHERE
	((`PHONE` IS NOT NULL)
		AND (`PHONE` <> ''))
		)
SELECT ROWNUM() AS `id`,
	`identification`,
	`name`,
	`value` FROM information;

CREATE  VIEW `v_ele_payments` AS SELECT ROWNUM() `id`,
        `t`.`code` AS `code`,
        `t`.`serie_number` AS `number`,
        CONVERT(IF((`p`.`PAYMENT` = 'cash'), '01', '20') , CHAR) AS `way_pay`,
        CONVERT(IF((`p`.`PAYMENT` = 'cash'),
                'SIN UTILIZACION DEL SISTEMA FINANCIERO',
                'OTROS CON UTILIZACION DEL SISTEMA FINANCIERO'),
            CHAR) AS `name`,
        CAST(`p`.`total` AS DECIMAL (19, 2 )) AS `total`,
        CONVERT(NULL, DECIMAL (9)) AS `payment_deadline`,
        CONVERT(NULL, CHAR (20)) AS `unit_time`
    FROM
        ((`tickets` `t`
        JOIN `receipts` `r` ON ((`t`.`ID` = `r`.`ID`)))
        JOIN `payments` `p` ON ((`r`.`ID` = `p`.`RECEIPT`)))
    WHERE
        (`t`.`TICKETTYPE` = 0);

CREATE  VIEW `v_ele_report_invoices` AS select `j`.`id` AS `id`,
    `j`.`code` AS `code`,
    `j`.`number` AS `number`,
    `j`.`date` AS `date`,
    `j`.`total` AS `total`,
    `j`.`identification` AS `identification`,
    `j`.`legal_name` AS `legal_name`,
    (select
        `i`.`value`
    from
        `v_ele_information` `i`
    where
        `i`.`name` = 'Email'
        and `i`.`identification` = `j`.`identification`
    limit 1) AS `email`,
    ifnull((select `e`.`status` from `ele_documents` `e` where `e`.`code` = `j`.`code` and `e`.`number` = `j`.`number`), 'NO ENVIADO') AS `status`
from
    `v_ele_invoices` `j`
order by `j`.`number` desc, `j`.`number` desc;

CREATE VIEW `v_version` AS select 1 AS `id`,
    version() AS `version_database`;

CREATE VIEW `v_ele_users` AS select rownum() AS `id`,
    `u`.`name` AS `username`,
    `u`.`apppassword` AS `password`,
    if(`r`.`name` in ('Administrator role', 'Manager role'), 'Administrator', 'Normal') AS `role`
from
    (`people` `u`
join `roles` `r` on
    (`u`.`role` = `r`.`id`))
where
    `u`.`visible` = 1;

CREATE TABLE `subscriptions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(300) NOT NULL,
  `url` VARCHAR(900) NOT NULL,
  PRIMARY KEY (`id`));
