package com.vsct.xspeedit;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vsct.xspeedit.model.Constante;
import com.vsct.xspeedit.rg.ControleurGen;


/**
 * Tests unitaires pour les RG génériques.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControleurGenTests {
	
    /**
     * Test RG_GEN_01.
     * Le format de la liste d'articles est correct (suite de chiffres).
     */
	@Test
	public void validerListeArticlesCasPassant() {
		String articles = "123456789";
		try {
			ControleurGen.validerListeArticles(articles);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
    /**
     * Test RG_GEN_01.
     * Le format de la liste d'articles est erroné, l'exception est bien remontée.
     */
	@Test
	public void validerListeArticlesCasBloquant() {
		String articles = "123456789a/!123456";
		try {
			ControleurGen.validerListeArticles(articles);
			Assert.fail();
		} catch (Exception e) {
			if(!Constante.ERREUR_FORMAT_LST_ART.equals(e.getMessage())){
				Assert.fail();
			}
		}
	}
	
	/**
     * Test RG_GEN_02.
     * La liste est correctement créée.
     * NB : Le format de la liste d'articles est obligatoirement une suite de chiffre (RG_GEN_01).
     */
	@Test
	public void creerListeArticlesCasPassant() {
		final ArrayList<String> expected = new ArrayList<String>();
		expected.add("1");
		expected.add("2");
		expected.add("3");
		
		String articles = "123";
		final ArrayList<String> actual = ControleurGen.creerListeArticles(articles);
		
		Assert.assertEquals(expected, actual);
	}
	
	/**
     * Test RG_GEN_03.
     * Le format de la liste d'articles est null.
     * La place occupée retournée est bien 0.
     */
	@Test
	public void getPlaceOccupeeCasPassantNull() {
		final int expected = 0;
		
		String articles = null;
		final int actual = ControleurGen.getPlaceOccupee(articles);
		
		Assert.assertEquals(expected, actual);		
	}
	
	/**
     * Test RG_GEN_03.
     * Le format de la liste d'articles est vide.
     * La place occupée retournée est bien 0.
     */
	@Test
	public void getPlaceOccupeeCasPassantVide() {
		final int expected = 0;
		
		String articles = "";
		final int actual = ControleurGen.getPlaceOccupee(articles);
		
		Assert.assertEquals(expected, actual);		
	}
	
	/**
     * Test RG_GEN_03.
     * Le format de la liste d'articles est une suite de chiffre (RG_GEN_01).
     * La place occupée retournée est correctement calculée.
     */
	@Test
	public void getPlaceOccupeeCasPassant() {
		final int expected = 7;
		
		String articles = "1123";
		final int actual = ControleurGen.getPlaceOccupee(articles);
		
		Assert.assertEquals(expected, actual);		
	}
}
