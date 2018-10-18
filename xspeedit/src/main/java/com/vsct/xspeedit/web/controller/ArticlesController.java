package com.vsct.xspeedit.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vsct.xspeedit.model.Packaging;
import com.vsct.xspeedit.traitement.TraitementTriBasique;
import com.vsct.xspeedit.traitement.TraitementTriOptimise;

@RestController
public class ArticlesController {	
	/**
	 * Service REST permettant de retourner les articles donnés selon un tri basique :
	 * - Les articles sont placés un par un dans un carton
	 * - Si l'article ne rentre pas, on passe au carton suivant.
	 * 
	 * @param articles
	 * 			String composée de chiffres représentant chacun la taille d'un article.
	 * @return Packaging
	 * 			Contient la liste du contenu des cartons (tableau de String)
	 * @throws Exception
	 * 			Exception levée en cas de problème lors de l'execution du service.
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/TriBasique/{articles}", method = RequestMethod.GET)
	public Packaging triBasique(@PathVariable String articles) throws Exception {		
		return TraitementTriBasique.traitementFonctionnel(articles);		
	}	
	
	/**
	 * Service REST permettant de retourner les articles donnés selon un tri optimisé :
	 * - Les articles sont placés de façon à limiter le nombre de cartons.
	 * 
	 * @param articles
	 * 			String composée de chiffres représentant chacun la taille d'un article.
	 * @return Packaging
	 * 			Contient la liste du contenu des cartons (tableau de String)
	 * @throws Exception
	 * 			Exception levée en cas de problème lors de l'execution du service.
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/TriOptimise/{articles}", method = RequestMethod.GET)
	public Packaging triOptimise(@PathVariable String articles) throws Exception {		
		return TraitementTriOptimise.traitementFonctionnel(articles);	
	}
}
