package davidtaylor.a8bitalu.EightBit;;

import davidtaylor.a8bitalu.logicGates.FourBitALU;
import davidtaylor.a8bitalu.logicGates.FourBitWord;

/**
 * Created by Nova on 2/7/2017.
 */
public class EightBitALU
{
    private FourBitALU alu1, alu2;
    private EightBitWord wordA, wordB, wordOutput;
    private AndGateExtension andGate;
    private OrGateExtension orGate;
    private XorGateExtension xOrGate;
    private OpCodes lastOpCode;// last used lastOpCode

    public EightBitALU()
    {
        alu1 = new FourBitALU();
        alu2 = new FourBitALU();
        wireItUp();
        andGate = new AndGateExtension();
        orGate = new OrGateExtension();
        xOrGate = new XorGateExtension();
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
        if(lastOpCode== OpCodes.ADD)
        {

            String carry = alu2.getC3().getBinValue();
            String output = wordOutput.getBinString();
            String binString = ( carry== "1") ? carry +output : output;

            System.out.print("Name: EightBitAlu" +" operation = "+lastOpCode.getOperation()+
                    " \nwordA:"+ wordA.getBinString() + " wordB:" + wordB.getBinString() +
                    "\n output = " + binString + "\n\n");
        }

        else
            {
            System.out.print("Name: EightBitAlu" +" operation = "+lastOpCode.getOperation()+
                    " \nwordA:"+ wordA.getBinString() + " wordB:"+ wordB.getBinString() +
                    "\n output = " + wordOutput.getBinString() + "\n\n");
        }
    }
    private void execute(boolean willSubtract)
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
        executeOpCode(OpCodes.ADD);
        print();


        word1 = new EightBitWord(true,false,false,false,false,false,false,false);
        word2 = new EightBitWord(true,false,false,false,false,false,false,false);
        setWordA(word1);
        setWordB(word2);
        executeOpCode(OpCodes.ADD);
        print();

        word1 = new EightBitWord(true,false,false,false,false,false,false,true);
        word2 = new EightBitWord(true,false,false,false,false,false,false,false);
        setWordA(word1);
        setWordB(word2);
        executeOpCode(OpCodes.SUB);
        print();
        word1 = new EightBitWord(false,true,false,false,false,false,false,false);
        word2 = new EightBitWord(true,false,false,false,false,false,false,false);
        setWordA(word1);
        setWordB(word2);
        executeOpCode(OpCodes.SUB);
        print();

        word1 = new EightBitWord(true,false,false,false,false,true,true,true);
        word2 = new EightBitWord(true,false,false,false,false,false,false,true);
        setWordA(word1);
        setWordB(word2);
        executeOpCode(OpCodes.ADD);
        print();

        word1 = new EightBitWord(true,false,false,false,false,true,true,true);
        word2 = new EightBitWord(true,false,false,false,false,false,false,true);
        setWordA(word1);
        setWordB(word2);
        executeOpCode(OpCodes.SUB);
        print();

        word1 = new EightBitWord(true,false,false,false,false,true,true,true);
        word2 = new EightBitWord(true,false,false,false,false,false,false,true);
        setWordA(word1);
        setWordB(word2);
        executeOpCode(OpCodes.AND);
        print();

        word1 = new EightBitWord(true,false,true,false,false,true,true,true);
        word2 = new EightBitWord(true,false,false,false,false,false,false,true);
        setWordA(word1);
        setWordB(word2);
        executeOpCode(OpCodes.OR);
        print();

        word1 = new EightBitWord(true,false,true,false,false,true,true,true);
        word2 = new EightBitWord(true,false,false,false,true,false,false,true);
        setWordA(word1);
        setWordB(word2);
        executeOpCode(OpCodes.XOR);
        print();

        word1 = new EightBitWord(true,false,false,false,false,true,true,true);
        word2 = new EightBitWord(true,false,false,false,true,false,false,true);
        setWordA(word1);
        setWordB(word2);
        executeOpCode(OpCodes.CLR);
        print();


        word1 = new EightBitWord(true,false,false,false,false,true,true,true);
        word2 = new EightBitWord(true,false,false,false,true,true,false,true);
        setWordA(word1);
        setWordB(word2);
        executeOpCode(OpCodes.SET);
        print();

        word1 = new EightBitWord(false,false,false,false,false,true,true,true);
        word2 = new EightBitWord(true,false,false,false,true,true,false,true);
        setWordA(word1);
        setWordB(word2);
        executeOpCode(OpCodes.NEG);
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
    private void transferWords()
    {
        transferWordA();
        transferWordB();
    }

    public void executeOpCode(OpCodes opCode)
    {
        this.lastOpCode = opCode;
        String opCodeString= opCode.getCode();
        boolean [] temp;
        switch (opCodeString)
        {
            case "000"://s=a and b
                andGate.setInputs(wordA,wordB);
                andGate.execute();
                wordOutput = andGate.getWordOutput();
                break;
            case "001":// s = A or B
                orGate.setInputs(wordA,wordB);
                orGate.execute();
                wordOutput = orGate.getWordOutput();
                break;
            case "010":// A xor B
                xOrGate.setInputs(wordA, wordB);
                xOrGate.execute();
                wordOutput = xOrGate.getWordOutput();
                break;
            case "011":// a + b
                execute(false);
                break;
            case "100":// a-b
                execute(true);
                break;
            case "101":// all registers set to 0
                temp = setRegisterTo(false);
                EightBitWord  allZeros= new EightBitWord(temp);
                this.wordA = allZeros;
                this.wordB = allZeros;
                this.wordOutput = allZeros;
                break;
            case "110":// all registers set to 1

                temp = setRegisterTo(true);
                EightBitWord  allOnes= new EightBitWord(temp);
               this.wordA = allOnes;
                this.wordB = allOnes;
                this.wordOutput = allOnes;
                break;
            case "111":// negate all registers
                wordA.negate();
                wordB.negate();
                wordOutput.negate();
                break;
        }
    }

    //provides a arraqy of  '0' bits to be changed into a 8-bit word or all 0's
    private boolean[] setRegisterTo(boolean value)
    {
        boolean[] result = new boolean[8];

        for (int i = 0; i < result.length; i++)
        {
            result[i] = value;
        }

        return result;
    }

    public FourBitALU getAlu2() {
        return alu2;
    }

    public EightBitWord getWordOutput() {
        return wordOutput;
    }
}
