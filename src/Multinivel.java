import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Multinivel extends JFrame{
	private Processo fila[];
	private int qtd,quant;
	Container tela;
	JButton iniciar;
	TextField qtdprocesso,quantum;
	JLabel lblqtd,lblprocesso,lblespera,lblburst,lblturn,lblmespera,lblmturn,lblquantum;
	List processo,espera,burst,turn;
	
	Multinivel(){
		this.tela = getContentPane();
		this.tela.setLayout(null);
     
		this.iniciar = new JButton("iniciar");
		this.qtdprocesso = new TextField();
		this.lblqtd = new JLabel("QTD de processo:");
		this.quantum = new TextField();
		this.lblquantum = new JLabel("Quantum:");
		
		this.tela.add(iniciar);
		this.tela.add(qtdprocesso);
		this.tela.add(quantum);
		this.tela.add(lblqtd);
		this.tela.add(lblquantum);
		
		this.iniciar.setBounds(150,50,200,50);
		this.qtdprocesso.setBounds(10,50,50,25);
		this.lblqtd.setBounds(10,20,150,25);
		this.quantum.setBounds(10, 100, 50,25);
		this.lblquantum.setBounds(10, 80, 100, 25);
		
		
		setSize(450,450);
		setVisible(true);
		iniciar_click();
	}
	
	public void iniciar_click(){
	       iniciar.addMouseListener( new MouseAdapter() {
	            public void mouseClicked(MouseEvent evento) {
	                   iniciar.setVisible(false);
	                   qtd = Integer.parseInt(qtdprocesso.getText());
	                   quant = Integer.parseInt(quantum.getText());
	                   fila = new Processo[qtd];
	                   processo = new List(qtd);
	                   burst = new List(qtd);
	                   espera = new List(qtd);
	                   turn = new List(qtd);
	                   lblprocesso = new JLabel("Processo:");
	                   lblburst = new JLabel("Burst:");
	                   lblespera = new JLabel("Espera:");
	                   lblturn = new JLabel("Turnaround:");
	                                   
	                   for(int x=0;x<qtd;x++){
	                	   int burst = Integer.parseInt(JOptionPane.showInputDialog("Burst Processo "+x));
	                	   fila[x] = new Processo("p"+x,burst);
	                   }
	                   executa();
	                   tela.add(processo);
	                   tela.add(burst);
	                   tela.add(espera);
	                   tela.add(turn);
	                   tela.add(lblprocesso);
	                   tela.add(lblburst);
	                   tela.add(lblespera);
	                   tela.add(lblturn);
	                   processo.setBounds(10,200,50,(qtd*20));
	                   burst.setBounds(100,200,50,(qtd*20));
	                   espera.setBounds(190,200,50,(qtd*20));
	                   turn.setBounds(280,200,50,(qtd*20));
	                   lblprocesso.setBounds(10,180,60,25);
	                   lblburst.setBounds(100,180,50,25);
	                   lblespera.setBounds(190,180,50,25);
	                   lblturn.setBounds(280,180,70,25);
	                   mostra();
	               
	            }
	       });
	}
	public void executa(){
		int x=0,tempo=0;
		while(tempo < 30 ){
			if(this.fila[x].getBurstado()>0){
				int espera =0;
				if(this.fila[x].getBurstado()>=this.quant)
					espera = this.quant;
				else
					espera = this.fila[x].getBurstado();
				this.fila[x].burstadoExec(this.quant);
				if(this.fila[x].getTe()>0)
					this.fila[x].setEspera(tempo - this.fila[x].getTe());	
				else
				    this.fila[x].setEspera(tempo);
				tempo+= espera;
				this.fila[x].setTe(tempo);
				if(this.fila[x].getBurstado()<=0)
					this.fila[x].setPronto(true);
				x++;
				if(x==this.qtd)
					x=0;
			}else{
				x++;
				if(x==this.qtd)
					x=0;
			}
		}
		while(this.aindaTem()){
			if(this.fila[x].getBurstado()>0){
		        this.fila[x].setEspera(this.fila[x].getBurst());
		        this.fila[x].burstadoExec(this.fila[x].getBurstado());
		        if(this.fila[x].getBurstado()<=0)
		        	this.fila[x].setPronto(true);
				x++;
				if(x==this.qtd)
					x=0;
			}else{
				x++;
				if(x==this.qtd)
					x=0;
			}
		}
	}
	public boolean aindaTem(){
		for(int x=0;x<this.qtd;x++){
			if(this.fila[x].getPronto() == false) 
				return true;
		}
		return false;
	}
	
	public void mostra(){
		int somae = 0,somat=0;
		double me,mt;
		for(int x=0;x<this.qtd;x++){
			this.processo.add(this.fila[x].getNome());
			this.burst.add(""+this.fila[x].getBurst());
			this.espera.add(""+this.fila[x].getEspera());
			somae+= this.fila[x].getEspera();
			this.turn.add(""+(this.fila[x].getBurst()+this.fila[x].getEspera()));
			somat+= this.fila[x].getBurst()+this.fila[x].getEspera();
		}
		me = somae / this.qtd;
		mt = somat / this.qtd;
		this.lblmespera = new JLabel("Media de espera: "+me);
		this.lblmturn = new JLabel("Media de turnaround: "+mt);
		this.tela.add(this.lblmespera);
		this.tela.add(this.lblmturn);
		this.lblmespera.setBounds(10, 300, 210, 30);
		this.lblmturn.setBounds(10, 330, 210, 30);
	}
	
	public static void main(String[] args) {
		Multinivel multi = new Multinivel();
		multi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
