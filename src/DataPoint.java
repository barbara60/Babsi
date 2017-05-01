import java.util.Comparator;


//The Comparable interface only allows to sort a single property. 

public class DataPoint implements Comparator {
	float size;
	float weight;
	float classification;
	
	public DataPoint(	float size,float weight,float classification)
	{
		this.size = size;
		this.weight = weight;
		this.classification = classification;	
	}

	public DataPoint() {
		this.size = 0;
		this.weight = 0;
		this.classification = 0;
	}

	@Override
	public int compare(Object arg0, Object arg1) {
		
		return 0;
	}


}
