package com.vsct.xspeedit;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vsct.xspeedit.model.Packaging;
import com.vsct.xspeedit.rg.ControleurGen;
import com.vsct.xspeedit.rg.ControleurTriOptimise;

/**
 * Tests unitaires pour les RG TriOptimise.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControleurTriOptimiseTests {
	
    /**
     * Test RG_TRI_OPTIMISE_02.
     * Verification que la RG retourne null lorsqu'elle traite une liste vide.
     */
	@Test
	public void getPlusGrandArticlePossibleListeVide() {
		String expected = null;
		
		ArrayList<String> listeArticles = new ArrayList<String>();
		String actual = ControleurTriOptimise.getPlusGrandArticlePossible(listeArticles, 0);
		
		Assert.assertEquals(expected, actual);
	}
	
    /**
     * Test RG_TRI_OPTIMISE_02.
     * Verification que la RG retourne le plus grand article (même avec des données mal formatées).
     * NB : Les données ne peuvent pas être mal formatées dans le traitement actuel.
     */
	@Test
	public void getPlusGrandArticlePossibleListePleine() {
		String expected = "7";
		
		ArrayList<String> listeArticles = new ArrayList<String>();
		listeArticles.add("5");
		listeArticles.add("0");
		listeArticles.add("4");
		listeArticles.add("7");
		listeArticles.add("!");
		String actual = ControleurTriOptimise.getPlusGrandArticlePossible(listeArticles, 0);
		
		Assert.assertEquals(expected, actual);
	}
	
    /**
     * Test RG_TRI_OPTIMISE_01.
     * Verification que la RG retourne une liste vide pour une liste en entrée vide.
     * NB : Cas impossible dans le traitement actuel.
     */
	@Test
	public void triArticlesListeVide() {
		Packaging expected = new Packaging();
		ArrayList<String> listeCartonsExpected = new ArrayList<>();
		expected.setListeCartons(listeCartonsExpected);
		
		ArrayList<String> listeArticles = new ArrayList<String>();
		Packaging actual = ControleurTriOptimise.triArticles(listeArticles);
		
		Assert.assertEquals(expected.getListeCartons().size(), actual.getListeCartons().size());
	}
	
    /**
     * Test RG_TRI_OPTIMISE_01.
     * Verification que la RG tri de manière optimisée la suite de l'énoncé.
     */
	@Test
	public void triArticlesCasListePleineCas1() {		
		ArrayList<String> listeArticles = ControleurGen.creerListeArticles("163841689525773");
		Packaging actual = ControleurTriOptimise.triArticles(listeArticles);
		ArrayList<String> listeArticlesActual = actual.getListeCartons();		

		ArrayList<String> listeArticlesExpected = new ArrayList<String>();
		listeArticlesExpected.add("91");
		listeArticlesExpected.add("82");
		listeArticlesExpected.add("81");
		listeArticlesExpected.add("73");
		listeArticlesExpected.add("73");
		listeArticlesExpected.add("64");
		listeArticlesExpected.add("6");
		listeArticlesExpected.add("55");
		
		for(int i=0; i < listeArticlesExpected.size(); i++){
			Assert.assertEquals(listeArticlesExpected.get(i), listeArticlesActual.get(i));
		}				
	}
	
    /**
     * Test RG_TRI_OPTIMISE_01.
     * Verification que la RG tri de manière optimisée une suite de chiffre.
     * NB: Ce tri serait erroné si la liste n'était pas d'abord trié par ordre décroissant.
     */
	@Test
	public void triArticlesCasListePleineCas2() {		
		ArrayList<String> listeArticles = ControleurGen.creerListeArticles("16382");
		Packaging actual = ControleurTriOptimise.triArticles(listeArticles);
		ArrayList<String> listeArticlesActual = actual.getListeCartons();		

		ArrayList<String> listeArticlesExpected = new ArrayList<String>();
		listeArticlesExpected.add("82");
		listeArticlesExpected.add("631");
		
		for(int i=0; i < listeArticlesExpected.size(); i++){
			Assert.assertEquals(listeArticlesExpected.get(i), listeArticlesActual.get(i));
		}				
	}

}
