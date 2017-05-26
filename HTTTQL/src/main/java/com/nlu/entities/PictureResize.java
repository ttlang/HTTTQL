package com.nlu.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
public class PictureResize {
	public PictureResize () {
		
	}
	   public static boolean isNumericArray(String str) {
		    if (str == null)
		        return false;
		    for (char c : str.toCharArray())
		        if (c < '0' || c > '9')
		            return false;
		    return true;
		    }
	   public BufferedImage resizetb(BufferedImage input ,int size){
	       BufferedImage output = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
	         Graphics2D g = output.createGraphics() ;
	         g.drawImage(input,0,0,size,size,null);
	         g.dispose();
	        return  output ;
	    }
	   public BufferedImage resizeImages(BufferedImage input){
	      int w = input.getWidth() ;
	      int h = input.getHeight();
	       if(w==h){
	    	  return input ;
	       }else{
	    	   BufferedImage output  = null;
	    	   if(w>h){
	    		  output = new BufferedImage(h, h, BufferedImage.TYPE_INT_ARGB);
	    		   Graphics g = output.createGraphics();
	    		  g.drawImage(input, -(w-h)/2,0 , input.getWidth(), input.getHeight(),null);
	    	   }else if(h>w){
	    		  output = new BufferedImage(w, w, BufferedImage.TYPE_INT_ARGB);
	    		  Graphics2D g = output.createGraphics();
	    		  g.drawImage(input, 0, -((h-w)/2), input.getWidth(), input.getHeight(),null); 		
	    	   } 
	    	   return output ;
	       }	
	    }
	    public BufferedImage resizeImagesBox(BufferedImage input){
	      int w = input.getWidth() ;
	      int h = input.getHeight();
	       if(w==h){
	    	  return input ;
	          }else{
	    	   BufferedImage output  = null;
	    	   if(w>h){
	    		  output = new BufferedImage(w, w, BufferedImage.TYPE_INT_ARGB);
	    		  Graphics2D g = output.createGraphics();
	    		  g.drawImage(input, 0, (w-h)/2, input.getWidth(), input.getHeight(),null);
	    	   }else if(h>w){
	    		  output = new BufferedImage(h, h, BufferedImage.TYPE_INT_ARGB);
	    		  Graphics2D g = output.createGraphics();
	    		  g.drawImage(input, ((h-w)/2), 0, input.getWidth(), input.getHeight(),null); 		
	    	   } 
	    	   return output ;
	       }	
	    }

	       public  String encodeFileToBase64Binary(FileInputStream fileInputStreamReader){
	            String encodedfile = null;
	            try {
	               
	                byte[] bytes = new byte[(int)fileInputStreamReader.getChannel().size()];
	                fileInputStreamReader.read(bytes);
	                encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
	            } catch (FileNotFoundException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }

	            return encodedfile;
	        }

}
