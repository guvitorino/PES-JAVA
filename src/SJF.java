import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SJF extends JFrame{
	private Processo fila[];
	private int qtd,soma=0,soma2=0;
	private double mtempo,mturn;
    Container tela;
    JButton iniciar;
    TextField qtdprocesso;
    List processo,burst,espera,turnaround;
    JLabel lblqtd,lblprocesso,lblburst,lblespera,lblturnaround,lblmtempo,lblmturn;
    
	SJF(){
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
                   turnaround = new List(qtd);
                   lblprocesso = new JLabel("Processo:");
                   lblburst = new JLabel("Burst:");
                   lblespera = new JLabel("Espera:");
                   lblturnaround = new JLabel("Turnaround:");
                                      
                   for(int x=0;x<qtd;x++){
                	   int burst = Integer.parseInt(JOptionPane.showInputDialog("Burst Processo "+x));
                	   fila[x] = new Processo("p"+x,burst);
                   }
                   ordenar();
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
                   mostrar();
                   
            }
       });
	}
	private void calculaEspera(){
		this.fila[0].setEspera(0);
		for(int x=1;x<this.qtd;x++){
			this.fila[x].setEspera(this.fila[x-1].getBurst()+this.fila[x-1].getEspera());
		}
	}
	public void ordenar(){
		for(int x =0;x< this.qtd;x++){
			for(int y=0;y<=x;y++){
				if(this.fila[x].getBurst() < this.fila[y].getBurst()){
					Processo aux = this.fila[x];
					this.fila[x] = this.fila[y];
					this.fila[y] = aux;
				}
			}
		}
	}
	public void mostrar(){
		for(int x=0;x<this.qtd;x++){
			this.processo.add(this.fila[x].getNome());
			this.burst.add(""+this.fila[x].getBurst());
			this.espera.add(""+this.fila[x].getEspera());
			this.turnaround.add(""+(this.fila[x].getBurst()+this.fila[x].getEspera()));
			this.soma+= this.fila[x].getEspera();
			this.soma2 = this.fila[x].getBurst()+this.fila[x].getEspera();
		}
		this.mtempo = (double) this.soma / this.qtd;
		this.mturn = (double) this.soma2 / this.qtd;
		this.lblmtempo = new JLabel(String.format("Tempo de espera media: %.2f",this.mtempo));
		this.lblmturn = new JLabel(String.format("Turnaround medio: %.2f",this.mturn));
		this.tela.add(this.lblmtempo);
		this.tela.add(this.lblmturn);
		this.lblmtempo.setBounds(10, 300, 210, 30);
		this.lblmturn.setBounds(10, 330, 210, 30);
		
	}
	
	public static void main(String[] args) {
		SJF sjf = new SJF();
		sjf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
