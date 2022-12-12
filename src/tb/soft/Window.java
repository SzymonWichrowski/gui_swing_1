package tb.soft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Map;

public class Window extends JFrame implements ActionListener {

    private JButton logIn_button, cancel_button;        //implementacja przycisków
    private JLabel login_label, password_label, text_label;   //implementacja etykiet
    private JTextField login_textField;     //implementacja pól tekstowych
    private JPasswordField password_pField;

    int attempt = 0;
    String typed_login;
    char[] typed_password;

    static Map<String, char[]> accounts;

    static {
        try {
            accounts = Account.readFromFile("plik.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Window(){    //konstruktor klasy Window

        setSize(500, 300); //rozmiary okienka
        setTitle("Login System");
        setLayout(null);
        getContentPane().setBackground(new Color(54, 59, 59, 255));   //ustawianie koloru tła

        logIn_button = new JButton("Log In");  //implementacja przycisku logowania
        logIn_button.setBounds(100, 200, 100, 30);   //położenie i wielkość przycisku
        logIn_button.setBackground(Color.gray);
        add(logIn_button);      //dodawanie przycisku logowania do okienka
        logIn_button.addActionListener(this);   //słuchaczem akcji jest Window

        cancel_button = new JButton("Cancel");  //implementacja przycisku anulowania
        cancel_button.setBounds(300, 200, 100, 30);
        cancel_button.setBackground(Color.gray);
        add(cancel_button);     //dodawanie przycisku anulowania do okienka
        cancel_button.addActionListener(this);

        login_label = new JLabel("login: ");        //implementacja etykiety loginu
        login_label.setBounds(125, 75, 100, 30);
        login_label.setForeground(Color.GRAY);
        add(login_label);

        login_textField = new JTextField("");           //implementacja pola tekstowego do wpisywania loginu
        login_textField.setBounds(200, 75, 150, 30);
        login_textField.setBackground(Color.white);
        add(login_textField);
        login_textField.addActionListener(this);

        password_label = new JLabel("password: ");      //implementacja etykiety hasła
        password_label.setBounds(125, 125, 100, 30);
        password_label.setForeground(Color.GRAY);
        add(password_label);

        password_pField = new JPasswordField("");   //implementacja pola do wpisywania hasła
        password_pField.setBounds(200, 125, 150, 30);
        password_pField.setEchoChar('*');
        password_pField.setBackground(Color.white);
        add(password_pField);
        password_pField.addActionListener(this);

        text_label = new JLabel("LOGIN SYSTEM");
        text_label.setBounds(175, 25, 170, 30);
        text_label.setFont(new Font("SegoePrint", Font.BOLD, 22));
        text_label.setForeground(Color.orange);
        add(text_label);
    }

    public void tooManyAttempts(int attempt) {  //warunek dotyczący prób wpisywania hasła
        if (attempt == 3) {
            JOptionPane.showMessageDialog(null, "Przekroczono limit prób!",  "UWAGA", JOptionPane.ERROR_MESSAGE);
            dispose();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();      //przypisujemy obiektowi źródło zdarzenia

        if (source == cancel_button) {      //działanie przycisku cancel
            login_textField.setText(null);
            password_pField.setText(null);
            login_textField.setBackground(Color.white);
            password_pField.setBackground(Color.white);
        }

        if (source == login_textField) {        //działanie pola tekstowego loginu
            typed_login = login_textField.getText();
            login_textField.setBackground(Color.pink);
        }

        if (source == password_pField) {        //działanie pola do wpisania hasła
            typed_password = password_pField.getPassword();
            password_pField.setBackground(Color.pink);
        }

        if (source == logIn_button) {       //działanie przycisku log in

            if (typed_login != null && accounts.containsKey(typed_login)) {   //każde konto musi mieć przypisany login
                if(String.copyValueOf(accounts.get(typed_login)).equals(String.copyValueOf(typed_password))) {       //jeśli hasło jest poprawne
                    login_textField.setBackground(Color.green);
                    password_pField.setBackground(Color.green);
                    attempt = 0;        //zerujemy próby
                    JOptionPane.showMessageDialog(null, "Poprawne hasło!", null, JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    login_textField.setBackground(Color.red);
                    password_pField.setBackground(Color.red);
                    attempt++;
                }
            }
            else JOptionPane.showMessageDialog(null,
                    "Błąd przy wpisywaniu loginu. Proszę spróbować ponownie.",
                             "ERROR", JOptionPane.ERROR_MESSAGE);


            tooManyAttempts(attempt); //sprawdzamy czy nie został przekroczony limit osób
        }
    }
}
