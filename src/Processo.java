
public class Processo{
	private String name;
	private int burst;
	private int burstado;
	private int espera;
	private int tempo_chegada;
	private int ti;
	private boolean pronto;
	private int te;
	
	Processo(String name, int burst){
		this.name = name;
		this.burst = burst ;
		this.burstado = burst;
		this.espera =0;
		this.te =0;
	}

	public int getBurst(){
		return this.burst;
	}
	public void setEspera(int espera){
		this.espera+= espera;
	}
	public int getEspera(){
		return this.espera;
	}
	public String getNome(){
		return this.name;
	}
	public void setTempoChegada(int tempo){
		this.tempo_chegada = tempo;
	}
	public int getTempoChegada(){
		return this.tempo_chegada;
	}
	public void burstadoExec(){
		this.burstado = this.burstado -1;
	}
	public void burstadoExec(int q){
		this.burstado = this.burstado - q;
	}
	
	public int getBurstado(){
		return this.burstado;
	}
	public void setPronto(boolean valor){
		this.pronto = valor;
	}
	public boolean getPronto(){
		return this.pronto;
	}
	public void ti(){
		this.ti = this.tempo_chegada;
	}
	public int getTi(){
		return this.ti;
	}
	public boolean isFinalBurst(){
		if(this.getBurstado() == 0)
			return true;
		return false;
	}
	public void setTe(int te){
		this.te = te;
	}
	public int getTe(){
		return this.te;
	}
	
}