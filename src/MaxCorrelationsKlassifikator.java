import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxCorrelationsKlassifikator {
	
	//Datenbasis, Daten die zur Entwicklung des Algorithmus benutzt werden
	//Testdaten, Daten zum Testen des Algorithmus
	//Beide Datensätze haben gleiche Qualität
	
	DataPoint []data;
	int[] correctData = new int[200];
	ErrorClass [] fehlerArr;
	
	public MaxCorrelationsKlassifikator()
	{
		data = new DataPoint[200];
		fehlerArr = new ErrorClass[4];
		for(int i = 0 ; i < fehlerArr.length;i++)
		{
			fehlerArr[i] = new ErrorClass();
		}
		for(int i = 0 ; i< data.length;i++)
		{
			data[i] = new DataPoint();
		}
		populateData();
		calculateCorrectData();
		
	}
	
	private void populateData()
	{
		for(int i = 1 ; i <=200 ; i++)
		{
			data[i-1].size = 162 + (7*i % 19) + (5*i % 7)* (i % 3);
			data[i-1].weight = data[i-1].size-100 + 2 * (i % 2-0.5f)* (3*i % 11 + 5*i % 13 + 1);
			if(i % 19 != 0 )
			{
				data[i-1].classification = i % 2;
			}
			else
			{
				data[i-1].classification = 1 - (i%2);
			}
		}
		
		int z;
		z = 4;
	}
	
	void classify(boolean isMean)
	{
		DataPoint p1 = new DataPoint(0,0,0);
		DataPoint p2 = new DataPoint(0,0,1);
		if(isMean)
			findRepresentativeMean(p1,p2);
		else
			findRepresentativeMedian(p1, p2);
		
		for(int i = 100; i < 200; i++)
		{
			double dist1 = calcDistance(data[i], p1);
			double dist2 = calcDistance(data[i], p2);
			if(dist1 <= dist2)
				data[i].classification = p1.classification;
			else
				data[i].classification = p2.classification;
		}
		
		if(isMean)
		{
			calculateErrorRate(fehlerArr[0]);
		}
		else
		{
			calculateErrorRate(fehlerArr[1]);
		}
	}
	
	public void runTests()
	{
//		this.classify(true);
//		this.classify(false);
		DataPoint p1 = new DataPoint(176,72,0);
		DataPoint p2 = new DataPoint(172,76,0);
		this.classify(p1, p2,true);
//		DataPoint p3 = new DataPoint(174,70,0);
//		DataPoint p4 = new DataPoint(174,78,0);
//		this.classify(p3, p4,false);
		this.printResultTable();
	}
	
	void classify(DataPoint p1, DataPoint p2,boolean partC)
	{	
		for(int i = 100; i < 200; i++)
		{
			double dist1 = calcDistance(data[i], p1);
			double dist2 = calcDistance(data[i], p2);
			if(dist1 <= dist2)
				data[i].classification = p1.classification;
			else
				data[i].classification = p2.classification;
		}
		
		if(partC)
			calculateErrorRate(fehlerArr[2]);
		else
			calculateErrorRate(fehlerArr[3]);
	}
	
	void findRepresentativeMean(DataPoint classRepMean1,DataPoint classRepMean2)
	{ 
		int anzahl1 = 0;
		int anzahl2 = 0;
        
		for(int i = 0 ; i < 100 ; i++)
		{
			if(data[i].classification == 0)
			{
				classRepMean1.size+=data[i].size;
				classRepMean1.weight+=data[i].weight;
				anzahl1++;
			}
			else
			{
				classRepMean2.size+=data[i].size;
				classRepMean2.weight+=data[i].weight;
				anzahl2++;
			}
		}
		
		//Repräsentant Klasse0
		classRepMean1.size /= anzahl1;
		classRepMean1.weight /= anzahl1;
		classRepMean1.classification = 0;
		
		//Repräsent Klasse1
		classRepMean2.size /= anzahl2;
		classRepMean2.weight /= anzahl2;
		classRepMean2.classification = 1;		
	}
	
	void findRepresentativeMedian(DataPoint classRepMedian1, DataPoint classRepMedian2)
	{
        List<DataPoint> lstClassRep1 = new ArrayList<DataPoint>();
        List<DataPoint> lstClassRep2 = new ArrayList<DataPoint>();
        
		for(int i = 0 ; i < 100 ; i++)
		{
			if(data[i].classification == 0)
			{
				lstClassRep1.add(data[i]);
			}
			else
			{
				lstClassRep2.add(data[i]);
			}
		}
		
		int median1 = (int)lstClassRep1.size()/2;
		int median2 = (int)lstClassRep2.size()/2;
		
		//Median size
		
		Collections.sort(lstClassRep1, new Comparator<DataPoint>(){ 
		    @Override 
		    public int compare(DataPoint p1, DataPoint p2) 
		    { 
		        return Float.compare(p1.size, p2.size); 
		    } 
		});

		classRepMedian1.size = lstClassRep1.get(median1).size;

		Collections.sort(lstClassRep2, new Comparator<DataPoint>(){ 
		    @Override 
		    public int compare(DataPoint p1, DataPoint p2) 
		    { 
		        return Float.compare(p1.size, p2.size); 
		    } 
		});

		classRepMedian2.size = lstClassRep2.get(median1).size;
		
		//Median weight
		
		Collections.sort(lstClassRep1, new Comparator<DataPoint>(){ 
		    @Override 
		    public int compare(DataPoint p1, DataPoint p2) 
		    { 
		        return Float.compare(p1.weight, p2.weight); 
		    } 
		});

		classRepMedian1.weight = lstClassRep1.get(median1).weight;

		Collections.sort(lstClassRep2, new Comparator<DataPoint>(){ 
		    @Override 
		    public int compare(DataPoint p1, DataPoint p2) 
		    { 
		        return Float.compare(p1.weight, p2.weight); 
		    } 
		});

		classRepMedian2.weight = lstClassRep2.get(median1).weight;
		
	}
	
	private double calcDistance(DataPoint p1,DataPoint p2 )
	{
		double xDist = p1.size - p2.size;
		double yDist = p1.weight - p2.weight;
		double dist = xDist * xDist + yDist * yDist;
		return Math.sqrt(dist);
	}
	
	public DataPoint[] getData()
	{
		return data;
	}
	
	double calculateErrorRate(ErrorClass error)
	{
		int counter = 0 ;
		
		for(int i = 100 ; i <200 ; i++)
		{
			if(data[i].classification != correctData[i])
			{
				if(correctData[i] == 0)
				{
					error.fehler1++;  // Expected 0 but 1
				}
				else
				{
					error.fehler2++;
				}
				counter++;
			}
		}
		
		return counter ;
	}
	
	void calculateCorrectData()
	{
		for(int i = 1 ; i <= 200;i++)
		{
			int erg  = i%2;
			if(i%19 == 0 )
			{
				correctData[i-1] = 1 -erg;
			}
			else
			{
				correctData[i-1] = erg;
			}
		}
	}
	
	public void printResultTable()
	{
		System.out.println("______________|Fehler 0____|Fehler 1_____");
		System.out.println("______a)______|_____" + fehlerArr[0].fehler1+"______|____"+fehlerArr[0].fehler2+"________" );
		System.out.println("______b)______|_____" + fehlerArr[1].fehler1+"______|____"+fehlerArr[1].fehler2+"________" );
		System.out.println("______c)______|_____" + fehlerArr[2].fehler1+"______|____"+fehlerArr[2].fehler2+"________" );
		System.out.println("______d)______|_____" + fehlerArr[3].fehler1+"______|____"+fehlerArr[3].fehler2+"________" );

	}

}





