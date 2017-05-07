
public class Main {

	public static void main(String[] args) {
		
		//Hier eine Aenderung
		//um Git zu testen
		// öffnet ein AWT-Fenster
		//Blubbbbb
		MaxCorrelationsKlassifikator max = new MaxCorrelationsKlassifikator();
		
		max.classify(true);
		max.classify(false);
		DataPoint p1 = new DataPoint(176,72,0);
		DataPoint p2 = new DataPoint(172,76,1);
		max.classify(p1, p2,true);
		DataPoint p3 = new DataPoint(174,70,0);
		DataPoint p4 = new DataPoint(174,78,1);
		max.classify(p3, p4,false);
		max.printResultTable();
	}

}
