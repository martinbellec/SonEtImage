package PngToWav;

public class Gammes {

	public void creerGamme(int choixGamme, int octaveBegin, int octaveEnd) {

		double c0 = 32.7032;
		double freqBegin = c0 * Math.pow(2, octaveBegin);
		int temp, temp2;
		int nombreDeNotes;

		double[] ArrayFreq = new double[] { freqBegin };

		if (choixGamme == 0) {
			// Five Tone Equal Temperament
			
			nombreDeNotes = 5; 
			
			for (temp = 0; temp < octaveEnd - octaveBegin; temp++) {
				for (temp2 = 0; temp2 < nombreDeNotes; temp2++) {
					
					ArrayFreq[temp2 + 1] = ArrayFreq[temp2] * 1.148698354997035;
					
				}
			}
		}

		else if (choixGamme == 1) {
			// Twelve Tone Equal Temperament (gamme chromatique)
			
			nombreDeNotes = 12;
			for (temp = 0; temp < octaveEnd - octaveBegin; temp++) {
				for (temp2 = 0; temp2 < nombreDeNotes; temp2++) {
					
					ArrayFreq[temp2 + 1] = ArrayFreq[temp2] * 1.05946;
					
				}
			}
		}
		
		else if (choixGamme == 2) {
			// Twelve Tone Equal Temperament (gamme chromatique)
			
			nombreDeNotes = 7;
			for (temp = 0; temp < octaveEnd - octaveBegin; temp++) {
				
				ArrayFreq[temp+1] = ArrayFreq[temp] * 1.05946 * 1.05946;
				ArrayFreq[temp+2] = ArrayFreq[temp+1] * 1.05946 * 1.05946;
				ArrayFreq[temp+3] = ArrayFreq[temp+2] * 1.05946;
				ArrayFreq[temp+4] = ArrayFreq[temp+3] * 1.05946 * 1.05946;
				ArrayFreq[temp+5] = ArrayFreq[temp+4] * 1.05946 * 1.05946;
				ArrayFreq[temp+6] = ArrayFreq[temp+5] * 1.05946 * 1.05946;
				ArrayFreq[temp+7] = ArrayFreq[temp+6] * 1.05946;
				
			}
		}
		
		else if (choixGamme == 3) {
			// Twelve Tone Equal Temperament (gamme chromatique)
			
			nombreDeNotes = 7;
			for (temp = 0; temp < octaveEnd - octaveBegin; temp++) {
				
				ArrayFreq[temp+1] = ArrayFreq[temp] * 1.05946 * 1.05946;
				ArrayFreq[temp+3] = ArrayFreq[temp+2] * 1.05946;
				ArrayFreq[temp+2] = ArrayFreq[temp+1] * 1.05946 * 1.05946;
				ArrayFreq[temp+4] = ArrayFreq[temp+3] * 1.05946 * 1.05946;
				ArrayFreq[temp+7] = ArrayFreq[temp+6] * 1.05946;
				ArrayFreq[temp+5] = ArrayFreq[temp+4] * 1.05946 * 1.05946;
				ArrayFreq[temp+6] = ArrayFreq[temp+5] * 1.05946 * 1.05946;
				
			}
		}
		
	}
}
