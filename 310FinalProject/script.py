import csv
print("hello")
insertStr = """-- MySQL Administrator dump 1.4
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
    BeginDate DATE,
    EndDate DATE,
    State VARCHAR(20),
    PropertyDamage FLOAT,
    CropDamage FLOAT,
    InjuriesDirect INT,
    DeathsDirect INT, 
    Magnitude FLOAT,
    MagnitudeType VARCHAR(5),
    event_narrative VARCHAR(500),
    episode_narrative VARCHAR(500),
    PRIMARY KEY(EventID)
) ENGINE=InnoDB;

CREATE TABLE location (
    EventID INT NOT NULL,
    LocationIndex INT,
    EpisodeID INT,
    Town VARCHAR(20),
    Latitude FLOAT,
    Longitude FLOAT,
    PRIMARY KEY(EventID, LocationIndex),
    FOREIGN KEY (EventID) REFERENCES storm(EventID)
) ENGINE=InnoDB;

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
) ENGINE=InnoDB;

CREATE TABLE tornadoDetails (
    EventID INT NOT NULL,
    tor_f_scale VARCHAR(5),
    tor_length FLOAT,
    tor_width INT,
    PRIMARY KEY(EventID),
    FOREIGN KEY (EventID) REFERENCES storm(EventID)
) ENGINE=InnoDB;

CREATE TABLE fatality (
    FatalityID INT NOT NULL,
    EventID INT,
    Age INT,
    Gender VARCHAR(20),
    Time DATE,
    Location VARCHAR(5),
    Type VARCHAR(5),
    PRIMARY KEY(FatalityID),
    FOREIGN KEY (EventID) REFERENCES storm(EventID)
) ENGINE=InnoDB;

"""
# storm table
with open('data_files/Storm_2020.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    i = 0
    for row in csv_reader:
        # if(i==1000):
        #     break
        if(i!=0):
            #Date Start Formatting: 'YYYY-MM-DD'
            dateStart = row[3].split("/")
            dayStartVal = dateStart[1]
            if(len(dayStartVal) == 1):
                dayStartVal = "0"+dayStartVal
            monthStartVal = dateStart[0]
            if(len(monthStartVal) == 1):
                monthStartVal = "0"+monthStartVal
            dateStartStr = dateStart[2]+"-"+monthStartVal+"-"+dayStartVal

            #Date End Formatting: 'YYYY-MM-DD'
            dateEnd = row[4].split("/")
            dayEndVal = dateEnd[1]
            if(len(dayEndVal) == 1):
                dayEndVal = "0"+dayEndVal
            monthEndVal = dateEnd[0]
            if(len(monthEndVal) == 1):
                monthEndVal = "0"+monthEndVal
            dateEndStr = dateEnd[2]+"-"+monthEndVal+"-"+dayEndVal
            
            #propDMG Formatting:
            propDMG = 0.0
            if(row[6]==""):
                continue
            elif(row[6][-1] == 'M'):
                propDMG = float(row[6][0:-1]) * 1000000
            elif(row[6][-1] == 'K'):
                propDMG = float(row[6][0:-1]) * 100000
            
            #cropDMG Formatting:
            cropDMG = 0.0
            if(row[7] == ""):
                continue
            elif(row[7][len(row[7])-1] == 'M'):
                propDMG = float(row[7][0:-1]) * 1000000
            elif(row[7][len(row[7])-1] == 'K'):
                propDMG = float(row[7][0:-1]) * 100000
            
            #magnitude formatting
            magnitude = 0.0
            if(row[12]!=""):
                magnitude = row[12]
            
            insertStr+="INSERT INTO storm VALUES "
            insertStr += "("+row[0]+","+row[1]+",\""+row[2]+"\",\""+dateStartStr+"\",\""+dateEndStr+"\",\""\
                        +row[5]+"\","+str(propDMG)+","+str(cropDMG)+","+str(row[8])+","+str(row[10])+","+str(magnitude)+",\""\
                        +row[13]+"\",\""+row[14]+"\",\""+row[15]+"\");\n"
        i+=1

# location table
with open('data_files/Locations_2020.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    i = 0
    for row in csv_reader:
        if(i!=0): 
            insertStr+="INSERT INTO location VALUES ("+row[0]+","+row[1]+","+row[2]+",\""+row[3]+"\","+str(row[4])+","+str(row[5])+");\n"
        i+=1

# stormPath table
with open('data_files/StormPath_2020.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    i = 0
    for row in csv_reader:
        if(i!=0): 
            begin_range = 0
            if(row[38]!=""):
                begin_range = row[38]

            begin_lat = 0.0
            if(row[44]!=""):
                begin_lat = row[44]

            begin_lon = 0.0
            if(row[45]!=""):
                begin_lon = row[45]

            end_lat = 0.0
            if(row[36]!=""):
                end_lat = row[46]

            end_lon = 0.0
            if(row[47]!=""):
                end_lon = row[47]

            insertStr+="INSERT INTO stormPath VALUES ("+row[7]+","+str(begin_range)+",\""+row[39]+"\",\""+row[40]+"\","+str(begin_lat)+","+str(begin_lon)+","+str(end_lat)+","+str(end_lon)+");\n"
        i+=1

# tornadoDetails table
with open('data_files/TornadoDetails_2020.csv') as csv_file:
    csv_reader = csv.reader(csv_file, delimiter=',')
    i = 0
    for row in csv_reader:
        tor_f_scale = "N/A"
        if(row[31]!=""):
            tor_f_scale = row[31]

        tor_length = 0
        if(row[32]!=""):
            tor_length = row[32]

        tor_width = 0
        if(row[33]!=""):
            tor_width = row[33]

        if(i!=0 and tor_f_scale != "N/A"): 
            insertStr+="INSERT INTO tornadoDetails VALUES ("+row[7]+",\""+tor_f_scale+"\","+str(tor_length)+","+str(tor_width)+");\n"
        i+=1

f = open("weatherDB.sql", "w+")
f.write(insertStr)
f.close()