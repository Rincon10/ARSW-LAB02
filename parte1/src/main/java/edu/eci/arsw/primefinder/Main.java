package edu.eci.arsw.primefinder;

import java.util.ArrayList;

public class Main {

	public synchronized static void main(String[] args) {
		int N = 300;
		int size = 30000000;
		int particion = size/N;

		ArrayList<PrimeFinderThread> arrayList= new ArrayList<>();

		for ( int i=0; i<N; i++){
			PrimeFinderThread pft;
			if ( i == N-1 ){
				pft=new PrimeFinderThread(i*particion, size );
			}
			else{
				pft=new PrimeFinderThread(i*particion, ((i+1)*particion) - 1);
			}
			arrayList.add(pft);
			arrayList.get(i).start();
		}

		arrayList.forEach( t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
}
