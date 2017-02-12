package davidtaylor.a8bitalu.logicGates;

/**
 * Created by Nova on 2/5/2017.
 */
public class FullAdder
{
    private HalfAdder h1, h2;
    private Wire a,b,c,s,t;
    private OrGate o1;
    private String name;

    public FullAdder()
    {
        this("");
    }

    public FullAdder(String name)
    {
        this.name = name;

        h1 = new HalfAdder("Half Adder 1");
        h2 = new HalfAdder("Half Adder 2");
        o1 = new OrGate();
        t = new Wire();
        //Wire things up
        // H1 first

        b = h1.getA();
        c = h1.getB();
        o1.setWireB(h1.getC());//connects h1 to the orgate
        //now lets wire h2
        h2.setB(h1.getS());//connects the two half adders
        a = h2.getA();
        s = h2.getS();
        o1.setWireA(h2.getC());
        //wire orGate output
        o1.setWireOutput(t);

    }

    public void exec()
    {
        h1.Exec();
        h2.Exec();
        o1.execute();
    }
    public void setInputs(boolean a, boolean b, boolean c)
    {
       this.a.setValue(a);
       this.b.setValue(b);
       this.c.setValue(c);
    }

    public void Print()
    {
        System.out.println("Name: "+ name+" inputs: "+ a.getValue()+", "+b.getValue() +", "+
                c.getValue()+
                "\noutput: " + s.getValue()+"" +
                "\ncarry: "+ t.getValue());
    }

    public void test()
    {
        setInputs(false,false,false);
        exec();
        Print();

        setInputs(false,false,true);
        exec();
        Print();
        setInputs(false,true,false);
        exec();
        Print();
        setInputs(false,true,true);
        exec();
        Print();
        setInputs(true,false,false);
        exec();
        Print();

        setInputs(true, false,true);
        exec();
        Print();

        setInputs(true,true,false);
        exec();
        Print();
        setInputs(true,true,true);
        exec();
        Print();

        System.out.println("=====================\n\n");
    }

    public HalfAdder getH1() {
        return h1;
    }

    public void setH1(HalfAdder h1) {

        this.h1 = h1;
    }

    public HalfAdder getH2() {
        return h2;
    }

    public void setH2(HalfAdder h2) {
        this.h2 = h2;
    }

    public Wire getA() {
        return a;
    }

    public void setA(Wire a)
    {
        h2.setA(a);
    }

    public Wire getB() {
        return b;
    }

    public void setB(Wire b)
    {
        h1.setA(b);
    }

    public Wire getC() {
        return c;
    }

    public void setC(Wire c) {
        h1.setB(c);
    }

    public Wire getS() {
        return s;
    }

    public void setS(Wire s) {
        h2.setS(s);
    }

    public Wire getT() {
        return t;
    }

    public void setT(Wire t) {
       o1.setWireOutput(t);
    }

    public OrGate getO1() {
        return o1;
    }

    public void setO1(OrGate o1) {
        this.o1 = o1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
