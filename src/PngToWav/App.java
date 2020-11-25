package PngToWav;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;



/* Read a image and make sound for each column of pixel.
 * You can def the maximum frequency (top a the image) and the minimum frequency (bottom of the image.
 * You also can set the 
 * 
 * if the pixel is black : no sound
 * the variation of red impact the volume of the frequency
 * the variation of green
 * the variation of blue impact the PAN
 */

public class App {

	public static void main(String[] args) {
		String path = "C:/ArtsEtSciences/pixelLinePan";

		// TODO Auto-generated method stub
		try {
			//open the input file to the stream
			InputStream streamInput =  new FileInputStream(path+".png");
			
			//create output file
			File outputfile = new File(path+".wav");

			//import the stream to an Array 

			BufferedImage image = ImageIO.read(streamInput);


			//initialize imagData array
			int nX = image.getWidth(); //the number of pixel on the x ax is the width
			int nY = image.getHeight(); //the number of pixel on the y ax is the Height
			Pixel[][] imagData = new Pixel[nX][nY];
			
			//variable to get the hue of each pixel 
//			float[] hsbValues = new float[3];
//			float hue, saturation, brightness;
			
			/* For the sound */
			long sampleRate = 44100; // Samples per second
			double maxfrequency = 1000;
			double minfrequency = 440;
			double amplitude = 1.0;
			double duration = 5.0; //seconds
			int channels = 2;
			int nbNotes = 10;
			int gamme = 0;
			
			
			if((maxfrequency - minfrequency) < nY)
			{
				maxfrequency = minfrequency + nY;
			}
			int frequencySteep = (int)(maxfrequency - minfrequency) / nY; //pas de fr�quence/pixel (partie entiere)
			

			//for each pixel
			for(int x = 0; x<nX; x++){
				for(int y = 0; y<nY; y++){

					int rgb = image.getRGB(x, y); //get the RGB the pixel located on x,y
					//always returns TYPE_INT_ARGB

                	if (rgb != -16777216) { //if the pixel is not black
                		int red =   (rgb >> 16) & 0xFF; //bits[16-23]
                    	int green = (rgb >>  8) & 0xFF; //bits[8-15]
                    	int blue =  (rgb      ) & 0xFF; //bits[0-7]
                    	System.out.println(red + " " + green + " " + blue + " " +rgb);
                    	imagData[x][y] = new Pixel(y*frequencySteep + (int)minfrequency, red, blue);
                    	imagData[x][y].display();
                	}else
                		imagData[x][y] = null;
                	
                	/*hsbValues = Color.RGBtoHSB(red,green,blue,null);
					
	               	hue =  hsbValues[0]; //nuence
	            	saturation = hsbValues[1]; 
	            	brightness = hsbValues[2];*/

					//imagData[x][y] =new Pixel(y*frequencySteep + (int)minfrequency, 1-(256 - red)/256, 1-(256 -blue)/256);
					//imagData[x][y].display();
				}
			}

			//close the input stream
			streamInput.close();



			System.out.println("---------------------------------------------------");
			System.out.println("Maximum frequency: " + maxfrequency);
			System.out.println("Minimum frequency: " + minfrequency);
			System.out.println("---------------------------------------------------");

			// Calculate the number of frames required for specified duration
			long numFrames = (long)(duration * sampleRate);
			int  sampleByPixel = (int) numFrames/nX;
			
			// Create a wav file with the name specified as the first argument
			WavFile wavFile = WavFile.newWavFile(outputfile, channels, numFrames, 16, sampleRate);
			

			//initialize soundData array
			double[][] soundData = new double[channels][(int) numFrames];
			
			
			// Initialise a local frame counter
			long frameCounter = 0;
			// Loop until all frames written
			//for all the image
			for (int x = 0; x<nX; x++) {
				//for all pixel of one column
				for(int y = 0; y<nY; y++){
					if(imagData[x][y] != null)
					{
						for(int sample = 0; sample < sampleByPixel ; sample++ , frameCounter++){
							// Fill the buffer, 
							soundData[0][x*sampleByPixel  + sample] += imagData[x][y].getGainLeft() * Math.sin( 2.0 * Math.PI * imagData[x][y].getFrequency() * frameCounter / sampleRate);
							soundData[1][x*sampleByPixel  + sample] += imagData[x][y].getGainRight() * Math.sin( 2.0 * Math.PI * imagData[x][y].getFrequency() * frameCounter / sampleRate);
							//System.out.println(x + " "+ y+ " " + sample+ " data "+ soundData[0][x*nY + y]);
						}
						//System.out.println(x + " "+ y+ " "+ " data "+ soundData[0][x*nY + y]);
					}
				}
			}
			
			System.out.println(numFrames/nX + " "+ nX +" "+nY + " "+(int)(sampleByPixel * nX));

			
			System.out.println("Ready to export ...");
			
			// Write the buffer
			wavFile.writeFrames(soundData, (int)numFrames);
			
			// Close the wavFile
			wavFile.close();
			
			//display info
			wavFile.display();


			/*            //Normalization
            double diff = maxAmp - minAmp;
            for (int i = 0; i < nX; i++){
            	soundData[i] = (soundData[i]-minAmp)/diff;
            }*/


			

			System.out.println("end");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (WavFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}