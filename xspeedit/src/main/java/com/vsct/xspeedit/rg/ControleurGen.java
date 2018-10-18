package com.vsct.xspeedit.rg;

import java.util.ArrayList;

import com.vsct.xspeedit.model.Constante;

/**
 * Controleur générique.
 * Contient les règles de gestion génériques à l'application XspeedIt.
 */
public class ControleurGen {
    /**
     * Règle de gestion RG_GEN_01.
     * Permet le controle du format de la liste des articles donnés en entrée de service.
     * 
	 * @param articles
	 * 			String composée de chiffres représentant chacun la taille d'un article. 
	 * @throws Exception
	 * 			Exception levée en cas de non validation du format.
	 */
	public static void validerListeArticles(String articles) throws Exception{
		// On vérifie que chaque caractères est un chiffre
		if (!articles.matches("[0-9]+") || articles.length() <= 2) {			
			throw new Exception(Constante.ERREUR_FORMAT_LST_ART);
		}
		
		// Si besoin, possibilité d'ajouter un controle pour les String null ou vide
		// Ici inutile car l'URI du service serait introuvable dans ce cas (404 Not found)
	}	

    /**
     * Règle de gestion RG_GEN_02.
     * Permet de créer un tableau d'entier à partir des articles.
     * 
	 * @param articles
	 * 			String composée de chiffres représentant chacun la taille d'un article. 
	 * @return ArrayList<String>
	 * 			Tableau de String représentant chacune un article.
	 */
	public static ArrayList<String> creerListeArticles(String articles){
		ArrayList<String> listeArticlesDisponibles = new ArrayList<>();
		
		String[] listeArticles = articles.split("");	
		for(String article : listeArticles){			
			listeArticlesDisponibles.add(article);
		}	
		
		return listeArticlesDisponibles;
	}
	
    /**
     * Règle de gestion RG_GEN_03.
     * Permet de calculer la place occupée dans un carton à partir des articles à l'intérieur.
     * 
	 * @param articles
	 * 			String composée de chiffres représentant chacun la taille d'un article.
	 * @return int
	 * 			Place occupée. 
	 */
	public static int getPlaceOccupee(String articles){
		int placeOccupee = 0;
		
		if (null != articles && !articles.isEmpty()) {
			for (char ch: articles.toCharArray()) {
				placeOccupee += Character.getNumericValue(ch);
			}
		}
		
		return placeOccupee;
	}
}
