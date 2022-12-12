package tb.soft;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Account {

    public static Map<String, char[]> readFromFile(Scanner scanner){

        Map<String, char[]> map = new HashMap<>();
        String line;
        while(scanner.hasNext()) {
            line = scanner.nextLine();
            String[] txt = line.split(" ");
            map.put(txt[0], txt[1].toCharArray());
        }
        return map;
    }

    public static Map<String, char[]> readFromFile(String file_name) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(file_name));
        return readFromFile(scanner);
    }

}
