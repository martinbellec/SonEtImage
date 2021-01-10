package spectrographeV2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Interface implements ActionListener{
	
	public Interface() {
		TextFieldExample();
	}
	
	JTextField tf1,tf2;  
    JButton b1,b2; 
    String path = "/home/martin/Documents/ArtsEtSciences/";
	
	public static void main(String[] args) {
		Interface inter = new Interface();
	}
	 
    public void TextFieldExample(){  
        JFrame f= new JFrame();
        tf1=new JTextField();  
        tf1.setBounds(50,50,150,20);  
        tf2=new JTextField();  
        tf2.setBounds(50,100,150,20);  
        b1=new JButton("Spectrographe");  
        b1.setBounds(50,200,200,50);  
        b2=new JButton("Musique");  
        b2.setBounds(300,200,200,50);          b1.addActionListener(this);  
        b2.addActionListener(this);  
        f.add(tf1);f.add(tf2);f.add(b1);f.add(b2);  
        f.setSize(550,300);  
        f.setLayout(null);  
        f.setVisible(true);  
    }         
    public void actionPerformed(ActionEvent e) {  
        String fileIn = tf1.getText();  
        String fileOut = tf2.getText();  
        App test = new App(path + fileIn, path + fileOut);
        
    }  

	
}
