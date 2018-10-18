package com.vsct.xspeedit.model;

import java.util.ArrayList;

/**
 * Objet de sortie des services TriBasique et TriOptimise.
 */
public class Packaging {
    private ArrayList<String> listeCartons;    

	public Packaging() {}

	public ArrayList<String> getListeCartons() {
		return listeCartons;
	}

	public void setListeCartons(ArrayList<String> listeCartons) {
		this.listeCartons = listeCartons;
	}  

}
