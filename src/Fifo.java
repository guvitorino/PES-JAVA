import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fifo extends JFrame{
	private Processo fila[];
	private int qtd,soma=0,soma2=0;
	private double tmedio,turnmedio;
	Container tela;
	JButton iniciar;
	TextField qtdprocesso;
	JLabel lblqtd,lblprocesso,lblburst,lblespera,lbltmedio,lblturnaround,lblturnmedio;
	List processo,burst,espera,turnaround;
	
	Fifo(){
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
	                       processo = new List(qtd,false);
	                       burst = new List(qtd,false);
	                       espera = new List(qtd,false);
	                       turnaround = new List(qtd,false);
	                       lblprocesso = new JLabel("Processo:");
	                       lblburst = new JLabel("Burst:");
	                       lblespera = new JLabel("Espera:");
	                       lblturnaround = new JLabel("Turnaround:");
	                       fila = new Processo[qtd];
	                       
	                       for(int x=0;x<qtd;x++){
	                    	   int burst = Integer.parseInt(JOptionPane.showInputDialog("Burst Processo "+x));
	                    	   fila[x] = new Processo("p"+x,burst);
	                       }
	                       
	                       calculaEspera();
	                       tela.add(processo);
	                       tela.add(burst);
	                       tela.add(espera);
	                       tela.add(turnaround);
	                       tela.add(lblprocesso);
	                       tela.add(lblburst);
	                       tela.add(lblespera);
	                       tela.add(lblturnaround);
	                       processo.setBounds(10,200,50,(qtd*20));
	                       burst.setBounds(100,200,50,(qtd*20));
	                       espera.setBounds(190,200,50,(qtd*20));
	                       turnaround.setBounds(280,200,50,(qtd*20));
	                       lblprocesso.setBounds(10,180,60,25);
	                       lblburst.setBounds(100,180,50,25);
	                       lblespera.setBounds(190,180,50,25);
	                       lblturnaround.setBounds(280,180,70,25);
	                       Mostra();
                    }
               }
             );
        }
	private void calculaEspera(){
		this.fila[0].setEspera(0);
		for(int x=1;x<this.qtd;x++){
			this.fila[x].setEspera(this.fila[x-1].getBurst()+this.fila[x-1].getEspera());
		}
	}
	
	private void Mostra(){
		for(int x=0;x<this.qtd;x++){
			this.processo.add(this.fila[x].getNome());
			this.burst.add(""+this.fila[x].getBurst());
			this.espera.add(""+this.fila[x].getEspera());
			this.soma2+= this.fila[x].getBurst()+this.fila[x].getEspera();
			this.turnaround.add(""+(this.fila[x].getBurst()+this.fila[x].getEspera()));
			this.soma+= this.fila[x].getEspera();
		}
		this.tmedio = (double)this.soma / this.qtd;
		this.lbltmedio = new JLabel("Tempo Medio de espera: "+this.tmedio);
		this.tela.add(this.lbltmedio);
		this.lbltmedio.setBounds(10, 300, 210, 30);
		this.turnmedio =(double) this.soma2 / this.qtd;
		this.lblturnmedio = new JLabel("Turnaround Medio: "+this.turnmedio);
		this.tela.add(this.lblturnmedio);
		this.lblturnmedio.setBounds(10, 330, 210, 30);
	}

	public static void main(String[] args) {
		Fifo fifo = new Fifo();
		fifo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}



}