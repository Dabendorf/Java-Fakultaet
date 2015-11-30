package fakultaet;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 * Dies ist die einzige Klasse des Fakultaet-Berechnungsprogrammes.<br>
 * Es nimmt eine Zahl entgegen und rechnet die mathematische Fakultaet aus.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Fakultaet {

	private NumberFormat format = NumberFormat.getInstance(); 
	private NumberFormatter formatter = new NumberFormatter(format);
	private long abfragezahl;
	
	public Fakultaet() {
		format.setGroupingUsed(false); 
		formatter.setAllowsInvalid(false);
		eingabe();
	}
	
	/**
	 * Diese Methode nimmt vom Nutzer eine Zahl entgegen, die fakultiert werden soll.<br>
	 * Bei falscher oder den long Typ ueberschreibender Eingabe wird der Nutzer nach einer anderen Zahl gefragt.
	 */
	public void eingabe() {
		JFormattedTextField nummernfeld = new JFormattedTextField(formatter);
		Object[] zahlenfrage = {"Von welcher Zahl möchtest Du die Fakultät wissen?", nummernfeld};
		JOptionPane pane = new JOptionPane(zahlenfrage, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
		pane.createDialog(null, "Eingabe").setVisible(true);
		String zahlStr = nummernfeld.getText();
		try {
			if(zahlStr.equals("")) {
				JOptionPane.showMessageDialog(null, "Bitte gib eine natürliche Zahl ein!", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
				eingabe();
			} else {
				abfragezahl = Long.parseLong(zahlStr);
				if(abfragezahl < 0) {
					JOptionPane.showMessageDialog(null, "Bitte gib eine natürliche Zahl ein!", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
					eingabe();
				} else {
					ausgabe();
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Bitte gib eine natürliche Zahl ein!", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
			eingabe();
		}	
	}
	
	/**
	 * Diese Methode berechnet die Fakultaet und gibt das Endergebnis aus.
	 * @param zahl Nimmt die abgefragte Zahl entgegen.
	 * @return Gibt das Ergebnis aus.
	 */
	public long berechnung(long zahl) {
		long ergebnis = 1;
		if(zahl == 1) {
			return ergebnis;
		}
		for(int n=2;n<=zahl;n++) {
			ergebnis *=n;
			if(ergebnis > 100000000000000000L) {
				JOptionPane.showMessageDialog(null, abfragezahl+" ist zu groß, um verarbeitet zu werden.\nBitte wähle eine kleinere Zahl!", "Ungültige Eingabe", JOptionPane.PLAIN_MESSAGE);
				eingabe();
			}
		}
		return ergebnis;
	}
	
	/**
	 * Diese Methode zeigt dem Nutzer das Endergebnis.
	 */
	public void ausgabe() {
		JOptionPane.showMessageDialog(null, abfragezahl+"! ergibt "+berechnung(abfragezahl), "Fakultät", JOptionPane.PLAIN_MESSAGE);
		neustart();
	}
	
	/**
	 * Diese Methode fragt den Nutzer, ob er eine weitere Fakultaet abfragen moechte.<br>
	 * Je nach Antwort wird eine neue Abfrage gestartet oder das Programm komplett beendet.
	 */
	public void neustart() {
		int dialogneustart = JOptionPane.showConfirmDialog(null, "Möchtest Du eine weitere Fakultät berechnen?", "Neue Abfrage?", JOptionPane.YES_NO_OPTION);
        if(dialogneustart == 0) {
     	   new Fakultaet();
        } else {
     	   System.exit(0);
        }
	}
	
	public static void main(String[] args) {
		new Fakultaet();
	}

}