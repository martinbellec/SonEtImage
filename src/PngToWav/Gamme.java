package PngToWav;

public class Gamme {
	
	public Gamme() {
	}

	public double[] creerGamme(int choixGamme, int octaveBegin, int octaveEnd) {

		double c0 = 32.7032;
		double freqBegin = c0 * Math.pow(2, octaveBegin);
		int temp, temp2;
		int nombreDeNotes;

		double[] ArrayFreq = {};

		if (choixGamme == 0) {
			// Five Tone Equal Temperament
			
			nombreDeNotes = 5; 
			ArrayFreq = new double[octaveEnd*nombreDeNotes +1];
	        ArrayFreq[0] = freqBegin;
			for (temp = 0; temp < octaveEnd; temp++) {
				for (temp2 = 0; temp2 < nombreDeNotes; temp2++) {
					
					ArrayFreq[nombreDeNotes * temp + temp2 + 1] = ArrayFreq[temp2] * 1.148698354997035;
					
				}
			}
		}

		else if (choixGamme == 1) {
			// Twelve Tone Equal Temperament (gamme chromatique)
			
			nombreDeNotes = 12;
			ArrayFreq = new double[octaveEnd*nombreDeNotes +1];
	        ArrayFreq[0] = freqBegin;
			for (temp = 0; temp < octaveEnd; temp++) {
				for (temp2 = 0; temp2 < nombreDeNotes; temp2++) {
					
					ArrayFreq[nombreDeNotes * temp + temp2 + 1] = ArrayFreq[temp2] * 1.05946;
					
				}
			}
		}
		
		else if (choixGamme == 2) {
			// Twelve Tone Equal Temperament (gamme chromatique)
			
			nombreDeNotes = 7;
			ArrayFreq = new double[octaveEnd*nombreDeNotes +1];
	        ArrayFreq[0] = freqBegin;
			for (temp = 0; temp < octaveEnd; temp++) {
				
				ArrayFreq[nombreDeNotes * temp+1] = ArrayFreq[nombreDeNotes * temp] * 1.05946 * 1.05946;
				ArrayFreq[nombreDeNotes * temp+2] = ArrayFreq[nombreDeNotes * temp+1] * 1.05946 * 1.05946;
				ArrayFreq[nombreDeNotes * temp+3] = ArrayFreq[nombreDeNotes * temp+2] * 1.05946;
				ArrayFreq[nombreDeNotes * temp+4] = ArrayFreq[nombreDeNotes * temp+3] * 1.05946 * 1.05946;
				ArrayFreq[nombreDeNotes * temp+5] = ArrayFreq[nombreDeNotes * temp+4] * 1.05946 * 1.05946;
				ArrayFreq[nombreDeNotes * temp+6] = ArrayFreq[nombreDeNotes * temp+5] * 1.05946 * 1.05946;
				ArrayFreq[nombreDeNotes * temp+7] = ArrayFreq[nombreDeNotes * temp+6] * 1.05946;
				
			}
		}
		
		else if (choixGamme == 3) {
			// Twelve Tone Equal Temperament (gamme chromatique)
			
			nombreDeNotes = 7;
			ArrayFreq = new double[octaveEnd*nombreDeNotes +1];
	        ArrayFreq[0] = freqBegin;
			for (temp = 0; temp < octaveEnd; temp++) {
				
				ArrayFreq[nombreDeNotes * temp+1] = ArrayFreq[nombreDeNotes * temp] * 1.05946 * 1.05946;
				ArrayFreq[nombreDeNotes * temp+3] = ArrayFreq[nombreDeNotes * temp+1] * 1.05946;
				ArrayFreq[nombreDeNotes * temp+2] = ArrayFreq[nombreDeNotes * temp+2] * 1.05946 * 1.05946;
				ArrayFreq[nombreDeNotes * temp+4] = ArrayFreq[nombreDeNotes * temp+3] * 1.05946 * 1.05946;
				ArrayFreq[nombreDeNotes * temp+7] = ArrayFreq[nombreDeNotes * temp+4] * 1.05946;
				ArrayFreq[nombreDeNotes * temp+5] = ArrayFreq[nombreDeNotes * temp+5] * 1.05946 * 1.05946;
				ArrayFreq[nombreDeNotes * temp+6] = ArrayFreq[nombreDeNotes * temp+6] * 1.05946 * 1.05946;
				
			}
		}
		
		for(int i = 0; i< ArrayFreq.length; i++) {
			System.out.println(ArrayFreq[i]);
		}
		
		return (ArrayFreq);
		
	}
}
