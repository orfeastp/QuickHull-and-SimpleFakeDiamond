package narkes;

/**
 *
 * @author Ορφέας Τσαρτσιανίδης
 * This class initialize the weight and the position of diamonds
 * Also, returns the weight and the position and sets the weight
 * 
 */
public class Diamond 
{
    double weight;
    int position;
        
    Diamond(double weight, int position)
    {
        this.weight = weight;
        this.position = position;
    }

    double getWeight() 
    {
        return weight;
    }

    void setWeight(double weight)
    {
        this.weight = weight;
    }         
        
    int getPosition()
    {
        return position;
    }
}