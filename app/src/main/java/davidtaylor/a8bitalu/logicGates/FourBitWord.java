package davidtaylor.a8bitalu.logicGates;

/**
 * Created by Nova on 2/5/2017.
 */
public class FourBitWord
{
    private boolean a,b,c,d;//d is msb
    private String binary;
    private final int WORDLENGTH = 4;
    public FourBitWord(boolean d, boolean c, boolean b, boolean a)
    {

        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    public FourBitWord(boolean[] word)
    {
        if(word.length != WORDLENGTH)
        {
            System.out.println("This is not  4-bits ");
            return;
        }
        this.a = word[0];
        this.b = word[1];
        this.c = word[2];
        this.d = word[3];

    }
    public boolean getA() {
        return a;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public boolean getB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean getC() {
        return c;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public boolean getD() {
        return d;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public String getBinary() {
        return binary;
    }

    @Override
    public String toString()
    {
        String representation = ""+d+", "+c+", "+b+", "+a;
        binary = representation.replaceAll("true,*","1");
        binary = binary.replaceAll("false,*","0");
        return representation;
    }
}
