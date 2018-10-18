package com.vsct.xspeedit.traitement;

import java.util.ArrayList;

import com.vsct.xspeedit.model.Packaging;
import com.vsct.xspeedit.rg.ControleurGen;
import com.vsct.xspeedit.rg.ControleurTriOptimise;

/**
 * Traitement fonctionnel du service TriOptimise.
 */
public class TraitementTriOptimise {	
    /**
     * Constructeur de la classe.
     */
	public TraitementTriOptimise(){
		super();
	}

	/** 
	 * Méthode permettant de l'execution du traitement fonctionnel du service
	 * 
	 * @param articles
	 * 			String composée de chiffres représentant chacun la taille d'un article
	 * @return Packaging
	 * 			Contient la liste du contenu des cartons (tableau de String)
	 * @throws Exception
	 * 			Exception levée en cas de problème lors du traitement fonctionnel
	 */
	public static Packaging traitementFonctionnel(String articles) throws Exception{
		// RG_GEN_01
		ControleurGen.validerListeArticles(articles);		

		// RG_GEN_02
		ArrayList<String> listeArticles = ControleurGen.creerListeArticles(articles);
		
		// RG_TRI_OPTIMISE_01
		Packaging emballageArticles = ControleurTriOptimise.triArticles(listeArticles);

		return emballageArticles;	
	}
}
