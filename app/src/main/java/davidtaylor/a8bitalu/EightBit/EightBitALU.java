package davidtaylor.a8bitalu.EightBit;

import davidtaylor.a8bitalu.logicGates.FourBitALU;
import davidtaylor.a8bitalu.logicGates.FourBitWord;

/**
 * Created by Nova on 2/7/2017.
 */
public class EightBitALU
{
    private FourBitALU alu1, alu2;
    private EightBitWord wordA, wordB, wordOutput;

    public EightBitALU()
    {
        alu1 = new FourBitALU();
        alu2 = new FourBitALU();
        wireItUp();
    }

    private void wireItUp()
    {
        alu2.setOp(alu1.getOp());//change the second alu op wire to be the same as first
        alu2.getFa0().setC(alu1.getC3());//sets the c value in second alu to be the carryout from the last adder in the first alu
    }

    public EightBitWord getWordA() {
        return wordA;
    }

    public void setWordA(EightBitWord wordA)
    {
        this.wordA = wordA;
    }

    public EightBitWord getWordB() {
        return wordB;
    }

    public void setWordB(EightBitWord wordB) {
        this.wordB = wordB;
    }
    public void print()
    {
        if(!alu1.getOp().getValue()) {
            System.out.print("Name: EightBitAlu" +" operation = a+b"+
                    " \nwordA:"+ wordA.getBinString() + " wordB:" + wordB.getBinString() +
                    "\n output = " + alu2.getC3().getBinValue() + wordOutput.getBinString() + "\n\n");
        }
        else {
            System.out.print("Name: EightBitAlu" +" operation = a-b"+
                    " \nwordA:"+ wordA.getBinString() + " wordB:"+ wordB.getBinString() +
                    "\n output = " + wordOutput.getBinString() + "\n\n");
        }
    }
    public void execute(boolean willSubtract)
    {
        transferWordA();
        transferWordB();
        alu1.getOp().setValue(willSubtract);
        alu1.executeOrGates();
        alu2.executeOrGates();
        alu1.partialExecution();
        alu2.partialExecution();
        wordOutput = new EightBitWord(alu1.getWordOutput(), alu2.getWordOutput());
    }
    public void test()
    {
        EightBitWord word1, word2;
        word1 = new EightBitWord(false,false,false,false,false,false,false,false);
        word2 = new EightBitWord(false,false,false,false,false,false,false,false);
        setWordA(word1);
        setWordB(word2);
        execute(false);
        print();


        word1 = new EightBitWord(true,false,false,false,false,false,false,false);
        word2 = new EightBitWord(true,false,false,false,false,false,false,false);
        setWordA(word1);
        setWordB(word2);
        execute(false);
        print();

        word1 = new EightBitWord(true,false,false,false,false,false,false,true);
        word2 = new EightBitWord(true,false,false,false,false,false,false,false);
        setWordA(word1);
        setWordB(word2);
        execute(true);
        print();
        word1 = new EightBitWord(false,true,false,false,false,false,false,false);
        word2 = new EightBitWord(true,false,false,false,false,false,false,false);
        setWordA(word1);
        setWordB(word2);
         execute(true);
        print();
        word1 = new EightBitWord(true,false,false,false,false,true,true,true);
        word2 = new EightBitWord(true,false,false,false,false,false,false,true);
        setWordA(word1);
        setWordB(word2);
        execute(false);
        print();

        word1 = new EightBitWord(true,false,false,false,false,true,true,true);
        word2 = new EightBitWord(true,false,false,false,false,false,false,true);
        setWordA(word1);
        setWordB(word2);
        execute(true);
        print();
    }

    private void transferWordB()
    {
        FourBitWord[] word = this.wordB.splitWord();
        alu1.setWordB(word[0]);
        alu2.setWordB(word[1]);
    }

    private void transferWordA()
    {
        //break word into 4 bit word; to use methods in 4bit alu
        FourBitWord[] wordA = this.wordA.splitWord();
        alu1.setWordA(wordA[0]);
        alu2.setWordA(wordA[1]);
    }

    public EightBitWord getWordOutput() {
        return wordOutput;
    }

    public FourBitALU getAlu2() {
        return alu2;
    }
}
