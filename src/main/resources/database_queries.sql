--create database 'jukebox'.
create database `jukebox`;

--create table 'Song' and added columns
create table `jukebox`.`song` (
`song_id` int not null primary key,
`song_name` varchar(60) not null,
`artist_name` varchar(60) not null,
`genre_name` varchar(60) not null,
`song_duration` double not null,
`album_name` varchar(60) not null
);

--
