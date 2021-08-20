package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public synchronized static void main(String[] args) throws InterruptedException {
		int N = 3;
		int size = 30000000;
		int particion = size/N;
		Scanner sc = new Scanner(System.in);
		ArrayList<PrimeFinderThread> arrayList;


		arrayList = prepareThreads(N, particion, size);
		Thread.sleep(5000);

		int cont = 0;
		for (PrimeFinderThread t : arrayList ){
			t.detenerHilo();
			cont+= t.getSize();
		}

		System.out.println(cont);
		System.out.println("===================");
		String enter = sc.nextLine();
		System.out.println("cont");
		cont = 0;

		for ( PrimeFinderThread t : arrayList ){
			t.continuarHilo();
			t.join();
			cont+= t.getSize();
		}
		System.out.println(cont);
	}

	public static ArrayList<PrimeFinderThread> prepareThreads(int N, int particion, int size){
		ArrayList<PrimeFinderThread> arrayList= new ArrayList<>();

		for ( int i=0; i<N; i++){
			int b;
			PrimeFinderThread pft;

			b = ( i == N-1 )? size: ((i+1)*particion) - 1;
			pft = new PrimeFinderThread(i*particion, b );

			arrayList.add(pft);
		}
		return arrayList;
	}
}
