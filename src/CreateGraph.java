
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class CreateGraph {
	public int nb_ligne;
	public String namefile;
	
	public CreateGraph(int nb_ligne, String namefile) {
		
		this.nb_ligne = nb_ligne;
		this.namefile = namefile;
		File file = new File(namefile);
		 
		   // créer le fichier s'il n'existe pas
		   if (!file.exists()) 
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
		   else 
			    try {
					   file.delete();
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
		   
		graphe_eg();
		graphe_ba();
		System.out.println("Génération d'un graphe de " + nb_ligne + " arrêtes.");
		
	}

	public  void graphe_eg() {
		int k;
		for (int i=1 ;i<nb_ligne; i++) {
			for(int j=i+1 ; j<nb_ligne ; j++) {
				if(i!=j) {
					Random r = new Random();
					k= r.nextInt(2); // genere un nbr entier sup ou egale a 0 et < 2 
					if(k==1) {
						String content = i+"	"+j;
						Fichier.writeInformation(this.namefile, content);
					}
				}
			}
		}	
	}
	
	public  void graphe_ba() {
		float p,k;
		String content;

		content = "1 2";  
		Fichier.writeInformation(this.namefile, content);
		
		content = "1 3";  
		Fichier.writeInformation(this.namefile, content);
		
		content = "2 3";  
		Fichier.writeInformation(this.namefile, content);

		
		for(int i =4 ; i<nb_ligne ; i++) {
			for(int j=1 ; j<i-1 ; j++) {
				p=deg(j)/nb_elem();
				Random r = new Random();
				k= r.nextInt(nb_elem()); 
				if(k<=deg(j)) {
					content = i+"	"+j;  
					Fichier.writeInformation(this.namefile,content);
				}
			}
		}
	}
	
	public  int nb_elem() {
	      File file = new File(this.namefile); 
	      int nbrLine = 0;       
	      try {
			  FileReader fr = new FileReader(file);
			  BufferedReader br = new BufferedReader(fr);  
			  String str;
			  while((str = br.readLine()) != null) {
			     nbrLine++;               
			        
			  }
			  fr.close();
	      } catch (IOException e) {
			   e.printStackTrace();
	      }
		return nbrLine ;
	}

	public int deg(int j) {
		
		File file = new File(this.namefile); 
	    String[] words = null; 
	    int count = 0;
	    try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);  
			String str;     
			String search = String.valueOf(j);   
			while((str = br.readLine()) != null)  {
			  words = str.split("");   //grr
			  for (String word : words) 
			    if (word.equals(search))  
			      count++;    
			}
			fr.close();
	    } catch (IOException e) {
		   e.printStackTrace();
	    }
		
		return count;
	}
}

