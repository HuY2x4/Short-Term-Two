CREATE DATABASE  IF NOT EXISTS `duanxueqi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `duanxueqi`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: duanxueqi
-- ------------------------------------------------------
-- Server version	5.6.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brand` (
  `brandName` varchar(45) NOT NULL,
  `brandRemark` varchar(45) DEFAULT NULL,
  `sortName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`brandName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES ('九牧','洁具',NULL),('九阳','电器',NULL),('亿家达','家具',NULL),('全友','家具',NULL),('卡贝','五金',NULL),('奥克斯','电器',NULL),('小熊','电器',NULL),('小米','',NULL),('我的日子','灯具',NULL),('戴安娜','背景墙',NULL),('摩恩','五金',NULL),('林氏木业','家具',NULL),('箭牌','洁具',NULL),('美的','电器',NULL),('雷士','灯具',NULL);
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `clientId` int(11) NOT NULL AUTO_INCREMENT,
  `clientName` varchar(45) DEFAULT NULL,
  `houseId` int(11) DEFAULT NULL,
  PRIMARY KEY (`clientId`),
  KEY `houseId_idx` (`houseId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'黄叶轩',1),(2,'张三',2),(3,'李四',3),(4,'aa',4),(5,'分红是的',5),(6,'三大',6);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `house` (
  `houseId` int(11) NOT NULL AUTO_INCREMENT,
  `houseAddress` varchar(45) DEFAULT NULL,
  `houseTotalArea` float DEFAULT NULL,
  `room` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`houseId`),
  KEY `userId_idx` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
INSERT INTO `house` VALUES (1,'拱墅区51号',100,12111,1),(2,'拱墅区52号',100,11001,1),(3,'拱墅区53好',100,11111,1),(4,'啥啊发顺丰卡',100,11112,8),(5,'as打算大所多',100,11112,9),(6,'案发生的',100,11112,10);
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materialbudget`
--

DROP TABLE IF EXISTS `materialbudget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materialbudget` (
  `mbId` int(11) NOT NULL AUTO_INCREMENT,
  `materialId` int(11) DEFAULT NULL,
  `roomId` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `totalPrice` int(11) DEFAULT NULL,
  PRIMARY KEY (`mbId`),
  KEY `materialId_idx` (`materialId`),
  KEY `roomId_idx` (`roomId`),
  CONSTRAINT `materialId` FOREIGN KEY (`materialId`) REFERENCES `materialinfo` (`materialId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `roomId` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialbudget`
--

LOCK TABLES `materialbudget` WRITE;
/*!40000 ALTER TABLE `materialbudget` DISABLE KEYS */;
INSERT INTO `materialbudget` VALUES (1,5,1,1,2790,2790),(2,6,1,1,699,699),(3,12,1,1,2249,2249),(4,15,1,1,497,497),(5,16,1,1,7,7),(6,17,1,1,9,9),(7,3,2,1,299,299),(8,6,2,1,699,699),(9,7,2,1,320,320),(10,5,3,1,2790,2790),(11,10,3,1,3290,3290),(12,12,3,1,2249,2249),(13,16,3,1,7,7),(14,17,3,1,9,9),(15,15,3,1,497,497),(16,3,4,1,299,299),(17,16,4,1,7,7),(18,1,4,1,899,899),(19,2,5,1,549,549),(20,16,5,1,7,7),(21,5,17,1,2790,2790),(22,8,22,1,2180,2180);
/*!40000 ALTER TABLE `materialbudget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materialinfo`
--

DROP TABLE IF EXISTS `materialinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materialinfo` (
  `materialId` int(11) NOT NULL AUTO_INCREMENT,
  `materialName` varchar(45) DEFAULT NULL,
  `sortName` varchar(45) DEFAULT NULL,
  `brandName` varchar(45) DEFAULT NULL,
  `specification` varchar(45) DEFAULT NULL,
  `version` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `unit` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`materialId`),
  KEY `sortId_idx` (`sortName`),
  KEY `brandId_idx` (`brandName`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialinfo`
--

LOCK TABLES `materialinfo` WRITE;
/*!40000 ALTER TABLE `materialinfo` DISABLE KEYS */;
INSERT INTO `materialinfo` VALUES (1,'一体式抽水马桶','洁具类','九牧','九牧卫浴卫生间一体式静音虹吸式抽水马桶节水防臭陶瓷连体坐便器 ','1166-A2','白色',899,'元'),(2,'淋浴花洒','洁具类','九牧','九牧大花洒淋浴花洒浴室全铜冷热水龙头蓬喷头升降花洒套装淋浴器 ',' 淋浴花洒套装1','爆款花洒',549,'元'),(3,'研磨粉碎机','电器类','九阳','九阳磨粉机家用超细电动五谷杂粮干磨打粉机中药材小型研磨粉碎机','JYS-M01','棕色',299,'元'),(4,'薄饼机','电器类','九阳','九阳电饼铛家用加深款电饼称小型迷你电煎锅烙薄饼机 2人-4人','JK30-J15','橙红色',159,'元'),(5,'组合家具','家具类','亿家达','林氏木业整装布艺沙发现代简约小户型经济型客厅储物组合家具2040','2040','银灰',2790,'元'),(6,'单人隐形床 ','家具类','亿家达','折叠床免安装 午休床加宽加固 成人办公室午睡简易床单人隐形床 ','经济型','酒红',699,'元'),(7,'电脑桌书桌','家具类','亿家达','北欧电脑桌书桌简易学习桌现代简约写字台家用转角儿童台式写字桌','1818','棕色',320,'元'),(8,'圆形阳台小沙发','家具类','亿家达','EVITAHOME简约单人沙发轻奢丝绒布艺沙发客厅卧室圆形阳台小沙发','ECC069','黑色',2180,'元'),(9,'现代时尚头层真皮床卧室储物双人床皮床','家具类','全友','全友家私皮艺床 现代时尚头层真皮床卧室储物双人床皮床105125','105125-PYC-DC','蓝/白',3899,'元'),(10,'榻榻米板式双人床','家具类','全友','维美家 北欧榻榻米板式双人床1.8米床1.5m现代简约日式主卧储物床','E9302','褐色 ',3290,'元'),(11,'梳妆台','家具类','全友','主卧成套家具 兔毛驼鸟毛圆床梳妆台衣柜E魅家居飞天卓尔全屋订制','DFNM-12','黑白',1995,'元'),(12,'奥克斯空调','电器类','奥克斯','AUX/奥克斯空调 大1.5匹变频冷暖静音挂机空调 KFR-35GW/BpNFW+3','KFR-35GW','白色',2249,'元'),(13,'半全自动8公斤洗衣机','电器类','奥克斯','AUX/奥克斯XPB80-98H半全自动8公斤洗衣机双桶筒缸大容量家用脱水','XPB80-98H','白色',318,'元'),(14,'小熊空气加湿器','电器类','小熊','小熊空气加湿器家用静音卧室孕妇婴儿室内小型迷你空调房大容量雾','JSQ-A30Q1','蓝绿色',99,'元'),(15,'欧普照明LED客厅灯','灯具类','我的日子','欧普照明LED客厅灯具大气长方形吸顶灯卧室灯现代简约大厅厨房','123','白色',497,'元'),(16,'大理石墙地砖','建材类','林氏木业','墙地配套砖灰色木纹内墙卫生间大理石墙地砖阳台釉面亮光砖300600','300600','褐色',7,'元/平方'),(17,'液压缓冲铰链','五金类','卡贝','卡贝304不锈钢橱柜门铰链飞机烟斗合页衣柜中弯阻尼液压缓冲铰链','56','白色',9,'元');
/*!40000 ALTER TABLE `materialinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msbinfo`
--

DROP TABLE IF EXISTS `msbinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msbinfo` (
  `msbId` int(11) NOT NULL AUTO_INCREMENT,
  `mbId` int(11) DEFAULT NULL,
  `sbId` int(11) DEFAULT NULL,
  `roomId` int(11) DEFAULT NULL,
  PRIMARY KEY (`msbId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msbinfo`
--

LOCK TABLES `msbinfo` WRITE;
/*!40000 ALTER TABLE `msbinfo` DISABLE KEYS */;
INSERT INTO `msbinfo` VALUES (1,1,1,1),(2,1,2,1),(3,2,3,1),(4,3,4,1),(5,4,4,1),(6,5,6,1),(7,7,7,2),(8,8,8,2),(9,9,7,2),(10,10,10,3),(11,10,11,3),(12,11,12,3),(13,12,13,3),(14,13,14,3),(15,15,13,3),(16,16,16,4),(17,17,17,4),(18,18,18,4),(19,19,19,5),(20,20,20,5),(21,21,21,17),(22,21,22,17),(23,22,23,22);
/*!40000 ALTER TABLE `msbinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msinfo`
--

DROP TABLE IF EXISTS `msinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msinfo` (
  `msId` int(11) NOT NULL AUTO_INCREMENT,
  `serviceId` int(11) DEFAULT NULL,
  `materialId` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`msId`),
  KEY `serviceId_idx` (`serviceId`),
  KEY `materialId_idx` (`materialId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msinfo`
--

LOCK TABLES `msinfo` WRITE;
/*!40000 ALTER TABLE `msinfo` DISABLE KEYS */;
INSERT INTO `msinfo` VALUES (1,1,1,1),(2,2,2,1),(3,3,3,1),(4,4,5,1),(5,5,5,1),(6,6,6,1),(7,3,7,1),(8,8,8,1),(9,9,9,1),(10,10,10,1),(11,11,12,1),(12,12,13,1),(13,6,14,1),(14,11,15,1),(15,15,16,1);
/*!40000 ALTER TABLE `msinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `roomId` int(11) NOT NULL AUTO_INCREMENT,
  `roomArea` float DEFAULT NULL,
  `houseId` int(11) DEFAULT NULL,
  `roomSort` int(11) DEFAULT NULL,
  `roomRemark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`roomId`),
  KEY `houseId_idx` (`houseId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,30,1,1,'睡觉房间'),(2,0,1,1,NULL),(3,0,1,2,NULL),(4,0,1,3,NULL),(5,0,1,4,NULL),(6,66,2,1,'主房间'),(7,0,2,4,NULL),(8,20,3,1,'20'),(9,0,3,2,NULL),(10,0,3,3,NULL),(11,0,3,4,NULL),(12,33,4,1,'333'),(13,0,4,2,NULL),(14,0,4,3,NULL),(15,0,4,4,NULL),(16,0,4,4,NULL),(17,11,5,1,'111'),(18,0,5,2,NULL),(19,0,5,3,NULL),(20,0,5,4,NULL),(21,0,5,4,NULL),(22,0,6,1,NULL),(23,0,6,2,NULL),(24,0,6,3,NULL),(25,0,6,4,NULL),(26,0,6,4,NULL);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicebudget`
--

DROP TABLE IF EXISTS `servicebudget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicebudget` (
  `sbId` int(11) NOT NULL AUTO_INCREMENT,
  `serviceId` int(11) DEFAULT NULL,
  `roomId` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `totalPrice` int(11) DEFAULT NULL,
  PRIMARY KEY (`sbId`),
  KEY `serviceId_idx` (`serviceId`),
  KEY `roomId_idx` (`roomId`),
  CONSTRAINT `roomId_sb` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `serviceId_sb` FOREIGN KEY (`serviceId`) REFERENCES `serviceinfo` (`serviceId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicebudget`
--

LOCK TABLES `servicebudget` WRITE;
/*!40000 ALTER TABLE `servicebudget` DISABLE KEYS */;
INSERT INTO `servicebudget` VALUES (1,4,1,1,1,'',100),(2,5,1,1,1,'',100),(3,6,1,1,1,'',50),(4,11,1,1,2,'',60),(5,11,1,1,2,'',60),(6,15,1,1,1,'',100),(7,3,2,1,1,'',20),(8,6,2,1,1,'',50),(9,3,2,1,1,'',20),(10,4,3,1,1,'',100),(11,5,3,1,1,'',100),(12,10,3,1,2,'',200),(13,11,3,1,2,'',60),(14,15,3,1,1,'',100),(15,11,3,1,2,'',60),(16,3,4,1,1,'',20),(17,15,4,1,1,'',100),(18,1,4,1,1,'',30),(19,2,5,1,1,'',20),(20,15,5,1,1,'',100),(21,4,17,1,1,'',100),(22,5,17,1,1,'',100),(23,8,22,1,2,'',50);
/*!40000 ALTER TABLE `servicebudget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serviceinfo`
--

DROP TABLE IF EXISTS `serviceinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `serviceinfo` (
  `serviceId` int(11) NOT NULL AUTO_INCREMENT,
  `serviceName` varchar(45) DEFAULT NULL,
  `serviceContent` varchar(45) DEFAULT NULL,
  `serviceLevel` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `count` varchar(45) DEFAULT NULL,
  `time` float DEFAULT NULL,
  PRIMARY KEY (`serviceId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviceinfo`
--

LOCK TABLES `serviceinfo` WRITE;
/*!40000 ALTER TABLE `serviceinfo` DISABLE KEYS */;
INSERT INTO `serviceinfo` VALUES (1,'装马桶','',1,30,'元',0.5),(2,'装花洒','',1,20,'元',0.5),(3,'洗','洗的很干净',1,20,'元',0.5),(4,'搬家具','开车过来',2,100,'元',1),(5,'装家具','',2,100,'元',1),(6,'清洗','上门服务',1,50,'元',1),(7,'洗','洗',1,60,'元',1),(8,'搬沙发','',1,50,'元',2),(9,'搬床','',1,60,'元',1),(10,'组装','',1,200,'元',2),(11,'装','',1,60,'元',2),(12,'搬','',1,100,'元',2),(13,'清洗','',1,60,'元',2),(14,'装','',1,100,'元',2),(15,'铺','',3,100,'元',1);
/*!40000 ALTER TABLE `serviceinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sort`
--

DROP TABLE IF EXISTS `sort`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sort` (
  `sortName` varchar(45) NOT NULL,
  `sortRemark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sortName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sort`
--

LOCK TABLES `sort` WRITE;
/*!40000 ALTER TABLE `sort` DISABLE KEYS */;
INSERT INTO `sort` VALUES ('五金类',''),('家具类',''),('建材类','地砖油漆等'),('洁具类',''),('灯具类',''),('电器类','');
/*!40000 ALTER TABLE `sort` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) DEFAULT NULL,
  `userAccount` varchar(45) DEFAULT NULL,
  `userPassword` varchar(45) DEFAULT NULL,
  `userLevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'黄叶轩','hyxzucc','123456',3),(5,'张三','123','123',3),(6,'123','123123','123123',1),(7,'火啊发放','hyxzuccc','123456',1),(8,'aa','aaaaaa','123456',1),(9,'ddd','dddddd','111111',1),(10,'啊发发','ffffff','111111',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-28 14:44:27
