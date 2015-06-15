import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class SRTF extends JFrame{
	private Processo fila[];
	private int qtd;
	Container tela;
	JButton iniciar;
	TextField qtdprocesso;
	JLabel lblqtd,lblprocesso,lblespera,lblburst,lblturn,lblmespera,lblmturn;
	List processo,espera,burst,turn;
	
	SRTF(){
		this.tela = getContentPane();
		this.tela.setLayout(null);
     
		this.iniciar = new JButton("iniciar");
		this.qtdprocesso = new TextField();
		this.lblqtd = new JLabel("QTD de processo:");
		this.tela.add(iniciar);
		this.tela.add(qtdprocesso);
		this.tela.add(lblqtd);
		
		this.iniciar.setBounds(150,50,200,50);
		this.qtdprocesso.setBounds(10,50,50,25);
		this.lblqtd.setBounds(10,20,150,25);
		
		setSize(450,450);
		setVisible(true);
		iniciar_Click();
	}
	private void iniciar_Click(){
	       iniciar.addMouseListener( new MouseAdapter() {
	            public void mouseClicked(MouseEvent evento) {
	                   iniciar.setVisible(false);
	                   qtd = Integer.parseInt(qtdprocesso.getText());
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
	                	   int chegada = Integer.parseInt(JOptionPane.showInputDialog("Tempo de chegada Processo "+x));
	                	   fila[x].setTempoChegada(chegada);
	                	   fila[x].ti();
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
	
	private void executa(){
		int tempo =0;
		int ind,ant=0;
		while(this.aindaTem()){
			ind = this.suaVez(tempo, ant);
			if(ind != ant){
				if(this.fila[ind].getTe() == 0)
					this.fila[ind].setEspera(tempo - this.fila[ind].getTi());
				else
					this.fila[ind].setEspera(tempo - this.fila[ind].getTe());
				this.fila[ant].setTe(tempo);
				this.fila[ant].setTempoChegada(tempo+1);
			}
			this.fila[ind].burstadoExec();
			if(this.fila[ind].getBurstado()<=0){
				this.fila[ind].setPronto(true);
				this.fila[ind].setTempoChegada(0);
			}
			ant = ind;
			tempo++;
			this.arrumaTempo(tempo);
		}
	}
	
	private void arrumaTempo(int tempo){
		for(int x=0;x<this.qtd;x++){
			if(this.fila[x].getTempoChegada() < tempo)
				this.fila[x].setTempoChegada(tempo+1);
		}
	}
	
	private int suaVez(int tempo,int ind_atual){
		if(this.fila[ind_atual].getPronto()){
			int inret =-1;
			for(int x=0;x<this.qtd;x++){
				if(this.fila[x].getPronto() == false && this.fila[x].getTempoChegada() == tempo){
					return x;
				}
			}
		}
		else{
			for(int x=0;x<this.qtd;x++){
				if(this.fila[x].getPronto() == false && this.fila[x].getTempoChegada() == tempo){
					if(this.fila[x].getBurstado()<this.fila[ind_atual].getBurstado())
						return x;
				}
			}
		}
		return ind_atual;
	}
	
	public boolean aindaTem(){
		for(int x=0;x<this.qtd;x++){
			if(this.fila[x].getPronto() == false) 
				return true;
		}
		return false;
	}
	
	public void mostra(){
		int se=0,st=0;
		double me,mt;
		for(int x=0;x<this.qtd;x++){
			this.processo.add(this.fila[x].getNome());
			this.burst.add(""+this.fila[x].getBurst());
			se+=this.fila[x].getEspera();
			this.espera.add(""+this.fila[x].getEspera());
			st+=(this.fila[x].getBurst()+this.fila[x].getEspera());
			this.turn.add(""+(this.fila[x].getBurst()+this.fila[x].getEspera()));
		}
		me =(double) se / this.qtd;
		mt =(double) st / this.qtd;
		this.lblmespera = new JLabel(String.format("Tempo de espera media: %.2f",me));
		this.lblmturn = new JLabel(String.format("Turnaround medio: %.2f",mt));
		this.tela.add(this.lblmespera);
		this.tela.add(this.lblmturn);
		this.lblmespera.setBounds(10, 300, 210, 30);
		this.lblmturn.setBounds(10, 330, 210, 30);
	}
	
	public static void main(String[] args) {
		SRTF srtf = new SRTF();
		srtf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
