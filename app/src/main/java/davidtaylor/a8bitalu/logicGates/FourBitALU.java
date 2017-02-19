package davidtaylor.a8bitalu.logicGates;

/**
 * Created by Nova on 2/5/2017.
 */
public class FourBitALU
{
    private String name;
    private FullAdder fa0,fa1,fa2,fa3;
    private  XorGate x0,x1,x2,x3;
    private Wire a0,a1,a2,a3;
    private Wire b0,b1,b2,b3;
    private Wire s0,s1,s2,s3;
    private Wire c0,c1,c2,c3,op;
    private FourBitWord wordA,wordB,wordOutput;

    public FourBitALU()
    {
        this("4-bit ALU");
    }

    public FourBitALU(String name)
    {
        this.name = name;
        fa0 = new FullAdder("full adder 0");
        fa1 = new FullAdder("full adder 1");
        fa2 = new FullAdder("full adder 2");
        fa3 = new FullAdder("full adder 3");
        x0 = new XorGate();
        x1 = new XorGate();
        x2 = new XorGate();
        x3 = new XorGate();
        a0 = new Wire();
        a1 = new Wire();
        a2 = new Wire();
        a3 = new Wire();
        b0 = new Wire();
        b1 = new Wire();
        b2 = new Wire();
        b3 = new Wire();
        s1 = new Wire();
        s2 = new Wire();
        s3 = new Wire();
        s0 = new Wire();
        c0 = new Wire();
        c1 = new Wire();
        c2 = new Wire();
        c3 = new Wire();
        op = new Wire();
        wireItUp();
    }

    private void wireItUp()
    {
        //start with Full adder 0
        fa0.setA(a0);
        // the b wire is Xor'd before connecting to fa0
        x0.setWireA(b0);
        x0.setWireB(op);//all xor gates with have this pattern in which op is the b wire
        x0.setWireOutput(new Wire());
        fa0.setB(x0.getWireOutput());
        fa0.setC(op);
        fa0.setS(s0);
        fa0.setT(c0);
        //FA1
        fa1.setA(a1);
        x1.setWireA(b1);
        x1.setWireB(op);
        x1.setWireOutput(new Wire());
        fa1.setB(x1.getWireOutput());
        fa1.setC(c0);
        fa1.setS(s1);
        fa1.setT(c1);
        //Fa2
        fa2.setA(a2);
        x2.setWireA(b2);
        x2.setWireB(op);
        x2.setWireOutput(new Wire());
        fa2.setB(x2.getWireOutput());
        fa2.setC(c1);
        fa2.setS(s2);
        fa2.setT(c2);
        //FA3
        fa3.setA(a3);
        x3.setWireA(b3);
        x3.setWireB(op);
        x3.setWireOutput(new Wire());
        fa3.setB(x3.getWireOutput());
        fa3.setC(c2);
        fa3.setS(s3);
        fa3.setT(c3);
    }
    public void exec(boolean willSubtract) // when boolean is true ALU turns into a subtractor
    {
        enterWordA();
        enterWordB();
        op.setValue(willSubtract);
        executeOrGates();
        fa0.exec();
        fa1.exec();
        fa2.exec();
        fa3.exec();
        wordOutput = new FourBitWord(s3.getValue(),s2.getValue(),s1.getValue(),s0.getValue());
    }


    public void executeOrGates()
    {
        x0.execute();
        x1.execute();
        x2.execute();
        x3.execute();
    }

    private void enterWordB()
    {
        b0.setValue(wordB.getA());
        b1.setValue(wordB.getB());
        b2.setValue(wordB.getC());
        b3.setValue(wordB.getD());
    }

    private void enterWordA()
    {
        a0.setValue(wordA.getA());
        a1.setValue(wordA.getB());
        a2.setValue(wordA.getC());
        a3.setValue(wordA.getD());
    }

    public void setWordA(boolean d, boolean c, boolean b, boolean a)
    {
        FourBitWord word = new FourBitWord(d,b,c,a);
        this.wordA = word;
        enterWordA();
    }


    public void setWordB(boolean d, boolean c, boolean b, boolean a)
    {
        FourBitWord word = new FourBitWord(d,b,c,a);
        this.wordB = word;
        enterWordB();
    }

    public FourBitWord getWordA() {
        return wordA;
    }

    public void setWordA(FourBitWord wordA) {
        this.wordA = wordA;
        enterWordA();
    }

    public FourBitWord getWordB() {
        return wordB;
    }

    public void setWordB(FourBitWord wordB) {
        this.wordB = wordB;
        enterWordB();
    }

    public void print()
    {


        if(!op.getValue()) {
            System.out.print("Name: " + name +" operation = a+b"+
                    " \nwordA:" + wordA.toString() + " " + wordA.getBinary() + " wordB:" + wordB.toString() + " " + wordB.getBinary() +
                    "\n output = " + c3.getValue() + ", " + wordOutput.toString() + "\n\n");
        }
        else {
            System.out.print("Name: " + name +" operation = a-b"+
                    " \nwordA:" + wordA.toString() + " " + wordA.getBinary() + " wordB:" + wordB.toString() + " " + wordB.getBinary() +
                            "\n output = " + wordOutput.toString() + "\n\n");
        }
    }

    public void test()
    {
        setWordA(false,false,false,false);
        setWordB(false,false,false,false);
        exec(false);
        print();

        setWordA(false,false,false,true);
        setWordB(false,false,false,true);
        exec(false);
        print();

        setWordA(false,false,false,true);
        setWordB(false,false,false,true);
        exec(true);
        print();
        setWordA(true,false,false,true);
        setWordB(false,false,false,true);
        exec(true);
        print();

        setWordA(true,true,true,true);
        setWordB(true,false,false,true);
        exec(false);
        print();

        setWordA(true,true,true,true);
        setWordB(true,false,false,true);
        exec(true);
        print();
        
        setWordA(true,true,true,true);
        setWordB(true,true,true,true);
        exec(false);
        print();
    }

    public FullAdder getFa0() {
        return fa0;
    }

    public FullAdder getFa1() {
        return fa1;
    }

    public FullAdder getFa2() {
        return fa2;
    }

    public FullAdder getFa3() {
        return fa3;
    }

    public Wire getA0() {
        return a0;
    }

    public Wire getA1() {
        return a1;
    }

    public Wire getA2() {
        return a2;
    }

    public Wire getA3() {
        return a3;
    }

    public Wire getB0() {
        return b0;
    }

    public Wire getB1() {
        return b1;
    }

    public Wire getB2() {
        return b2;
    }

    public Wire getB3() {
        return b3;
    }

    public Wire getS0() {
        return s0;
    }

    public Wire getS1() {
        return s1;
    }

    public Wire getS2() {
        return s2;
    }

    public Wire getS3() {
        return s3;
    }

    public Wire getC0() {
        return c0;
    }

    public Wire getC1() {
        return c1;
    }

    public Wire getC2() {
        return c2;
    }

    public Wire getC3() {
        return c3;
    }

    public Wire getOp() {
        return op;
    }

    public void setOp(Wire op)
    {
        x0.setWireB(op);
        x1.setWireB(op);
        x2.setWireB(op);
        x3.setWireB(op);
    }

    public FourBitWord getWordOutput() {
        return wordOutput;
    }

    public void partialExecution()
    {
        fa0.exec();
        fa1.exec();
        fa2.exec();
        fa3.exec();
        wordOutput = new FourBitWord(s3.getValue(),s2.getValue(),s1.getValue(),s0.getValue());
    }
}
