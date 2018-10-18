package com.vsct.xspeedit.rg;

import java.util.ArrayList;
import java.util.Collections;

import com.vsct.xspeedit.model.Packaging;
import com.vsct.xspeedit.model.Constante;

/**
 * Controleur du service TriOptimise.
 * Contient les règles de gestion de ce service.
 */
public class ControleurTriOptimise {	
	/**
	 * Règle de gestion RG_TRI_OPTIMISE_01.
	 * Permet de trier de manière optimisée les articles.
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
		String carton, plusGrandArticle = "";
		
		// On verifie par securité que la liste en entrée n'est pas vide
		// NB : Cas impossible dans le traitement actuel
		if(listeArticles.size() > 0){
			// On traite les plus grands en priorité
			Collections.sort(listeArticles, Collections.reverseOrder());	
			
			// Tant qu'il y a des articles a traiter
			do{
				// On met le premier article dans le carton et on l'enlève des articles à traiter
				carton = listeArticles.remove(0);

				// Tant que des articles rentrent et que le carton n'est pas rempli									
				do{
					// On prend le plus grand article disponible qui rentre encore
					plusGrandArticle = getPlusGrandArticlePossible(listeArticles, ControleurGen.getPlaceOccupee(carton));
					
					// Si celui-ci existe
					if(plusGrandArticle != null && !plusGrandArticle.isEmpty()){
						// On l'ajoute au carton
						carton += plusGrandArticle;
						
						// On le supprime des articles à traiter
						listeArticles.remove(plusGrandArticle);					
					}
				} while(plusGrandArticle != null && !plusGrandArticle.isEmpty() && ControleurGen.getPlaceOccupee(carton) < Constante.TAILLE_MAX_CARTON);

				listeCartons.add(carton);			
				
			} while (listeArticles.size() > 0);	
		}		
		
		packaging.setListeCartons(listeCartons);
		return packaging;
	}
	
	/**
	 * Règle de gestion RG_TRI_OPTIMISE_02
	 * Permet de récupérer le plus grand article pouvant entrer dans le carton
	 * 
	 * @param listeArticles
	 * 			Tableau de String représentant chacune un article
	 * @param placeOccupee
	 *  		Place occupée dans le carton
	 * @return String 
	 * 			String représentant la taille du plus grand article pouvant entrer dans le carton
	 */
	public static String getPlusGrandArticlePossible(ArrayList<String> listeArticles, Integer placeOccupee) {
		// Initialisation
		ArrayList<Integer> listeArticlesEntrantsDansCarton = new ArrayList<Integer>();
		
		// Place restante dans le carton
		int placeRestante = Constante.TAILLE_MAX_CARTON - placeOccupee;
		
		// On récupère tous les articles pouvant encore entrer dans le carton
		for (String article : listeArticles){
			int tailleArticle;
			
			// Si jamais un article n'était pas représenté pas un chiffre (NumberFormatException), on fixe sa taille à zéro
			// NB : Cas impossible dans le traitement actuel
			try{
				tailleArticle = Integer.parseInt(article);				
			}catch (Exception e){
				tailleArticle = 0;
			}
			
			if(Integer.compare(tailleArticle, placeRestante) <= 0){
				listeArticlesEntrantsDansCarton.add(tailleArticle);
			}
		}
				
		// On retourne le plus grand d'entres eux (retourne null si il n'y en a plus)
		try{
			return Collections.max(listeArticlesEntrantsDansCarton).toString();			
		}catch (Exception e) {
			return null;
		}		
	}
}
