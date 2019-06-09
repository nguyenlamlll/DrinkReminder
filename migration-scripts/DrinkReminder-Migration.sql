USE [master]
GO
/****** Object:  Database [DrinkReminder]    Script Date: 09-Jun-19 3:50:04 PM ******/
CREATE DATABASE [DrinkReminder]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DrinkReminder', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.LAM\MSSQL\DATA\DrinkReminder.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DrinkReminder_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL13.LAM\MSSQL\DATA\DrinkReminder_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [DrinkReminder] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DrinkReminder].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DrinkReminder] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DrinkReminder] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DrinkReminder] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DrinkReminder] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DrinkReminder] SET ARITHABORT OFF 
GO
ALTER DATABASE [DrinkReminder] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DrinkReminder] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DrinkReminder] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DrinkReminder] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DrinkReminder] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DrinkReminder] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DrinkReminder] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DrinkReminder] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DrinkReminder] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DrinkReminder] SET  DISABLE_BROKER 
GO
ALTER DATABASE [DrinkReminder] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DrinkReminder] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DrinkReminder] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DrinkReminder] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DrinkReminder] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DrinkReminder] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DrinkReminder] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DrinkReminder] SET RECOVERY FULL 
GO
ALTER DATABASE [DrinkReminder] SET  MULTI_USER 
GO
ALTER DATABASE [DrinkReminder] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DrinkReminder] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DrinkReminder] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DrinkReminder] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [DrinkReminder] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'DrinkReminder', N'ON'
GO
ALTER DATABASE [DrinkReminder] SET QUERY_STORE = OFF
GO
USE [DrinkReminder]
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [DrinkReminder]
GO
/****** Object:  User [test_user]    Script Date: 09-Jun-19 3:50:05 PM ******/
CREATE USER [test_user] FOR LOGIN [test_user] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [test_user]
GO
ALTER ROLE [db_accessadmin] ADD MEMBER [test_user]
GO
ALTER ROLE [db_securityadmin] ADD MEMBER [test_user]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [test_user]
GO
ALTER ROLE [db_backupoperator] ADD MEMBER [test_user]
GO
ALTER ROLE [db_datareader] ADD MEMBER [test_user]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [test_user]
GO
ALTER ROLE [db_denydatareader] ADD MEMBER [test_user]
GO
ALTER ROLE [db_denydatawriter] ADD MEMBER [test_user]
GO
/****** Object:  Table [dbo].[DrinkRecord]    Script Date: 09-Jun-19 3:50:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DrinkRecord](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[UserId] [int] NOT NULL,
	[Amount] [float] NULL,
	[TimeTaken] [datetime] NULL,
 CONSTRAINT [PK_DrinkRecord] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[User]    Script Date: 09-Jun-19 3:50:05 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](255) NULL,
	[Password] [nvarchar](255) NULL,
	[Name] [nvarchar](255) NULL,
	[DateOfBirth] [date] NULL,
	[Weight] [float] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[DrinkRecord]  WITH CHECK ADD  CONSTRAINT [FK_DrinkRecord_User] FOREIGN KEY([UserId])
REFERENCES [dbo].[User] ([Id])
GO
ALTER TABLE [dbo].[DrinkRecord] CHECK CONSTRAINT [FK_DrinkRecord_User]
GO
USE [master]
GO
ALTER DATABASE [DrinkReminder] SET  READ_WRITE 
GO
