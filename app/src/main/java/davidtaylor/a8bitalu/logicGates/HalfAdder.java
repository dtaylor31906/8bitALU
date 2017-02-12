package davidtaylor.a8bitalu.logicGates;

/**
 * Created by Nova on 2/3/2017.
 */
public class HalfAdder
{
    private AndGate a1,a2;
    private OrGate o1;
    private NotGate n1;
    private String name;
    private Wire a,b,s,c;

    public  HalfAdder()
    {
        this("");
    }
    public HalfAdder(String name)
    {
        this.name = name;
        a = new Wire();
        b = new Wire();
        s = new Wire();
        c = new Wire();

        //wire up lthe or Gate
        o1 = new OrGate();
        o1.setWireA(a);
        o1.setWireB(b);
        o1.setWireOutput(new Wire());
        //wire up the first And gate
        a1 = new AndGate();
        a1.setWireA(a);
        a1.setWireB(b);
        a1.setWireOutput(c);
        //wire up the notGate
        n1 = new NotGate();
        n1.setWireA(c);
        n1.setWireOutput(new Wire());
        //wire up second and gate

        Wire a2InputA = o1.getWireOutput();
        Wire a2InputB = n1.getWireOutput();
        a2 = new AndGate();
        a2.setWireA(a2InputA);
        a2.setWireB(a2InputB);
        a2.setWireOutput(s);

    }
    public void Exec()
    {
        a1.execute();
        o1.execute();
        n1.execute();
        a2.execute();
    }
    public void setInputs(boolean inputA, boolean inputB)
    {
        a.setValue(inputA);
        b.setValue(inputB);
    }
    public void Print()
    {
        System.out.println("Name: "+ name+" inputs: "+ a.getValue()+", "+b.getValue() +
                "\noutput: " + s.getValue()+"" +
                "\ncarry: "+ c.getValue());
    }

    public void test()
    {
        setInputs(false,false);
        Exec();
        Print();

        setInputs(false,true);
        Exec();
        Print();

        setInputs(true,true);
        Exec();
        Print();

        setInputs(true,false);
        Exec();
        Print();
        System.out.println("=====================\n\n");
    }

    public AndGate getA1() {
        return a1;
    }

    public void setA1(AndGate a1) {
        this.a1 = a1;
    }

    public AndGate getA2() {
        return a2;
    }

    public void setA2(AndGate a2) {
        this.a2 = a2;
    }

    public OrGate getO1() {
        return o1;
    }

    public void setO1(OrGate o1) {
        this.o1 = o1;
    }

    public NotGate getN1() {
        return n1;
    }

    public void setN1(NotGate n1) {
        this.n1 = n1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wire getA() {
        return a;
    }

    public void setA(Wire a) {
        a1.setWireA(a);
        o1.setWireA(a);
    }

    public Wire getB() {
        return b;
    }

    public void setB(Wire b)
    {
        a1.setWireB(b);
        o1.setWireB(b);
    }

    public Wire getS() {
        return s;
    }

    public void setS(Wire s) {
        a2.setWireOutput(s);
    }

    public Wire getC() {
        return c;
    }

    public void setC(Wire c) {
        a1.setWireOutput(c);
        n1.setWireA(c);
    }
}
