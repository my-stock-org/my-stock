package Frame;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultPieDataset.*;

import request.CaissierRequest;
import request.ClientRequest;
import request.CommandeRequest;
import request.ProduitRequest;

public class CourbeEvolution extends JFrame  implements Fenetre {
	
	private static CourbeEvolution instance = null;
    ImageIcon background=new ImageIcon("img/vente.jpg");
    String[][] tb1 ;
    String[][] tb2 ;
    String[][] tb3 ;
    JPanel monpanel=new JPanel();
	private JButton produit,client,commande;
	// Constructeur de l'objet.
	private CourbeEvolution() {
		super();
		this.proprieteFenetre();
		this.proprieteButton();
		this.setLayout(null);
		
//		Diagramme();
//		DiagrammeCirculaire();
		
		this.setVisible(true);
	}
	static CourbeEvolution getInstance() 
	{
		if (instance == null) 
			{
				instance = new CourbeEvolution();
			}
		return instance;
	}
    
	 public void proprieteFenetre(){
		this.setTitle("Statisque d'evolution");
		this.setSize(500,420);
		this.setLocationRelativeTo(null);
		this.setResizable(false); //pouvoir ou non redefinir la fenetre
		this.setLocationRelativeTo(null); //position de la fenetre a l'ecran
		this.setDefaultLookAndFeelDecorated(rootPaneCheckingEnabled);
		
		
	}
	 
	 public void DiagrammeCirculaire( ) {
		 DefaultPieDataset union = new DefaultPieDataset();
	     tb1 = ProduitRequest.getInstance().getProduit();
	     for (int j = 0; j <tb1.length;j++){
			union.setValue(tb1[j][2], Integer.valueOf(tb1[j][4]));
	     }
	        /*cree un diagramme circulaire 3D*/
	        JFreeChart repart = ChartFactory.createPieChart3D("GESTION DES STOCK", union, true, true, false);
	        /*Ajout de l'effet transparent au diagramme*/
	        PiePlot3D plot3 = (PiePlot3D) repart.getPlot();
	        plot3.setForegroundAlpha(0.6f);
	        plot3.setCircular(true);
	        Plot plot = repart.getPlot();
		 
	        // cree et affiche le frame...
	        ChartFrame frame = new ChartFrame("Diagramme", repart);
	        frame.pack();
	        frame.setVisible(true);
	        frame.setAlwaysOnTop(true);
	    }
	 
	 public void Diagramme() {
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		 tb2 = CommandeRequest.getInstance().getCommande();
		 
		 for (int j = 0; j <tb2.length;j++){
		    dataset.addValue(Integer.valueOf(tb2[j][2]), tb2[j][1], tb2[j][5]); 
		 }

		    JFreeChart barChart = ChartFactory.createBarChart("Evolution des ventes", "", 
		      "Unité vendue", dataset, PlotOrientation.VERTICAL, true, true, false); 
		    
	        // cree et affiche le frame...
	        ChartFrame frame = new ChartFrame("Diagramme", barChart);
	        frame.pack();
	        frame.setVisible(true);
	        frame.setAlwaysOnTop(true);
	    }
	 public void DiagrammeCaissier() {
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		 tb3 = CaissierRequest.getInstance().getCaissier();
		 
		 for (int j = 0; j <tb3.length;j++){
		    dataset.addValue( tb3[j][3]!=null ?Integer.valueOf(tb3[j][3]) : 0, tb3[j][1], tb3[j][1]);
		 }

		 JFreeChart barChart = ChartFactory.createBarChart("Visualisez votre meilleur vendeur", "", 
		  "Unité vendue", dataset, PlotOrientation.VERTICAL, true, true, false); 
		    
	     // cree et affiche le frame...
	     ChartFrame frame = new ChartFrame("Diagramme", barChart);
	     frame.pack();
	     frame.setVisible(true);
	     frame.setAlwaysOnTop(true);
	    }
	 
	 public void proprieteButton() {
			produit =new JButton("Gerer vos ventes");
			client =new JButton("Meilleur Caissier");
			commande =new JButton("Produits en stock");
			
			this.produit.setBounds(150, 80, 200, 40);
			this.add(produit);
			
			this.client.setBounds(150, 150, 200, 40);
			this.add(client);
			
			this.commande.setBounds(150, 220, 200, 40);
			this.add(commande);
			
			
			this.produit.addActionListener(new ActionListener()  {
			   public void actionPerformed(ActionEvent e) {
				   Diagramme();
			   }
			 });

			this.commande.addActionListener(new ActionListener() {
				@Override
				public  void actionPerformed(  ActionEvent e) {
					DiagrammeCirculaire();
				}
			});
			this.client.addActionListener(new ActionListener() {
				@Override
				public  void actionPerformed(  ActionEvent e) {
					DiagrammeCaissier();
				}
			});
			
		}
	 
	 public void courbe() {
		 
	 }

	public void openFrame() {
		this.setVisible(true);
	}
	
//	fermer
	public void Fermer_actionPerformed(ActionEvent e){
		this.dispose();
	}
	
	

}
