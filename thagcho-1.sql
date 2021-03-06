USE [master]
GO
/****** Object:  Database [HTTTQL]    Script Date: 5/18/2017 3:46:10 PM ******/
CREATE DATABASE [HTTTQL]
 CONTAINMENT = NONE
GO
ALTER DATABASE [HTTTQL] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HTTTQL].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HTTTQL] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HTTTQL] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HTTTQL] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HTTTQL] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HTTTQL] SET ARITHABORT OFF 
GO
ALTER DATABASE [HTTTQL] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HTTTQL] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [HTTTQL] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HTTTQL] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HTTTQL] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HTTTQL] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HTTTQL] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HTTTQL] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HTTTQL] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HTTTQL] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HTTTQL] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HTTTQL] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HTTTQL] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HTTTQL] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HTTTQL] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HTTTQL] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HTTTQL] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HTTTQL] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HTTTQL] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HTTTQL] SET  MULTI_USER 
GO
ALTER DATABASE [HTTTQL] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HTTTQL] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HTTTQL] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HTTTQL] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [HTTTQL]
GO
/****** Object:  StoredProcedure [dbo].[proc_add_cauhoi]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[proc_add_cauhoi] 
  @noidung ntext ,
  @machuong int ,
  @mamon int ,
  @madokho int ,
  @magv int ,
  @out  int  OUTPUT
  AS
   set @out  = 0 ;
  BEGIN 
  IF EXISTS (SELECT * FROM CHUONG_MUC CM WHERE CM.MACHUONG = @machuong AND CM.MAMON =@mamon )
       AND EXISTS (SELECT * FROM GIANG_VIEN GV  WHERE GV.MAGV = @magv)
	    AND EXISTS (SELECT * FROM DO_KHO DK WHERE DK.MADOKHO = @madokho)
		  BEGIN 
		  BEGIN TRANSACTION;  
		  INSERT INTO CAU_HOI VALUES(@noidung , @machuong , @mamon, @madokho , @magv ,0)
		  set @out =  SCOPE_IDENTITY()
		  COMMIT
		   END
	END   
GO
/****** Object:  StoredProcedure [dbo].[proc_add_dapan]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
 CREATE PROC [dbo].[proc_add_dapan] 
	@MACH INT ,
    @NOIDUNG ntext ,
	@DAPANDUNG BIT 
	AS 
	SET NOCOUNT ON
	DECLARE @res bit 
	BEGIN 
	 set @res =   0 
	  IF  EXISTS( SELECT * FROM CAU_HOI  DN WHERE  DN.MACH = @MACH )
	  BEGIN
	   INSERT INTO DAP_AN  VALUES(@MACH ,  @NOIDUNG , @DAPANDUNG )
	   set @res = 1 
	    END
	   ELSE 
	   PRINT N'MÃ CÂU HỎI KHÔNG TỒN TẠI'
	   SELECT @res
	  END
GO
/****** Object:  StoredProcedure [dbo].[proc_DsChuongByMAMH]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[proc_DsChuongByMAMH] 
@MAMH  int 
AS
SELECT * FROM MON_HOC MH INNER JOIN CHUONG_MUC CH ON MH.MAMON = CH.MAMON  WHERE MH.MAMON = @MAMH
GO
/****** Object:  StoredProcedure [dbo].[proc_DSMonHocByMAGV]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[proc_DSMonHocByMAGV] 
  @MAGV int  
  AS
  BEGIN
 SELECT MH.MAMON  ,MH.TENMON , MH.MABOMON  FROM GIANG_VIEN GV JOIN QUAN_LY_MH QL ON GV.MAGV = QL.MAGV JOIN MON_HOC MH ON MH.MAMON = QL.MAMON   WHERE GV.MAGV = @MAGV 
  END
GO
/****** Object:  StoredProcedure [dbo].[proc_login_GIANGVIEN_ResSmg]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC  [dbo].[proc_login_GIANGVIEN_ResSmg] 
	 @email nvarchar(50) ,
	 @pass nvarchar(40) 
   AS
    BEGIN
	 DECLARE @res nvarchar(255) ; 
	  if EXISTS (SELECT * FROM GIANG_VIEN GV  where GV.EMAIL = @email )
	     BEGIN 
			  if EXISTS ( SELECT * FROM GIANG_VIEN GV where GV.email = @email AND  GV.MATKHAU  = @pass)
			   set @res = ( SELECT G.MAGV FROM GIANG_VIEN G where G.EMAIL = @email AND  G.MATKHAU = @pass) 
			   ELSE
			   set @res = N'Mật khẩu không đúng '
		END
		ELSE
		set @res = N' Tài khoản không tồn tại ' 
     SELECT @res 
	END
GO
/****** Object:  Table [dbo].[BO_MON]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BO_MON](
	[MABOMON] [int] IDENTITY(1,1) NOT NULL,
	[TENBOMON] [nvarchar](50) NULL,
	[MAKHOA] [int] NULL,
	[MAGV] [int] NULL,
 CONSTRAINT [PK_BO_MON] PRIMARY KEY CLUSTERED 
(
	[MABOMON] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CAU_HOI]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CAU_HOI](
	[MACH] [int] IDENTITY(1,1) NOT NULL,
	[NOIDUNG] [ntext] NULL,
	[MACHUONG] [int] NULL,
	[MAMON] [int] NULL,
	[MADOKHO] [int] NULL,
	[MAGV] [int] NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_CAU_HOI] PRIMARY KEY CLUSTERED 
(
	[MACH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CAU_TRUC_DE_THI]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CAU_TRUC_DE_THI](
	[MACTDT] [int] IDENTITY(1,1) NOT NULL,
	[MAMON] [int] NULL,
	[NGAYCAPNHAT] [datetime] NULL,
	[MAGV] [int] NULL,
	[TRANGTHAI] [bit] NULL,
	[SLDETOIDA] [smallint] NULL,
 CONSTRAINT [PK_CAU_TRUC_DE_THI] PRIMARY KEY CLUSTERED 
(
	[MACTDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CHI_TIET_CTDT]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHI_TIET_CTDT](
	[MACTDT] [int] NOT NULL,
	[MACHUONG] [int] NOT NULL,
	[MADOKHO] [int] NOT NULL,
	[SLCAUHOI] [smallint] NULL,
	[TONGDIEM] [float] NULL,
 CONSTRAINT [PK_CHI_TIET_CTDT] PRIMARY KEY CLUSTERED 
(
	[MACTDT] ASC,
	[MACHUONG] ASC,
	[MADOKHO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CHI_TIET_DE_THI]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHI_TIET_DE_THI](
	[MADETHI] [int] NOT NULL,
	[MACH] [int] NOT NULL,
	[VITRIDAPANDUNG] [smallint] NULL,
	[DIEM] [float] NULL,
 CONSTRAINT [PK_CHI_TIET_DE_THI] PRIMARY KEY CLUSTERED 
(
	[MADETHI] ASC,
	[MACH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CHUONG_MUC]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHUONG_MUC](
	[MACHUONG] [int] IDENTITY(1,1) NOT NULL,
	[TIEUDE] [nvarchar](50) NULL,
	[MOTA] [nvarchar](100) NULL,
	[MAMON] [int] NULL,
 CONSTRAINT [PK_CHUONG_MUC] PRIMARY KEY CLUSTERED 
(
	[MACHUONG] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CONG_VIEC]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CONG_VIEC](
	[MACV] [int] IDENTITY(1,1) NOT NULL,
	[MAMON] [int] NULL,
	[MAGV] [int] NULL,
	[MALOAICV] [int] NULL,
	[TGBABTDAU] [datetime] NULL,
	[TGKETTHUC] [datetime] NULL,
	[NOIDUNGCV] [ntext] NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_CONG_VIEC] PRIMARY KEY CLUSTERED 
(
	[MACV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CT_TAO_CAU_HOI]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CT_TAO_CAU_HOI](
	[MACV] [int] NOT NULL,
	[MACHUONG] [int] NOT NULL,
	[SL] [int] NULL,
 CONSTRAINT [PK_CT_TAO_CAU_HOI] PRIMARY KEY CLUSTERED 
(
	[MACV] ASC,
	[MACHUONG] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DAP_AN]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DAP_AN](
	[MACH] [int] NOT NULL,
	[MADAPAN] [int] IDENTITY(1,1) NOT NULL,
	[NOIDUNG] [ntext] NULL,
	[DAPANDUNG] [bit] NULL,
 CONSTRAINT [PK_DAP_AN] PRIMARY KEY CLUSTERED 
(
	[MADAPAN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DE_THI]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DE_THI](
	[MADETHI] [int] IDENTITY(1,1) NOT NULL,
	[MAMON] [int] NULL,
	[NGAYTHEM] [datetime] NULL,
	[NGAYTHI] [date] NULL,
	[THOIGIANLAMBAI] [nvarchar](20) NULL,
	[TRANGTHAI] [bit] NULL,
 CONSTRAINT [PK_DE_THI] PRIMARY KEY CLUSTERED 
(
	[MADETHI] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DO_KHO]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DO_KHO](
	[MADOKHO] [int] IDENTITY(1,1) NOT NULL,
	[TENDOKHO] [nvarchar](50) NULL,
 CONSTRAINT [PK_DO_KHO] PRIMARY KEY CLUSTERED 
(
	[MADOKHO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[GIANG_VIEN]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[GIANG_VIEN](
	[MAGV] [int] IDENTITY(1,1) NOT NULL,
	[HOGV] [nvarchar](50) NULL,
	[TENGV] [nvarchar](50) NULL,
	[NGAYSINH] [datetime] NULL,
	[EMAIL] [nvarchar](50) NULL,
	[MATKHAU] [nvarchar](50) NULL,
	[ANHGV] [varchar](300) NULL,
	[DIACHI] [nvarchar](100) NULL,
	[DIENTHOAI] [varchar](12) NULL,
	[GIOITINH] [bit] NULL,
	[MABOMON] [int] NULL,
 CONSTRAINT [PK_GIANG_VIEN] PRIMARY KEY CLUSTERED 
(
	[MAGV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[KHOA]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KHOA](
	[MAKHOA] [int] IDENTITY(1,1) NOT NULL,
	[TENKHOA] [nvarchar](50) NULL,
	[MAGV] [int] NULL,
 CONSTRAINT [PK_KHOA] PRIMARY KEY CLUSTERED 
(
	[MAKHOA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LOAI_CONG_VIEC]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAI_CONG_VIEC](
	[MALOAICV] [int] IDENTITY(1,1) NOT NULL,
	[TENLOAICV] [nvarchar](100) NULL,
 CONSTRAINT [PK_LOAI_CONG_VIEC] PRIMARY KEY CLUSTERED 
(
	[MALOAICV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MON_HOC]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MON_HOC](
	[MAMON] [int] IDENTITY(1,1) NOT NULL,
	[TENMON] [nvarchar](50) NULL,
	[MABOMON] [int] NULL,
	[MAGV] [int] NULL,
 CONSTRAINT [PK_MON_HOC] PRIMARY KEY CLUSTERED 
(
	[MAMON] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[QUAN_LY_MH]    Script Date: 5/18/2017 3:46:10 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QUAN_LY_MH](
	[MAGV] [int] NOT NULL,
	[MAMON] [int] NOT NULL,
 CONSTRAINT [PK_QUAN_LY_MH] PRIMARY KEY CLUSTERED 
(
	[MAGV] ASC,
	[MAMON] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[BO_MON] ON 

INSERT [dbo].[BO_MON] ([MABOMON], [TENBOMON], [MAKHOA], [MAGV]) VALUES (1, N'Hệ thống thông tin', 1, 2)
INSERT [dbo].[BO_MON] ([MABOMON], [TENBOMON], [MAKHOA], [MAGV]) VALUES (2, N'Mạng máy tính', 1, 3)
INSERT [dbo].[BO_MON] ([MABOMON], [TENBOMON], [MAKHOA], [MAGV]) VALUES (3, N'Khoa học máy tính', 1, 3)
SET IDENTITY_INSERT [dbo].[BO_MON] OFF
SET IDENTITY_INSERT [dbo].[CAU_HOI] ON 

INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (1, N'Quyền và nghĩa vụ của công dân được nhà nước quy định trong', 1, 2, 1, 2, 1)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (2, N'Khi công dân vi phạm pháp luật với tính chất và mức độ vi phạm như nhau, trong một hoàn cảnh như nhau thì đều phải chịu trách nhiệm pháp lí', 1, 2, 2, 2, 1)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (3, N'Quyền và nghĩa vụ của công dân không bị phân biệt bởi', 2, 2, 2, 2, 1)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (4, N'Học tập là một trong những', 2, 2, 2, 2, 1)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (5, N'Công dân bình đẳng về trách nhiệm pháp lí là', 4, 2, 2, 2, 1)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (6, N'Công dân bình đẳng trước pháp luật là', 4, 2, 2, 2, 1)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (7, N'Trách nhiệm của nhà nước trong việc bảo đảm quyền bình đẳng của công dân trước pháp luật thể hiện qua việc', 12, 2, 1, 2, 1)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (8, N'Việc đảm bảo quyền bình đẳng của công dân trước pháp luật là trách nhiệm của ', 12, 2, 1, 2, 1)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (9, N'Ai đẹp trai nhất team', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (13, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (14, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (15, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (16, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (17, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (19, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (20, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (21, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (22, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (23, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (24, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (25, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (26, N'Ai xinh nhất team ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (27, N'ai là nhóm trưởng nhóm chúng ta  ?', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (28, N'To get the value of the Value attribute you can do something like this:', 1, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (29, N'em hãy trình bài về bệnh thành tích ở nước ta ?', 5, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (30, N'Tao làm biến lắm ròi', 4, 2, 1, 2, 0)
INSERT [dbo].[CAU_HOI] ([MACH], [NOIDUNG], [MACHUONG], [MAMON], [MADOKHO], [MAGV], [TRANGTHAI]) VALUES (31, N'ai la thang dep trai nha', 5, 2, 2, 2, 0)
SET IDENTITY_INSERT [dbo].[CAU_HOI] OFF
SET IDENTITY_INSERT [dbo].[CAU_TRUC_DE_THI] ON 

INSERT [dbo].[CAU_TRUC_DE_THI] ([MACTDT], [MAMON], [NGAYCAPNHAT], [MAGV], [TRANGTHAI], [SLDETOIDA]) VALUES (1, 2, CAST(0x0000A5A000000000 AS DateTime), 2, 1, 1)
SET IDENTITY_INSERT [dbo].[CAU_TRUC_DE_THI] OFF
INSERT [dbo].[CHI_TIET_CTDT] ([MACTDT], [MACHUONG], [MADOKHO], [SLCAUHOI], [TONGDIEM]) VALUES (1, 1, 1, 1, 1)
INSERT [dbo].[CHI_TIET_CTDT] ([MACTDT], [MACHUONG], [MADOKHO], [SLCAUHOI], [TONGDIEM]) VALUES (1, 1, 2, 1, 1.5)
INSERT [dbo].[CHI_TIET_CTDT] ([MACTDT], [MACHUONG], [MADOKHO], [SLCAUHOI], [TONGDIEM]) VALUES (1, 2, 2, 1, 2.5)
INSERT [dbo].[CHI_TIET_CTDT] ([MACTDT], [MACHUONG], [MADOKHO], [SLCAUHOI], [TONGDIEM]) VALUES (1, 4, 2, 1, 2.5)
INSERT [dbo].[CHI_TIET_CTDT] ([MACTDT], [MACHUONG], [MADOKHO], [SLCAUHOI], [TONGDIEM]) VALUES (1, 12, 1, 1, 0.5)
INSERT [dbo].[CHI_TIET_DE_THI] ([MADETHI], [MACH], [VITRIDAPANDUNG], [DIEM]) VALUES (1, 1, 0, 1)
INSERT [dbo].[CHI_TIET_DE_THI] ([MADETHI], [MACH], [VITRIDAPANDUNG], [DIEM]) VALUES (1, 2, 1, 1.5)
INSERT [dbo].[CHI_TIET_DE_THI] ([MADETHI], [MACH], [VITRIDAPANDUNG], [DIEM]) VALUES (1, 4, 2, 2.5)
INSERT [dbo].[CHI_TIET_DE_THI] ([MADETHI], [MACH], [VITRIDAPANDUNG], [DIEM]) VALUES (1, 5, 3, 2.5)
INSERT [dbo].[CHI_TIET_DE_THI] ([MADETHI], [MACH], [VITRIDAPANDUNG], [DIEM]) VALUES (1, 8, NULL, 0.5)
SET IDENTITY_INSERT [dbo].[CHUONG_MUC] ON 

INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (1, N'Chapter 1', N'INFORMATION SYSTEMS IN BUSINESS TODAY', 2)
INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (2, N'Chapter 2', N'GLOBAL E-BUSINESS AND COLLABORATION', 2)
INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (3, N'Chapter 3', N'INFORMATION SYSTEMS, ORGANIZATIONS, AND STRATEGY ', 2)
INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (4, N'Chapter 4', N'ETHICAL AND SOCIAL ISSUES IN INFORMATION SYSTEMS', 2)
INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (5, N'Chapter 5', N'IT INFRASTRUCTURE AND EMERGING TECHNOLOGIES', 2)
INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (6, N'Chapter 6', N'FOUNDATIONS OF BUSINESS INTELLIGENCE DATABASES AND INFORMATION MANAGEMENT', 2)
INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (7, N'Chapter 7', N'TELECOMMUNICATIONS, THE INTERNET, AND WIRELESS TECHNOLOGY', 2)
INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (8, N'Chapter 8', N'SECURING INFORMATION SYSTEMS', 2)
INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (9, N'Chapter 9', N'ACHIEVING OPERATIONAL EXCELLENCE AND CUSTOMER INTIMACY: ENTERPRISE APPLICATIONS ', 2)
INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (10, N'Chapter 10', N'E-COMMERCE: DIGITAL MARKETS, DIGITAL GOODS', 2)
INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (11, N'Chapter 11', N'MANAGING KNOWLEDGE', 2)
INSERT [dbo].[CHUONG_MUC] ([MACHUONG], [TIEUDE], [MOTA], [MAMON]) VALUES (12, N'Chapter 12', N'ENHANCING DECISION MAKING', 2)
SET IDENTITY_INSERT [dbo].[CHUONG_MUC] OFF
SET IDENTITY_INSERT [dbo].[CONG_VIEC] ON 

INSERT [dbo].[CONG_VIEC] ([MACV], [MAMON], [MAGV], [MALOAICV], [TGBABTDAU], [TGKETTHUC], [NOIDUNGCV], [TRANGTHAI]) VALUES (1, 2, 2, 1, CAST(0x0000A58000000000 AS DateTime), CAST(0x0000A5A000000000 AS DateTime), N'Thêm các chương vào môn học này nhé! =))', 0)
INSERT [dbo].[CONG_VIEC] ([MACV], [MAMON], [MAGV], [MALOAICV], [TGBABTDAU], [TGKETTHUC], [NOIDUNGCV], [TRANGTHAI]) VALUES (2, 2, 2, 3, CAST(0x0000A58000000000 AS DateTime), CAST(0x0000A5A000000000 AS DateTime), N'Tạo cấu trúc đề thi cho môn học này nhé cưng', 0)
INSERT [dbo].[CONG_VIEC] ([MACV], [MAMON], [MAGV], [MALOAICV], [TGBABTDAU], [TGKETTHUC], [NOIDUNGCV], [TRANGTHAI]) VALUES (3, 2, 2, 2, CAST(0x0000A58000000000 AS DateTime), CAST(0x0000A5A000000000 AS DateTime), N'Tạo câu hỏi cho các chương sau nhé', 0)
SET IDENTITY_INSERT [dbo].[CONG_VIEC] OFF
INSERT [dbo].[CT_TAO_CAU_HOI] ([MACV], [MACHUONG], [SL]) VALUES (3, 1, 2)
INSERT [dbo].[CT_TAO_CAU_HOI] ([MACV], [MACHUONG], [SL]) VALUES (3, 2, 2)
INSERT [dbo].[CT_TAO_CAU_HOI] ([MACV], [MACHUONG], [SL]) VALUES (3, 4, 2)
INSERT [dbo].[CT_TAO_CAU_HOI] ([MACV], [MACHUONG], [SL]) VALUES (3, 12, 2)
SET IDENTITY_INSERT [dbo].[DAP_AN] ON 

INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (1, 1, N'Hiến pháp', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (1, 2, N'Hiến pháp và luật', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (1, 3, N'Luật hiến pháp', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (1, 4, N'Luật và chính sách', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (2, 5, N'Như nhau', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (2, 6, N'Ngang nhau', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (2, 8, N'Bằng nhau', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (2, 9, N'Có thể khác nhau', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (3, 10, N'Dân tộc, giới tính, tôn giáo', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (3, 11, N'Thu nhập, tuổi tác, địa vị', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (3, 12, N'Dân tộc, địa vị, giới tính, tôn giáo', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (3, 13, N'Dân tộc, độ tuổi, giới tính', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (4, 14, N'Nghĩa vụ của công dân', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (4, 15, N'Quyền của công dân', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (4, 16, N'Trách nhiệm của công dân', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (4, 17, N'Quyền và nghĩa vụ của công dân', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (5, 18, N'Công dân ở bất kỳ độ tuổi nào vi phạm pháp luật đều bị xử lý như nhau', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (5, 19, N'Công dân nào vi phạm quy định của cơ quan, đơn vị, đều phải chịu trách nhiệm kỷ luật', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (5, 20, N'Công dân nào vi phạm pháp luật cũng bị xử lý theo quy định của pháp luật', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (5, 21, N'Công dân nào do thiếu hiểu biết về pháp luật mà vi phạm pháp luật thì không phải chịu trách nhiệm pháp lý', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (6, 22, N'Công dân có quyền và nghĩa vụ như nhau nếu cùng giới tính, dân tộc, tôn giáo', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (6, 23, N'Công dân có quyền và nghĩa vụ giống nhau tùy theo địa bàn sinh sống', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (6, 24, N'Công dân nào vi phạm pháp luật cũng bị xử lý theo quy định của đơn vị, tổ chức, đoàn thể mà họ tham gia', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (6, 25, N'Công dân không bị phân biệt đối xử trong việc hưởng quyền, thực hiện, nghĩa vụ, và chịu trách nhiệm pháp lý theo quy định của pháp luật', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (7, 26, N'Quy định quyền và nghĩa vụ công dân trong Hiến pháp và Luật', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (7, 27, N'Tạo ra các điều kiện bảo đảm cho công dân thực hiện quyền bình đẳng trước pháp luật', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (7, 28, N'Không ngừng đổi mới và hoàn thiện hệ thống pháp luật', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (7, 29, N'Tất cả các phương án trên', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (8, 30, N'Nhà nước', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (8, 31, N'Nhà nước và xã hội', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (8, 32, N'Nhà nước và pháp luật', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (8, 33, N'Nhà nước và công dân', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (8, 39, N'THành ', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (9, 40, N'Huỳnh tính thành', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (9, 41, N'Trương tam lang', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (9, 42, N'Trần văn thắng', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (9, 43, N'Phan nu thoai my', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (9, 44, N'Phan nu thoai my ng', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (9, 45, N'Phan nu thoai my ng', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (9, 46, N'Phan nu thoai my ng', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (24, 47, N'Trương tam lang', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (27, 48, N'TRINH', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (27, 49, N'trang', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (27, 50, N'thắng', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (27, 51, N'lang', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (28, 52, N'However this will return the same ', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (28, 53, N'differnt to the submitted form behaviour.', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (28, 54, N'To check whether it is checked or not, do:', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (28, 55, N'What does the first example do if there are multiple checkboxes on the page? ', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (29, 56, N'mở bài 
thân bài 
kết bài', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (30, 57, N'a ihihihi', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (31, 58, N'thanh', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (31, 59, N'thang', 0)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (31, 60, N'lang', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (31, 61, N'phat', 1)
INSERT [dbo].[DAP_AN] ([MACH], [MADAPAN], [NOIDUNG], [DAPANDUNG]) VALUES (31, 62, N'trung', 0)
SET IDENTITY_INSERT [dbo].[DAP_AN] OFF
SET IDENTITY_INSERT [dbo].[DE_THI] ON 

INSERT [dbo].[DE_THI] ([MADETHI], [MAMON], [NGAYTHEM], [NGAYTHI], [THOIGIANLAMBAI], [TRANGTHAI]) VALUES (1, 2, CAST(0x0000A58F00000000 AS DateTime), CAST(0x193B0B00 AS Date), N'60p', 1)
SET IDENTITY_INSERT [dbo].[DE_THI] OFF
SET IDENTITY_INSERT [dbo].[DO_KHO] ON 

INSERT [dbo].[DO_KHO] ([MADOKHO], [TENDOKHO]) VALUES (1, N'Khó')
INSERT [dbo].[DO_KHO] ([MADOKHO], [TENDOKHO]) VALUES (2, N'Dễ')
INSERT [dbo].[DO_KHO] ([MADOKHO], [TENDOKHO]) VALUES (3, N'Trung bình')
SET IDENTITY_INSERT [dbo].[DO_KHO] OFF
SET IDENTITY_INSERT [dbo].[GIANG_VIEN] ON 

INSERT [dbo].[GIANG_VIEN] ([MAGV], [HOGV], [TENGV], [NGAYSINH], [EMAIL], [MATKHAU], [ANHGV], [DIACHI], [DIENTHOAI], [GIOITINH], [MABOMON]) VALUES (1, N'Phan', N'đình long', CAST(0x0000888000735B40 AS DateTime), N'dinhlong@gmail.com', N'fd3dc2c8cfc002a42c76eaa31bd7b462', N'/UploadedFiles/default_avatar.jpg', N'TP HCM', N'0981773084', 0, 1)
INSERT [dbo].[GIANG_VIEN] ([MAGV], [HOGV], [TENGV], [NGAYSINH], [EMAIL], [MATKHAU], [ANHGV], [DIACHI], [DIENTHOAI], [GIOITINH], [MABOMON]) VALUES (2, N'quynh 11', N'nhu', CAST(0x0000A82700735B40 AS DateTime), N'quynhnhu@gmail.com', N'fd3dc2c8cfc002a42c76eaa31bd7b462', N'/UploadedFiles/77134963393931914591495096819949.jpg', N'10.871178,106.7913021', N'0981773084', 0, 1)
INSERT [dbo].[GIANG_VIEN] ([MAGV], [HOGV], [TENGV], [NGAYSINH], [EMAIL], [MATKHAU], [ANHGV], [DIACHI], [DIENTHOAI], [GIOITINH], [MABOMON]) VALUES (3, N'Phạm Văn', N'Tính', CAST(0x000072A000000000 AS DateTime), N'phamvantinh@gmail.com', N'fd3dc2c8cfc002a42c76eaa31bd7b462', N'/UploadedFiles/no_img.jpg', N'Tp.Hồ Chí Minh', N'01203947318', 1, 3)
INSERT [dbo].[GIANG_VIEN] ([MAGV], [HOGV], [TENGV], [NGAYSINH], [EMAIL], [MATKHAU], [ANHGV], [DIACHI], [DIENTHOAI], [GIOITINH], [MABOMON]) VALUES (4, N'Huỳnh Tính ', N'Thành', CAST(0x000087C800000000 AS DateTime), N'tinhthanh@gmail.com', N'fd3dc2c8cfc002a42c76eaa31bd7b462', N'/UploadedFiles/no_img.jpg', N'Đồng Tháp', N'0981773084', 1, 3)
SET IDENTITY_INSERT [dbo].[GIANG_VIEN] OFF
SET IDENTITY_INSERT [dbo].[KHOA] ON 

INSERT [dbo].[KHOA] ([MAKHOA], [TENKHOA], [MAGV]) VALUES (1, N'Khoa Công Nghệ Thông Tin', 3)
INSERT [dbo].[KHOA] ([MAKHOA], [TENKHOA], [MAGV]) VALUES (2, N'Khoa Công Nghệ Thực Phẩm', NULL)
INSERT [dbo].[KHOA] ([MAKHOA], [TENKHOA], [MAGV]) VALUES (3, N'Khoa Cơ khí', NULL)
SET IDENTITY_INSERT [dbo].[KHOA] OFF
SET IDENTITY_INSERT [dbo].[LOAI_CONG_VIEC] ON 

INSERT [dbo].[LOAI_CONG_VIEC] ([MALOAICV], [TENLOAICV]) VALUES (1, N'Tạo đề cương')
INSERT [dbo].[LOAI_CONG_VIEC] ([MALOAICV], [TENLOAICV]) VALUES (2, N'Tạo câu hỏi')
INSERT [dbo].[LOAI_CONG_VIEC] ([MALOAICV], [TENLOAICV]) VALUES (3, N'Tạo cấu trúc đề thi')
SET IDENTITY_INSERT [dbo].[LOAI_CONG_VIEC] OFF
SET IDENTITY_INSERT [dbo].[MON_HOC] ON 

INSERT [dbo].[MON_HOC] ([MAMON], [TENMON], [MABOMON], [MAGV]) VALUES (1, N'Mạng máy tính cơ bản', 2, 2)
INSERT [dbo].[MON_HOC] ([MAMON], [TENMON], [MABOMON], [MAGV]) VALUES (2, N'Hệ thống thông tin quản lý', 1, 2)
INSERT [dbo].[MON_HOC] ([MAMON], [TENMON], [MABOMON], [MAGV]) VALUES (3, N'Mạng máy tính nâng cao', 2, 3)
INSERT [dbo].[MON_HOC] ([MAMON], [TENMON], [MABOMON], [MAGV]) VALUES (4, N'Cấu trúc máy tính', 3, 3)
SET IDENTITY_INSERT [dbo].[MON_HOC] OFF
INSERT [dbo].[QUAN_LY_MH] ([MAGV], [MAMON]) VALUES (2, 1)
INSERT [dbo].[QUAN_LY_MH] ([MAGV], [MAMON]) VALUES (2, 2)
INSERT [dbo].[QUAN_LY_MH] ([MAGV], [MAMON]) VALUES (2, 3)
INSERT [dbo].[QUAN_LY_MH] ([MAGV], [MAMON]) VALUES (3, 1)
INSERT [dbo].[QUAN_LY_MH] ([MAGV], [MAMON]) VALUES (3, 3)
ALTER TABLE [dbo].[BO_MON]  WITH CHECK ADD  CONSTRAINT [FK_BO_MON_KHOA] FOREIGN KEY([MAKHOA])
REFERENCES [dbo].[KHOA] ([MAKHOA])
GO
ALTER TABLE [dbo].[BO_MON] CHECK CONSTRAINT [FK_BO_MON_KHOA]
GO
ALTER TABLE [dbo].[CAU_HOI]  WITH CHECK ADD  CONSTRAINT [FK_CAU_HOI_CHUONG_MUC] FOREIGN KEY([MACHUONG])
REFERENCES [dbo].[CHUONG_MUC] ([MACHUONG])
GO
ALTER TABLE [dbo].[CAU_HOI] CHECK CONSTRAINT [FK_CAU_HOI_CHUONG_MUC]
GO
ALTER TABLE [dbo].[CAU_HOI]  WITH CHECK ADD  CONSTRAINT [FK_CAU_HOI_DO_KHO] FOREIGN KEY([MADOKHO])
REFERENCES [dbo].[DO_KHO] ([MADOKHO])
GO
ALTER TABLE [dbo].[CAU_HOI] CHECK CONSTRAINT [FK_CAU_HOI_DO_KHO]
GO
ALTER TABLE [dbo].[CAU_HOI]  WITH CHECK ADD  CONSTRAINT [FK_CAU_HOI_MON_HOC] FOREIGN KEY([MAMON])
REFERENCES [dbo].[MON_HOC] ([MAMON])
GO
ALTER TABLE [dbo].[CAU_HOI] CHECK CONSTRAINT [FK_CAU_HOI_MON_HOC]
GO
ALTER TABLE [dbo].[CAU_TRUC_DE_THI]  WITH CHECK ADD  CONSTRAINT [FK_CAU_TRUC_DE_THI_GIANG_VIEN] FOREIGN KEY([MAGV])
REFERENCES [dbo].[GIANG_VIEN] ([MAGV])
GO
ALTER TABLE [dbo].[CAU_TRUC_DE_THI] CHECK CONSTRAINT [FK_CAU_TRUC_DE_THI_GIANG_VIEN]
GO
ALTER TABLE [dbo].[CAU_TRUC_DE_THI]  WITH CHECK ADD  CONSTRAINT [FK_CAU_TRUC_DE_THI_MON_HOC] FOREIGN KEY([MAMON])
REFERENCES [dbo].[MON_HOC] ([MAMON])
GO
ALTER TABLE [dbo].[CAU_TRUC_DE_THI] CHECK CONSTRAINT [FK_CAU_TRUC_DE_THI_MON_HOC]
GO
ALTER TABLE [dbo].[CHI_TIET_CTDT]  WITH CHECK ADD  CONSTRAINT [FK_CHI_TIET_CTDT_CAU_TRUC_DE_THI] FOREIGN KEY([MACTDT])
REFERENCES [dbo].[CAU_TRUC_DE_THI] ([MACTDT])
GO
ALTER TABLE [dbo].[CHI_TIET_CTDT] CHECK CONSTRAINT [FK_CHI_TIET_CTDT_CAU_TRUC_DE_THI]
GO
ALTER TABLE [dbo].[CHI_TIET_CTDT]  WITH CHECK ADD  CONSTRAINT [FK_CHI_TIET_CTDT_CHUONG_MUC] FOREIGN KEY([MACHUONG])
REFERENCES [dbo].[CHUONG_MUC] ([MACHUONG])
GO
ALTER TABLE [dbo].[CHI_TIET_CTDT] CHECK CONSTRAINT [FK_CHI_TIET_CTDT_CHUONG_MUC]
GO
ALTER TABLE [dbo].[CHI_TIET_DE_THI]  WITH CHECK ADD  CONSTRAINT [FK_CHI_TIET_DE_THI_CAU_HOI] FOREIGN KEY([MACH])
REFERENCES [dbo].[CAU_HOI] ([MACH])
GO
ALTER TABLE [dbo].[CHI_TIET_DE_THI] CHECK CONSTRAINT [FK_CHI_TIET_DE_THI_CAU_HOI]
GO
ALTER TABLE [dbo].[CHI_TIET_DE_THI]  WITH CHECK ADD  CONSTRAINT [FK_CHI_TIET_DE_THI_DE_THI] FOREIGN KEY([MADETHI])
REFERENCES [dbo].[DE_THI] ([MADETHI])
GO
ALTER TABLE [dbo].[CHI_TIET_DE_THI] CHECK CONSTRAINT [FK_CHI_TIET_DE_THI_DE_THI]
GO
ALTER TABLE [dbo].[CHUONG_MUC]  WITH CHECK ADD  CONSTRAINT [FK_CHUONG_MUC_MON_HOC] FOREIGN KEY([MAMON])
REFERENCES [dbo].[MON_HOC] ([MAMON])
GO
ALTER TABLE [dbo].[CHUONG_MUC] CHECK CONSTRAINT [FK_CHUONG_MUC_MON_HOC]
GO
ALTER TABLE [dbo].[CONG_VIEC]  WITH CHECK ADD  CONSTRAINT [FK_CONG_VIEC_GIANG_VIEN] FOREIGN KEY([MAGV])
REFERENCES [dbo].[GIANG_VIEN] ([MAGV])
GO
ALTER TABLE [dbo].[CONG_VIEC] CHECK CONSTRAINT [FK_CONG_VIEC_GIANG_VIEN]
GO
ALTER TABLE [dbo].[CONG_VIEC]  WITH CHECK ADD  CONSTRAINT [FK_CONG_VIEC_LOAI_CONG_VIEC] FOREIGN KEY([MALOAICV])
REFERENCES [dbo].[LOAI_CONG_VIEC] ([MALOAICV])
GO
ALTER TABLE [dbo].[CONG_VIEC] CHECK CONSTRAINT [FK_CONG_VIEC_LOAI_CONG_VIEC]
GO
ALTER TABLE [dbo].[CT_TAO_CAU_HOI]  WITH CHECK ADD  CONSTRAINT [FK_CT_TAO_CAU_HOI_CHUONG_MUC] FOREIGN KEY([MACHUONG])
REFERENCES [dbo].[CHUONG_MUC] ([MACHUONG])
GO
ALTER TABLE [dbo].[CT_TAO_CAU_HOI] CHECK CONSTRAINT [FK_CT_TAO_CAU_HOI_CHUONG_MUC]
GO
ALTER TABLE [dbo].[CT_TAO_CAU_HOI]  WITH CHECK ADD  CONSTRAINT [FK_CT_TAO_CAU_HOI_CONG_VIEC] FOREIGN KEY([MACV])
REFERENCES [dbo].[CONG_VIEC] ([MACV])
GO
ALTER TABLE [dbo].[CT_TAO_CAU_HOI] CHECK CONSTRAINT [FK_CT_TAO_CAU_HOI_CONG_VIEC]
GO
ALTER TABLE [dbo].[DAP_AN]  WITH CHECK ADD  CONSTRAINT [FK_DAP_AN_CAU_HOI] FOREIGN KEY([MACH])
REFERENCES [dbo].[CAU_HOI] ([MACH])
GO
ALTER TABLE [dbo].[DAP_AN] CHECK CONSTRAINT [FK_DAP_AN_CAU_HOI]
GO
ALTER TABLE [dbo].[DE_THI]  WITH CHECK ADD  CONSTRAINT [FK_DE_THI_MON_HOC] FOREIGN KEY([MAMON])
REFERENCES [dbo].[MON_HOC] ([MAMON])
GO
ALTER TABLE [dbo].[DE_THI] CHECK CONSTRAINT [FK_DE_THI_MON_HOC]
GO
ALTER TABLE [dbo].[GIANG_VIEN]  WITH CHECK ADD  CONSTRAINT [FK_GIANG_VIEN_BO_MON1] FOREIGN KEY([MABOMON])
REFERENCES [dbo].[BO_MON] ([MABOMON])
GO
ALTER TABLE [dbo].[GIANG_VIEN] CHECK CONSTRAINT [FK_GIANG_VIEN_BO_MON1]
GO
ALTER TABLE [dbo].[MON_HOC]  WITH CHECK ADD  CONSTRAINT [FK_MON_HOC_BO_MON] FOREIGN KEY([MABOMON])
REFERENCES [dbo].[BO_MON] ([MABOMON])
GO
ALTER TABLE [dbo].[MON_HOC] CHECK CONSTRAINT [FK_MON_HOC_BO_MON]
GO
ALTER TABLE [dbo].[QUAN_LY_MH]  WITH CHECK ADD  CONSTRAINT [FK_QUAN_LY_MH_GIANG_VIEN] FOREIGN KEY([MAGV])
REFERENCES [dbo].[GIANG_VIEN] ([MAGV])
GO
ALTER TABLE [dbo].[QUAN_LY_MH] CHECK CONSTRAINT [FK_QUAN_LY_MH_GIANG_VIEN]
GO
ALTER TABLE [dbo].[QUAN_LY_MH]  WITH CHECK ADD  CONSTRAINT [FK_QUAN_LY_MH_MON_HOC] FOREIGN KEY([MAMON])
REFERENCES [dbo].[MON_HOC] ([MAMON])
GO
ALTER TABLE [dbo].[QUAN_LY_MH] CHECK CONSTRAINT [FK_QUAN_LY_MH_MON_HOC]
GO
USE [master]
GO
ALTER DATABASE [HTTTQL] SET  READ_WRITE 
GO
