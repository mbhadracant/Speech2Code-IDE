import edu.cmu.sphinx.linguist.g2p.G2PConverter;
import edu.cmu.sphinx.linguist.g2p.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by mb on 17/04/2017.
 */
public class SpeechPlayground {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        G2PConverter converter = new G2PConverter(ClassLoader.getSystemClassLoader().
                getResource("sphinx/g2p_model/model.fst.ser"));

        String line = null;

        while((line = s.nextLine()) != null) {
            ArrayList<Path> path = converter.phoneticize(line, 1);
            System.out.println(line + " " + path.get(0).toString().split("\\t")[1]);

        }
    }
}
