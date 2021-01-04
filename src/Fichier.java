import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Fichier {
	
	    public HashMap<Integer, ArrayList<Integer>> edgesMapping = new HashMap<Integer,ArrayList<Integer>>();
	    
	    public Fichier(ArrayList<String> lines){ 
	        generateMapping(lines);
	    }

	    /*
	     * Une fonction qui permet de rï¿½cuperer un fichier 
	     * et le mettre dans un tableau de String en sortie
	     */
	    public static ArrayList<String> getFileInformation(String filepath){
	        ArrayList<String> returned = new ArrayList<String>(); // Rï¿½cupï¿½re chaque ligne du file
	        String line = null;

	        try {
	            FileReader fileReader = new FileReader(filepath); // FileReader reads text files in the default encoding.
	            BufferedReader bufferedReader = new BufferedReader(fileReader);  // Always wrap FileReader in BufferedReader.
	            
	            while((line = bufferedReader.readLine()) != null) {
	                returned.add(line);
	            }
	            
	            bufferedReader.close(); // Always close files.
	        }catch(FileNotFoundException ex) { // S'il ne trouve pas le fichier
	            System.out.println("Unable to open file '" +filepath + "'");
	        } catch(IOException ex) { // si le fichier est corrompu
	            System.out.println("Error reading file '"+ filepath + "'");
	        }

	        return returned;
	    }

	    /*
	     * Cette fonction permet d'instancier la hashmap avec les valeurs du fichier
	     */
	    public void generateMapping(ArrayList<String> lines) {
	        for(String line : lines){
	        	if( line.length() != 0  && line.charAt(0) != '#'  ) {
	                String[] temp_information = line.split("	");
	                Integer from = Integer.valueOf(temp_information[0]);
	                Integer to = Integer.valueOf(temp_information[1]);

	                // Map one direction
	                if( !edgesMapping.containsKey(from) ){
	                    ArrayList<Integer> tmp = new ArrayList<Integer>();
	                    tmp.add(to);
	                    edgesMapping.put(from, tmp); 
	                }else
	                    edgesMapping.get(from).add(to);
	                
	                // Map second direction
	                if( !edgesMapping.containsKey(to) ){
	                    ArrayList<Integer> tmp = new ArrayList<Integer>();
	                    tmp.add(from);
	                    edgesMapping.put(to, tmp);
	                }else 
	                    // Pour ne pas écraser une valeur de from du fichier
	                    if( !edgesMapping.get(to).contains(from) )
	                        edgesMapping.get(to).add(from);      
	            }
	        }
	    }
	    
	    /*
	     * Une fonction qui permet de lire l'ï¿½crire dans une fichier
	     */
	    public static void writeInformation(String file, String ecriture) {
	    	
	    	//Transforme l'espace entre les valeurs en tabulation
			if(ecriture.contains(" ")) { // S'il y a des espaces entre les lettres
				String[] temp_information = ecriture.split(" ");
		        Integer from = Integer.valueOf(temp_information[0]);
		        Integer to = Integer.valueOf(temp_information[1]);
		        ecriture = from + "	" + to; // Il y a une tabulation entre les valeurs
			}
	    	
	    	try(FileWriter fw = new FileWriter(file, true) ) {
	    		fw.write(ecriture + "\n");
	    		System.out.println();
	    		
	    	}catch (IOException e) { 
	            e.printStackTrace();
	         } 
	    }
	    
	    /*
	     * Une fonction d'aide pour afficher le contenue du fichier dans le terminal
	     */
	    public void showFile() {			
			for(Entry<Integer, ArrayList<Integer>> value: edgesMapping.entrySet()) {
				System.out.println(value);
				//System.out.println(edgesMapping.get(3));
			}
	    }
	  
	    /*
	     * Cette méthode affiche sur le terminal le nombre de sommet du graphe
	     */
	    public void nombre_sommets(String filepath) {
	    	int sommets = edgesMapping.size();
	    	System.out.println("Le nombre de sommets est : " + sommets);
	    }
	    
	    /*
	     * Cette méthode permet d'afficher sur le terminal le degre maximal du graphe
	     */
	    public void degre_maximal(String filepath) {
	    	int degre_max=0;
	    	//degre_max = edgesMapping.get(2).size();
	    	//System.out.println(degre_max);
	    	for(int key : edgesMapping.keySet()){
	    		if(degre_max <= edgesMapping.get(key).size()){
	    			degre_max = edgesMapping.get(key).size();
	    			//System.out.println("Le degre du sommet " + key + " est : " + edgesMapping.get(key).size());
	    		}	
	    	}
	    	System.out.println("Le degre maximal du graphe est : " + degre_max);
	    }
	    
	    /*
	     * Cette méthode permet d'afficher sur le terminal le degre moyen
	     */
	    public void degre_moyen(String filepath) {
	    	int degre_moyen = 0;
	    	for(int key : edgesMapping.keySet()) {
	    		degre_moyen += edgesMapping.get(key).size();
	    	}
	    	degre_moyen = degre_moyen/(edgesMapping.size());
	    	System.out.println("Le degre moyen du graphe est : " + degre_moyen);
	    }
	    
	    /*
	     * Cette fonction petmet d'afficher sur le terminal le nombre d'arrête du graphe
	     */
	    public void nombre_aretes(String filepath) {
	    	int aretes = 0;
	    	String line = null;
	    	try {
	    		BufferedReader reader = new BufferedReader(new FileReader(filepath));
	    		while ((line=reader.readLine()) != null) {
	    			if(line.length() != 0 && line.charAt(0) != '#')
	    				aretes++;
	    		}
	    		reader.close();
	    	}catch(FileNotFoundException ex) { // S'il ne trouve pas le fichier
	            System.out.println("Unable to open file '" +filepath + "'");
	        } catch(IOException ex) { // si le fichier est corrompu
	            System.out.println("Error reading file '"+ filepath + "'");
	        }
	    	System.out.println("Le nombre d'aretes est : " + aretes);
	    }
}