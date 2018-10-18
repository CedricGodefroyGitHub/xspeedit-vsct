package com.vsct.xspeedit;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vsct.xspeedit.model.Packaging;
import com.vsct.xspeedit.rg.ControleurGen;
import com.vsct.xspeedit.rg.ControleurTriBasique;

/**
 * Tests unitaires pour les RG TriBasique.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControleurTriBasiqueTests {
	
    /**
     * Test RG_TRI_BASIQUE_01.
     * Verification que la RG retourne une liste vide pour une liste en entrée vide.
     * NB : Cas impossible dans le traitement actuel.
     */
	@Test
	public void triArticlesListeVide() {
		Packaging expected = new Packaging();
		ArrayList<String> listeCartonsExpected = new ArrayList<>();
		expected.setListeCartons(listeCartonsExpected);
		
		ArrayList<String> listeArticles = new ArrayList<String>();
		Packaging actual = ControleurTriBasique.triArticles(listeArticles);
		
		Assert.assertEquals(expected.getListeCartons().size(), actual.getListeCartons().size());
	}
	
    /**
     * Test RG_TRI_BASIQUE_01.
     * Verification que la RG tri basiquement une suite de chiffre.
     */
	@Test
	public void triArticlesCasListePleine() {		
		ArrayList<String> listeArticles = ControleurGen.creerListeArticles("1638272156");
		Packaging actual = ControleurTriBasique.triArticles(listeArticles);
		ArrayList<String> listeArticlesActual = actual.getListeCartons();		

		ArrayList<String> listeArticlesExpected = new ArrayList<String>();
		listeArticlesExpected.add("163");
		listeArticlesExpected.add("82");
		listeArticlesExpected.add("721");
		listeArticlesExpected.add("5");
		listeArticlesExpected.add("6");
		
		for(int i=0; i < listeArticlesExpected.size(); i++){
			Assert.assertEquals(listeArticlesExpected.get(i), listeArticlesActual.get(i));
		}				
	}

}
