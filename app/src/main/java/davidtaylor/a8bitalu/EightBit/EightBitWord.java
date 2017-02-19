package davidtaylor.a8bitalu.EightBit;

import davidtaylor.a8bitalu.logicGates.FourBitWord;

/**
 * Created by Nova on 2/7/2017.
 */
public class EightBitWord
{
    private boolean[] word; // least significant bit is index 0
    private int[] binaryRepresentation;
    private String binString;
    private final int WORDLENGTH = 8;


    public EightBitWord(boolean... word)
    {
        if(word.length != WORDLENGTH)
        {
            System.out.println("This is not  8-bits ");
            return;
        }
        this.word = word;
        findBinRepresentation();
    }
    //make an 8-bit word with by combining two 4-bit words
    public EightBitWord(FourBitWord wordA, FourBitWord wordB)
    {
        word = new boolean[WORDLENGTH];

        word[0] = wordA.getA();
        word[1] = wordA.getB();
        word[2] = wordA.getC();
        word[3] = wordA.getD();
        word[4] = wordB.getA();
        word[5] = wordB.getB();
        word[6] = wordB.getC();
        word[7] = wordB.getD();
        findBinRepresentation();

    }
    //finds the array and string fot the binary representation
    public void findBinRepresentation()
    {
        binaryRepresentation = new int[WORDLENGTH];
        binString = "";
        for (int i = 7; i >=0; i--)
        {
            if (word[i])
                binaryRepresentation[i] = 1;
            else
                binaryRepresentation[i] = 0;

            binString += binaryRepresentation[i];
        }
    }

    public int[] getBinaryRepresentation()
    {
        return binaryRepresentation;
    }
    public String getBinString()
    {
        return binString;
    }

    @Override
    public String toString()
    {
        String result = "";
        for (int i = 7; i  >= 0 ; i--)
        {
            if(i != 0)
                result += word[i] + ", ";

            else
                result +=word[i];
        }
        return result;
    }
    // spit an 8-bit word into two 4 bit words
    public FourBitWord[] splitWord()
    {
        FourBitWord[] result = new FourBitWord[2];
        boolean[] temp = new boolean[4];
        temp[0]= word[0];
        temp[1]= word[1];
        temp[2]= word[2];
        temp[3]= word[3];
        result[0] = new FourBitWord(temp);

        temp[0]= word[4];
        temp[1]= word[5];
        temp[2]= word[6];
        temp[3]= word[7];
        result[1] = new FourBitWord(temp);
        return result;
    }
    //flips every bit in the word
    public void negate()
    {
        for (int i = 0; i < word.length; i++)
        {
            word[i] = !word[i];
        }

        findBinRepresentation();
    }

    public boolean[] getWord() {
        return word;
    }
}
