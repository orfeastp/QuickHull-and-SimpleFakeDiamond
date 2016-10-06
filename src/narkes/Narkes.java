package narkes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.geom.Point2D;


/**
 *
 * @author Ορφέας Τσαρτσιανίδης
 */
public class Narkes {

    /**
     * @param args the command line arguments
     * The main function reads the input file and then uses the QuickHull class to
     * estimate the shortest distance and the shortest path. Also, the main function uses
     * the SimpleFakeDiamond class to estimate the least number of weightings to find
     * the fake diamond.
     */
    public static void main(String[] args) throws IOException {
        
        int i=0,num_diamonds=0;
        String line;
        boolean ins = false;
        ArrayList<Point> points = new ArrayList<Point>();
        int x_1=0,y_1=0;
        
        try (BufferedReader input = new BufferedReader(new FileReader("input.txt"));) 
	{                        
            while ((line = input.readLine()) != null) 
            {
                String token;
                StringTokenizer strTok = new StringTokenizer(line);
                token = strTok.nextToken();
                int x = Integer.parseInt(token);
                if(i==0)
                {
                    x_1=x;
                }
                if((i==2) && (ins == false)){
                   ins = true;
                   num_diamonds=x;
                }
                else if(strTok.countTokens()<=2)
                {
                    token = strTok.nextToken();
                    int y = Integer.parseInt(token);
                    Point e = new Point(x,y);
                    points.add(i,e);
                    if(i==0)
                    {
                        y_1=y;
                    }
                    i++;
                }
            }
	}
        
        String show = "The shortest path is:";
        String s1="0",s2="0",s3="0",s4="0";
        Double x1,x2,y1,y2,d=0.0;
        QuickHull qh = new QuickHull();
        
        ArrayList<Point> p = qh.quickHull(points);
        
        if(p.get(0).x!=x_1)
        {
            Point z = new Point(x_1,y_1);
            p.add(0,z);
            for(int a=1;a<p.size();a++)
            {
                if(p.get(a).x==x_1)
                {
                    p.remove(a);
                }
            }
        }
        
        for (int j = 0; j < 2; j++)
        {
            if(j==1)
            {
                x1 = (double) p.get(j).x;
                y1 = (double) p.get(j).y;
                x2 = (double) p.get(p.size()-1).x;
                y2 = (double) p.get(p.size()-1).y;
                d += Point2D.distance(x1,y1,x2,y2);
                s3 = Double.toString(p.get(p.size()-1).x);
                s4 = Double.toString(p.get(p.size()-1).y);
                show+="-->"+"("+s3+", "+s4+")";
            }
            else
            {
                x1 = (double) p.get(j).x;
                y1 = (double) p.get(j).y;
                x2 = (double) p.get(j+1).x;
                y2 = (double) p.get(j+1).y;
                d += Point2D.distance(x1,y1,x2,y2);
                s1 = Double.toString(p.get(j).x);
                s2 = Double.toString(p.get(j).y);
                s3 = Double.toString(p.get(j+1).x);
                s4 = Double.toString(p.get(j+1).y);
                show+="("+s1+", "+s2+")"+"-->"+"("+s3+", "+s4+")";
            }
        }
        System.out.println("The shortest distance is "+d);
        System.out.println(show);
        
        SimpleFakeDiamond fc = new SimpleFakeDiamond();
        Diamond[] diamonds = fc.initializeDiamond(156);
        int fakeDiamondPosition = fc.findFakeDiamond(diamonds);
        int weightings = fc.getZyg()-1;
        System.out.println("Number of weightings: "+weightings);
    }
}