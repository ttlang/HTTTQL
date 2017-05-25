﻿USE HTTTQL;
GO
IF EXISTS (SELECT  *  FROM SYS.triggers WHERE name='TRIGGER_INSERT_CAUTRUCDETHI_WHEN_CREATE_MONHOC')
DROP TRIGGER TRIGGER_INSERT_CAUTRUCDETHI_WHEN_CREATE_MONHOC;
GO
CREATE TRIGGER TRIGGER_INSERT_CAUTRUCDETHI_WHEN_CREATE_MONHOC ON MON_HOC AFTER INSERT
AS
BEGIN
	DECLARE @MAMON INT;
	SET @MAMON = (SELECT MAMON FROM inserted)
	INSERT INTO CAU_TRUC_DE_THI(MAMON,NGAYCAPNHAT,TRANGTHAI,SLDETOIDA) VALUES(@MAMON,GETDATE(),0,0);
END
GO
INSERT INTO MON_HOC VALUES(N'TEST TRIGGER',1,NULL);
GO
--  add chi tiet cau truc de thi
if exists(select * from sys.objects where  object_id = OBJECT_ID(N'PROCEDURE_INSERTORUPDATE_CAUTRUCDETHI')
                    AND type IN ( N'P', N'PC' ) )  
		DROP PROCEDURE PROCEDURE_INSERTORUPDATE_CAUTRUCDETHI;
GO
 CREATE PROCEDURE PROCEDURE_INSERTORUPDATE_CAUTRUCDETHI(@MACTDT INT, @MACHUONG INT, @MADOKHO INT, @SLCAUHOI SMALLINT, @TONGDIEM FLOAT) 
 AS
 BEGIN
 IF EXISTS (SELECT * FROM CHI_TIET_CTDT WHERE MACTDT= @MACTDT AND MACHUONG=@MACHUONG AND MADOKHO=@MADOKHO)
	BEGIN
		 IF (SELECT COUNT(*) FROM CAU_HOI WHERE MACHUONG=@MACHUONG AND MADOKHO=@MADOKHO )>= @SLCAUHOI
			BEGIN
				UPDATE CHI_TIET_CTDT SET SLCAUHOI=@SLCAUHOI, TONGDIEM=@TONGDIEM WHERE MACTDT=@MACTDT AND MACHUONG=@MACHUONG AND MADOKHO=@MADOKHO;
			END
		 ELSE
			BEGIN
				RAISERROR(N'SỐ LƯỢNG QUÁ MỨC CHO PHÉP!',16,1);
			END
	END
 ELSE
	BEGIN
		IF (SELECT COUNT(*) FROM CAU_HOI WHERE MACHUONG=@MACHUONG AND MADOKHO=@MADOKHO )>= @SLCAUHOI
			BEGIN
				INSERT INTO CHI_TIET_CTDT VALUES(@MACTDT, @MACHUONG, @MADOKHO, @SLCAUHOI, @TONGDIEM);
			END
		 ELSE
			BEGIN
				RAISERROR(N'SỐ LƯỢNG QUÁ MỨC CHO PHÉP!',16,1);
			END
	END
 END	
 GO
  --EXECUTE  PROCEDURE_INSERTORUPDATE_CAUTRUCDETHI @MACTDT=?, @MACHUONG =?, @MADOKHO=?, @SLCAUHOI=?, @TONGDIEM=?)
 
--=======
 -- thêm ctdt cho tất cả các môn còn thiếu
 insert into CAU_TRUC_DE_THI(mamon,NGAYCAPNHAT,TRANGTHAI,SLDETOIDA)
  select mh.mamon,GETDATE(),0,0 from MON_HOC mh where not exists(select mactdt  from CAU_TRUC_DE_THI where mamon=mh.MAMON) 
  --


alter proc addDeThi @MAMON INT, @MACHUONG INT, @SL INT, @MADOKHO INT
 AS
 BEGIN
	declare @sqlcommand nvarchar(95)
	set @sqlcommand = 'select top '+CAST(@SL as nvarchar(2))+' * from cau_hoi where mamon='+cast(@MAMON as varchar(10))+' and machuong='+cast(@MACHUONG as varchar(10))+'and madokho='+cast(@MADOKHO as varchar(10))+' order by NEWID()'
	EXECUTE sp_executesql @sqlcommand
 END
  execute addDeThi @MAMON =2,@MACHUONG=1,@SL=3,@MADOKHO=1



  --Lang

  USE HTTTQL
GO


CREATE PROC p_themCongViec_CONG_VIEC @MAMON int,@MAGV int ,@MALOAICV int,@TGBATDAU datetime,
@TGKETTHUC datetime,@NOIDUNGCV ntext
AS 
BEGIN
	BEGIN TRY	
	INSERT INTO CONG_VIEC(MAMON,MAGV,MALOAICV,TGBABTDAU,TGKETTHUC,NOIDUNGCV,TRANGTHAI)
	VALUES(@MAMON,@MAGV,@MALOAICV,@TGBATDAU,@TGKETTHUC,@NOIDUNGCV,0);
	END TRY

	BEGIN CATCH
	RAISERROR (N'THÊM THẤT BẠI',16,1);
	END CATCH
END


GO
CREATE PROC p_themCongViec2_CONG_VIEC @MAMON int,@MAGV int ,@MALOAICV int,@TGBATDAU datetime,
@TGKETTHUC datetime,@NOIDUNGCV ntext,@SOlUONGDETOIDA int
AS 
BEGIN
	BEGIN TRY
		
	INSERT INTO CONG_VIEC(MAMON,MAGV,MALOAICV,TGBABTDAU,TGKETTHUC,NOIDUNGCV,TRANGTHAI)
	VALUES(@MAMON,@MAGV,@MALOAICV,@TGBATDAU,@TGKETTHUC,@NOIDUNGCV,0);

	UPDATE CAU_TRUC_DE_THI SET TRANGTHAI =0,SLDETOIDA=@SOlUONGDETOIDA
	
	END TRY

	BEGIN CATCH
	
	RAISERROR (N'THÊM THẤT BẠI',16,1);
	END CATCH
END 

GO
CREATE PROC p_themCongViec_CAUHOI_CONG_VIEC @MAMON int,@MAGV int ,@MALOAICV int,@TGBATDAU datetime,
@TGKETTHUC datetime,@NOIDUNGCV ntext, @MACHUONG int,@SOLUONGCAUHOITOIDA int
AS 

BEGIN
BEGIN TRY


INSERT INTO CONG_VIEC(MAMON,MAGV,MALOAICV,TGBABTDAU,TGKETTHUC,NOIDUNGCV,TRANGTHAI)
	VALUES(@MAMON,@MAGV,@MALOAICV,@TGBATDAU,@TGKETTHUC,@NOIDUNGCV,0);
	INSERT INTO CT_TAO_CAU_HOI VALUES (IDENT_CURRENT('CONG_VIEC'),@MACHUONG,@SOLUONGCAUHOITOIDA);
		
END TRY

BEGIN CATCH
	RAISERROR('CẬP NHẬT THẤT BẠI',16,1);
END CATCH



END

