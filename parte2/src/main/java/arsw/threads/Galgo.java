package arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private Boolean isPaused = false;
	private int paso;
	private Carril carril;
	RegistroLlegada regl;

	public Galgo(Carril carril, String name, RegistroLlegada reg) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {			
			Thread.sleep(100);
			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			synchronized (this){
				while(isPaused){
					wait();
				}
			}
			if (paso == carril.size()) {
				synchronized (regl){
					carril.finish();
					int ubicacion = regl.getUltimaPosicionAlcanzada();
					regl.setUltimaPosicionAlcanzada(ubicacion+1);
					System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
					if (ubicacion == 1){
						regl.setGanador(this.getName());
					}
				}
			}
		}
	}

	public synchronized void reanudar(){
		isPaused = false;
		notifyAll();
	}

	public synchronized void pause(){
		isPaused = true;
	}

	@Override
	public void run() {
		try {
			corra();
		} catch (InterruptedException e) { }

	}

}
