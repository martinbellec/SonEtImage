package IHM;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import wavToPng.App;

public class InterfaceWavToPng {

	private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

	private JFrame f;

	public InterfaceWavToPng() {
		makeFrame();
	}

	JTextField label1, label2;
	JButton bopen, bOut, bStart;

	public static void main(String[] args) {
		InterfaceWavToPng inter = new InterfaceWavToPng();
	}

	public void makeFrame() {
		f = new JFrame("Convertisseur Son -> Image");
		f.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("INP.jpg")).getImage());

		f.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		Container contentPane = f.getContentPane();
		contentPane.setBackground(Color.black);
		Insets insets = new Insets(20, 10, 5, 20);

		label1 = new JTextField();
		label1.setBackground(Color.WHITE);
		c.gridx = 1;
		c.gridy = 2;
		c.insets = insets;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(label1, c);

		// insets.set(5, 10, 5, 20);

		bopen = new JButton(new ImageIcon(getClass().getClassLoader().getResource("BoutonOpen.PNG")));
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		c.insets = insets;
		c.gridheight = 2;
		bopen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = openFile();
				// System.out.println("ici");
				if (file != null) {
					// file= openFile();
					label1.setText(file.getPath());
					label2.setText(file.getPath().substring(0, file.getPath().indexOf('.')) + ".png");
				}
			}
		});
		contentPane.add(bopen, c);

		bOut = new JButton(new ImageIcon(getClass().getClassLoader().getResource("BoutonOut.PNG")));
		// bOut.setBackground(Color.BLACK);
		c.gridx = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		// c.weighty = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.CENTER;
		c.insets = insets;
		c.gridheight = 2;
		bOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// System.out.println("la");
				File file = openDirectory();
				if (file != null) {

					label2.setText(file.getPath() + label2.getText().substring(label2.getText().lastIndexOf("\\"),
							label2.getText().length()));
				}
			}
		});
		contentPane.add(bOut, c);

		label2 = new JTextField();
		label2.setText((System.getProperty("user.dir") + "\\out.wav"));
		c.gridx = 1;
		c.gridy = 6;
		c.gridheight = 1; // do not remove
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(label2, c);


		JLabel labelHue = new JLabel("Teinte :");
		labelHue.setForeground(Color.WHITE);
		c.gridx = 2;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelHue, c);

		JLabel labelSaturation = new JLabel("Saturation :");
		labelSaturation.setForeground(Color.WHITE);
		c.gridx = 2;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelSaturation, c);
		
		JLabel labelBrightness = new JLabel("Brillance :");
		labelBrightness.setForeground(Color.WHITE);
		c.gridx = 2;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelBrightness, c);

		Label labelValHue = new Label("40");
		labelValHue.setForeground(Color.WHITE);
		labelValHue.setAlignment(java.awt.Label.CENTER);
		c.gridx = 3;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelValHue, c);
		
		Label labelValSaturation = new Label("100");
		labelValSaturation.setForeground(Color.WHITE);
		labelValSaturation.setAlignment(java.awt.Label.CENTER);
		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelValSaturation, c);
		
		Label labelValBrightness = new Label("100");
		labelValBrightness.setForeground(Color.WHITE);
		labelValBrightness.setAlignment(java.awt.Label.CENTER);
		c.gridx = 3;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelValBrightness, c);


		Scrollbar scrollbarHue = new Scrollbar();
		scrollbarHue.setOrientation(Scrollbar.HORIZONTAL);
		scrollbarHue.setValues(40, 20, 0, 120);
		scrollbarHue.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				labelValHue.setText(String.valueOf(scrollbarHue.getValue()));
			}
			});
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(scrollbarHue, c);
		

		Scrollbar scrollbarSaturation = new Scrollbar();
		scrollbarSaturation.setOrientation(Scrollbar.HORIZONTAL);
		scrollbarSaturation.setValues(100, 20, 0, 120);
		scrollbarSaturation.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				labelValSaturation.setText(String.valueOf(scrollbarSaturation.getValue()));
			}
			});
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(scrollbarSaturation, c);
		
		Scrollbar scrollbarBrightness = new Scrollbar();
		scrollbarBrightness.setValues(100, 20, 0, 120);
		scrollbarBrightness.setOrientation(Scrollbar.HORIZONTAL);
		scrollbarBrightness.addAdjustmentListener(new AdjustmentListener() {
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				labelValBrightness.setText(String.valueOf(scrollbarBrightness.getValue()));
			}
			});
		c.gridx = 4;
		c.gridy = 4;
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(scrollbarBrightness, c);
		

		bStart = new JButton("Start");// new ImageIcon(System.getProperty("user.dir") + "\\images\\BoutonOut.png"));
		c.gridx = 4;
		c.gridy = 5;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 2;
		c.gridwidth = 2;
		c.insets = insets;
		bStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileIn = label1.getText();
				String fileOut = label2.getText();
				Config conf = new Config(scrollbarHue.getValue(),scrollbarBrightness.getValue(),scrollbarSaturation.getValue());
				App test = new App(fileIn, fileOut, conf);
			}
		});
		contentPane.add(bStart, c);
		

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		//f.pack();
		f.setLocation(d.width / 3, d.height / 5);
		f.setSize(800, 500);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}

	/** Open function: open a file chooser to select a new automaton */
	private File openFile() {

		fileChooser.resetChoosableFileFilters();
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Sound Filter", "wav")); // permet de choisir en
																								// priorité les fichiers
																								// de type .wav
		fileChooser.setAcceptAllFileFilterUsed(true); // permet de choisir tous les types de fichier

		int returnVal = fileChooser.showOpenDialog(f);

		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return null; // cancelled
		}

		File selectedFile = fileChooser.getSelectedFile(); // récupère le fichier choisi par l'utilisateur

		if (selectedFile.canRead()) {
			return selectedFile;
		} else {
			JOptionPane.showMessageDialog(f, "The file was not in a recognized read file format. (PNG)",
					"File read Error", JOptionPane.ERROR_MESSAGE); // message d'erreur : le fichier n'a pas pu être lu
			return null;
		}

	}

	private File openDirectory() {

		fileChooser.resetChoosableFileFilters();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);

		int returnVal = fileChooser.showOpenDialog(f);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getCurrentDirectory();
		} else {
			System.out.println("No Selection ");
		}
		return null;
	}

}
