package davidtaylor.a8bitalu.logicGates;

/**
 * Created by Nova on 2/1/2017.
 */
public abstract class Gate
{
     boolean output,inputA,inputB;
     Wire wireA,wireB, wireOutput;
     int inputCount;
     int truthCount;//count of inputs that are == true

    public boolean getOutput()
    {
        return output;
    }

    public boolean equals(Gate gate2)
    {
        if(this.inputCount != gate2.inputCount)//if the gats have diffrent number inputs
        {
            return false;
        }

        if(this.output != gate2.output) // if the outputs of the gates are diffrent
        {
            return false;
        }

        getTruthCount();

        if(this.truthCount != gate2.truthCount)
        {
            return false;
        }
        return true;
    }

    public <e extends Gate> void makeEqual(e gate2) throws GateMismatchException
    {
        if(this.getClass() != gate2.getClass())
        {
            throw new GateMismatchException("Gates must be the same type to makeEqual");
        }

        this.output = gate2.output;
        this.inputA = gate2.inputA;

        if(this.inputCount == 2)//since not all gates have 2 inputs this statement is needed.
        {
            this.inputB = gate2.inputB;
        }
    }
    public boolean getInputA()
    {
        return inputA;
    }

    public boolean getInputB()
    {
        return inputB;
    }
    public void set(boolean inputA, boolean inputB)
    {
        this.inputA = inputA;
        this.inputB = inputB;
    }

    public Wire getWireA()
    {
        return wireA;
    }

    public void setWireA(Wire wireA) {
        this.wireA = wireA;
    }

    public Wire getWireB() {
        return wireB;
    }

    public void setWireB(Wire wireB) {
        this.wireB = wireB;
    }

    public Wire getWireOutput() {
        return wireOutput;
    }

    public void setWireOutput(Wire wireOutput) {
        this.wireOutput = wireOutput;
    }

    void getTruthCount()// counts the number of inputs set to "true" for the current gate\
     {
         truthCount = 0;
         if(inputA) truthCount++;
         if(inputB)truthCount++;
     }
    public  void print()
    {
        String gateType = this.getClass().toString();
        int index = gateType.indexOf(".");
        gateType= gateType.substring(index+1);
        System.out.println("Gate type: "+ gateType+" inputs: "+ inputA+", "+inputB +
                "\noutput: " + output);

    }
    public void test()
    {
        set(false,false);
        execute();
        print();
        set(false,true);
        execute();
        print();
        set(true,false);
        execute();
        print();
        set(true,true);
        execute();
        print();
        System.out.println("=====================\n\n");
    }
    public abstract void execute();


}
