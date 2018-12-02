package edu.eci.arsw.primefinder;

public class Main {

	public static void main(String[] args) {
		
		PrimeFinderThread pft=new PrimeFinderThread(0, 30000000);
		
		pft.start();
		
		
	}
	
}
