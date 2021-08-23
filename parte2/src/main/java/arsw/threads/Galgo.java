package arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private boolean continuar;
	private Carril carril;
	RegistroLlegada regl;

	public Galgo(Carril carril, String name, RegistroLlegada reg) {
		super(name);
		this.carril = carril;
		this.regl=reg;
		continuar=true;
		paso = 0;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {


			Thread.sleep(100);
			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			if (paso == carril.size()) {
				carril.finish();

				synchronized ( regl ){
					int ubicacion=regl.getUltimaPosicionAlcanzada();
					regl.setUltimaPosicionAlcanzada(ubicacion+1);
					System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
					if (ubicacion==1){
						regl.setGanador(this.getName());
					}
				}

			}
			// Region critica
			synchronized ( this ){
				while ( !continuar ){
					wait();
				}
			}
		}
	}


	@Override
	public void run() {
		
		try {
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void detener(){
		continuar = false;
	}

	public void continuar() {
		continuar = true;
		synchronized ( this ) {  super.notify(); }
	}
}
