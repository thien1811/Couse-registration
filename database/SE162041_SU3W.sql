USE [master]
GO
/****** Object:  Database [SE162041_SU3W]    Script Date: 8/24/2022 10:39:45 AM ******/
CREATE DATABASE [SE162041_SU3W]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SE162041_SU3W', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\SE162041_SU3W.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SE162041_SU3W_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\SE162041_SU3W_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SE162041_SU3W] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SE162041_SU3W].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SE162041_SU3W] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET ARITHABORT OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SE162041_SU3W] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SE162041_SU3W] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SE162041_SU3W] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SE162041_SU3W] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET RECOVERY FULL 
GO
ALTER DATABASE [SE162041_SU3W] SET  MULTI_USER 
GO
ALTER DATABASE [SE162041_SU3W] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SE162041_SU3W] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SE162041_SU3W] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SE162041_SU3W] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SE162041_SU3W] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SE162041_SU3W] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'SE162041_SU3W', N'ON'
GO
ALTER DATABASE [SE162041_SU3W] SET QUERY_STORE = OFF
GO
USE [SE162041_SU3W]
GO
/****** Object:  Table [dbo].[Course]    Script Date: 8/24/2022 10:39:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Course](
	[cId] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[image] [varchar](100) NOT NULL,
	[Description] [nvarchar](200) NULL,
	[Tuition] [float] NOT NULL,
	[StartDate] [date] NOT NULL,
	[EndDate] [date] NOT NULL,
	[Category] [varchar](20) NOT NULL,
	[Status] [varchar](10) NOT NULL,
	[Quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[cId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 8/24/2022 10:39:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[orderId] [int] IDENTITY(1000,16) NOT NULL,
	[userName] [varchar](20) NULL,
	[Name] [nvarchar](50) NULL,
	[Email] [varchar](50) NULL,
	[Phone] [varchar](10) NULL,
	[BuyDateTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderItem]    Script Date: 8/24/2022 10:39:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderItem](
	[orderId] [int] NULL,
	[cId] [int] NULL,
	[courseName] [varchar](50) NULL,
	[amount] [int] NULL,
	[tuition] [float] NULL,
	[total] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 8/24/2022 10:39:45 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Registration](
	[userName] [varchar](20) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[Lastname] [nvarchar](100) NOT NULL,
	[isAdmin] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[userName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[OrderItem]  WITH CHECK ADD FOREIGN KEY([cId])
REFERENCES [dbo].[Course] ([cId])
GO
ALTER TABLE [dbo].[OrderItem]  WITH CHECK ADD FOREIGN KEY([orderId])
REFERENCES [dbo].[Order] ([orderId])
GO
USE [master]
GO
ALTER DATABASE [SE162041_SU3W] SET  READ_WRITE 
GO
