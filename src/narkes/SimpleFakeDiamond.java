package narkes;

import java.util.Random;
import java.util.Arrays;

/**
 *
 * @author Ορφέας Τσαρτσιανίδης
 * I took the philosophy of counterfeit coin problem by this web-site:
 * http://www.dreamincode.net/forums/topic/226405-coin-forgery/
 * complexity: 0(logn)
 */

public class SimpleFakeDiamond 
{    
    int weig=0;
    
    /**
     * 
     * @param numDiamonds
     * This function initialize the diamonds 
     * by put the weight and their position randomly.
     * @return diamonds
     */
    
    public Diamond[] initializeDiamond(int numDiamonds)
    {
        Diamond[] diamonds = new Diamond[numDiamonds];
        for(int j = 0; j < diamonds.length; j++)
        {
            diamonds[j] = new Diamond(0.02, j);
        }
        Random ran = new Random();
        Float f = numDiamonds*ran.nextFloat();
        int fakePosition = f.intValue();
        diamonds[fakePosition].setWeight(0.01);  // this is the fake diamond
        return diamonds;
    }

    /**
     * 
     * @param left
     * @param mid
     * @param right
     * @return a number depending on which of three pan is heavier
     *   -1 means the left pan is heavier
     *    0 means the pans have equal weight
     *    1 means the right pan is heavier
     */
    public int compareDiamonds(Diamond[] left, Diamond[] mid, Diamond[] right)
    {
        double leftWeight = 0.0;
        for(int j = 0; j < left.length; j++)
        {
            leftWeight += left[j].getWeight();
        }
        
        double rightWeight = 0.0;
        for(int k = 0; k < right.length; k++)
        {
            rightWeight += right[k].getWeight();
        }
        
        double midWeight = 0.0;
        for(int q = 0; q < mid.length; q++)
        { 
            midWeight += mid[q].getWeight();
        }
        
        if(leftWeight < rightWeight)
        {
            weig++;
            return 1;
        }
        else if (leftWeight > rightWeight)
        {
            weig++;
            return -1;
        }
        else
        {
            weig++;
            return 0;
        }
    }
    
    /**
     * 
     * @param diamonds
     * @return the fake diamond's position
     * With this retroactive function estimate the position of the fake diamond
     * The FindFakeDiamond function separates the diamonds into three parts
     * and then depending on which of tree is heavier 
     * finds the position of the fake diamond
     */
 
    public int findFakeDiamond(Diamond[] diamonds)
    {
        if(diamonds.length == 0)
        { 
            return -1;// fake diamond not found
        } 
        else if(diamonds.length == 1)
        { 
            return diamonds[0].getPosition();
        }
        else 
        {
            boolean oddNumDiamonds = diamonds.length % 3 == 1;
            int mid = diamonds.length/3;
            int mid2 = diamonds.length*2/3;
            Diamond[] leftDiamonds = Arrays.copyOfRange(diamonds, 0, mid);
            Diamond[] midDiamonds = Arrays.copyOfRange(diamonds, mid, mid2);
            Diamond[] rightDiamonds = Arrays.copyOfRange(diamonds, mid2, diamonds.length);
            int result = compareDiamonds(leftDiamonds, midDiamonds, rightDiamonds);
            if(result == 1) 
            {
                return findFakeDiamond(leftDiamonds);
            }
            else if(result == -1)
            {
                return findFakeDiamond(rightDiamonds);
            }
            else if (result == 0)
            {
                return findFakeDiamond(midDiamonds);
            }
            else if(oddNumDiamonds)
            { 
                return diamonds[diamonds.length-1].getPosition();
            }
            else
            {
                return -1;// no fake diamond found
            } 
        }
    }
    
    /**
     * 
     * @return weig
     * This function returns the least number of weightings to find
     * the fake diamond.
     */
    
    public int getZyg()
    {
        return weig;
    }
}
