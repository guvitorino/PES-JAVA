import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Menu extends JFrame{

	JButton fifo;
	JButton robin;
	JButton sjf;
	JButton srt;
    JButton mn;
	Menu(){
		Container tela = getContentPane();
		tela.setLayout(null);

		this.fifo = new JButton("FCFS");
		this.sjf = new JButton("SJF");
		this.srt = new JButton("SRTF");
		this.robin = new JButton("Round Robin");
		this.mn = new JButton("Multinivel");

		tela.add(this.fifo);
		tela.add(this.robin);
		tela.add(this.sjf);
		tela.add(this.srt);
		tela.add(this.mn);

		this.fifo.setBounds(100,20,200,50);
		this.sjf.setBounds(100,100,200,50);
		this.srt.setBounds(100,180,200,50);
		this.robin.setBounds(100,260,200,50);
		this.mn.setBounds(100,340,200,50);

		setSize(450,450);
		setVisible(true);
		fifo_Click();
		sjf_Click();
		srt_Click();
		robin_Click();
		multi_Click();
		
	}
	private void fifo_Click(){
        fifo.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent evento) {
                   Fifo fifo = new Fifo();	 
            }
       });
	}
	private void sjf_Click(){
        sjf.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent evento) {
                   SJF sjf = new SJF();	 
            }
       });
	}
	
	private void srt_Click(){
        srt.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent evento) {
            	SRTF srtf = new SRTF();	 
            }
       });
	}
	
	private void robin_Click(){
        robin.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent evento) {
                   RR rr = new RR();	 
            }
       });
	}
	
	private void multi_Click(){
        mn.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent evento) {
                   Multinivel multi = new Multinivel();	 
            }
       });
	}
	
	 public static void main(String[] args) {
		Menu menu = new Menu();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}