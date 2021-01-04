import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		String fileName = "graphe.txt";		
		Graphe g = new Graphe(fileName);
		
		int num;
		boolean exit = false;
		
		while(exit != true) {
			
			do {
				System.out.println("1) Générer le graphe");
				System.out.println("2) Afficher les arrêtes");
				System.out.println("3) Nombre de sommets");
				System.out.println("4) Nombre d'arêtes");
				System.out.println("5) Degré maximal");
				System.out.println("6) Degré moyen");
				System.out.println("7) Plus court chemin");
				System.out.println("8) Quitter");
				System.out.println("Entrer votre choix: ");
				
				Scanner scan = new Scanner(System.in);
				num = scan.nextInt();

				switch(num) {
					case 1:
						System.out.println("Combien souhaitez vous avez d'arrêtes ? ");
						num = scan.nextInt();
						fileName = "cg_graphe.txt";
						CreateGraph cg = new CreateGraph(num, fileName);
						break;
					case 2:
						g.showFile();
						break;
					case 3:
						g.nombre_sommets();
						break;
					case 4:
						g.nombre_aretes();
						break;
					case 5:
						g.degre_maximal();
						break;
					case 6:
						g.degre_moyen();
						break;
					case 7:
						int a, b;
						System.out.println("Entrer le sommet de départ: ");
						a = scan.nextInt();
						System.out.println("Entrer le sommet d'arriver: ");
						b = scan.nextInt();
						Dijkstra chemin_court = new  Dijkstra(a, b, fileName); 
					case 8:
						exit = true;
						break;
					default:
						System.out.println("eummmm");
						break;
					}
				} while( num != 9);
		}

	}

}
