CREATE DATABASE  IF NOT EXISTS `jukebox` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `jukebox`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: jukebox
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist` (
  `playlist_id` int NOT NULL AUTO_INCREMENT,
  `playlist_name` varchar(60) NOT NULL,
  `song_list` varchar(200) NOT NULL,
  PRIMARY KEY (`playlist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'vibesss','5,3,2,6,9'),(2,'Chillin','1,4,8,15,12'),(3,'Mood','7,10,11,13,14'),(11,'slowed + reverbed','1,4,6,7'),(17,'snehahahA','10,11'),(18,'Pillya','3,6,11');
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `song` (
  `song_id` int NOT NULL AUTO_INCREMENT,
  `song_name` varchar(60) NOT NULL,
  `artist_name` varchar(60) NOT NULL,
  `genre_name` varchar(60) NOT NULL,
  `song_duration` double NOT NULL,
  `album_name` varchar(60) NOT NULL,
  `file_path` varchar(200) NOT NULL,
  PRIMARY KEY (`song_id`),
  KEY `genre_name` (`genre_name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (1,'Dandelions','Ruth B',' R&B',2.3,'Safe Haven','src/main/resources/songs/Ruth B. - Dandelions (Lyrics) (Slowed + Reverb).wav'),(2,'Gangster','Labrinth','Electronic',2.3,'Euphoria','src/main/resources/songs/Labrinth – Gangster (Official Audio) _ Euphoria (Original Score from the HBO Series).wav'),(3,'On my Own','TroyBoi','Trap[EDM]',3.55,'V!bez,Vol.4','src/main/resources/songs/TroyBoi - On My Own (feat. Nefera).wav'),(4,'telepatia','Kali Uchis','Pop',2.4,'Sin Miedo','src/main/resources/songs/Kali Uchis – telepatía [Official Audio].wav'),(5,'You should see me in a crown','Billie Eilish','Electropop',3,'Euphoria','src/main/resources/songs/Billie Eilish - you should see me in a crown (Vertical Video).wav'),(6,'Stay Alive','Jungkook','KPop',3.3,'Stay Alive','src/main/resources/songs/Jungkook - Stay Alive (Prod. SUGA of BTS) 「Audio」.wav'),(7,'Escape Plan','Travis Scott','Hip-Hop',2.29,'Mafia','src/main/resources/songs/Travis Scott - ESCAPE PLAN (Official Audio).wav'),(8,'Laugh now cry later','Drake','Hip-Hop',4.21,'Laugh now cry later','src/main/resources/songs/Drake - Laugh Now Cry Later (Official Music Video) ft. Lil Durk.wav'),(9,'River','Bishop Briggs','Indie',3.36,'Church of Scars','src/main/resources/songs/Bishop Briggs - River.wav'),(10,'Mount Everest','Labrinth','R&B',2.37,'Euphoria','src/main/resources/songs/Labrinth - Mount Everest (Official Audio).wav'),(11,'Shutdown','Blackpink','Kpop',3.1,'Born Pink','src/main/resources/songs/BLACKPINK - ‘Shut Down’ M_V.wav'),(12,'Schedule','Tegi Pannu','Punjabi',2.28,'Schedule','src/main/resources/songs/Schedule.wav'),(13,'No Way','DONGURIZU','Hip-Hop',3.03,'4EP1','src/main/resources/songs/Dongurizu - No Way (Lyrics).wav'),(14,'Ric Flair Drip','Offset ','Hip-Hop',2.52,'Without Warning','src/main/resources/songs/Offset-_-Metro-Boomin-_Ric-Flair-Drip_-_Official-Audio_.wav'),(15,'Heartbreak Anniversary','Giveon','R&B',3.18,'Take Time','src/main/resources/songs/Giveon-Heartbreak-Anniversary-_Audio_.wav');
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'jukebox'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-25 20:51:21
