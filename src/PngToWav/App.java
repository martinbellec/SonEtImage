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

public class App extends Thread{
	
	private int x;
	
	public static BufferedImage image;
	public static Pixel[][] imagData;
	public static double maxfrequency, duration;
	public static double frequencySteep;

	public static int nX, nY;
	public static long sampleRate;
	public static double[][] soundData;
	public static double[] gammeDeFrequence;
	
	public App(int x) {
		this.x = x;
	}
	
	public void run() {
		
		// Calculate the number of frames required for specified duration
		long numFrames = (long)(duration * sampleRate);
		int  sampleByPixel = (int) numFrames/nX;
		int temp;
				
		for(int y = 0; y<nY; y++){
			
			int rgb = image.getRGB(x, y); //get the RGB the pixel located on x,y
			//always returns TYPE_INT_ARGB
				

        	if (rgb != -1) { //if the pixel is not white
        		int red =   (rgb >> 16) & 0xFF; //bits[16-23]
            	//int green = (rgb >>  8) & 0xFF; //bits[8-15]
            	int blue =  (rgb      ) & 0xFF; //bits[0-7]
            	
            	double frequence = maxfrequency - y*frequencySteep;    ////////////////////////////////////
            	
            	temp = 0;
            	
            	while (frequence > gammeDeFrequence[temp]) {
            		temp ++;
            	}
            	frequence = gammeDeFrequence[temp-1];
            	
            	
            	
            	//System.out.println(red + " " + green + " " + blue + " " +rgb);
            	imagData[x][y] = new Pixel(frequence, red, blue);
            	//imagData[x][y].display();
            	
            	for(int sample = 0; sample < sampleByPixel ; sample++){
					// Fill the buffer, 
					soundData[0][x*sampleByPixel  + sample] += imagData[x][y].getGainLeft() * Math.sin( 2.0 * Math.PI * imagData[x][y].getFrequency() * (sampleByPixel*x+sample) / sampleRate);
					soundData[1][x*sampleByPixel  + sample] += imagData[x][y].getGainRight() * Math.sin( 2.0 * Math.PI * imagData[x][y].getFrequency() * (sampleByPixel*x+sample) / sampleRate);
					//System.out.println(x + " "+ y+ " " + sample+ " data "+ soundData[0][x*nY + y]);
				}
				//System.out.println(x + " "+ y+ " "+ " data "+ soundData[0][x*nY + y]);
        	}else
        		imagData[x][y] = null;
		}
	}
	
	public static void main(String[] args) {
		String path = "/home/martin/Documents/ArtsEtSciences/Sans titre";

		try {
			//open the input file to the stream
			InputStream streamInput =  new FileInputStream(path+".png");
			
			//create output file
			File outputfile = new File(path+".wav");

			//import the stream to an Array 
			image = ImageIO.read(streamInput);

			//close the input stream
			streamInput.close();

			//initialize imagData array
			nX = image.getWidth(); //the number of pixel on the x ax is the width
			nY = image.getHeight(); //the number of pixel on the y ax is the Height
			imagData = new Pixel[nX][nY];
			
			int choixGamme = 0;         /////////////////////////////////////////////////////////////////////
			int octaveBegin = 0;
			int octaveEnd = 3;
			
			Gamme gamme = new Gamme();
			gammeDeFrequence = gamme.creerGamme(choixGamme, octaveBegin, octaveEnd);
			//System.out.println(gammeDeFrequence[gammeDeFrequence.length - 4]);
			
			
			/* For the sound */
			sampleRate = 44100; // Samples per second
			maxfrequency = gammeDeFrequence[gammeDeFrequence.length - 1];
			double minfrequency = gammeDeFrequence[0];
			//double amplitude = 1.0;
			duration = 5.0; //seconds
			int channels = 2;
			//int nbNotes = 10;
			//int choixGamme = 0; // 0 5TET ; 1 12TET ; 2 Majeur ; 3 Mineur
			//int colorTolerance = 5; // 0-10 scale
			//int xTolerance = 5; // à-10 scale

			frequencySteep = (maxfrequency - minfrequency) / nY; //pas de fr�quence/pixel (partie entiere)
			
			/*System.out.println("---------------------------------------------------");
			System.out.println("Maximum frequency: " + maxfrequency);
			System.out.println("Minimum frequency: " + minfrequency);
			System.out.println("---------------------------------------------------");*/

			long numFrames = (long)(duration * sampleRate);

			// Create a wav file with the name specified as the first argument
			WavFile wavFile = WavFile.newWavFile(outputfile, channels, numFrames, 16, sampleRate);
			
			//initialize soundData array
			soundData = new double[channels][(int) numFrames];

			//creation of an App array for the threads
			App[] column = new App[nX];
			
			// Loop until all frames written
			//for all the image
			for (int x = 0; x<nX; x++) {
				//for all pixel of one column
				column[x] = new App(x);
	            column[x].start();
			}
			
			for (int x = 0; x<nX; x++) {
	            try {
					column[x].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			// Write the buffer
			wavFile.writeFrames(soundData, (int)numFrames);
			// Close the wavFile
			wavFile.close();
			//display info
			wavFile.display();

			System.out.println("Traitement terminé !");

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