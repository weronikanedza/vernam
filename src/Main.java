import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String text = null;
        try {
            text = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Vernam vernam = new Vernam();
        vernam.encode(text);
        vernam.decode();

//        String text2 = new String(new BigInteger(binary, 2).toByteArray());
//        System.out.println("As text: "+text2);
//
//        BlumBlum blum = new BlumBlum(10);
//        BitSet key = blum.generateKey();
//
//        StringBuilder s = new StringBuilder();
//        IntStream.range(0, key.length()).forEach(i -> s.append(key.get(i) == true ? 1 : 0));
//
//        System.out.println(s);
    }
}
