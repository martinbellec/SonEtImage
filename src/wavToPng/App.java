package wavToPng;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import IHM.Config;
import IHM.Json;

public class App {
	
	
	public App(String filepathIn, String fileOut, Config conf) {
		spectro(filepathIn, fileOut,conf);
	}
		
	public void spectro(String filepathIn, String fileOut,Config config) {
        
        try {
            //get raw double array containing .WAV data
            ReadWAV2Array audioTest = new ReadWAV2Array(filepathIn, false);
            double[] rawData = audioTest.getByteArray();
            int length = rawData.length;
            
            //initialize parameters for FFT
            int WS = 2048; //WS = window size
            int OF = 16;    //OF = overlap factor
            int windowStep = WS/OF;

            //calculate FFT parameters
/*            double SR = audioTest.getSR();
            double time_resolution = WS/SR;
            double frequency_resolution = SR/WS;
            double highest_detectable_frequency = SR/2.0;
            double lowest_detectable_frequency = 5.0*SR/WS;

            System.out.println("time_resolution:              " + time_resolution*1000 + " ms");
            System.out.println("frequency_resolution:         " + frequency_resolution + " Hz");
            System.out.println("highest_detectable_frequency: " + highest_detectable_frequency + " Hz");
            System.out.println("lowest_detectable_frequency:  " + lowest_detectable_frequency + " Hz");
*/
            //initialize plotData array
            int nX = (length-WS)/windowStep;
            //int nY = WS;
            int nY = WS/2 + 1; //only the f/2 shanon
            double[][] plotData = new double[nX][nY]; 

            //apply FFT and find MAX and MIN amplitudes

            double maxAmp = Double.MIN_VALUE;
            double minAmp = Double.MAX_VALUE;

            double amp_square;

            double[] inputImag = new double[length];

            for (int i = 0; i < nX; i++){
                Arrays.fill(inputImag, 0.0);
                double[] WS_array = FFTbase.fft(Arrays.copyOfRange(rawData, i*windowStep, i*windowStep+WS), inputImag, true);
                for (int j = 0; j < nY; j++){
                    amp_square = (WS_array[2*j]*WS_array[2*j]) + (WS_array[2*j+1]*WS_array[2*j+1]);
                    if (amp_square == 0.0){
                        plotData[i][j] = amp_square;
                    }
                    else{
                    	/*Better*/
                    	// select threshold based on the expected spectrum amplitudes
                    	// e.g. 80dB below your signal's spectrum peak amplitude
                    	double threshold = 1.0;
                    	// limit values and convert to dB
                    	plotData[i][nY-j-1] = 10 * Math.log10(Math.max(amp_square,threshold));
                    }

                    //find MAX and MIN amplitude
                    if (plotData[i][j] > maxAmp)
                        maxAmp = plotData[i][j];
                    else if (plotData[i][j] < minAmp)
                        minAmp = plotData[i][j];
                }
            }

   /*         System.out.println("---------------------------------------------------");
            System.out.println("Maximum amplitude: " + maxAmp);
            System.out.println("Minimum amplitude: " + minAmp);
            System.out.println("---------------------------------------------------");

     */     //Normalization
            double diff = maxAmp - minAmp;
            for (int i = 0; i < nX; i++){
                for (int j = 0; j < nY; j++){
                    plotData[i][j] = (plotData[i][j]-minAmp)/diff;
                }
            }
            

            //plot image
            BufferedImage theImage = new BufferedImage(nX, nY, BufferedImage.TYPE_INT_RGB);
            double ratio;
            for(int x = 0; x<nX; x++){
                for(int y = 0; y<nY; y++){
                    ratio = plotData[x][y];
                    int newColor = Color.HSBtoRGB((float)((1-ratio) * config.getkeyHue()), config.getKeySaturation(),config.getKeyBrightness());
                  //  int newColor = Color.HSBtoRGB((float)((1-ratio) * 0.4), (float)1.0,(float)1.0);
                    theImage.setRGB(x, y, newColor);
                }
            }
            
			File outputfile = new File(fileOut);
            ImageIO.write(theImage, "png", outputfile);
            
            Json json = new Json(fileOut,config,false); //false because WavToPng
            
/*            System.out.println("File output path:  " + fileOut);
            System.out.println("end");
*/
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
