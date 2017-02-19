package davidtaylor.a8bitalu.logicGates;

/**
 * Created by Nova on 2/3/2017.
 */
public class Wire
{
    private boolean value;

    public Wire ()
    {
        this(false);
    }
    public Wire(boolean value)
    {
        this.value = value;
    }

    public boolean getValue()
    {
        return value;
    }

    public void setValue(boolean value)
    {
        this.value = value;
    }

    public String getBinValue()
    {
        if(value)
            return "1";
        else
            return "0";
    }
}
