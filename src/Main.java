import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/aoc8_test.txt"));
        String line=br.readLine();
        ArrayList<String> t= new ArrayList<>();

        while(line!=null){
            line=br.readLine();
            t.add(line);
        }

    }
}