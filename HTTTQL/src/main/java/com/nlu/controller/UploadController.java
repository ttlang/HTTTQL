package com.nlu.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nlu.entities.PictureResize;

@Controller
// upload?url='xxxxx'&&w=250
@RequestMapping(value = "/upload")
public class UploadController {
	@Autowired
	ServletContext context;

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(HttpServletRequest request) {
		// String id = request.getParameter("key");
		// System.out.println(key[1] +" key");
		return "0";
	}

	@RequestMapping(value = "/resize", method = RequestMethod.GET)
	public @ResponseBody void reSize(@RequestParam("url") String url, @RequestParam("w") String w,
			HttpServletResponse response) {
		String uploadingdir = context.getRealPath("/");
		InputStream fis;
		File file = null;
		PictureResize model = new PictureResize();

		try {
			if (url != null && !url.equalsIgnoreCase("") && w != null && !w.equalsIgnoreCase("")) {
				w = (model.isNumericArray(w) ? w : "250");
				if (url.contains("http://") || url.contains("htpps://")) {
					URL urlc;
					urlc = new URL(url);
					fis = urlc.openStream();
				} else {
					fis = new FileInputStream(file = new File(uploadingdir + File.separator + url));

				}
			} else {
				fis = new FileInputStream(
						file = new File(uploadingdir + File.separator + "/UploadedFiles/no_images.jpg"));
				w = "250";
			}

			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + "temp.jpg");
			BufferedImage image = ImageIO.read(fis);
			image = model.resizetb(model.resizeImages(image), Integer.parseInt(w));

			ByteArrayOutputStream ba = new ByteArrayOutputStream();
			ImageIO.write(image, "gif", ba);
			fis = new ByteArrayInputStream(ba.toByteArray());

			FileCopyUtils.copy(fis, response.getOutputStream());
		} catch (NumberFormatException | IOException ex) {
			// TODO Auto-generated catch block

			try {
				response.setHeader("Content-Disposition", "attachment; filename=" + "no_images.jpg");
				fis = new FileInputStream(file = new File(uploadingdir + File.separator + "/UploadedFiles/no-img.jpg"));
				FileCopyUtils.copy(fis, response.getOutputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@RequestMapping(value = "/avatar", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> avatar(@RequestParam("file[]") MultipartFile[] files,
			HttpServletRequest request) {
		String uploadingdir = context.getRealPath("/");

		Map<String, Object> map = new HashMap<String, Object>();

		Random rd = new Random();
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(calendar.getTime().getTime());
		long time = date.getTime();

		// API

		List<String> initialPreview = new ArrayList<>();
		List<Map<String, Object>> infoImagenesSubidas = new ArrayList<>();
		map.put("file_id", 0);
		map.put("overwriteInitial", true);

		String name = null;
		Map<String, Object> temp = null;
		// int product_id = ManagerSession.ADD_PRODUCT_ID(request);
		// String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];

			name = String.valueOf(Math.abs(rd.nextLong())) + String.valueOf(time) + ".jpg";
			String pathname = "/UploadedFiles/" + name;
			// int item=
			// Integer.parseInt(uploadService.addImgProduct(product_id,pathname));
			temp = new HashMap<String, Object>();
			temp.put("caption", "Hình " + i);
			temp.put("height", "120px");
			temp.put("url", "/upload/delete");
			temp.put("key", "O");
			infoImagenesSubidas.add(temp);
			initialPreview.add("<img   style='width: 100%;' src='" + pathname + "' class='file-preview-image'/>");
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file

				// String rootPath = System.getProperty("catalina.home");

				// Create the file on server
				File serverFile = new File(uploadingdir + pathname);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				// message = message + "You successfully uploaded file=" + name
				// + "";
			} catch (Exception e) {
				return map;
			}
		}

		map.put("initialPreviewConfig", infoImagenesSubidas);
		map.put("initialPreview", initialPreview);

		return map;
	
		

	}

	 
	
	  
}
