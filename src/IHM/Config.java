package IHM;

public class Config {
	
	private int gamme;
	private int octaveMin;
	private int octaveMax;
	private int keyVolumeColor;
	private int keyPannignColor;
	private double duration;

	/**
	 * 
	 * @param gamme  0 5TET ; 1 12TET ; 2 Majeur ; 3 Mineur
	 * @param octaveMin  0 to 
	 * @param octaveMax  1 to
	 * @param volumeColor  
	 * @param pannignColor
	 * @param duration
	 */
	public Config(int gamme, String octaveMin, String octaveMax, String volumeColor, String pannignColor, String duration) {
		super();
		this.gamme = gamme;
		this.octaveMin = Integer.parseInt(octaveMin);
		this.octaveMax = Integer.parseInt(octaveMax);
		System.out.println(volumeColor + pannignColor+duration);
		this.keyVolumeColor = StringRGBtoint(volumeColor);
		this.keyPannignColor = StringRGBtoint(pannignColor);
		this.duration =  Double.parseDouble(duration);
	}
	
	
	private int StringRGBtoint(String rgb) {
		int num = 0;
		switch (rgb) {
		case "Red":
			num = 0;
			break;
		case "Green":
			num = 1;
			break;
		case "Blue":
			num = 2;
			break;

		}
		return num;
	}

	/**
	 * 
	 * @return 0 5TET ; 1 12TET ; 2 Majeur ; 3 Mineur
	 */
	public int getGamme() {
		return gamme;
	}

	public int getOctaveMin() {
		return octaveMin;
	}

	public int getOctaveMax() {
		return octaveMax;
	}

	public int getVolumeColor() {
		return keyVolumeColor;
	}

	public int getPannignColor() {
		return keyPannignColor;
	}

	public double getDuration() {
		return duration;
	}
	
	public void display() {
		System.out.println("gamme "+gamme+" "+octaveMin + " "+octaveMax+" "+keyVolumeColor+" "+keyPannignColor+" " +duration);
	}
	
	
	
}
