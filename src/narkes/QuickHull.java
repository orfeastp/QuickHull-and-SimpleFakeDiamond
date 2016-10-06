package narkes;

import java.util.ArrayList;
import java.awt.Point;

/**
 *
 * @author Ορφέας Τσαρτσιανίδης
 * I took the philosophy of QuickHull class by this web-site: 
 * http://www.sanfoundry.com/java-program-implement-quick-hull-algorithm-find-convex-hull/
 * complexity: 0(nlogn)
 */
public class QuickHull {
    
    /**
     * 
     * @param points
     * @return convex hull
     * This function estimates the min-point and the max-point. Also uses hullset function
     * to find the convex hull
     */
    
    public ArrayList<Point> quickHull(ArrayList<Point> points)
    {
        ArrayList<Point> convexHull = new ArrayList<Point>();
        if (points.size() < 3)
        {
            return (ArrayList) points.clone();
        }
        
        int minPoint = 0, maxPoint = 1;
        
        // A=min  B=max
        Point A = points.get(minPoint);
        Point B = points.get(maxPoint);
        convexHull.add(A);
        convexHull.add(B);
        
        points.remove(A);
        points.remove(B);
 
        ArrayList<Point> leftSet = new ArrayList<Point>();
        ArrayList<Point> rightSet = new ArrayList<Point>();

        for (int i = 0; i < points.size(); i++)
        {
            Point p = points.get(i);
            if (pointLocation(A, B, p) == -1)
            {
                leftSet.add(p);
            }
            else if (pointLocation(A, B, p) == 1)
            {
                rightSet.add(p);
            }
        }
        
        hullSet(A, B, rightSet, convexHull);
        hullSet(B, A, leftSet, convexHull);
        
        return convexHull;
    }
    
    /**
     * 
     * @param A
     * @param B
     * @param C
     * @return num
     * This function estimates the distance between a point and the max and min points 
     */
    public int distance(Point A, Point B, Point C)
    {
        int ABx = B.x - A.x;
        int ABy = B.y - A.y;
        int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        if (num < 0)
        {
            num = -num;
        }
        return num;
    }
    
    /**
     * 
     * @param A
     * @param B
     * @param set
     * @param hull
     * With this retroactive function estimate the convex  hull
     */
    
    public void hullSet(Point A, Point B, ArrayList<Point> set,
            ArrayList<Point> hull)
    {
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0)
        {
            return;
        }
        if (set.size() == 1)
        {
            Point p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
        }
        int dist = Integer.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++)
        {
            Point p = set.get(i);
            int distance = distance(A, B, p);
            if (distance > dist)
            {
                dist = distance;
                furthestPoint = i;
            }
        }
        Point P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, P);

        // Determine who's to the left of AP
        ArrayList<Point> leftSetAP = new ArrayList<Point>();
        for (int i = 0; i < set.size(); i++)
        {
            Point M = set.get(i);
            if (pointLocation(A, P, M) == 1)
            {
                leftSetAP.add(M);
            }
        }
 
        // Determine who's to the left of PB
        ArrayList<Point> leftSetPB = new ArrayList<Point>();
        for (int i = 0; i < set.size(); i++)
        {
            Point M = set.get(i);
            if (pointLocation(P, B, M) == 1)
            {
                leftSetPB.add(M);
            }
        }

        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);
 
    }
    
    /**
     * 
     * @param A
     * @param B
     * @param P
     * @return a number to estimate the position of each point(left or right)
     */
    
    public int pointLocation(Point A, Point B, Point P)
    {
        int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
        if (cp1 > 0)
        {
            return 1;
        }
        else if (cp1 == 0)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
