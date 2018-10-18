package com.vsct.xspeedit.rg;

import java.util.ArrayList;

import com.vsct.xspeedit.model.Packaging;
import com.vsct.xspeedit.model.Constante;

/**
 * Controleur du service TriBasique.
 * Contient les règles de gestion de ce service.
 */
public class ControleurTriBasique {
	/**
	 * Règle de gestion RG_TRI_BASIQUE_01.
	 * Permet de trier de manière basique les articles.
	 * 
	 * @param listeArticles
	 * 			Tableau de String représentant chacune un article.
	 * @return Packaging
	 * 			Contient la liste du contenu des cartons (tableau de String).
	 */
	public static Packaging triArticles(ArrayList<String> listeArticles){
		// Initialisation 
		Packaging packaging = new Packaging();	
		ArrayList<String> listeCartons = new ArrayList<>();
		String carton = "";
		
		// On verifie par securité que la liste en entrée n'est pas vide
		// NB : Cas impossible dans le traitement actuel
		if(listeArticles.size() > 0){
			do{
				// On prend le premier article de la liste
				String article = listeArticles.get(0);
				
				// On regarde si celui-ci rentre dans le carton
				if(ControleurGen.getPlaceOccupee(carton) + Integer.parseInt(article) <= Constante.TAILLE_MAX_CARTON){
					// Si oui, on l'ajoute
					carton += article;
				}else{
					// Si non, on ferme le carton
					listeCartons.add(carton);
					
					// Et on initialise un nouveau carton avec cet article
					carton = article;			
				}
								
				// On le supprime des articles à traiter
				listeArticles.remove(article);			
			} while (listeArticles.size() > 0);
			
			// On ferme le dernier carton resté ouvert
			listeCartons.add(carton);	
		}		

		packaging.setListeCartons(listeCartons);
		return packaging;				
	}
}
