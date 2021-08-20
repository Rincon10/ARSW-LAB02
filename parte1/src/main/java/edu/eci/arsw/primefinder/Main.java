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

		int cont = 0;

		Thread.sleep(5000);
		for (PrimeFinderThread t : arrayList ){
			t.detenerHilo();
			cont+= t.getSize();
		}

		System.out.println(cont);
		System.out.println("===================");
		for (PrimeFinderThread t : arrayList ){
			t.continuarHilo();
			cont+= t.getSize();
		};

		System.out.println(cont);

	}
	
}
