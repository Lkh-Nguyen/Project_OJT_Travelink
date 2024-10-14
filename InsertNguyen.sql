﻿Use Travelink;
GO

--insert table Account--
select *
from Account
-----------------------------------------------------------------------------------------------------------------------------
INSERT INTO Account (Email, Password, Name, Gender, DateOfBirth, PhoneNumber,AvatarURL, Role, Status)
VALUES
--customer
('cus1@email.com', '123', N'Lê Kim Hoàng Nguyên', 'M', '1990-01-01', '0912345678','/Travelink/img_Avatar/nguyen.jpg',1,1),
('cus2@email.com', '123', N'Nguyễn Văn Duy An', 'M', '1990-01-01', '0912345678','/Travelink/img_Avatar/an.jpg',1,1),
('cus3@email.com', '123', N'Phạm Trọng Hùng', 'M', '1990-01-01', '0912345678','/Travelink/img_Avatar/hung.jpg',1,1),
('cus4@email.com', '123', N'Nguyễn Đình Hoàng Hải', 'M', '1990-01-01', '0912345678','/Travelink/img_Avatar/hai.jpg',1,1),
('cus5@email.com', '123', N'Hoàng Công Minh', 'M', '1990-01-01', '0912345678','/Travelink/img_Avatar/minh.jpg',1,1),

--hotelhost
('hot1@email.com', '123', N'Hotel Host 1', 'M', '1990-01-01', '0912345678','/Travelink/img_Avatar/nguyen.jpg',2,1),
('hot2@email.com', '123', N'Hotel Host 2', 'M', '1990-01-01', '0912345678','/Travelink/img_Avatar/minh.jpg',2,1),
--admin
('ad1@email.com', '123', N'Admin 1', 'M', '1990-01-01', '0912345678','/Travelink/img_Avatar/hai.jpg',3,1),
('ad2@email.com', '123', N'Admin 2', 'M', '1990-01-01', '0912345678','/Travelink/img_Avatar/an.jpg',3,1);
-----------------------------------------------------------------------------------------------------------------------------
select *
from Hotel

INSERT INTO Hotel (
  Name, Email, Star, Rating, PhoneNumber, Description, CheckInTimeStart, CheckInTimeEnd, CheckOutTimeStart, CheckOutTimeEnd, Address, Ward_ID, Status
) VALUES 

--da nang--
(N'Khách sạn và căn hộ Seashore', 'email1@gmail.com', 5,0, '0123456789',
 N'Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Seashore Hotel & Apartment sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
 Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Davue Hotel Da Nang sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
 Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Davue Hotel Da Nang.',
 '13:30:00', '14:00:00', '11:30:00', '12:00:00', N'15-16 Hoàng Sa, Mân Thái, Sơn Trà, Đà Nẵng, Việt Nam', 6337, 'ACTIVE'),
(N'Davue Hotel Da Nang', 'email2@gmail.com', 4,0, '0123456789',
 N'Davue Hotel Da Nang là đề xuất hàng đầu dành cho những tín đồ du lịch "bụi" mong muốn được nghỉ tại một khách sạn vừa thoải mái lại hợp túi tiền.
 Dành cho những du khách muốn du lịch thoải mái cùng ngân sách tiết kiệm, Davue Hotel Da Nang sẽ là lựa chọn lưu trú hoàn hảo, nơi cung cấp các tiện nghi chất lượng và dịch vụ tuyệt vời.
 Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Davue Hotel Da Nang',
  '13:30:00', '14:00:00', '11:30:00', '12:00:00', N'57-59 Đỗ Bí, Mỹ An, Ngũ Hành Sơn, Đà Nẵng, Việt Nam', 6337,'ACTIVE'),
(N'Cicilia Danang Hotels & Spa Powered by ASTON', 'email3@gmail.com', 3,0, '0123456789',
 N'Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Cicilia Danang Hotels & Spa Powered by ASTON.
 Cicilia Danang Hotels & Spa Powered by ASTON là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.
 Một trong những đặc điểm chính của khách sạn này là các liệu pháp spa đa dạng. Hãy nâng niu bản thân bằng các liệu pháp thư giãn, phục hồi giúp quý khách tươi trẻ thân, tâm.',
 '13:30:00', '14:00:00', '11:30:00', '12:00:00', N'06-10 Đỗ Bá, Bãi Biển T20, Phường Mỹ An, Mỹ An, Ngũ Hành Sơn, Đà Nẵng, Việt Nam', 6337,'ACTIVE'),
(N'Bay Capital Danang', 'email4@gmail.com', 2,0, '0123456789',
 N'Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Bay Capital Danang là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
 Khách sạn này là lựa chọn hoàn hảo cho các kỳ nghỉ mát lãng mạn hay tuần trăng mật của các cặp đôi. Quý khách hãy tận hưởng những đêm đáng nhớ nhất cùng người thương của mình tại Bay Capital Danang
 Bay Capital Danang là lựa chọn sáng giá dành cho những ai đang tìm kiếm một trải nghiệm xa hoa đầy thú vị trong kỳ nghỉ của mình. Lưu trú tại đây cũng là cách để quý khách chiều chuộng bản thân với những dịch vụ xuất sắc nhất và khiến kỳ nghỉ của mình trở nên thật đáng nhớ.',
  '13:30:00', '14:00:00', '11:30:00', '12:00:00', N'17 Quang Trung, Phường Hải Châu 1, Quận Hải Châu, Đà Nẵng, Việt Nam', 6337,'ACTIVE'),
(N'Khu nghỉ dưỡng và Spa Mikazuki Nhật Bản', 'email5@gmail.com', 1,0, '0123456789',
 N'Từ sự kiện doanh nghiệp đến họp mặt công ty, Da Nang – Mikazuki JAPANESE RESORTS & SPA cung cấp đầy đủ các dịch vụ và tiện nghi đáp ứng mọi nhu cầu của quý khách và đồng nghiệp.
 Dù quý khách muốn tổ chức một sự kiện hay các dịp kỷ niệm đặc biệt khác, Da Nang – Mikazuki JAPANESE RESORTS & SPA là lựa chọn tuyệt vời cho quý khách với phòng chức năng rộng lớn, được trang bị đầy đủ để sẵn sàng đáp ứng mọi yêu cầu.
 Hãy tận hưởng thời gian vui vẻ cùng cả gia đình với hàng loạt tiện nghi giải trí tại Da Nang – Mikazuki JAPANESE RESORTS & SPA, một nơi nghỉ tuyệt vời phù hợp cho mọi kỳ nghỉ bên người thân.',
  '13:30:00', '14:00:00', '11:30:00', '12:00:00', N'Đường Nguyễn Tất Thành, Phường Hòa Hiệp Nam, Hòa Hiệp Nam, Liên Chiểu, Đà Nẵng, Việt Nam', 6337,'ACTIVE');
-----------------------------------------------------------------------------------------------------------------
 select * 
 from Owned_Hotel

Insert into Owned_Hotel (Hotel_ID, Account_ID) values
(1,6),
(2,6),
(3,7),
(4,7),
(5,7);

-----------------------------------------------------------------------------------------------------------------
  update Province
  set URL = 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d245367.87556902596!2d107.91331720877241!3d16.072075925890392!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x314219c792252a13%3A0x1df0cb4b86727e06!2zxJDDoCBO4bq1bmcsIFZp4buHdCBOYW0!5e0!3m2!1svi!2s!4v1721269400250!5m2!1svi!2s'
  where Province_ID = 32


  update Province
  set URL = 'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d29793.980453126584!2d105.81636411845396!3d21.02277841921115!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab9bd9861ca1%3A0xe7887f7b72ca17a9!2zSMOgIE7hu5lpLCBWaeG7h3QgTmFt!5e0!3m2!1svi!2s!4v1721269219673!5m2!1svi!2s'
  where Province_ID = 1
-----------------------------------------------------------------------------------------------------------------
 --insert table Hotel_Image --
 select *
 from Hotel_Image

CREATE TABLE #TempURLs (
    URL NVARCHAR(MAX)
);
-- Insert the URLs into the temporary table
INSERT INTO #TempURLs (URL)
VALUES
('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20010910-20983969df0ca0525a15f20af9488f4c.jpeg?_src=imagekit&tr=c-at_max,h-360,q-40,w-640'),
('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20010910-ca79c5cfc41762e1963c20b8336afb05.jpeg?_src=imagekit&tr=c-at_max,h-360,q-40,w-640'),
('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20010910-8f5a52e0864d04a9277c4e66e4743f7a.jpeg?_src=imagekit&tr=c-at_max,h-360,q-40,w-640'),
('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20010910-f5552e5aaa82d9d1b8263b741ea12957.jpeg?_src=imagekit&tr=c-at_max,h-360,q-40,w-640'),
('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20010910-a184757c040c218aa85aed6644735011.jpeg?_src=imagekit&tr=c-at_max,h-360,q-40,w-640'),

('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011770-76da33ac4a4d1e377f606253e67c93c6.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011770-96fdc30e525f75f478d6cf9977234d70.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011770-8edf3caa310bfe8644baf32ac8a95e46.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20011770-6856c92e44088b0f32691dd2917067a0.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20062926-bdd09b2d0f2c9417b220faebaeb05bb4.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),

('https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20062926-2640d0bcf268a98fc7c45cf6c322121c.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/20062926-1557a5590743685128f98ad15f28b23e.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20062926-4efb40291d4870bdf6f41d5b48f79e73.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20062926-5770c550f8e19f83883844e605ab9e7d.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/Ixf4aptF5N2Qdfmh4fGGYhTN274kJXuNMkUAzpL5HuD9jzSxIGG5kZNhhHY-p7nw/hotel/asset/20062926-f65eeb99d8499329e2368ebfb454cf0d.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),

('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10024807-b0ce24c491643118ff49e215cd5aa99a.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10024807-f8a2fa44c2d7ba6b1d393c5283d6075d.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/dgXfoyh24ryQLRcGq00cIdKHRmotrWLNlvG-TxlcLxGkiDwaUSggleJNPRgIHCX6/hotel/asset/10024807-5e52206ed708143cc1dc249afe8a4af6.jpeg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/5000000/4630000/4624400/4624340/771a1a01_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/5000000/4630000/4624400/4624340/f8320d6c_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),

('https://ik.imagekit.io/tvlk/apr-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/5000000/4630000/4624400/4624340/570406d7_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/5000000/4630000/4624400/4624340/87f96b23_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/5000000/4630000/4624400/4624340/14698fd3_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/5000000/4630000/4624400/4624340/03621650_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968'),
('https://ik.imagekit.io/tvlk/apr-asset/TzEv3ZUmG4-4Dz22hvmO9NUDzw1DGCIdWl4oPtKumOg=/lodging/5000000/4630000/4624400/4624340/2af36bd1_z.jpg?_src=imagekit&tr=c-at_max,f-jpg,h-616,pr-true,q-100,w-968');

-- Loop to insert 15 random URLs for each Hotel_ID from 1 to 30
DECLARE @Hotel_ID INT = 1;

WHILE @Hotel_ID <= 5
BEGIN
    -- Insert 15 random URLs for the current Hotel_ID
    INSERT INTO Hotel_Image (URL, Hotel_ID)
    SELECT TOP 5 URL, @Hotel_ID
    FROM #TempURLs
    ORDER BY NEWID();

    -- Increment the Hotel_ID
    SET @Hotel_ID = @Hotel_ID + 1;
END;
 -----------------------------------------------------------------------------------------------------------------
  -----------------------------------------------------------------------------------------------------------------
  --insert table Favourite_Hotel--
select *
from Favourite_Hotel

-- Set up variables
DECLARE @HotelID INT = 1;
DECLARE @MaxHotelID INT = 5;

-- Loop through Hotel_ID values from 1 to 30 but only odd numbers
WHILE @HotelID <= @MaxHotelID
BEGIN
    -- Insert the record into Favourite_Hotel
    INSERT INTO Favourite_Hotel (Hotel_ID, Account_ID)
    VALUES (@HotelID, 1);
    -- Increment the Hotel_ID by 2 to get the next odd number
    SET @HotelID = @HotelID + 1;
END;
-----------------------------------------------------------------------------------------------------------------
 -----------------------------------------------------------------------------------------------------------------
--insert table Room--
select *
from Room

-- Create a table variable to hold room types
DECLARE @RoomTypes TABLE (
    Name NVARCHAR(255),
    Room_Description NVARCHAR(MAX),
    Capacity INT
);
-- Insert room types into the table variable
INSERT INTO @RoomTypes (Name, Room_Description, Capacity)
VALUES 
('Standard Room', 'This is a cozy room with one king-size bed', 2),
('Deluxe Suite', 'Spacious suite with a separate living area and a king-size bed', 4),
('Family Room', 'Room with two queen-size beds, suitable for families', 4);
-- Declare variables for the loop
DECLARE @HotelID INT = 1;
DECLARE @MaxHotelID INT = 5;
DECLARE @Price INT;
DECLARE @TotalRooms INT;
DECLARE @Status NVARCHAR(50) = 'ACTIVE';

-- Loop through Hotel_ID values from 1 to 30
WHILE @HotelID <= @MaxHotelID
BEGIN

    -- Loop through each room type
    DECLARE @Name NVARCHAR(255);
    DECLARE @Room_Description NVARCHAR(MAX);
    DECLARE @Capacity INT;
    
    DECLARE RoomCursor CURSOR FOR 
    SELECT Name, Room_Description, Capacity FROM @RoomTypes;
    
    OPEN RoomCursor;
    FETCH NEXT FROM RoomCursor INTO @Name, @Room_Description, @Capacity;
    
    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Generate random Price between 10000 and 50000
        SET @Price = FLOOR(RAND() * (50000 - 10000 + 1)) + 10000;
        
        -- Generate random TotalRooms between 1 and 5
        SET @TotalRooms = FLOOR(RAND() * (5 - 1 + 1)) + 1;
        
        -- Insert the record into Room
        INSERT INTO Room (Name, Room_Description, Capacity, Total_Rooms, Price, Hotel_ID, Status)
        VALUES (@Name, @Room_Description, @Capacity, @TotalRooms, @Price, @HotelID, @Status);
        
        FETCH NEXT FROM RoomCursor INTO @Name, @Room_Description, @Capacity;
    END;
    
    CLOSE RoomCursor;
    DEALLOCATE RoomCursor;
    
    -- Increment the Hotel_ID
    SET @HotelID = @HotelID + 1;
END;
 ----------------------------------------------------------------------------------------------------------------
 --INSERT INTO Room_Image-
select *
from Room_Image
order by Room_ID


-- Loop to insert 15 random URLs for each Hotel_ID from 1 to 30
DECLARE @Room_ID INT = 1;

WHILE @Room_ID <= 15
BEGIN
    -- Insert 15 random URLs for the current Hotel_ID
    insert into Room_Image(URL, Room_ID)
    SELECT TOP 5 URL, @Room_ID
    FROM #TempURLs
    ORDER BY NEWID();

    -- Increment the Hotel_ID
    SET @Room_ID = @Room_ID + 1;
END;

----------------------------------------------------------------------------------------------------------------
--INSERT INTO Facility--
select *
from Facility

INSERT INTO Facility (URL, Name)
VALUES 
    ('bx bx-swim', N'Hồ bơi ngoài trời'),
    ('bx bxs-car', N'Xe đưa đón sân bay'),
    ('bx bxs-edit-alt', N'Phòng không hút thuốc'),
    ('bx bxs-cart-download', N'Chỗ đỗ xe miễn phí'),
    ('bx bx-wifi', N'WiFi miễn phí'),
    ('bx bxs-home-heart', N'Nhà hàng'),
    ('bx bx-child', N'Lễ tân 24 giờ'),
    ('bx bxs-wallet', N'Sân thượng / hiên'),
    ('bx bx-dumbbell', N'Phòng gym'),
    ('bx bx-spa', N'Spa & Wellness'),
    ('bx bxs-hot', N'Sauna'),
    ('bx bx-drink', N'Quầy bar'),
    ('bx bx-cab', N'Dịch vụ taxi'),
    ('bx bx-coffee', N'Quán cà phê'),
    ('bx bx-tv', N'TV màn hình phẳng'),
    ('bx bx-bed', N'Giường phụ'),
    ('bx bx-shield-quarter', N'Bảo vệ 24 giờ'),
    ('bx bxs-bath', N'Bồn tắm'),
    ('bx bx-bus', N'Dịch vụ xe buýt'),
    ('bx bxs-gas-pump', N'Trạm xăng'),
    ('bx bx-hotel', N'Khách sạn'),
    ('bx bx-shopping-bag', N'Cửa hàng quà tặng'),
    ('bx bx-gift', N'Dịch vụ quà tặng'),
    ('bx bx-landscape', N'Vườn'),
    ('bx bx-parking', N'Bãi đỗ xe'),
    ('bx bx-bicycle', N'Dịch vụ cho thuê xe đạp'),
    ('bx bx-restaurant', N'Khu ẩm thực'),
    ('bx bx-money', N'Máy ATM');
	----------------------------------------------------------------------------------------------------------------
--INSERT INTO Hotel_Facility--
select * 
from Hotel_Facility
-- Declare variables for the loops
DECLARE @Hotel_ID INT = 1;
DECLARE @MaxHotel_ID INT = 5;
DECLARE @Facility_ID INT;
DECLARE @InsertionCount INT;
DECLARE @MaxInsertions INT = 15;

-- Loop through Hotel_ID values from 1 to 30
WHILE @Hotel_ID <= @MaxHotel_ID
BEGIN
    -- Reset the insertion count for each hotel
    SET @InsertionCount = 1;
    
    -- Insert 10 random facility IDs for each hotel
    WHILE @InsertionCount <= @MaxInsertions
    BEGIN
        -- Generate a random Facility_ID between 1 and 18
        SET @Facility_ID = FLOOR(RAND() * (18 - 1 + 1)) + 1;
        
        -- Insert the record into Hotel_Facility
        INSERT INTO Hotel_Facility (Hotel_ID, Facility_ID)
        VALUES (@Hotel_ID, @Facility_ID);

        -- Increment the insertion count
        SET @InsertionCount = @InsertionCount + 1;
    END;
    
    -- Increment the Hotel_ID
    SET @Hotel_ID = @Hotel_ID + 1;
END;
----------------------------------------------------------------------------------------------------------------
--INSERT INTO Service--
select *
from Service 
INSERT INTO Service (Name) VALUES
('Big Pool'),
('Breakfast'),
('Buffet'),
('Airport Shuttle');
----------------------------------------------------------------------------------------------------------------
select *
from Hotel_Service

-- Declare variables for the loop
DECLARE @Hotel_ID INT = 1;
DECLARE @Service_ID INT;
DECLARE @Price INT;

-- Loop through Hotel_ID values from 1 to 30, excluding multiples of 5
WHILE @Hotel_ID <= 5
BEGIN
    -- Check if the current Hotel_ID is not a multiple of 5
    IF @Hotel_ID % 5 != 0
    BEGIN
        -- Generate a random Service_ID between 1 and 4
        SET @Service_ID = FLOOR(RAND() * (4 - 1 + 1)) + 1;
        
        -- Generate a random Price between 20000 and 100000 that is a multiple of 1000
        SET @Price = (FLOOR(RAND() * (100 - 20 + 1)) + 20) * 1000;
        
        -- Insert the record into Hotel_Service
        INSERT INTO Hotel_Service (Hotel_ID, Service_ID, Price)
        VALUES (@Hotel_ID, @Service_ID, @Price);
    END

    -- Increment the Hotel_ID
    SET @Hotel_ID = @Hotel_ID + 1;
END; 

----------------------------------------------------------------------------------------------------------------
-- Insert Bed
INSERT INTO Bed (Name, Description, URL) VALUES 
(N'Giường đơn', N'Rộng 90 - 130 cm', 'URL_for_single_bed_image'),
(N'Giường đôi', N'Rộng 131 - 150 cm', 'URL_for_double_bed_image'),
(N'Giường lớn (cỡ King)', N'Rộng 151 - 180 cm', 'URL_for_king_bed_image'),
(N'Giường cực lớn (cỡ Super-king)', N'Rộng 181 - 210 cm', 'URL_for_super_king_bed_image');
select *
from Bed
----------------------------------------------------------------------------------------------------------------

select *
from Room_Bed
-- Declare variables for the loops
DECLARE @Room_ID INT = 1;
DECLARE @MaxRoom_ID INT = 15;
DECLARE @Amount INT;
DECLARE @Bed_ID INT;

-- Loop through Room_ID values from 1 to @MaxRoom_ID
WHILE @Room_ID <= @MaxRoom_ID
BEGIN
    -- Set Bed_ID and Amount based on Room_ID modulo 3
    IF (@Room_ID % 3) = 1
    BEGIN
        SET @Bed_ID = 1;
        SET @Amount = 1;
    END
    ELSE IF (@Room_ID % 3) = 2
    BEGIN
        SET @Bed_ID = 2;
        SET @Amount = 2;
    END
    ELSE
    BEGIN
        SET @Bed_ID = 3;
        SET @Amount = 2;
    END;

    -- Insert record into Room_Bed
    INSERT INTO Room_Bed (Amount, Bed_ID, Room_ID)
    VALUES (@Amount, @Bed_ID, @Room_ID);

    -- Increment Room_ID
    SET @Room_ID = @Room_ID + 1;
END;

-----------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------------------------
delete Reserved_Room
DBCC CHECKIDENT ('Reserved_Room', RESEED, 0);

select *
from Reservation

DECLARE @Account_ID INT = 1;
INSERT INTO Reservation (Reservation_Date, Number_of_guests, CheckInDate, CheckOutDate, Total_Price, Payment_Method, Status, Account_ID)
VALUES
--feedback
('2024-05-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-03-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-04-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-03-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-02-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-01-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-04-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-05-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-02-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-01-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID);
-----------------------------------------------------------------------------------------------------------------------------------------

DECLARE @Account_ID INT = 2;
INSERT INTO Reservation (Reservation_Date, Number_of_guests, CheckInDate, CheckOutDate, Total_Price, Payment_Method, Status, Account_ID)
VALUES
--feedback
('2024-05-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-03-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-04-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-03-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-02-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-01-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-04-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-05-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-02-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-01-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID);
-----------------------------------------------------------------------------------------------------------------------------------------
DECLARE @Account_ID INT = 3;
INSERT INTO Reservation (Reservation_Date, Number_of_guests, CheckInDate, CheckOutDate, Total_Price, Payment_Method, Status, Account_ID)
VALUES
--feedback
('2024-05-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-03-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-04-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-03-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-02-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-01-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-04-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-05-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-02-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FEEDBACKED', @Account_ID),
('2024-01-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FEEDBACKED', @Account_ID);

----------------------------------------------------------------------------------------------------------------
select *
from Feedback

-- Create a procedure to insert random data
create PROCEDURE InsertRandomFeedbackData1
AS
BEGIN
    DECLARE @ReservationID INT = 1;
    DECLARE @MaxReservationID INT = 30;
    DECLARE @Rating INT;
    DECLARE @LikesCount INT;
    DECLARE @DislikesCount INT;
    DECLARE @Description NVARCHAR(255);
    DECLARE @Date DATE;

    -- Predefined list of feedback phrases
    DECLARE @FeedbackPhrases TABLE (Phrase NVARCHAR(255));
    INSERT INTO @FeedbackPhrases (Phrase)
    VALUES 
        ('The room was clean, and the staff was extremely friendly and helpful. We had a great stay and will return.'),
        ('Our experience at the hotel was wonderful. The service was excellent, the location was perfect, and the amenities were fantastic.'),
        ('We enjoyed our stay very much. The breakfast buffet was amazing, and the pool area was beautiful and relaxing.'),
        ('The hotel exceeded our expectations. The room was spacious and comfortable, and the staff went above and beyond to assist us.'),
        ('The overall experience was good. However, the WiFi connection was a bit unstable, which was a minor inconvenience during our stay.'),
        ('Loved the ambiance of the hotel. The decor was stylish, and the atmosphere was cozy. Would definitely recommend to others.'),
        ('The location of the hotel was ideal, close to many attractions and restaurants. The room was clean and well-maintained.'),
        ('We had a pleasant stay. The staff was courteous, and the food at the hotel restaurant was delicious. Would stay here again.'),
        ('The hotel provided excellent value for money. The facilities were top-notch, and the service was impeccable. Highly recommended.'),
        ('Had a few issues with the air conditioning, but the staff resolved them quickly. Overall, a positive experience.');

    WHILE @ReservationID <= @MaxReservationID
    BEGIN
        -- Generate a random Rating between 1 and 5
        SET @Rating = ROUND((RAND() * 4) + 1, 0);

        -- Generate a random LikesCount between 0 and 10
        SET @LikesCount = ROUND((RAND() * 10), 0);

        -- Generate a random DislikesCount between 0 and 10
        SET @DislikesCount = ROUND((RAND() * 10), 0);

        -- Generate a random Description from the predefined list
        SET @Description = (SELECT TOP 1 Phrase FROM @FeedbackPhrases ORDER BY NEWID());

        -- Generate a random Date within the last month
        SET @Date = DATEADD(DAY, -1 * FLOOR(RAND() * 30), GETDATE());

        -- Insert the record into Feedback
        INSERT INTO Feedback (Description, Rating, Date, LikesCount, DislikesCount, Reservation_ID)
        VALUES (@Description, @Rating, @Date, @LikesCount, @DislikesCount, @ReservationID);

        -- Increment the Reservation_ID
        SET @ReservationID = @ReservationID + 1;
    END;
END;

-- Execute the procedure to insert data
EXEC InsertRandomFeedbackData1;
-----------------------------------------------------------------------------------------------------------------
delete Reserved_Room
select *
from Reserved_Room
order by Reservation_ID

-- Chèn dữ liệu vào bảng Reserved_Room với Reservation_ID từ 1 đến 21, Room_ID random từ 1 đến 90, amount từ 1 đến 2
DECLARE @ReservationID INT = 1;

WHILE @ReservationID <= 30
BEGIN
    INSERT INTO Reserved_Room (Reservation_ID, Room_ID, Amount)
    VALUES (@ReservationID, FLOOR(RAND() * 3) + 1, FLOOR(RAND() * 2) + 1);

    SET @ReservationID = @ReservationID + 1;
END;
--------------------------------------------------------------------------------------------------------------------------------------------
DECLARE @Account_ID INT = 1;
INSERT INTO Reservation (Reservation_Date, Number_of_guests, CheckInDate, CheckOutDate, Total_Price, Payment_Method, Status, Account_ID)
VALUES
--cancel
('2024-06-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-05-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-05-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-04-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-03-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-02-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-01-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'CANCEL', @Account_ID),

--refunding
('2024-06-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-05-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-05-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-04-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-03-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-02-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-01-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'REFUNDING', @Account_ID),

--paid
('2024-07-02', 5, '2024-07-11', '2024-07-26', 2100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-07-05', 6, '2024-07-12', '2024-07-27', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-07-04', 7, '2024-07-13', '2024-07-28', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-07-05', 2, '2024-07-14', '2024-07-29', 3100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-07-06', 3, '2024-07-15', '2024-07-30', 2100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-07-02', 5, '2024-07-11', '2024-07-26', 2100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-07-05', 6, '2024-07-12', '2024-07-27', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-07-04', 7, '2024-07-13', '2024-07-28', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-07-05', 2, '2024-07-14', '2024-07-29', 3100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-07-06', 3, '2024-07-15', '2024-07-30', 2100.00, 'VIETQR', 'PAID', @Account_ID),

--Processing
('2024-05-03', 5, '2024-07-11', '2024-07-26', 2100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-05-05', 6, '2024-07-12', '2024-07-27', 4100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-05-04', 7, '2024-07-13', '2024-07-28', 4100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-05-05', 2, '2024-07-14', '2024-07-29', 3100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-05-06', 3, '2024-07-15', '2024-07-30', 2100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-06-03', 5, '2024-06-11', '2024-08-26', 2100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-01-05', 6, '2024-06-12', '2024-08-27', 4100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-02-04', 7, '2024-06-13', '2024-08-28', 4100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-03-05', 2, '2024-06-14', '2024-08-29', 3100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-04-06', 3, '2024-06-15', '2024-08-30', 2100.00, 'VIETQR', 'PROCESSING', @Account_ID),
--finish
('2024-05-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-03-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-04-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-03-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-02-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-01-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-04-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-05-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-02-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-01-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FINISHED', @Account_ID);

-----------------------------------------------------------------------------------------------------------------

select *
from Reserved_Room
order by Reservation_ID

-- Chèn dữ liệu vào bảng Reserved_Room với Reservation_ID từ 1 đến 21, Room_ID random từ 1 đến 90, amount từ 1 đến 2
DECLARE @ReservationID INT = 31;

WHILE @ReservationID <= 60
BEGIN
    INSERT INTO Reserved_Room (Reservation_ID, Room_ID, Amount)
    VALUES (@ReservationID, FLOOR(RAND() * 3) + 4, FLOOR(RAND() * 2) + 1);

    SET @ReservationID = @ReservationID + 1;
END;

-----------------------------------------------------------------------------------------------------------------------------------------
-- Chèn dữ liệu vào bảng Reserved_Room với Reservation_ID từ 1 đến 21, Room_ID random từ 1 đến 90, amount từ 1 đến 2
DECLARE @ReservationID INT = 61;

WHILE @ReservationID <= 84
BEGIN
    INSERT INTO Reserved_Room (Reservation_ID, Room_ID, Amount)
    VALUES (@ReservationID, FLOOR(RAND() * 3) + 7, FLOOR(RAND() * 2) + 1);

    SET @ReservationID = @ReservationID + 1;
END;
-----------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------
DECLARE @Account_ID INT = 1;
INSERT INTO Reservation (Reservation_Date, Number_of_guests, CheckInDate, CheckOutDate, Total_Price, Payment_Method, Status, Account_ID)
VALUES
--cancel
('2024-06-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-05-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-05-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-04-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-03-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-02-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-01-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'CANCEL', @Account_ID),

--refunding
('2024-06-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-05-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-05-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-04-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-03-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-02-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-01-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'REFUNDING', @Account_ID),

--paid
('2024-06-02', 5, '2024-06-11', '2024-06-26', 2100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-06-05', 6, '2024-06-12', '2024-06-27', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-06-04', 7, '2024-06-13', '2024-06-28', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-06-05', 2, '2024-06-14', '2024-06-29', 3100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-06-06', 3, '2024-06-15', '2024-06-30', 2100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-05-02', 5, '2024-06-11', '2024-06-26', 2100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-04-05', 6, '2024-06-12', '2024-06-27', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-03-04', 7, '2024-06-13', '2024-06-28', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-02-05', 2, '2024-06-14', '2024-06-29', 3100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-01-06', 3, '2024-06-15', '2024-06-30', 2100.00, 'VIETQR', 'PAID', @Account_ID);
--------------------------------------------------------------------------------------------------------------------------------------------
DECLARE @Account_ID INT = 2;
INSERT INTO Reservation (Reservation_Date, Number_of_guests, CheckInDate, CheckOutDate, Total_Price, Payment_Method, Status, Account_ID)
VALUES
--Processing
('2024-05-03', 5, '2024-06-11', '2024-07-26', 2100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-05-05', 6, '2024-06-12', '2024-07-27', 4100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-05-04', 7, '2024-06-13', '2024-07-28', 4100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-05-05', 2, '2024-06-14', '2024-07-29', 3100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-05-06', 3, '2024-06-15', '2024-07-30', 2100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-06-03', 5, '2024-06-11', '2024-08-26', 2100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-01-05', 6, '2024-06-12', '2024-08-27', 4100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-02-04', 7, '2024-06-13', '2024-08-28', 4100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-03-05', 2, '2024-06-14', '2024-08-29', 3100.00, 'VIETQR', 'PROCESSING', @Account_ID),
('2024-04-06', 3, '2024-06-15', '2024-08-30', 2100.00, 'VIETQR', 'PROCESSING', @Account_ID),
--finish
('2024-05-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-03-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-04-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-03-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-02-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-01-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-04-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-05-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-02-01', 5, '2024-05-13', '2024-05-30', 2100.00, 'VIETQR', 'FINISHED', @Account_ID),
('2024-01-02', 6, '2024-05-14', '2024-05-31', 4100.00, 'VIETQR', 'FINISHED', @Account_ID);

-----------------------------------------------------------------------------------------------------------------

select *
from Reserved_Room
order by Reservation_ID

-- Chèn dữ liệu vào bảng Reserved_Room với Reservation_ID từ 1 đến 21, Room_ID random từ 1 đến 90, amount từ 1 đến 2
DECLARE @ReservationID INT = 31;

WHILE @ReservationID <= 60
BEGIN
    INSERT INTO Reserved_Room (Reservation_ID, Room_ID, Amount)
    VALUES (@ReservationID, FLOOR(RAND() * 3) + 4, FLOOR(RAND() * 2) + 1);

    SET @ReservationID = @ReservationID + 1;
END;
-----------------------------------------------------------------------------------------------------------------

-- Chèn dữ liệu vào bảng Reserved_Room với Reservation_ID từ 1 đến 21, Room_ID random từ 1 đến 90, amount từ 1 đến 2
DECLARE @ReservationID INT = 85;

WHILE @ReservationID <= 114
BEGIN
    INSERT INTO Reserved_Room (Reservation_ID, Room_ID, Amount)
    VALUES (@ReservationID, FLOOR(RAND() * 3) + 10, FLOOR(RAND() * 2) + 1);

    SET @ReservationID = @ReservationID + 1;
END;

-----------------------------------------------------------------------------------------------------------------------------------------
	-- Chèn dữ liệu vào bảng Reserved_Room với Reservation_ID từ 1 đến 21, Room_ID random từ 1 đến 90, amount từ 1 đến 2
	DECLARE @ReservationID INT = 115;

	WHILE @ReservationID <= 138
	BEGIN
		INSERT INTO Reserved_Room (Reservation_ID, Room_ID, Amount)
		VALUES (@ReservationID, FLOOR(RAND() * 3) + 13, FLOOR(RAND() * 2) + 1);

		SET @ReservationID = @ReservationID + 1;
	END;
	-----------------------------------------------------------------------------------------------------------------
DECLARE @Account_ID INT = 5;
INSERT INTO Reservation (Reservation_Date, Number_of_guests, CheckInDate, CheckOutDate, Total_Price, Payment_Method, Status, Account_ID)
VALUES
--cancel
('2024-06-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-06-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-05-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-05-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-04-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-03-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-02-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'CANCEL', @Account_ID),
('2024-01-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'CANCEL', @Account_ID),

--refunding
('2024-06-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-06-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-05-01', 2, '2024-06-11', '2024-06-16', 3500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-05-02', 3, '2024-06-12', '2024-06-17', 500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-04-03', 4, '2024-06-13', '2024-06-18', 4500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-03-04', 1, '2024-06-14', '2024-06-19', 5500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-02-05', 3, '2024-06-15', '2024-06-20', 6500.00, 'VIETQR', 'REFUNDING', @Account_ID),
('2024-01-06', 5, '2024-06-16', '2024-06-21', 7500.00, 'VIETQR', 'REFUNDING', @Account_ID),

--paid
('2024-06-02', 5, '2024-06-11', '2024-06-26', 2100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-06-05', 6, '2024-06-12', '2024-06-27', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-06-04', 7, '2024-06-13', '2024-06-28', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-06-05', 2, '2024-06-14', '2024-06-29', 3100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-06-06', 3, '2024-06-15', '2024-06-30', 2100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-05-02', 5, '2024-06-11', '2024-06-26', 2100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-04-05', 6, '2024-06-12', '2024-06-27', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-03-04', 7, '2024-06-13', '2024-06-28', 4100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-02-05', 2, '2024-06-14', '2024-06-29', 3100.00, 'VIETQR', 'PAID', @Account_ID),
('2024-01-06', 3, '2024-06-15', '2024-06-30', 2100.00, 'VIETQR', 'PAID', @Account_ID);

delete Reservation
where Reservation_ID >= 139 and Reservation_ID <= 274

select *
from Reservation

DECLARE @Account_ID INT = 1;
INSERT INTO Reservation (Reservation_Date, Number_of_guests, CheckInDate, CheckOutDate, Total_Price, Payment_Method, Status, Account_ID)
VALUES
--cancel
('2024-06-01', 2, '2024-07-25', '2024-07-26', 3500.00, 'VIETQR', 'PAID', @Account_ID),
('2024-06-02', 3, '2024-07-27', '2024-07-28', 500.00, 'VIETQR', 'PAID', @Account_ID);

	DECLARE @ReservationID INT = 278;

	WHILE @ReservationID <= 279
	BEGIN
		INSERT INTO Reserved_Room (Reservation_ID, Room_ID, Amount)
		VALUES (@ReservationID, FLOOR(RAND() * 3) + 13, FLOOR(RAND() * 2) + 1);

		SET @ReservationID = @ReservationID + 1;
	END;


--------------------------------------------------------------------------------------------
select *
from Reported_Feedback

DECLARE @ReportTime DATETIME = GETDATE();
DECLARE @Reason NVARCHAR(255);
DECLARE @Feedback_ID INT;
DECLARE @Account_ID INT;

DECLARE @Reasons TABLE (Reason NVARCHAR(255));
INSERT INTO @Reasons (Reason) VALUES
    ('Vulgar and offensive review'),
    ('Duplicate reviews (junk information)'),
    ('Contains personal information'),
    ('Unauthorized advertising'),
    ('Inaccurate/misleading reviews (e.g. reviews and products do not match,...)');

DECLARE @ReasonsCount INT = (SELECT COUNT(*) FROM @Reasons);

SET @Feedback_ID = 1;

WHILE @Feedback_ID <= 30
BEGIN
    SET @Reason = (SELECT Reason FROM @Reasons ORDER BY NEWID() OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY);
    SET @Account_ID = 4;

    INSERT INTO Reported_Feedback (ReportTime, Reason, Feedback_ID, Account_ID)
    VALUES (@ReportTime, @Reason, @Feedback_ID, @Account_ID);

    SET @Feedback_ID = @Feedback_ID + 1;
END
--------------------------------------------------------------------------------------------
DECLARE @ReportTime DATETIME = GETDATE();
DECLARE @Reason NVARCHAR(255);
DECLARE @Feedback_ID INT;
DECLARE @Account_ID INT;

DECLARE @Reasons TABLE (Reason NVARCHAR(255));
INSERT INTO @Reasons (Reason) VALUES
    ('Vulgar and offensive review'),
    ('Duplicate reviews (junk information)'),
    ('Contains personal information'),
    ('Unauthorized advertising'),
    ('Inaccurate/misleading reviews (e.g. reviews and products do not match,...)');

DECLARE @ReasonsCount INT = (SELECT COUNT(*) FROM @Reasons);

SET @Feedback_ID = 1;

WHILE @Feedback_ID <= 30
BEGIN
    SET @Reason = (SELECT Reason FROM @Reasons ORDER BY NEWID() OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY);
    SET @Account_ID = 2;

    INSERT INTO Reported_Feedback (ReportTime, Reason, Feedback_ID, Account_ID)
    VALUES (@ReportTime, @Reason, @Feedback_ID, @Account_ID);

    SET @Feedback_ID = @Feedback_ID + 1;
END

--------------------------------------------------------------------------------------------
DECLARE @ReportTime DATETIME = GETDATE();
DECLARE @Reason NVARCHAR(255);
DECLARE @Feedback_ID INT;
DECLARE @Account_ID INT;

DECLARE @Reasons TABLE (Reason NVARCHAR(255));
INSERT INTO @Reasons (Reason) VALUES
    ('Vulgar and offensive review'),
    ('Duplicate reviews (junk information)'),
    ('Contains personal information'),
    ('Unauthorized advertising'),
    ('Inaccurate/misleading reviews (e.g. reviews and products do not match,...)');

DECLARE @ReasonsCount INT = (SELECT COUNT(*) FROM @Reasons);

SET @Feedback_ID = 1;

WHILE @Feedback_ID <= 30
BEGIN
    SET @Reason = (SELECT Reason FROM @Reasons ORDER BY NEWID() OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY);
    SET @Account_ID = 3;

    INSERT INTO Reported_Feedback (ReportTime, Reason, Feedback_ID, Account_ID)
    VALUES (@ReportTime, @Reason, @Feedback_ID, @Account_ID);

    SET @Feedback_ID = @Feedback_ID + 1;
END

delete MonthlyPayment
INSERT INTO MonthlyPayment (Month, Year, Amount, Status, PaymentTime, Hotel_ID)
VALUES
(1, 2024, 5000, 'PAID', '2024-01-15 10:30:00', 1),
(2, 2024, 7000, 'PAID', '2024-02-18 12:45:00', 2),
(3, 2024, 6000, 'PAID', '2024-03-20 09:00:00', 3),
(4, 2024, 8000, 'NOT PAID', '2024-04-22 11:30:00', 4),
(5, 2024, 7500, 'PAID', '2024-05-25 14:00:00', 5),
(6, 2024, 5000, 'NOT PAID', '2024-06-15 10:30:00', 1),
(7, 2024, 7000, 'PAID', '2024-07-18 12:45:00', 2),
(8, 2024, 6000, 'NOT PAID', '2024-08-20 09:00:00', 3),
(9, 2024, 8000, 'NOT PAID', '2024-09-22 11:30:00', 4),
(10, 2024, 7500, 'PAID', '2024-10-25 14:00:00', 5),
(11, 2024, 6500, 'PAID', '2024-11-10 08:30:00', 1),
(12, 2024, 7000, 'NOT PAID', '2024-12-12 13:45:00', 2),
(1, 2025, 6000, 'PAID', '2025-01-15 10:00:00', 3),
(2, 2025, 8000, 'NOT PAID', '2025-02-18 15:30:00', 4),
(3, 2025, 7500, 'PAID', '2025-03-20 14:00:00', 5),
(4, 2025, 5000, 'NOT PAID', '2025-04-22 09:30:00', 1),
(5, 2025, 7000, 'PAID', '2025-05-25 12:45:00', 2),
(6, 2025, 6000, 'NOT PAID', '2025-06-15 11:00:00', 3),
(7, 2025, 8000, 'NOT PAID', '2025-07-18 13:30:00', 4),
(8, 2025, 7500, 'PAID', '2025-08-20 16:00:00', 5),
(9, 2025, 5000, 'NOT PAID', '2025-09-22 08:00:00', 1),
(10, 2025, 7000, 'PAID', '2025-10-25 14:45:00', 2),
(11, 2025, 6000, 'NOT PAID', '2025-11-10 09:30:00', 3),
(12, 2025, 8000, 'NOT PAID', '2025-12-12 12:00:00', 4),
(1, 2026, 7500, 'PAID', '2026-01-15 10:30:00', 5),
(2, 2026, 5000, 'NOT PAID', '2026-02-18 11:45:00', 1),
(3, 2026, 7000, 'PAID', '2026-03-20 14:00:00', 2),
(4, 2026, 6000, 'NOT PAID', '2026-04-22 15:30:00', 3),
(5, 2026, 8000, 'NOT PAID', '2026-05-25 16:00:00', 4),
(6, 2026, 7500, 'PAID', '2026-06-15 10:00:00', 5);

-------------------------------------------------------------
insert into Refunding_Reservation(Cancel_Date,Amount,Status,Reservation_ID) values 
('2024-06-05',3500,'NOT PAID',43),
('2024-06-06',500,'NOT PAID',44),
('2024-06-07',4500,'NOT PAID',45),
('2024-06-08',5500,'NOT PAID',46),
('2024-06-09',6500,'NOT PAID',47),
('2024-06-10',7500,'NOT PAID',48);
