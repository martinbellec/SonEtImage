package IHM;

import java.awt.Choice;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;


public class Json {

	/**	
	 * 	if choice is true : PngToWav
	 *	else : WavToPng
	 */	
	public Json (String fileOut,Config config, boolean choice)
	{
		JSONObject obj = new JSONObject();

		if (choice) { //PngToWav
			//provides a put function to insert the details into json object
			obj.put("Type", "PngToWav");
			
			String gamme;
			switch (config.getGamme()) {
			case 0 : 
					gamme = "5TET";
				break;
				
			case 1 : 
					gamme = "12TET";
			    break;
			
			case 2 : 
					gamme = "Majeur";
			    break;
			
			case 3 : 
					gamme = "Mineur";
			    break;

			default:
					gamme = "Erreur";
			    break;
			}
			obj.put("Gamme",gamme);
			
			obj.put("Octave Min",config.getOctaveMin());
			obj.put("Octave Max", config.getOctaveMax());
			obj.put("Key Volume Color", intToRGBString(config.getVolumeColor()));
			obj.put("key Panning Color", intToRGBString(config.getPannignColor()));
			obj.put("Duration", config.getDuration());
		} else { //WavToPng
			obj.put("Type", "WavToPng");
			obj.put("Key Hue",config.getkeyHue()*100);
			obj.put("Key Brightness",config.getKeyBrightness()*100);
			obj.put("Key Saturation", config.getKeySaturation()*100);
		}


		/*	JSONArray list = new JSONArray();

		//This is a JSON Array List , 
		//It creates an array and then add the values in it  
		list.add("remark 1");
		list.add("remark 2");
		list.add("remark 3");

		obj.put("remarks", list);//adding the list to our JSON Object*/

		//Write the object in a file
		//jsonWrite(obj,fileOut.substring(0,fileOut.indexOf(".")) + ".json");
		jsonWrite(obj,fileOut + ".json");
	}

	private void jsonWrite(JSONObject obj, String path)
	{
		try (FileWriter file = new FileWriter(path)) {
			//File Writer creates a file in write mode at the given location 
			file.write(obj.toJSONString());

			//write function is use to write in file,
			//here we write the Json object in the file
			file.flush();

		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 
	 * @param number
	 * @return Rouge if number = 0; Vert if number = 1; Bleu if number = 2
	 */
	private String intToRGBString(int number) {
		String color;
		switch (number) {
		case 0:
			color = "Rouge";
			break;
			
		case 1:
			color = "Vert";
			break;
		case 2:
			color = "Bleu";
			break;
		default:
			color = "Erreur";
			break;
		}
		
		return color;
	}

}
