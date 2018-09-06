import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Ventana extends JFrame{
	JTextField campo1;
	JButton boton;
	
	public Ventana(int x,int y,String titulo,Boolean ask) {
		setBounds(x,y,600,400);
		PanelPrincipal mainPanel=new PanelPrincipal(titulo,ask);
		add(mainPanel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class PanelPrincipal extends JPanel{
		public PanelPrincipal(String titulo,Boolean ask) {
			JLabel texto=new JLabel(titulo);
			add(texto);
			
			if(ask) {
				campo1=new JTextField(30);
				add(campo1);
				boton=new JButton("Enviar");
				add(boton);
			}else {
				this.setLayout(new BorderLayout());
				JTextArea areaTexto=new JTextArea();
				add(areaTexto,BorderLayout.CENTER);
			}
		}

	}
}

