import java.math.BigInteger;
import java.util.BitSet;
import java.util.stream.IntStream;

public class Vernam {

    private BitSet key;
    private BitSet bits;

    public void encode(String text) {
        String binaryText = new BigInteger(text.getBytes()).toString(2);
        int textLength = binaryText.length();
        bits = new BitSet(textLength);

        IntStream.range(0, textLength).forEach(i -> bits.set(i, binaryText.charAt(i) == '0' ? false : true));

        BlumBlum blum = new BlumBlum(textLength);
        key = blum.generateKey();

        bits.xor(key);

        System.out.println("TEXT BITS    : " + binaryText);
        displayBits("KEY TEXT    ", key);
        displayBits("ENCODED TEXT", bits);
    }

    private void displayBits(String text, BitSet set) {
        StringBuilder s = new StringBuilder();
        IntStream.range(0, set.length()).forEach(i -> s.append(set.get(i) == true ? 1 : 0));
        System.out.println(text + " : " + s);
    }

    public void decode() {
        bits.xor(key);
        displayBits("DECODED TEXT", bits);
        StringBuilder s = new StringBuilder();
        IntStream.range(0, bits.length()).forEach(i -> s.append(bits.get(i) == true ? 1 : 0));
        System.out.println("As text: " + new String(new BigInteger(s.toString(), 2).toByteArray()));
    }
}
