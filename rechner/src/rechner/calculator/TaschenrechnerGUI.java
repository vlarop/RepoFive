package rechner.calculator;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class TaschenrechnerGUI extends JFrame {
	
private static final long serialVersionUID = 36212012028893L;
	
	private JTextField eingabe1, eingabe2;
	private JButton schaltflaecheBerechnen, schaltflaecheBeenden;
	private JLabel ausgabe;
	private String[] operationAuswahl = {"Addition", "Subtraktion", "Multiplikation", "Division"};
	private JComboBox <String> operation;
	
	
	public TaschenrechnerGUI (String string) {

		super(string);
		//insgesamt 3 Panels
		JPanel panelEinAus, panelOperationen, panelButtons;
		
		//die Panels ?ber die Methoden erstellen
		panelEinAus = panelEinAusErzeugen();
		panelOperationen = panelKombinationenErzeugen();
		panelButtons = panelButtonErzeugen();
		
		//ein Gridlayout mit 3 Spalten
		setLayout(new GridLayout(0,3));
		
		//die Panels hinzuf?gen
		add(panelEinAus);
		add(panelOperationen);
		add(panelButtons);

		//die Standard-Aktion setzen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//packen und anzeigen
		pack();
		setVisible(true);
		
		//Gr??en?nderungen sind nicht zugelassen
		//damit das m?hsam erstellte Layout nicht durcheinander kommt
		setResizable(false);
	}
	
	
	//die innere Klasse f?r die Ereignisverarbeitung
	class MeinListener implements ActionListener {
		
		@Override
		
		public void actionPerformed(ActionEvent e) {
			
			//wurde auf Berechnen geklickt?
			//dann eine Methode f?r die Berechnung aufrufen
			//und das Ergebnis anzeigen			
			if (e.getActionCommand().equals("rechnen")) { 
				ausgabe.setText(berechnen());
			}
			//wurde auf Beenden geklickt?
			//dann das Programm beenden
			if (e.getActionCommand().equals("enden")) 
				System.exit(0);
			
		}
	}
	
	
	//die Methode erzeugt das Panel f?r die Ein- und Ausgabe
	//und liefert es zur?ck
	private JPanel panelEinAusErzeugen() {
		JPanel tempPanel = new JPanel();
		//es enth?lt die Eingabefelder mit beschreibendem Text und die Ausgabe
		//die L?nge der Felder ist auf 10 Zeichen beschr?nkt
		eingabe1 = new JTextField(10);
		eingabe2 = new JTextField(10);
		ausgabe = new JLabel("");
		
		//das Panel bekommt ein GridLaoyut mit 2 Spalten und etwas Abstand
		tempPanel.setLayout(new GridLayout(0,2,10,10));
		//ein beschreibendes Label f?r die erste Eingabe
		tempPanel.add(new JLabel("Zahl 1:"));
		//das erste Eingabefeld
		tempPanel.add(eingabe1);
		
		//und jetzt das zweite Eingabefeld
		tempPanel.add(new JLabel("Zahl 2: "));
		tempPanel.add(eingabe2);
		
		//und nun das Ausgabefeld f?r das Ergebnis
		tempPanel.add(new JLabel("Ergebnis: "));
		tempPanel.add(ausgabe);
		
		//einen Rahmen um das Panel ziehen
		tempPanel.setBorder(new TitledBorder("Ein- und Ausgabe"));
		
		//das Panel zur?ckliefern
		return tempPanel;
	}
	
	
	//die Methode erzeugt das Panel f?r Auswahl von Operationen
	//und liefert es zur?ck
	private JPanel panelKombinationenErzeugen() {
		JPanel tempPanel = new JPanel();
		//die Liste fuer das Kombinationsfeld
		operation = new JComboBox<String> (operationAuswahl);
		//die Felder fuer die Operationen		
		tempPanel.add(operation);
		tempPanel.setLayout(new GridLayout(4,4));
		//einen Rahmen um das Panel ziehen
		tempPanel.setBorder(new TitledBorder("Operation: "));
		//das Panel zur?ckliefern	
		return tempPanel;
	
	}
	
	
	//die Methode erzeugt das Panel f?r die Schaltfl?chen
	//und liefert es zur?ck
	private JPanel panelButtonErzeugen() {
		JPanel tempPanel = new JPanel();
	
		schaltflaecheBeenden = new JButton(" Beenden ");
		//das Aktion-Command setzen
		schaltflaecheBeenden.setActionCommand("enden");
		schaltflaecheBerechnen = new JButton("Berechnen");
		schaltflaecheBerechnen.setActionCommand("rechnen");
		
		//Zwischenpanel f?r die Schaltfl?chen
		//das Panel wird linksb?ndig ausgerichtet mit mehr Abstand
		tempPanel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
		tempPanel.add(schaltflaecheBerechnen);
		tempPanel.add(schaltflaecheBeenden);
		
		//die Schaltfl?chen mit dem Listener verbinden
		MeinListener listener = new MeinListener();
		schaltflaecheBeenden.addActionListener(listener);
		schaltflaecheBerechnen.addActionListener(listener);
		
		//das Panel zur?ckliefern
		return tempPanel;
	}
	
	
	//die Methode berechnet das Ergebnis und liefert es als 
	//Zeichenkette zur?ck
	private String berechnen() {
		
		//ergebnis muss initialisiert werden
		double zahl1, zahl2, ergebnis = 0;
		//f?r die Steuerung der R?ckgabe
		boolean fehlerFlag = false;
			
		
		try {
			//den Wert beschaffen, umformen und zuweisen
			zahl1 = Double.parseDouble(eingabe1.getText());
		}
		catch (Exception NumberFormatException) {
			fehlermeldung(eingabe1);
			return ("Nicht definiert");
		}
		
		//Fehlerbehandlung und Ausgabe der Meldung durch eine eigene Methode
		try {
			//den Wert beschaffen, umformen und zuweisen
			zahl2 = Double.parseDouble(eingabe2.getText());
		}
		catch (Exception NumberFormatException) {
			fehlermeldung(eingabe2);
			//das Ergebnis ist nicht definiert
			return ("Nicht definiert");
		}
	
		//welche Operation ist ausgew?hlt?
		//die Operation ist ueber den Index ermittelt
		if (operation.getSelectedIndex()== 0)
			
			ergebnis = zahl1 + zahl2;
		
		if (operation.getSelectedIndex()== 1)
			
			ergebnis = zahl1 - zahl2;
		
		if (operation.getSelectedIndex()== 2)
			
			ergebnis = zahl1 * zahl2;
		
		if (operation.getSelectedIndex()== 3){
			
			if (zahl2 != 0)
				ergebnis = zahl1 / zahl2;
			else 
				fehlerFlag = true;
		}
		
		if (fehlerFlag == false)
			return Double.toString(ergebnis);
		else
			return ("Nicht definiert");
	}
	
	private void fehlermeldung(JTextField eingabefeld) {
		JOptionPane.showMessageDialog(this, "Ihre Eingabe ist nicht g?ltig","Eingabefehler", JOptionPane.ERROR_MESSAGE);
		eingabefeld.requestFocus();
	}

}
