import csv
print("hello")
f = open("weatherDB.sql", "w+")
f.write("""-- MySQL Administrator dump 1.4
    --
    -- ------------------------------------------------------
    -- Server version	5.1.38-community


    /*!40101 SET @OLD_CHARACTER_SET_CLIENT= @@CHARACTER_SET_CLIENT */;
    /*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
    /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
    /*!40101 SET NAMES utf8 */;

    /*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
    /*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
    /*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


    --
    -- Create schema weatherdb
    --

CREATE DATABASE IF NOT EXISTS weatherdb;
USE weatherdb;

CREATE TABLE storm (
    EventID INT NOT NULL,
    EpisodeID INT,
    StormType VARCHAR(20),
    BeginDate DATETIME,
    EndDate DATETIME,
    State VARCHAR(20),
    PropertyDamage INT,
    CropDamage INT,
    InjuriesDirect INT,
    DeathsDirect INT, 
    Magnitude FLOAT,
    MagnitudeType VARCHAR(5),
    event_narrative VARCHAR(500),
    episode_narrative VARCHAR(500),
    PRIMARY KEY(EventID)
) ENGINE=InnoDB  CHARSET=utf8;

CREATE TABLE IF NOT EXISTS location (
    EventID INT NOT NULL,
    LocationIndex INT,
    EpisodeID INT,
    Longitude FLOAT,
    Town VARCHAR(20),
    Latitude FLOAT,
    PRIMARY KEY(EventID, LocationIndex),
    FOREIGN KEY (EventID) REFERENCES storm(EventID)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE stormPath (
    EventID INT NOT NULL,
    begin_range FLOAT,
    begin_azimuth VARCHAR(5),
    begin_location VARCHAR(20),
    begin_lat FLOAT,
    begin_lon FLOAT,
    end_lat FLOAT,
    end_lon FLOAT,
    PRIMARY KEY(EventID),
    FOREIGN KEY (EventID) REFERENCES storm(EventID)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE tornadoDetails (
    EventID INT NOT NULL,
    tor_f_scale VARCHAR(5),
    tor_length FLOAT,
    tor_width INT,
    PRIMARY KEY(EventID),
    FOREIGN KEY (EventID) REFERENCES storm(EventID)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE fatality (
    FatalityID INT NOT NULL,
    EventID INT,
    Age INT,
    Gender VARCHAR(20),
    Time DATE,
    Location VARCHAR(5),
    Type VARCHAR(5),
    PRIMARY KEY(EventID),
    FOREIGN KEY (EventID) REFERENCES storm(EventID)
) ENGINE=InnoDB CHARSET=utf8;
""")

f.close();
# with open('data_files/StormEvents_details_2020.csv') as csv_file:
#     csv_reader = csv.reader(csv_file, delimiter=',')
#     line_count = 0
#     for row in csv_reader:
#         if line_count == 0:
#             print(f'Column names are {", ".join(row)}')
#             line_count += 1
#         else:
#             print(f'\t{row[0]} works in the {row[1]} department, and was born in {row[2]}.')
#             line_count += 1
#     print(f'Processed {line_count} lines.')