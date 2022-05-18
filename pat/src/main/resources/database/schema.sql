DROP TABLE IF EXISTS USER;

CREATE TABLE "USER"(
    "USER_ID" VARCHAR(400) NOT NULL PRIMARY KEY,
    "USER_NAME" VARCHAR(400) NOT NULL,
    "USER_PASSWORD" VARCHAR(400) NOT NULL,
    "USER_EMAIL" VARCHAR(400) NOT NULL,
    "USER_AGE" NUMERIC NOT NULL
);

DROP TABLE IF EXISTS SONG;
CREATE TABLE "SONG"(
    "SONG_ID" VARCHAR(400) NOT NULL PRIMARY KEY,
    "SONG_NAME" VARCHAR(400) NOT NULL,
    "SONG_ARTIST" VARCHAR(400) NOT NULL,
    "SONG_ALBUM" VARCHAR(400) NOT NULL,
    "SONG_DURATION" NUMERIC NOT NULL
);

DROP TABLE IF EXISTS ALBUM;
CREATE TABLE "ALBUM"(
    "ALBUM_ID" VARCHAR(400) NOT NULL PRIMARY KEY,
    "ALBUM_NAME" VARCHAR(400) NOT NULL,
    "ALBUM_ARTIST" VARCHAR(400) NOT NULL,
    "ALBUM_DATE" VARCHAR(400) NOT NULL,
    "ALBUM_IMAGE" VARCHAR(400) NOT NULL,
    "ALBUM_TRACKS" NUMERIC NOT NULL
);

DROP TABLE IF EXISTS ARTIST;
CREATE TABLE "ARTIST"(
    "ARTIST_ID" VARCHAR(400) NOT NULL PRIMARY KEY,
    "ARTIST_NAME" VARCHAR(400) NOT NULL,
    "ARTIST_IMAGE" VARCHAR(400) NOT NULL,
    "ARTIST_GENRES" VARCHAR(400) NOT NULL,
    "ARTIST_FOLLOWERS" NUMERIC NOT NULL
);

DROP TABLE IF EXISTS EPISODE;
CREATE TABLE "EPISODE"(
    "EPISODE_ID" VARCHAR(400) NOT NULL PRIMARY KEY,
    "EPISODE_NAME" VARCHAR(400) NOT NULL,
    "EPISODE_PUBLISHER" VARCHAR(400) NOT NULL,
    "EPISODE_DESCRIPTION" VARCHAR(400) NOT NULL,
    "EPISODE_IMAGE" VARCHAR(400) NOT NULL,
    "EPISODE_TRACKS" NUMERIC NOT NULL
);


DROP TABLE IF EXISTS FAVOURITE;
CREATE TABLE FAVOURITE(

    "USER_ID" VARCHAR(400) NOT NULL,
    "FAV_ID" VARCHAR(400) NOT NULL,
    "TIPO" VARCHAR(45) NOT NULL,
    PRIMARY KEY ("USER_ID","FAV_ID")
);
