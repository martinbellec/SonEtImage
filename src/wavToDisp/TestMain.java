package wavToDisp;

import com.sin.java.plot.Plot;
import com.sin.java.plot.PlotFrame;

public class TestMain {

	public static double[] Integers2Doubles(int[] raw) {
		double[] res = new double[raw.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = raw[i];
		}
		return res;
	}

	public static void drawWaveFile(String filename) {
		WaveFileReader reader = new WaveFileReader(filename);

		String[] pamss = new String[] { "-r", "-g", "-b" };
		if (reader.isSuccess()) {
			PlotFrame frame = Plot.figrue(String.format("%s %dHZ %dBit %dCH", filename, reader.getSampleRate(), reader.getBitPerSample(), reader.getNumChannels()));
			frame.setSize(500, 200);
			Plot.hold_on();
			for (int i = 0; i < reader.getNumChannels(); ++i) {

				int[] data = reader.getData()[i];

				Plot.plot(Integers2Doubles(data), pamss[i % pamss.length]);
			}
			Plot.hold_off();
		} else {
			System.err.println(filename + "erreur");
		}
	}

	public static void main(String[] args) {
		//drawWaveFile("/home/martin/Documents/ArtsEtSciences/file_example_WAV_1MG.wav");
		drawWaveFile("C:/ArtsEtSciences/RecordAudio.wav");

	}
}