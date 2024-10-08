-- Use the Travelink database
USE Travelink;


-- Create the Account Table
CREATE TABLE Account (
    Account_ID INT AUTO_INCREMENT PRIMARY KEY, -- Primary key with auto-increment
    Email VARCHAR(255) NOT NULL, -- Email field with VARCHAR type
    Password VARCHAR(255), -- Password field with VARCHAR type
    CMND VARCHAR(20), -- CMND field with VARCHAR type
    Name NVARCHAR(255) NOT NULL, -- Name field with NVARCHAR type
    Gender CHAR(1), -- Gender field with CHAR type
    DateOfBirth DATE,
    PhoneNumber VARCHAR(20),
    AvatarURL VARCHAR(255),
    Address NVARCHAR(255),
    Role INT NOT NULL,
    Status INT NOT NULL
);

-- Create the Hotel table
CREATE TABLE Hotel (
    Hotel_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name NVARCHAR(255),
    Email VARCHAR(255) NOT NULL UNIQUE,
    Star TINYINT,
    Rating FLOAT,
    PhoneNumber VARCHAR(20),
    Description TEXT, -- NVARCHAR(MAX) replaced by TEXT
    CheckInTimeStart TIME,
    CheckInTimeEnd TIME,
    CheckOutTimeStart TIME,
    CheckOutTimeEnd TIME,
    Status NVARCHAR(50),
    Ward_ID INT,
    Address NVARCHAR(255),
    URL_Business_License NVARCHAR(256),
    URL_Security_License NVARCHAR(256),
    URL_FireSafety_License NVARCHAR(256),
    Account_ID INT,
    FOREIGN KEY (Account_ID) REFERENCES Account(Account_ID) ON DELETE CASCADE
);

-- Create the Hotel_Image table
CREATE TABLE Hotel_Image (
    Hotel_Image_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name NVARCHAR(255), -- Optional: Name of the image
    URL VARCHAR(255) NOT NULL, -- Image URL
    Hotel_ID INT,
    FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID) ON DELETE CASCADE
);

-- Create Favourite_Hotel table
CREATE TABLE Favourite_Hotel (
    Hotel_ID INT,
    Account_ID INT,
    PRIMARY KEY (Hotel_ID, Account_ID),
    FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID) ON DELETE CASCADE,
    FOREIGN KEY (Account_ID) REFERENCES Account(Account_ID) ON DELETE CASCADE
);

-- Create the Room table
CREATE TABLE Room (
    Room_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name NVARCHAR(255) NOT NULL,
    Room_Description NVARCHAR(255),
    Capacity TINYINT,
    Total_Rooms TINYINT,
    Price INT,
    Status NVARCHAR(50) NOT NULL,
    Hotel_ID INT,
    FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID) ON DELETE CASCADE
);

-- Create the Room_Image table
CREATE TABLE Room_Image (
    Room_Image_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name NVARCHAR(255), -- Optional: Name of the image
    URL VARCHAR(255) NOT NULL, -- Image URL
    Room_ID INT,
    FOREIGN KEY (Room_ID) REFERENCES Room(Room_ID) ON DELETE CASCADE
);

-- Create the Facility table
CREATE TABLE Facility (
    Facility_ID INT AUTO_INCREMENT PRIMARY KEY,
    URL VARCHAR(255), -- Optional: URL for an icon or image representing the facility
    Name NVARCHAR(255) NOT NULL -- Name of the facility
);

-- Create the Hotel_Facility table
CREATE TABLE Hotel_Facility (
    Hotel_ID INT NOT NULL,
    Facility_ID INT NOT NULL,
    PRIMARY KEY (Hotel_ID, Facility_ID),
    FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID) ON DELETE CASCADE,
    FOREIGN KEY (Facility_ID) REFERENCES Facility(Facility_ID) ON DELETE CASCADE
);

-- Create the Room_Facility table
CREATE TABLE Room_Facility (
    Room_ID INT,
    Facility_ID INT,
    PRIMARY KEY (Room_ID, Facility_ID),
    FOREIGN KEY (Room_ID) REFERENCES Room(Room_ID) ON DELETE CASCADE,
    FOREIGN KEY (Facility_ID) REFERENCES Facility(Facility_ID) ON DELETE CASCADE
);

-- Create the Bed table
CREATE TABLE Bed (
    Bed_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name NVARCHAR(255),
    Description NVARCHAR(255),
    URL VARCHAR(255)
);

-- Create the Room_Bed table
CREATE TABLE Room_Bed (
    Room_Bed_ID INT AUTO_INCREMENT PRIMARY KEY,
    Amount TINYINT,
    Bed_ID INT,
    Room_ID INT,
    FOREIGN KEY (Bed_ID) REFERENCES Bed(Bed_ID) ON DELETE CASCADE,
    FOREIGN KEY (Room_ID) REFERENCES Room(Room_ID) ON DELETE CASCADE
);

-- Create the Service table
CREATE TABLE Service (
    Service_ID INT AUTO_INCREMENT PRIMARY KEY,
    Name NVARCHAR(255) NOT NULL
);

-- Create the Hotel_Service table
CREATE TABLE Hotel_Service (
    Hotel_Service_ID INT AUTO_INCREMENT PRIMARY KEY,
    Hotel_ID INT NOT NULL,
    Service_ID INT NOT NULL,
    Price INT,
    FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID) ON DELETE CASCADE,
    FOREIGN KEY (Service_ID) REFERENCES Service(Service_ID) ON DELETE CASCADE,
    UNIQUE (Hotel_ID, Service_ID) -- Maintain uniqueness for hotel-service combination
);

-- Create the Reservation table
CREATE TABLE Reservation (
    Reservation_ID INT AUTO_INCREMENT PRIMARY KEY,
    Reservation_Date DATETIME NOT NULL,
    Number_of_guests TINYINT NOT NULL,
    CheckInDate DATE NOT NULL,
    CheckOutDate DATE NOT NULL,
    Total_Price INT NOT NULL,
    Payment_Method NVARCHAR(50) NOT NULL,
    Status NVARCHAR(50) NOT NULL,
    Account_ID INT NOT NULL,
    FOREIGN KEY (Account_ID) REFERENCES Account(Account_ID) ON DELETE CASCADE,
    CHECK (CheckInDate >= Reservation_Date), -- Ensure check-in is after reservation date
    CHECK (CheckOutDate > CheckInDate)
);

-- Create the Reserved_Room table
CREATE TABLE Reserved_Room (
    Reserved_Room_ID INT AUTO_INCREMENT PRIMARY KEY,
    Amount TINYINT,
    Reservation_ID INT,
    Room_ID INT,
    FOREIGN KEY (Reservation_ID) REFERENCES Reservation(Reservation_ID) ON DELETE CASCADE,
    FOREIGN KEY (Room_ID) REFERENCES Room(Room_ID) ON DELETE CASCADE
);

-- Create the Refunding_Reservation table
CREATE TABLE Refunding_Reservation (
    Refunding_Reservation_ID INT AUTO_INCREMENT PRIMARY KEY,
    Cancel_Date DATETIME NOT NULL,
    Amount INT,
    Percentage INT, -- Spelling correction
    RefundTime DATETIME,
    Status NVARCHAR(50),
    Reservation_ID INT NOT NULL,
    FOREIGN KEY (Reservation_ID) REFERENCES Reservation(Reservation_ID) ON DELETE CASCADE,
    UNIQUE (Reservation_ID) -- Ensures only one pending cancellation request per reservation
);

-- Create the Feedback table
CREATE TABLE Feedback (
    Feedback_ID INT AUTO_INCREMENT PRIMARY KEY,
    Description TEXT, -- NVARCHAR(MAX) replaced by TEXT
    Rating TINYINT,
    Date DATE,
    LikesCount INT,
    DislikesCount INT,
    Reservation_ID INT,
    FOREIGN KEY (Reservation_ID) REFERENCES Reservation(Reservation_ID) ON DELETE CASCADE
);

-- Create the Reported_Feedback table
CREATE TABLE Reported_Feedback (
    Reported_Feedback_ID INT AUTO_INCREMENT PRIMARY KEY,
    ReportTime DATETIME,
    Reason NVARCHAR(255),
    Feedback_ID INT,
    Account_ID INT,
    FOREIGN KEY (Feedback_ID) REFERENCES Feedback(Feedback_ID),
    FOREIGN KEY (Account_ID) REFERENCES Account(Account_ID)
);

-- Create the Reason_Feedback table
CREATE TABLE Reason_Feedback (
    Reaction_ID INT AUTO_INCREMENT PRIMARY KEY,
    Reaction_Type NVARCHAR(256),
    Feedback_ID INT,
    Account_ID INT,
    FOREIGN KEY (Feedback_ID) REFERENCES Feedback(Feedback_ID) ON DELETE CASCADE,
    FOREIGN KEY (Account_ID) REFERENCES Account(Account_ID) ON DELETE CASCADE
);

-- Create the MonthlyPayment table
CREATE TABLE MonthlyPayment (
    Monthly_Payment_ID INT AUTO_INCREMENT PRIMARY KEY,
    Month TINYINT,
    Year SMALLINT,
    Amount INT,
    Status NVARCHAR(50),
    PaymentTime DATETIME,
    Hotel_ID INT,
    FOREIGN KEY (Hotel_ID) REFERENCES Hotel(Hotel_ID) ON DELETE CASCADE
);
