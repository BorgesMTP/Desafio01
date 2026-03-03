import java.time.temporal.ValueRange;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Desafio01 {
    public static void mainMenu(){
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (true) {
            System.out.println("""
                    +------------------------------------+
                    | Selecione a opção desejada:        |
                    |                                    |
                    | 1 - Consultar Saldo                |
                    | 2 - Receber Valor                  |
                    | 3 - Transferir Valor               |
                    | 4 - Sair                           |
                    +------------------------------------+
                    Digite a opção desejada:""");

            try {
                option = scanner.nextInt();

                if (option < 1 || option > 4){
                    System.out.println("""
                        
                        +------------------------------------+
                        | O valor inserido não é válido,     |
                        | tente novamente.                   |
                        +------------------------------------+
                        """);
                    mainMenu();
                }

            } catch (InputMismatchException e) {

                System.out.println("""
                        
                        +------------------------------------+
                        | O valor inserido não é válido,     |
                        | tente novamente.                   |
                        +------------------------------------+
                        """);
                mainMenu();

            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        mainMenu();

    }
}