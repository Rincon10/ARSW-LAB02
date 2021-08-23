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

		System.out.println("Deteniendo threads....");
		System.out.println("Se han encontrado " + cont + " numeros primos.");
		System.out.println("============================================== \n presiona ENTER");
		String enter = sc.nextLine();

		cont = 0;
		for ( PrimeFinderThread t : arrayList ){
			t.continuarHilo();
			t.join();
			cont+= t.getSize();
		}
		System.out.println("Continuando threads....");
		System.out.println("Se han encontrado " + cont + " numeros primos.");
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
