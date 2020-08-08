import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Image_Processing
{
	public BufferedImage inversion(String path) throws IOException
	{
		File f = new File(path);
		
		BufferedImage in = ImageIO.read(f);

		int h = in.getHeight();

		int w = in.getWidth();

		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				int p = in.getRGB(j,i); 

				int a = (p>>24)&0xff; 

                int red = (p>>16)&0xff; 
                
                int green = (p>>8)&0xff; 
                
                int blue = p&0xff; 

                red = 255-red;
                green = 255-green;
                blue = 255-blue;

                p = (a<<24) | (red<<16) | (green<<8) | blue; 
                in.setRGB(j,i,p); 
			}
		}
		return in;
	}

	public BufferedImage grayscale(String path) throws IOException
	{
		File f = new File(path);
		
		BufferedImage in = ImageIO.read(f);

		int h = in.getHeight();

		int w = in.getWidth();

		for(int i=0;i<h;i++)
		{
			for(int j=0;j<w;j++)
			{
				int p = in.getRGB(j,i); 

				// System.out.println("The value of p " + p);

				int a = (p>>24)&0xff; 

                int red = (p>>16)&0xff; 
                
                int green = (p>>8)&0xff; 
                
                int blue = p&0xff; 

               	/*System.out.println("The value of p " + p);

               	System.out.println("The value of a " + a);
 
               	System.out.println("The value of red " + red);

               	System.out.println("The value of green " + green);

               	System.out.println("The value of blue " + blue);*/
                
                int avg = (red+blue+green)/3;

                p = (a<<24) | (avg<<16) | (avg<<8) | avg;

                in.setRGB(j,i,p); 
			}
		}

		return in;
	}

	public void testgray() throws IOException
	{
		File directoryPath = new File("/Users/saikarthikbonagiri/Desktop/Academics@S2/Images");

		File a[] = directoryPath.listFiles();

		File original[] = a;

		Vector<String> types = new Vector<String>();

		types.add("tif"); // Tagged Image File Format

		types.add("tiff");

		types.add("bmp"); // Bit map

		types.add("jpg"); 

		types.add("jpeg"); // Joint Photographic Experts Groups

		types.add("gif"); // Graphics Interchange Format

		types.add("png"); // Portable Network Graphics

		types.add("eps"); //  Encapsulated PostScript


		/* RAW images are images that are unprocessed that have been created by a camera or scanner. 
		Many digital SLR cameras can shoot in RAW, whether it be a .raw, .cr2, or .nef. 
		These RAW images are the equivalent of a digital negative, meaning that they hold a lot
		of image information, but still need to be processed in an editor such as 
		Adobe Photoshop or Lightroom. */
		
		types.add("raw");

		types.add("cr2");

		types.add("nef");

		types.add("orf");

		types.add("sr2");


		for(File file : original)
		{
			String path = file.getAbsolutePath();

			String fname = file.getName();

			int i;

			String s1 = ".";

			for(i=0;i<fname.length();i++)
			{
				String temp = "";
				temp = temp+fname.charAt(i);

				if(temp.equals(s1))
				{
					break;
				}
			}
		
			String check = "";

			for(int j=i+1;j<fname.length();j++)
			{
				check = check+fname.charAt(j);
			}

			if(types.contains(check))
			{
				BufferedImage newi = grayscale(path);

				String newname = "GrayScale-"+file.getName();
			
				File f1 = new File("/Users/saikarthikbonagiri/Desktop/Academics@S2/Images"+"/"+newname);

				ImageIO.write(newi,"png",f1); 

				BufferedImage new_inverted = inversion(path);

				String newname2 = "Inverted-"+file.getName();

				File f2 = new File("/Users/saikarthikbonagiri/Desktop/Academics@S2/Images"+"/"+newname2);

				ImageIO.write(new_inverted,"png",f2);
			}
			else
			{
				continue;
			}
		}
	}

	public static void main(String args[]) throws IOException
	{
		Image_Processing o1 = new Image_Processing();
		o1.testgray();
	}
}