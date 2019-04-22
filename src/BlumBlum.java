import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;

public class BlumBlum {

    private static final int BIT_LENGTH = 512;
    private BigInteger p, q, n, s;
    private int numberOfBits;

    public BlumBlum(int numberOfBits) {
        this.numberOfBits = numberOfBits;
    }

    public BitSet generateKey() {
        generatePrime();
        generateS();
        return createKey();
    }

    private BitSet createKey() {
        BitSet key = new BitSet(numberOfBits);
        BigInteger two = BigInteger.valueOf(2);
        BigInteger x = s.pow(2).mod(n);

        int k0 = x.mod(two).intValue();
        key.set(0, k0);

        for (int i = 1; i < numberOfBits; i++) {
            x = x.pow(2).mod(n);
            boolean val = x.mod(two).intValue() == 1 ? true : false;
            key.set(i, val);
        }
        return key;
    }

    private void generateS() {
        BigInteger limit = n.subtract(BigInteger.ONE);
        do {
            s = new BigInteger(limit.bitLength(), new Random());
        } while (!(s.compareTo(BigInteger.ZERO) > 0) || !checkNWD(s, n));
    }

    private boolean checkNWD(BigInteger s, BigInteger n) {
        BigInteger a = s;
        BigInteger b = n;
        while (a.compareTo(b) != 0) {
            if (a.compareTo(b) > 0) {
                a = a.subtract(b);
            } else {
                b = b.subtract(a);
            }
        }

        return a.compareTo(BigInteger.ONE) == 0;
    }

    private void generatePrime() {
        do {
            p = BigInteger.probablePrime(BIT_LENGTH, new Random());
            q = BigInteger.probablePrime(BIT_LENGTH, new Random());
        } while (checkPQ(p, q));

        n = p.multiply(q);
    }

    private boolean checkPQ(BigInteger p, BigInteger q) {
        BigInteger four = BigInteger.valueOf(4);
        BigInteger tree = BigInteger.valueOf(3);
        return !p.mod(four).equals(tree) || !q.mod(four).equals(tree) || p.equals(q);
    }


}
