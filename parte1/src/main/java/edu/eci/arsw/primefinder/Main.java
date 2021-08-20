package edu.eci.arsw.primefinder;

import java.util.ArrayList;

public class Main {

	public synchronized static void main(String[] args) throws InterruptedException {
		int N = 3;
		int size = 30000000;
		int particion = size/N;

		ArrayList<PrimeFinderThread> arrayList= new ArrayList<>();

		for ( int i=0; i<N; i++){
			int b;
			PrimeFinderThread pft;

			b = ( i == N-1 )? size: ((i+1)*particion) - 1;
			pft = new PrimeFinderThread(i*particion, b );

			arrayList.add(pft);
		}

		while(true){
			int cont = 0;

			Thread.sleep(5000);
			for (PrimeFinderThread t : arrayList ){
				t.detenerHilo();
				cont+= t.getSize();
			}

			System.out.println(cont);
			arrayList.forEach( t -> t.continuarHilo());
		}
	}
	
}
