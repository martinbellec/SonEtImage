package IHM;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import PngToWav.App;

public class InterfacePngToWav {

	private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

	private JFrame f;

	public InterfacePngToWav() {
		makeFrame();
	}

	JTextField label1, label2;
	JButton bopen, bOut, bStart;

	public static void main(String[] args) {
		InterfacePngToWav inter = new InterfacePngToWav();
	}

	public void makeFrame() {
		f = new JFrame("Convertisseur Image -> Son");
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
					label2.setText(file.getPath().substring(0, file.getPath().indexOf('.')) + ".wav");
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

		JLabel labelGamme = new JLabel("Gamme :");
		labelGamme.setForeground(Color.WHITE);
		c.gridx = 2;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelGamme, c);

		JLabel labelOctave = new JLabel("Octave :");
		labelOctave.setForeground(Color.WHITE);
		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelOctave, c);

		JLabel labelVolume = new JLabel("Volume :");
		labelVolume.setForeground(Color.WHITE);
		c.gridx = 2;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelVolume, c);

		JLabel labelPanning = new JLabel("Panning :");
		labelPanning.setForeground(Color.WHITE);
		c.gridx = 2;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelPanning, c);

		JLabel labelDuration = new JLabel("Duration :");
		labelDuration.setForeground(Color.WHITE);
		c.gridx = 2;
		c.gridy = 4;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelDuration, c);

		Choice gamme = new Choice();
		gamme.add("5TET"); // 0
		gamme.add("12TET"); // 1
		gamme.add("Majeur"); // 2
		gamme.add("Mineur"); // 3
		gamme.select(2);
		c.gridx = 3;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(gamme, c);

		Choice minOctave = new Choice();
		for (Integer i = 0; i < 10; i++) {
			minOctave.add(i.toString());
		}
		minOctave.select(1);
		c.gridx = 3;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(minOctave, c);

		Choice maxOctave = new Choice();
		for (Integer i = 1; i < 10; i++) {
			maxOctave.add(i.toString());
		}
		maxOctave.select(4);
		c.gridx = 4;
		c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(maxOctave, c);

		CheckboxGroup grpVolume = new CheckboxGroup();
		Checkbox VolumeR = new Checkbox("Red", grpVolume, true);
		VolumeR.setForeground(Color.RED);
		c.gridx = 3;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(VolumeR, c);

		Checkbox VolumeG = new Checkbox("Green", grpVolume, false);
		VolumeG.setForeground(Color.GREEN);
		c.gridx = 4;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(VolumeG, c);

		Checkbox VolumeB = new Checkbox("Blue", grpVolume, false);
		VolumeB.setForeground(Color.BLUE);
		c.gridx = 5;
		c.gridy = 2;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(VolumeB, c);

		CheckboxGroup grpPanning = new CheckboxGroup();
		Checkbox PanningR = new Checkbox("Red", grpPanning, false);
		PanningR.setForeground(Color.RED);
		c.gridx = 3;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(PanningR, c);

		Checkbox PanningG = new Checkbox("Green", grpPanning, false);
		PanningG.setForeground(Color.GREEN);
		c.gridx = 4;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(PanningG, c);

		Checkbox PanningB = new Checkbox("Blue", grpPanning, true);
		PanningB.setForeground(Color.BLUE);
		c.gridx = 5;
		c.gridy = 3;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(PanningB, c);
		
		JTextField tfDuration = new JTextField("10");
		c.gridx = 3;
		c.gridy = 4;
		c.gridheight = 1; // do not remove
		c.gridwidth = 1; // do not remove
		c.insets = insets;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		contentPane.add(tfDuration, c);

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
				Config conf = new Config(gamme.getSelectedIndex(),minOctave.getSelectedItem(),maxOctave.getSelectedItem(),grpVolume.getSelectedCheckbox().getLabel(),grpPanning.getSelectedCheckbox().getLabel(),tfDuration.getText()  );
				App test = new App(fileIn, fileOut, conf);
			}
		});
		contentPane.add(bStart, c);

		JLabel labelSeconde = new JLabel("(Second)");
		labelSeconde.setForeground(Color.WHITE);
		c.gridx = 4;
		c.gridy = 4;
		c.gridheight = 1; //do not remove
		c.gridwidth = 1; //do not remove
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = insets;
		contentPane.add(labelSeconde, c);

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
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Filter", "png")); // permet de choisir en
																								// priorité les fichiers
																								// de type .png
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

	private void quit() {
		System.exit(0);
	}

}
