package tb.soft;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Account {

    private String login;
    private char[] password;

    public Account(String login, char[] password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public char[] getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

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
