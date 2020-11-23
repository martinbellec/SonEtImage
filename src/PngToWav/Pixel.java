package PngToWav;

public class Pixel {
	
	private int frequency;
	private double volume; // 0 - 255
	private int pan; // 0 - 255
	

	public Pixel(int frequency, int volume, int pan) {
		this.frequency = frequency;
		this.volume = volume /255.0;
		this.pan = pan;
	}


	public int getFrequency() {
		return frequency;
	}


	public double getVolume() {
		return volume;
	}

	public int getPan() {
		
		return pan;
	}

	public double getPanRight() {
		
		return pan/255.0;
	}
	
	public double getPanLeft() {
		return 1-(pan/255.0);
	}
	
	public double getGainLeft() {
		return volume * getPanLeft();
	}
	
	public double getGainRight() {
		return volume * getPanRight();
	}
	
	public void display() {
		System.out.println("Fréquence : "+ getFrequency() + " Volume : "+getVolume()+" Pan : " + getPan()+" PanR : " + getPanRight()+" PanL : " + getPanLeft());
	}
}
