import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Graphe {
	
	public HashMap<Integer,ArrayList<Integer>> edgesMapping = new HashMap<Integer, ArrayList<Integer>>();
	private String filename;
	
	public Graphe(String filename) {
		this.filename = filename;
	}
	
	/*
	 * Cette méthode pour permet d'afficher le nombre de sommet dans un graphe
	 */
    public void nombre_sommets() {
    	int sommets = edgesMapping.size();
    	System.out.println("Le nombre de sommets est : " + sommets);
    }
    
    /*
     * Cette méthode permet d'afficher le degré maximal du graphe
     */
    public void degre_maximal() {
    	int degre_max=0;
    	//degre_max = edgesMapping.get(2).size();
    	//System.out.println(degre_max);
    	for(int key : edgesMapping.keySet()) {
    		if(degre_max <= edgesMapping.get(key).size()) {
    			degre_max = edgesMapping.get(key).size();
    			//System.out.println("Le degre du sommet " + key + " est : " + edgesMapping.get(key).size());
    		}	
    	}
    	System.out.println("Le degre maximal du graphe est : " + degre_max);
    }
    
    /*
     * Cette méthode affiche le degré moyen du graphe
     */
    public void degre_moyen() {
    	int degre_moyen = 0;
    	for(int key : edgesMapping.keySet()) {
    		degre_moyen += edgesMapping.get(key).size();
    	}
    	degre_moyen = degre_moyen/(edgesMapping.size());
    	
    	System.out.println("Le degre moyen du graphe est : " + degre_moyen);
    }
    
    /*
     * Cette méthode nous permet d'afficher le nombre d'arrête du graphe
     */
    public void nombre_aretes() {
    	int aretes = 0;
    	String line = null;
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(this.filename));
    		while ((line=reader.readLine()) != null) {
    			if(line.length() != 0 && line.charAt(0) != '#')
    				aretes++;
    		}
    		reader.close();
    	}catch(FileNotFoundException ex) { // S'il ne trouve pas le fichier
            System.out.println("Unable to open file '" +this.filename + "'");
        } catch(IOException ex) { // si le fichier est corrompu
            System.out.println("Error reading file '"+ this.filename + "'");
        }
    	System.out.println("Le nombre d'aretes est : " + aretes);
    }
    
    /*
     * Une fonction d'aide pour afficher le contenu du fichier dans le terminal
     */
    public void showFile() {			
		for(Entry<Integer, ArrayList<Integer>> value: edgesMapping.entrySet()) {
			System.out.println(value); 
		}
    }

}
