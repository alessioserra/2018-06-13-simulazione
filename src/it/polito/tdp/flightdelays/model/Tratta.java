package it.polito.tdp.flightdelays.model;

public class Tratta implements Comparable<Tratta>{
	
	private Airport id1;
	private Airport id2;
	private double peso;
	
	
	public Tratta(Airport id1, Airport id2, double peso) {
		this.id1 = id1;
		this.id2 = id2;
		this.peso = peso;
	}

	public Airport getId1() {
		return id1;
	}


	public Airport getId2() {
		return id2;
	}


	public double getPeso() {
		return peso;
	}

	@Override
	public int compareTo(Tratta o) {	
		if (peso<o.peso)return -1;
		if (peso>o.peso)return 1;
		return 0;
	}
	
	

}
