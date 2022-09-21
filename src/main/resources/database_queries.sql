--create database 'jukebox'.
create database `jukebox`;

--create table 'Song' and add columns
create table `jukebox`.`song` (
`song_id` int not null primary key,
`song_name` varchar(60) not null,
`artist_name` varchar(60) not null,
`genre_name` varchar(60) not null,
`song_duration` double not null,
`album_name` varchar(60) not null
);

--create table 'Playlist' and add columns
create table playlist (
playlist_id int not null primary key,
playlist_name varchar(60),
genre_name varchar(60),
song_list varchar(200)
);
