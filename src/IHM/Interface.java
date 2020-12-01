package IHM;

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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;


import PngToWav.*;

public class Interface implements ActionListener{
	
	 private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
	
	 
	 private JFrame f;
	 
	 
	public Interface() {
		makeFrame();
	}
	
	JTextField label1,label2;  
    JButton b1,b2,bopen,bOut; 
    String path = "/home/martin/Documents/ArtsEtSciences/";
	
	public static void main(String[] args) {
		Interface inter = new Interface();
	}
	 
    public void makeFrame(){
        f= new JFrame("Convertisseur Son -> Image");
        try {
			f.setIconImage(ImageIO.read(new FileInputStream(System.getProperty("user.dir") + "\\images\\INP.jpg")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
        f.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        Container contentPane = f.getContentPane();
        contentPane.setBackground( Color. black );
       // contentPane.setLayout(new BorderLayout(6, 6));
        Insets insets = new Insets(5, 10,20, 20);
        
        
        label1=new JTextField();
        label1.setBackground(Color.WHITE);
        //label1.setBounds(50,50,150,20);
        //label1.setText("coucou");
        c.gridx = 1;
        c.gridy = 2;
        c.insets = insets;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.weightx = 1;
       // c.ipady = 20;
        contentPane.add(label1,c);
        
        insets.set(5, 10, 5, 20);
        
        bopen=new JButton(new ImageIcon(System.getProperty("user.dir") + "\\images\\BoutonOpen.png"));
        //bopen.setBounds(50,100,200,50);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.NONE;
        //c.ipady = 0;
        c.insets = insets;
        bopen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file =  openFile();
				//System.out.println("ici");
				if (file !=null )
				{
					//file= openFile();
					label1.setText(file.getPath());
				}
			}
		});
        contentPane.add(bopen,c);
        
        
        
        
        bOut=new JButton(new ImageIcon(System.getProperty("user.dir") + "\\images\\BoutonOut.png"));
        //bopen.setBounds(50,100,200,50);
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.SOUTHWEST;
        c.fill = GridBagConstraints.NONE;
        c.insets = insets;
        bOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("la");
				File file =  openDirectory();
				if (file !=null )
				{
					label2.setText(file.getPath());
				}
			}
		});
        contentPane.add(bOut,c);
        
        
        label2=new JTextField();
        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.SOUTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = insets;
       
        contentPane.add(label2,c);
        
        
        
       

        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        f.setLocation(d.width/3, d.height/5); 
        f.setSize(800,500);  
       // f.setLayout(null);  
        f.setVisible(true);
    }
    
    /** Open function: open a file chooser to select a new automaton */
    private File openFile(){
    	
    	fileChooser.resetChoosableFileFilters();
    	fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Filter", "png")); //permet de choisir en priorité les fichiers de type .png
    	fileChooser.setAcceptAllFileFilterUsed(true); //permet de choisir tous les types de fichier

        int returnVal = fileChooser.showOpenDialog(f);

        
        if(returnVal != JFileChooser.APPROVE_OPTION) {
            return null;  // cancelled
        }
        
        File selectedFile = fileChooser.getSelectedFile(); //récupère le fichier choisi par l'utilisateur
        
        if(selectedFile.canRead()){
        	return selectedFile;
        } else {
            JOptionPane.showMessageDialog(f,
                    "The file was not in a recognized read file format. (PNG)",
                    "File read Error",
                    JOptionPane.ERROR_MESSAGE); //message d'erreur : le fichier n'a pas pu être lu
            return null;
        }

    }
    
    
    private File openDirectory(){
    	
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
    
    public void actionPerformed(ActionEvent e) {  
        String fileIn = label1.getText();  
        String fileOut = label2.getText();  
        App test = new App(path + fileIn, path + fileOut);
        
    }

}
