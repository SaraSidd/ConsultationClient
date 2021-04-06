-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 10, 2021 at 03:18 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ebsagrop_ocs`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `AdminId` int(6) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Cnic` bigint(13) NOT NULL,
  `Mobile` bigint(11) DEFAULT NULL,
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AdminId`, `Name`, `Cnic`, `Mobile`, `UserName`, `Password`) VALUES
(1, 'rao fahim', 4230161786253, 3337309969, 'rao', '123'),
(2, 'Owais', 4230165423314, 3333832079, 'owaiskhan', 'abcd'),
(3, 'Abdullah', 4565323464646, 3320349711, 'ghaznavi', '1234'),
(4, 'Manish', 6564613454545, 3453409797, 'mani', 'ab12');

-- --------------------------------------------------------

--
-- Table structure for table `communication`
--

CREATE TABLE `communication` (
  `ComId` int(6) NOT NULL,
  `DocId` int(6) NOT NULL,
  `PatientId` int(6) NOT NULL,
  `Message` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `communication`
--

INSERT INTO `communication` (`ComId`, `DocId`, `PatientId`, `Message`) VALUES
(1, 1, 1, '0');

-- --------------------------------------------------------

--
-- Table structure for table `consultationhistory`
--

CREATE TABLE `consultationhistory` (
  `HistoryId` int(6) NOT NULL,
  `DocId` int(6) NOT NULL,
  `PatientId` int(6) NOT NULL,
  `ComId` int(6) NOT NULL,
  `PrescriptionId` int(6) NOT NULL,
  `DataId` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `DataId` int(6) NOT NULL,
  `DocId` int(6) DEFAULT NULL,
  `PatientId` int(6) DEFAULT NULL,
  `HistoryId` int(6) DEFAULT NULL,
  `video` blob DEFAULT NULL,
  `Image` varchar(100) DEFAULT NULL,
  `Audio` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `devices`
--

CREATE TABLE `devices` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `token` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `devices`
--

INSERT INTO `devices` (`id`, `email`, `token`) VALUES
(1, 'owwii@hotmail.com', 'sdfgw3rt54t3fgd'),
(2, 'aziz@gmail.com', 'cTy3v_3cUYE:APA91bH8Q1XR0yC5UQNzsj1GkL6j7k0RjHIc0o55xsWR_hzyt7z1_1JphQwVTt1MeCwNie4ekBxSnSLK3Hsu0VRo3CNsSqRLqwWDa2ZwkjIpaRVD7IaZG78s9wa5sxuMuq_N-CTYfmQW'),
(3, 'a@gmail.com', 'cTy3v_3cUYE:APA91bH8Q1XR0yC5UQNzsj1GkL6j7k0RjHIc0o55xsWR_hzyt7z1_1JphQwVTt1MeCwNie4ekBxSnSLK3Hsu0VRo3CNsSqRLqwWDa2ZwkjIpaRVD7IaZG78s9wa5sxuMuq_N-CTYfmQW'),
(4, 'abcd@gmail.com', 'cTy3v_3cUYE:APA91bH8Q1XR0yC5UQNzsj1GkL6j7k0RjHIc0o55xsWR_hzyt7z1_1JphQwVTt1MeCwNie4ekBxSnSLK3Hsu0VRo3CNsSqRLqwWDa2ZwkjIpaRVD7IaZG78s9wa5sxuMuq_N-CTYfmQW'),
(5, 'raofahim31@gmail.com', 'fc25H1N_lGw:APA91bFAoQtgw5x7vxhbrRplUBY4m8UHLUVG3n7pVtWTZzmZ2mKgBXvSPFNplZsUNKb3uvQTFuOP-zc9uV-5Y4yv_k0q8MJ8JzhsuSlqhIxZeRISElf1ziJmUeeygt7-SOgqwEumymYv'),
(6, 'manish@gmail.com', 'cIU8y4Q_JbY:APA91bHUx-qidQYcY0FV3125hs3pBO9xz_eUIpfRmDfEMzd6TxvfFTiRG1tdAd8cdrOqy6fBQmTiV8TGg6Y2F6rMb4g6umE5sU9cucXRqtya8dsLjJULpVhBRiI-pNBKtVDrxPGY24sq'),
(7, 'manish6@gmail.com', 'cIU8y4Q_JbY:APA91bHUx-qidQYcY0FV3125hs3pBO9xz_eUIpfRmDfEMzd6TxvfFTiRG1tdAd8cdrOqy6fBQmTiV8TGg6Y2F6rMb4g6umE5sU9cucXRqtya8dsLjJULpVhBRiI-pNBKtVDrxPGY24sq'),
(8, '', 'fc25H1N_lGw:APA91bFAoQtgw5x7vxhbrRplUBY4m8UHLUVG3n7pVtWTZzmZ2mKgBXvSPFNplZsUNKb3uvQTFuOP-zc9uV-5Y4yv_k0q8MJ8JzhsuSlqhIxZeRISElf1ziJmUeeygt7-SOgqwEumymYv');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `DocId` int(6) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Qualification` varchar(50) NOT NULL,
  `Speciality` varchar(50) NOT NULL,
  `Timings` varchar(50) NOT NULL,
  `Days` varchar(50) NOT NULL,
  `Experience` varchar(50) NOT NULL,
  `Age` int(6) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `Cnic` bigint(13) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Mobile` bigint(11) NOT NULL,
  `Status` varchar(50) NOT NULL,
  `DeleteStatus` int(11) NOT NULL,
  `Rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`DocId`, `Name`, `Qualification`, `Speciality`, `Timings`, `Days`, `Experience`, `Age`, `Gender`, `Cnic`, `Email`, `Password`, `Mobile`, `Status`, `DeleteStatus`, `Rating`) VALUES
(1, 'Rao Naeem', 'mbbs,frcs', 'General Physician', '6pm-9pm ', 'Mon-Fri', '20 years', 58, 'male', 4230161786253, 'raomnaeem60@gmail.com', 'rao', 3337309969, '1', 0, 5),
(2, 'owais', 'MBBS,MRCP', 'Chest Physician', '2pm-4pm', 'Mon,Wed,Fri', '15 years', 45, 'male', 423016565894, 'owais@gmail.com', 'owais', 3333832079, '1', 0, 4),
(3, 'Dr. Shahnaz Azam', 'FCPS,MBBS', 'General Physician', '4pm-6pm', 'Fri-Sun', '5 years', 32, 'Female', 4230161786542, 'dr.shahnaz_azam@hotmail.com', 'abcd', 3232783332, '1', 0, 4),
(4, 'Dr. Saleem Uz Zaman', 'MBBS,MRCP', 'Chest Specialist', '11am-2pm', 'Mon-Wed', '20', 48, 'Male', 4263241289652, 'info@shalamarhospital.org.pk', '1234567', 3337309969, '1', 0, 5),
(5, 'Dr. Faisal Ilyas', 'MBBS. Masters In Diabetes & Endocrinology', 'Diabetologist', '2pm-8pm', 'Mon-Wed', '4 years', 32, 'Male', 6547863214589, 'faisal9_dr@yahoo.com', '2121', 3332704879, '1', 1, 3),
(6, 'Dr. Syed Imran Ali', 'MBBS', 'General Physician', '10pm-12am', 'Mon-Fri', '5', 33, 'Male', 6895113547896, 'abbu@gmail.com', '123', 3213666889, '1', 0, 2),
(7, 'Dr. Muhammad Ashraf Wahla', 'M.B.B.S , DCH , MD (PEDIATRICS)', 'Child Specialist', '6pm-10pm', 'Mon-Sat', '15', 42, 'Male', 7895213647892, 'dr.ashrafwahla@gmail.com', '123', 3008689722, '1', 0, 4),
(8, 'Dr.  Naheed Nabi', 'FCPS,FRCPS,MBBS', 'General Physician', '6pm-9pm', 'Sun', '18', 44, 'Male', 4220145286235, 'naheednabi11@gmail.com', 'nabi123', 33334930051, '1', 0, 4),
(9, 'Dr. Mohammad Javaid Shah Aftab', 'MBBS, DGO, MCPS, MD, MAFC, MEHSR', 'Chest Specialist', '12am-2pm', 'Mon-Thurs', '20', 47, 'Male', 4230165894563, 'javaidshah32@hotmail.com', '1234', 3333832079, '1', 0, 5),
(10, 'rauf', 'MBBS', 'General Physician', '2pm-4pm', 'Mon-thurs', '6', 35, 'male', 4230146845644, 'rauf@gmail.com', '2233', 3452341788, '', 0, 3),
(11, 'saeed', 'frcps', '', '', '', '', 20, 'male', 4771286253885, 'saeed@gmail.com', '1111', 3452123856, '', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `files`
--

CREATE TABLE `files` (
  `Fid` int(11) NOT NULL,
  `PatientId` int(11) NOT NULL,
  `Url` varchar(100) NOT NULL,
  `Name` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `files`
--

INSERT INTO `files` (`Fid`, `PatientId`, `Url`, `Name`) VALUES
(1, 1, 'CV.docx', ''),
(2, 1, 'VID-20170422-WA0002.mp4', ''),
(3, 2, 'Screenshot_2017-07-02-05-33-36.png', ''),
(4, 2, 'Proposals.pptx', ''),
(5, 2, 'CV.docx', ''),
(12, 1, 'IMG_20170410_175936.jpg', ''),
(6, 1, 'Screenshot_2017-07-02-05-33-36.png', ''),
(7, 1, 'Proposals.pptx', ''),
(13, 1, 'IMG-20170327-WA0000.jpg', ''),
(14, 3, 'IMG_20170420_150712.jpg', ''),
(31, 4, 'Screenshot_2017-05-30-02-51-50.png', ''),
(30, 0, 'IMG-20170703-WA0020.jpg', ''),
(27, 2, 'Screenshot_2017-06-28-17-42-34.png', ''),
(29, 0, 'Balance(magazine)-03-2.3.001-bigpicture_03_2.jpg', ''),
(28, 2, 'Screenshot_2017-06-28-17-20-56.png', ''),
(32, 0, 'IMG-20201120-WA0014.jpeg', ''),
(33, 2, 'IMG-20201123-WA0006.jpg', ''),
(34, 2, 'VID-20201123-WA0001.mp4', '');

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE `messages` (
  `id` int(11) NOT NULL,
  `message` varchar(100) NOT NULL,
  `users_id` int(11) NOT NULL,
  `reciever_id` int(11) NOT NULL DEFAULT 4,
  `sentat` timestamp NOT NULL DEFAULT current_timestamp(),
  `SendBy` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `message`, `users_id`, `reciever_id`, `sentat`, `SendBy`) VALUES
(1, 'this is ok', 1, 4, '2017-01-10 15:08:36', 'Patient'),
(2, 'are u serious?', 3, 4, '2017-04-13 21:33:00', 'Patient'),
(3, 'what', 3, 4, '2017-04-13 21:33:05', ''),
(4, 'kya be?', 4, 4, '2017-04-13 21:41:55', ''),
(5, 'ao', 3, 4, '2017-04-13 21:42:14', ''),
(6, 'aya msg?', 3, 4, '2017-04-13 21:46:10', 'Patient'),
(7, 'u', 4, 4, '2017-04-13 21:46:55', 'Patient'),
(8, 'naacho', 4, 4, '2017-04-19 19:36:05', ''),
(9, 'aap kon?', 6, 4, '2017-04-21 16:21:10', 'Patient'),
(10, 'main h', 7, 4, '2017-04-21 16:24:41', ''),
(11, 'g', 4, 4, '2017-04-21 16:32:07', 'Patient'),
(12, 't', 7, 4, '2017-04-21 16:32:23', 'Doctor'),
(13, 'ap kon?', 7, 4, '2017-04-21 21:04:41', 'Patient'),
(14, 'kyaa hai', 4, 4, '2017-04-21 21:05:55', ''),
(15, 'h bhai', 4, 4, '2017-04-21 21:07:47', ''),
(16, 'kesi hai?', 4, 4, '2017-04-21 21:20:55', ''),
(17, 'set', 6, 4, '2017-04-21 21:22:02', ''),
(18, 'hello sir', 4, 2, '2017-06-28 19:00:00', ''),
(19, 'Assalam u alaikum doctor', 4, 2, '2017-06-28 19:00:00', 'Patient'),
(23, 'm m', 3, 4, '2017-06-29 14:00:14', 'Patient'),
(22, 'message', 3, 4, '2017-04-13 21:33:00', 'Doctor'),
(24, 'ms', 3, 4, '2017-06-29 14:00:14', 'Patient'),
(25, 'popopop', 3, 4, '2017-06-29 14:04:00', 'Doctor'),
(26, 'h', 3, 4, '2017-06-29 14:09:15', 'Doctor'),
(27, 'k', 3, 4, '2017-06-29 14:12:38', 'Patient'),
(28, 'lm', 3, 4, '2017-06-29 14:17:45', ''),
(29, '56l', 3, 4, '2017-06-29 14:19:28', ''),
(30, 'mmmmm', 3, 4, '2017-06-29 14:21:37', ''),
(31, 'kkkk', 3, 4, '2017-06-29 14:27:19', ''),
(32, '98uuu', 3, 4, '2017-06-29 14:27:48', ''),
(33, 'ink', 3, 4, '2017-06-29 14:29:22', ''),
(34, 'dddd', 3, 4, '2017-06-29 14:29:35', ''),
(35, 'Dr sahab', 3, 4, '2017-06-29 16:31:49', ''),
(36, 'hello, Dr', 4, 1, '2017-06-29 16:36:35', ''),
(37, 'hey', 4, 1, '2017-06-29 19:00:00', ''),
(38, '??', 4, 1, '2017-06-30 03:04:37', ''),
(39, '?', 4, 1, '2017-06-30 03:04:45', ''),
(43, 'ghytt', 4, 1, '2017-06-30 07:56:30', ''),
(42, 'hello sir', 4, 7, '2017-06-29 19:00:00', ''),
(44, 'ghytt', 4, 1, '2017-06-30 07:56:30', ''),
(45, 'h', 4, 1, '2017-06-30 08:01:40', ''),
(46, 'm', 4, 1, '2017-06-30 08:03:19', ''),
(47, 'aala', 4, 1, '2017-06-30 08:03:41', ''),
(48, 'why are you here?', 4, 1, '2017-06-30 08:07:35', ''),
(49, 'because i am a hero', 4, 1, '2017-06-30 08:21:39', ''),
(50, 'chal jhuta', 4, 1, '2017-06-30 08:24:15', ''),
(51, 'hello brother', 4, 2, '2017-06-29 19:00:00', ''),
(52, 'my leg is injured', 6, 8, '2017-06-29 19:00:00', ''),
(53, 'hi', 1, 2, '0000-00-00 00:00:00', 'Docter'),
(54, 'hello', 3, 4, '2017-07-03 16:37:42', 'Doctor'),
(55, 'kya hai bhai', 3, 4, '2017-07-03 16:39:47', 'Doctor'),
(56, 'hello sir', 4, 1, '2017-07-03 22:09:27', 'Patient'),
(57, 'dear doctor, i am having flu and headache since two days just after the rainfall', 4, 3, '2017-07-03 19:00:00', ''),
(58, 'salam', 4, 4, '2017-07-03 19:00:00', ''),
(59, 'wsalam', 0, 0, '0000-00-00 00:00:00', ''),
(60, 'wasalam', 4, 4, '2017-07-03 23:57:22', 'Doctor'),
(61, 'bhai', 4, 1, '2017-07-04 05:09:22', 'Patient'),
(62, 'hello', 4, 2, '2017-07-04 05:10:57', 'Doctor'),
(63, 'IMG_20170704_100100.jpg', 4, 2, '2017-07-04 05:12:09', 'Doctor'),
(64, 'hi', 4, 2, '2017-07-03 19:00:00', ''),
(65, 'haan bhai kaisa hai ?', 4, 2, '2017-07-04 07:33:08', 'Patient'),
(66, 'bhai reply karo na', 4, 2, '2017-07-04 08:13:43', 'Patient'),
(67, 'karra hun bhai', 4, 2, '2017-07-04 08:14:03', 'Doctor'),
(68, 'h bhai', 4, 2, '2017-07-04 08:14:24', 'Patient'),
(69, 'bhai mujhy bemar ho rahi hai kya karun ?', 4, 2, '2017-07-04 08:16:21', 'Patient'),
(70, 'tasveer bhejun ?', 4, 2, '2017-07-04 08:16:27', 'Patient'),
(71, 'ðŸ˜›', 4, 2, '2017-07-04 08:16:38', 'Patient'),
(72, '', 4, 2, '2017-07-03 19:00:00', ''),
(73, 'Balance(magazine)-03-2.3.001-bigpicture_03_2.jpg', 4, 2, '2017-07-04 08:17:31', 'Doctor'),
(74, '', 4, 2, '2017-07-03 19:00:00', ''),
(75, 'kya bhai', 4, 2, '2017-07-04 09:35:41', 'Doctor'),
(76, 'IMG-20170703-WA0020.jpg', 4, 2, '2017-07-04 09:37:03', 'Doctor'),
(77, 'acidity', 4, 2, '2017-07-03 19:00:00', ''),
(78, 'kya hwa', 4, 2, '2017-07-04 09:59:55', 'Patient'),
(79, 'hello', 2, 4, '2017-07-04 10:00:34', 'Patient'),
(80, 'kuch', 4, 2, '2017-07-04 10:01:31', 'Doctor'),
(81, 'kya', 4, 2, '2020-11-20 13:21:30', 'Doctor'),
(82, 'kuch nai', 4, 2, '2020-11-20 13:21:39', 'Doctor'),
(83, 'new', 4, 2, '2020-11-20 13:44:58', 'Doctor'),
(84, '', 4, 2, '2020-11-20 13:44:59', 'Doctor'),
(85, 'new.jpeg', 4, 2, '2020-11-20 13:45:06', 'Doctor'),
(86, 'is the file', 4, 2, '2020-11-20 13:45:12', 'Doctor'),
(87, 'hi', 2, 4, '2020-11-23 07:30:22', 'Patient'),
(88, 'bye', 2, 4, '2020-11-23 07:30:33', 'Patient'),
(89, 'dandruff in beard and itching aswell', 2, 2, '2020-11-23 07:44:42', ''),
(90, 'ponka', 2, 7, '2021-01-14 11:11:12', ''),
(91, 'ghanta', 2, 7, '2021-01-14 11:11:23', 'Patient'),
(92, 'mera sar dukh rha hy', 2, 2, '2021-03-10 14:15:04', ''),
(93, 'kyun?', 2, 2, '2021-03-10 14:16:20', 'Doctor'),
(94, 'bcoz', 2, 2, '2021-03-10 14:16:32', 'Patient'),
(95, 'whyy', 2, 2, '2021-03-10 14:16:40', 'Doctor'),
(96, '', 2, 2, '2021-03-10 14:16:54', 'Patient');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `PatientId` int(6) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Age` int(6) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `Mobile` bigint(11) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Cnic` bigint(13) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Delivery` varchar(200) DEFAULT NULL,
  `Password` varchar(50) NOT NULL,
  `Status` varchar(20) NOT NULL,
  `DeleteStatus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`PatientId`, `Name`, `Age`, `Gender`, `Mobile`, `Address`, `Cnic`, `Email`, `Delivery`, `Password`, `Status`, `DeleteStatus`) VALUES
(1, 'Owais Khan', 22, 'Male', 923333832079, 'Pechs Block 2, house no. b-4', 4230161786234, 'owaiskhan402@gmail.com', NULL, 'owais6878', '1', 0),
(2, 'Rao Fahim', 23, 'Male', 923337309969, 'DHA phase II ext. cartian court 52-p house no. B-6', 4230161786253, 'rao@gmail.com', NULL, '1234', '1', 0),
(3, 'Abdullah Ahmed', 22, 'Male', 923320349711, 'shahfaisal no.3', 4230145659542, 'abdullah@yahoo.com', NULL, 'abcd1', '1', 0),
(4, 'Muneeb Siddique', 25, 'Male', 923138553332, 'DHA Phase V Opposite to Dandeez', 4230156894569, 'muneeb@gmail.com', '', '1234', '1', 0),
(5, 'Manish Kumar', 25, 'Male', 3453409797, 'B-4 Block 6 KAECHS', 5623145896523, 'Mani112@hotmail.com', '', '123', '1', 0),
(6, 'Uzair Khan', 30, 'Male', 923002937122, 'house# 44 Street 21 Korangi no. 5', 9654723156583, 'UzairKhan@gmail.com', '', '123', '1', 0),
(7, 'Afaaq Ahmed', 23, 'Male', 923312195606, 'Shahfaisal no.5', 4220562134598, 'maak@gmail.com', '', 'abcd', '1', 0),
(8, 'Tooba Batool', 26, 'Female', 3223508100, 'karimabad', 4128595479823, 'tooba_bat@yahoo.com', '', 'qwer', '1', 1),
(12, 'wasif', 17, 'male', 3332326754, 'liaqtabad', 4220134289663, 'wasif@gmail.com', '', '1212', '', 0),
(13, 'usama', 23, 'male', 3005526119, 'jauhar', 0, '', '', '', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy`
--

CREATE TABLE `pharmacy` (
  `PharmacyId` int(6) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Phone` bigint(13) NOT NULL,
  `Status` varchar(50) NOT NULL,
  `DeleteStatus` int(11) NOT NULL,
  `Rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacy`
--

INSERT INTO `pharmacy` (`PharmacyId`, `Name`, `Username`, `Password`, `Address`, `Phone`, `Status`, `DeleteStatus`, `Rating`) VALUES
(1, 'Time Medicos', 'Afaaq Ahmed', 'ahmed', 'Afzal Apartments SB-12, \r\nKDA Scheme No.1\r\nKarachi, Pakistan.', 922134932278, '1', 0, 5),
(2, 'Sunny Medicos', 'Owais', 'abc123', 'DHA Phase I defence market', 92333832079, '', 0, 4),
(3, 'Al- Madina Medical Store', 'Rao', 'rao123', 'F-112, Mini Market, Phase-II, Defence View, Karachi, Sindh, Pakistan', 923337309969, '', 0, 3),
(4, 'BlueLine Pharmacy', 'abdullah', 'abcd', 'Building 1-C, Muslim Commercial Lane 2\r\nKhayaban-e-ittehad,\r\nPhase 6, D.H.A.\r\nKarachi, Pakistan', 923002903669, '1', 0, 3),
(10, 'Health Mart Pharmacy', 'Ansari', 'xyz', 'No 1, Dolmen Mall \r\nMarine Drive, Block 4 \r\nClifton\r\nKarachi, Pakistan.', 922135293980, '1', 0, 2),
(11, 'Afroz Medical Store', 'afroz', 'step12', 'Plot No 119, Shop No 4, Block 8, Ground Floor, Azizabad, Karachi', 923002165439, '1', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `PrescriptionId` int(6) NOT NULL,
  `DocId` int(6) DEFAULT NULL,
  `PatientId` int(6) DEFAULT NULL,
  `PharmacyId` int(6) DEFAULT NULL,
  `Medicines` varchar(100) DEFAULT NULL,
  `Time` varchar(25) DEFAULT NULL,
  `OrderStatus` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`PrescriptionId`, `DocId`, `PatientId`, `PharmacyId`, `Medicines`, `Time`, `OrderStatus`) VALUES
(2, 1, 2, 2, '5 glucometer strips', NULL, ''),
(3, 2, 1, 2, '2 strips of glucovance 5/80', NULL, ''),
(15, 1, 1, 1, 'panadol', '0000-00-00', ''),
(18, 1, 1, 1, 'Tablet panadol 500mg 3 times a day tablet flagyl 400 MG 2 times a day', '0000-00-00', 'Completed'),
(19, 1, 1, 1, 'Tablet panadol 500mg 3 times a day tablet flagyl 400 MG 2 times a day', '0000-00-00', 'Completed'),
(21, 1, 1, 1, 'flagyl 400 MG twice daily', '0000-00-00', 'Completed'),
(26, 1, 4, 2, 'ponstan', '', ''),
(29, 1, 4, 1, 'flagyl syrup 3 din tak Subha Sham', '', 'Completed'),
(32, 1, 4, 2, 'Panadol', '', ''),
(36, 1, 4, 1, 'ponstan', '', ''),
(40, 1, 4, 2, 'flux', '', ''),
(41, 1, 4, 2, 'claritek', '', ''),
(42, 1, 4, 1, 'claritek', '', ''),
(43, 1, 4, 3, 'Glucometer strips 20', NULL, 'Completed'),
(44, 1, 4, 1, 'hello hello', '', ''),
(45, 1, 4, 1, 'Lenovo', '', ''),
(46, 1, 4, 1, 'random', '', ''),
(47, 1, 4, 1, 'hello hello', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `gcmtoken` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `gcmtoken`) VALUES
(1, 'rao', 'rao@rao.com', ''),
(2, 'rao2', 'rao2@rao2.com', ''),
(3, 'Owais', 'owaiskhan402@gmail.com', 'eVokaq9dCFU:APA91bHy5hozLNu6HP85NapL0Tf9FhIl2Xrkbx7pGn3Fja0RTwfnmRbDo2ySjHTAtFIQWA-NxUOmPoyK7tYMbdXND8O-hMQWynr4g6g9Zq4MQF9cuOr6uJlcIE4qaEC2C15x1-u7H-YD'),
(4, 'salman', 'sal@yahoo.com', 'eVokaq9dCFU:APA91bHy5hozLNu6HP85NapL0Tf9FhIl2Xrkbx7pGn3Fja0RTwfnmRbDo2ySjHTAtFIQWA-NxUOmPoyK7tYMbdXND8O-hMQWynr4g6g9Zq4MQF9cuOr6uJlcIE4qaEC2C15x1-u7H-YD'),
(6, 'afaaq', 'h@hotmail.com', 'eV7rg9-V5j8:APA91bF8oruOr-Yep_Yg4Y5QZdEoefCMXDq-udm7bPNoq5w7z8GrFFhS9bAuZFBMU3Mti_DujATn2926Yp-aDtpT6djCh04d9OR80dTSVI-HEmTSFHpouMOQSVdg6WsNty0bI3pMjY6W'),
(7, 'shakoor', 'sh@yahoo.com', 'eVokaq9dCFU:APA91bHy5hozLNu6HP85NapL0Tf9FhIl2Xrkbx7pGn3Fja0RTwfnmRbDo2ySjHTAtFIQWA-NxUOmPoyK7tYMbdXND8O-hMQWynr4g6g9Zq4MQF9cuOr6uJlcIE4qaEC2C15x1-u7H-YD'),
(8, 'mushtaq', 'mu@gmail.com', 'eVokaq9dCFU:APA91bHy5hozLNu6HP85NapL0Tf9FhIl2Xrkbx7pGn3Fja0RTwfnmRbDo2ySjHTAtFIQWA-NxUOmPoyK7tYMbdXND8O-hMQWynr4g6g9Zq4MQF9cuOr6uJlcIE4qaEC2C15x1-u7H-YD');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`AdminId`),
  ADD UNIQUE KEY `UserName` (`UserName`);

--
-- Indexes for table `communication`
--
ALTER TABLE `communication`
  ADD PRIMARY KEY (`ComId`),
  ADD KEY `DocId` (`DocId`),
  ADD KEY `PatientId` (`PatientId`);

--
-- Indexes for table `consultationhistory`
--
ALTER TABLE `consultationhistory`
  ADD PRIMARY KEY (`HistoryId`),
  ADD KEY `DocId` (`DocId`),
  ADD KEY `PatientId` (`PatientId`),
  ADD KEY `ComId` (`ComId`),
  ADD KEY `PrescriptionId` (`PrescriptionId`),
  ADD KEY `DataId` (`DataId`);

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`DataId`),
  ADD KEY `DocId` (`DocId`),
  ADD KEY `PatientId` (`PatientId`);

--
-- Indexes for table `devices`
--
ALTER TABLE `devices`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`DocId`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indexes for table `files`
--
ALTER TABLE `files`
  ADD PRIMARY KEY (`Fid`),
  ADD KEY `PatientId` (`PatientId`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `messages_users` (`users_id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`PatientId`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indexes for table `pharmacy`
--
ALTER TABLE `pharmacy`
  ADD PRIMARY KEY (`PharmacyId`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`PrescriptionId`),
  ADD KEY `PatientId` (`PatientId`),
  ADD KEY `DocId` (`DocId`),
  ADD KEY `PharmacyId` (`PharmacyId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `AdminId` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `communication`
--
ALTER TABLE `communication`
  MODIFY `ComId` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `consultationhistory`
--
ALTER TABLE `consultationhistory`
  MODIFY `HistoryId` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `data`
--
ALTER TABLE `data`
  MODIFY `DataId` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `devices`
--
ALTER TABLE `devices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `DocId` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `files`
--
ALTER TABLE `files`
  MODIFY `Fid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `PatientId` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `pharmacy`
--
ALTER TABLE `pharmacy`
  MODIFY `PharmacyId` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `PrescriptionId` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `communication`
--
ALTER TABLE `communication`
  ADD CONSTRAINT `communication_ibfk_1` FOREIGN KEY (`DocId`) REFERENCES `doctor` (`DocId`),
  ADD CONSTRAINT `communication_ibfk_2` FOREIGN KEY (`PatientId`) REFERENCES `patient` (`PatientId`);

--
-- Constraints for table `consultationhistory`
--
ALTER TABLE `consultationhistory`
  ADD CONSTRAINT `consultationhistory_ibfk_1` FOREIGN KEY (`DocId`) REFERENCES `doctor` (`DocId`),
  ADD CONSTRAINT `consultationhistory_ibfk_2` FOREIGN KEY (`PatientId`) REFERENCES `patient` (`PatientId`),
  ADD CONSTRAINT `consultationhistory_ibfk_3` FOREIGN KEY (`ComId`) REFERENCES `communication` (`ComId`),
  ADD CONSTRAINT `consultationhistory_ibfk_4` FOREIGN KEY (`PrescriptionId`) REFERENCES `prescription` (`PrescriptionId`),
  ADD CONSTRAINT `consultationhistory_ibfk_5` FOREIGN KEY (`DataId`) REFERENCES `data` (`DataId`);

--
-- Constraints for table `data`
--
ALTER TABLE `data`
  ADD CONSTRAINT `data_ibfk_1` FOREIGN KEY (`DocId`) REFERENCES `doctor` (`DocId`),
  ADD CONSTRAINT `data_ibfk_2` FOREIGN KEY (`PatientId`) REFERENCES `patient` (`PatientId`);

--
-- Constraints for table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`PatientId`) REFERENCES `patient` (`PatientId`),
  ADD CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`DocId`) REFERENCES `doctor` (`DocId`),
  ADD CONSTRAINT `prescription_ibfk_3` FOREIGN KEY (`PharmacyId`) REFERENCES `pharmacy` (`PharmacyId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
