USE [DrinkReminder]
GO
/****** Object:  User [test_user]    Script Date: 6/10/2019 6:20:51 PM ******/
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
/****** Object:  Table [dbo].[DrinkRecord]    Script Date: 6/10/2019 6:20:51 PM ******/
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
/****** Object:  Table [dbo].[User]    Script Date: 6/10/2019 6:20:52 PM ******/
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
	[Height] [float] NULL,
	[WorkoutTime] [int] NULL,
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
