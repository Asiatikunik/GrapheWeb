import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Dijkstra {
	private HashMap<Integer, ArrayList<Integer>> edgesMapping;
	protected TabSommet[] algo;
	protected Integer[] precedent;
	protected int nb_sommet;
	private String fileName;
	protected final int depart;
	protected final int arriver;
	public int sommet_actuel;
	protected Stack<Integer> resultat;
	public static final int INFINITE = 20000;
	private int temps;
  
	public Dijkstra(int depart, int arriver, String fileName) {
		this.fileName = fileName;
		Fichier manager = new Fichier(Fichier.getFileInformation(fileName));
		this.edgesMapping = manager.edgesMapping;
		this.nb_sommet = nb_sommet() + 1;
		this.depart = depart;
		this.arriver = arriver;
		this.algo = new TabSommet[nb_sommet];
		this.precedent = new Integer[nb_sommet];
		sommet_actuel = depart;
		init_station();
		init_precedent();
		resultat = new Stack<Integer>();
		
		application_algo();
		show_resultat();
	}
	
	/*
	 * Cette méthode regroupe toutes les méthodes pour faire tournée l'algo Dijkstra
	 */
	public void application_algo(){	
		int distance_pere_fils=0;
		algo[depart].poids=0;
		
		while(algo[arriver].flag!=true){
			if(get_indice_min()!=-1)
				sommet_actuel=get_indice_min();
			
			algo[sommet_actuel].flag=true;
			for(int fils : edgesMapping.get(sommet_actuel)){ 
				distance_pere_fils = 1;
				if(algo[fils].flag==false) 
					if(algo[fils].poids>(algo[sommet_actuel].poids+distance_pere_fils) || algo[fils].poids==-1) {
						algo[fils].poids = algo[sommet_actuel].poids+distance_pere_fils;
						precedent[fils]=sommet_actuel;
					}
			}
			
		}
		temps=algo[arriver].poids;
	}
	
	/*
	 * Cette fonction sert a trouver le poids minimume de toute les arcs
	 * dans notre cas, toutes les poids sont a 1.
	 * Mais dans le cas où nous avons des poids
	 */
	private int get_indice_min(){
		int indice=-1;
		int min=-1;
		int i=-1;
			
		while(i<nb_sommet && min==-1){
			i++;
			if(algo[i].poids!= -1 && algo[i].flag==false) {
				min=algo[i].poids;
				indice= i;
			}		
		}
		for(int suivant=indice; suivant<nb_sommet;suivant++){
			if(min>algo[suivant].poids && algo[suivant].poids!=-1 && algo[suivant].flag == false){
				min=algo[suivant].poids;
				indice= suivant;
			}	
		}
		return indice;
	}
	
	/*
	 * Fonction d'affichage des résultats qu'on a stocker dans une pile.
	 * Elle affiche donc le plus court chemin 
	 */
	public void show_resultat() {
		empiler();
		int from=resultat.pop();
		System.out.print("Le chemin le plus court: \n" + depart);
		Boolean start = true;
		while(resultat.empty() != true) {
			int to = resultat.pop();
			System.out.print(" - "+ to );
			from=to;
			start = false;
		}
		System.out.println("\nIl y a " + temps + " arrêtes.");
	}
	
	/*
	 * Cette initiation permet d'empiler les résultats fur et a mesure du déroulement du code
	 */
	public void empiler(){
		int temp;
		resultat.push(arriver);
		temp=arriver;
		
		while(precedent[temp]!=null){
			temp=precedent[temp];
			resultat.push(temp);
		}
	}

	/*
	 * Méthode d'initiation de la structure du tableau
	 */
	private void init_station(){
		for(int s=0; s<nb_sommet; s++){
			algo[s]=new TabSommet();		
		}
	}
	
	/*
	 * Méthode d'initiation du tableau precedent
	 */
	private void init_precedent(){
		for(int s=0; s<nb_sommet; s++){
			precedent[s]=null;
		}	
	}
	
	/*
	 * C'est pour avoir le nombre de sommet
	 */
    public int nb_sommet() {
    	return this.edgesMapping.size();
    }


    /*
     * C'est pour avoir un tableau à deux valeurs
     * Elle permet d'avoir le poids
     * et si on a déjà visité un sommet
     */
	public class TabSommet {
		public int poids;
		public boolean flag;
		
		public TabSommet(){
			this.poids=-1; 
			this.flag=false;
		}
	} 
}




