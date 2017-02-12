package davidtaylor.a8bitalu.logicGates;

/**
 * Created by Nova on 2/1/2017.
 */
public class NotGate extends Gate
{
    //inputB is ignored in this gate because it conceptually has one input

    public NotGate()//no args constructor sets inputA to false
    {
       this(false);
    }
    public NotGate(boolean inputA)
    {
        this.inputA = inputA;
        inputCount = 1;
    }

    public void set(boolean inputA)
    {
         this.inputA = inputA;
    }

    @Override
    void getTruthCount()
    {
        if(inputA == true)
        {
            truthCount = 1;
        }
        else
        {
            truthCount = 0;
        }
    }

    @Override
    public void execute()
    {
        if(wireA != null )
        {
            inputA = wireA.getValue();
        }

        output = !inputA;

        if (wireOutput != null)
        {
            wireOutput.setValue(output);
        }
    }

    @Override
    public void print()
    {
        System.out.println("Gate type: NotGate  input: "+ inputA+
                "\noutput: " + output);
    }

    @Override
    public void setWireB(Wire wireB) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void test()
    {
        set(false);
        execute();
        print();

        set(true);
        execute();
        print();

        System.out.println("=====================\n\n");
    }

    @Override
    public Wire getWireB() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getInputB()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(boolean inputA, boolean inputB)
    {
        throw new UnsupportedOperationException();
    }
}
