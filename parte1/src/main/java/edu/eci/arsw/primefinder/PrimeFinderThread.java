package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	private boolean detener;
	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
		detener = false;
		start();
	}

	public void run(){
		try {
			for (int i=a;i<=b;i++){
				if (isPrime(i)){
					primes.add(i);
					while ( detener ){
						synchronized ( this ){
							wait();
							System.out.println("me detuve");
						}
					}
					/*System.out.println(i);*/
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}

	public  synchronized void detenerHilo(){
		detener=true;
	}

	public synchronized void continuarHilo(){
		detener=false;
		notify();
	}

	public synchronized int getSize(){
		return primes.size();
	}
	
}
