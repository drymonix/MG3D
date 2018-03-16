
import MG3D.Camera;
import MG3D.Clavier;
import MG3D.Fenetre;
import MG3D.geometrie.Couleur;
import MG3D.geometrie.Cube;
import MG3D.geometrie.Cylindre;
import MG3D.geometrie.Point3D;

class Main {
	
	public static void main(String[] args) {
		Fenetre f = new Fenetre("Mon appli MG3D", 1080, 720);
		Clavier clavier = f.getClavier(); 
		Camera cam = f.getCamera();
		Point3D origine = new Point3D();
		Cylindre c = new Cylindre(origine, 1, 2);
		Cube cube = new Cube(Couleur.JAUNE, origine, 1.5);
		
		// A gauche et en haut, on diminue les valeurs
		// A droite et en bas, on augmente les valeurs
		float vitesse = 0.2f;
		float sensi = 1.5f;
		float vitGD = cam.getDeplacementGD();
		float vitHB = cam.getDeplacementHB();
		float orientGD = cam.getDirectionGD();
		float orientHB = cam.getDirectionHB();
		
		cube.translater(2, 0, 0);
		c.setCouleur(Couleur.MAGENTA);
		f.ajouter(c);
		f.ajouter(cube);
		
		// boucle de jeu
		while (true) {
			try {
				Thread.sleep(10);
			} catch (Exception err) {}
			
			if ( clavier.getEspaceTape() ){
				c.translater(0, 2, 0);
				c.setCouleur(Couleur.CYAN);
			}
			
			/*** DEPLACEMENTS ***/
			// avant
			if ( clavier.getZEnfoncee() ){
				vitHB+=vitesse;
				cam.setDeplacementHB(vitHB);
				System.out.println("J'avance..."+cam.getDeplacementHB());
			}
			// arriere
			if ( clavier.getSEnfoncee() ){
				vitHB-=vitesse;
				cam.setDeplacementHB(vitHB);
				System.out.println("Je recule..."+cam.getDeplacementHB());
			}
			// droite
			if ( clavier.getDEnfoncee() ){
				vitGD-=vitesse;
				cam.setDeplacementGD(vitGD);
				System.out.println("Droite..."+cam.getDeplacementGD());
			}
			// gauche
			if ( clavier.getQEnfoncee() ){
				vitGD+=vitesse;
				cam.setDeplacementGD(vitGD);
				System.out.println("Gauche..."+cam.getDeplacementGD());
			}
			
			/*** ORIENTATIONS / DIRECTIONS ***/
			// orientation droite
			if ( clavier.getDroiteEnfoncee() ){
				orientGD+=sensi;
				cam.setDirectionGD(orientGD);
				System.out.println("Orientation Droite..."+cam.getDirectionGD());
			}
			// orientation gauche
			if ( clavier.getGaucheEnfoncee() ){
				orientGD-=sensi;
				cam.setDirectionGD(orientGD);
				System.out.println("Orientation Gauche..."+cam.getDirectionGD());
			}
			// orientation haut
			if ( clavier.getHautEnfoncee() ){
				orientHB-=sensi;
				cam.setDirectionHB(orientHB);
				System.out.println("Orientation Haut..."+cam.getDirectionHB());
			}
			// orientation bas
			if ( clavier.getBasEnfoncee() ){
				orientHB+=sensi;
				cam.setDirectionHB(orientHB);
				System.out.println("Orientation Bas..."+cam.getDirectionHB());
			}
			f.rafraichir();
		}
		
	}
	
}
