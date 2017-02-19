package davidtaylor.a8bitalu.logicGates;

/**
 * Created by Nova on 2/1/2017.
 */
public class AndGate extends Gate
{
    public AndGate()
    {
        this(false,false);
    }

    public AndGate(boolean inputA, boolean inputB)
    {
        this.inputA = inputA;
        this.inputB = inputB;
        inputCount = 2;
    }


    @Override
    public void execute()
    {
        //check for input wires and set inputs equal to the wire value
        if(wireA != null )
        {
            inputA = wireA.getValue();
        }

        if(wireB != null )
        {
            inputB = wireB.getValue();
        }

        output = inputA &inputB;

        if (wireOutput != null)
        {
            wireOutput.setValue(output);
        }
    }

}
