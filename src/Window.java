import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class Window  {
	
	MaxCorrelationsKlassifikator maxCorrClassificator;
	
	public Window()
	{

	}
	
	public void paint(Graphics g) {
		
//		Graphics2D ga = (Graphics2D)g;
//		DataPoint[] data = maxCorrClassificator.getData();
//		List<DataPoint> lstdata0 = new ArrayList<DataPoint>();
//		List<DataPoint> lstdata1 = new ArrayList<DataPoint>();
//		
//		for(int i = 100; i <200; i++)
//		{
//			if(data[i].classification == 0)
//			{
//				lstdata0.add(data[i]);
//			}
//			else
//			{
//				lstdata1.add(data[i]);
//			}
//		}
//		
//		Line2D line = new Line2D.Double();
//		for(int i = 0; i < lstdata0.size()-1;i++)
//		{
//			line.setLine((lstdata0.get(i).size-150)*8,lstdata0.get(i).weight *4,(lstdata0.get(i+1).size-150)*8,lstdata0.get(i+1).weight);
//			ga.draw(line);
//		}	
		
//		for(int i = 0; i < lstdata1.size()-1;i++)
//		{
//			line.setLine((lstdata1.get(i).size-150)*8,lstdata1.get(i).weight *4,(lstdata1.get(i+1).size-150)*8,lstdata1.get(i+1).weight);
//			ga.draw(line);
//		}
	}
	
	class TestWindowListener extends WindowAdapter
	{
	  public void windowClosing(WindowEvent e)
	  {
	    e.getWindow().dispose();                   // Fenster "killen"
	    System.exit(0);                            // VM "killen" 
	  }           
	}

}
