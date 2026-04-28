import java.util.ArrayList;
import java.util.Scanner;

public class Conta {
    private final String titular;
    private double saldo;
    private final String tipo;
    private final int numero;
    public static ArrayList<Conta> contas = new ArrayList<>();

    public Conta(String titular, String tipo, int numero){
        this.titular = titular;
        this.saldo = 0;
        this.tipo = tipo;
        this.numero = numero;
    }

    public static void criarConta(ArrayList<Conta> contas, Scanner input){
        System.out.println("|---------| CRIAÇÃO DE CONTA |---------|");
        System.out.println("Digite o nome do titular da conta: ");
        String nome = input.nextLine();
        System.out.println("Digite o tipo da conta: ");
        String tipo = input.nextLine();
        int numero = contas.size() + 1;
        Conta conta = new Conta(nome, tipo, numero);
        contas.add(conta);
        System.out.println("Conta criada com sucesso! Número da conta: " + numero);
        Menu.exibirMenuPrincipal();
    }

    public static void listarContas(ArrayList<Conta> contas, Scanner scanner) {
        System.out.println("\n|---------| LISTA DE CONTAS |---------|");
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta cadastrada no sistema.");
        } else {
            System.out.printf("%-10s | %-20s | %-15s | %-10s%n", "NÚMERO", "TITULAR", "TIPO", "SALDO");
            System.out.println("------------------------------------------------------------------");
            for (Conta conta : contas) {
                System.out.printf("%-10d | %-20s | %-15s | R$ %.2f%n",
                        conta.numero, conta.titular, conta.tipo, conta.saldo);
            }
        }
        System.out.println("------------------------------------------------------------------");
        Menu.voltarAoMenu(scanner);
    }

    public static Conta buscarConta(ArrayList<Conta> contas, Scanner input) {
        System.out.println("Insira o número da conta:");
        int numero = input.nextInt();
        input.nextLine();

        for(Conta conta : contas) {
            if (conta.numero == numero) {
                System.out.println("        -- Conta " + numero + " encontrada! --");
                return conta;
            }
        }
        System.out.println("        -- Conta " + numero + " não encontrada! --");
        return null;
    }

    public static void consultarSaldo(ArrayList<Conta> contas, Scanner scanner) {
        Conta conta = buscarConta(contas, scanner);
        if (conta == null) {
            Menu.voltarAoMenu(scanner);
            return;
        }

        System.out.printf("""
                +------------------------------------+
                    Nome:   %s
                    Conta:  %d
                    Tipo:   %s
                    Saldo:  R$ %.2f
                +------------------------------------+%n""", conta.titular, conta.numero, conta.tipo, conta.saldo);
        Menu.voltarAoMenu(scanner);
    }

    public static void depositarValor(ArrayList<Conta> contas, Scanner scanner) {
        Conta conta = buscarConta(contas, scanner);
        if (conta == null) {
            Menu.voltarAoMenu(scanner);
            return;
        }

        System.out.println("""
                +------------------------------------------------+
                    Informe o valor a depositar.
                    ou pressione ENTER sem valor para voltar.
                +------------------------------------------------+""");
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.isEmpty()) {
                Menu.exibirMenuPrincipal();
                break;
            } else {
                try {
                    double valor = Double.parseDouble(entrada);
                    if (valor > 0) {
                        conta.saldo += valor;
                        System.out.printf("R$%.2f Depositado com sucesso!%n", valor);
                        Menu.voltarAoMenu(scanner);
                        break;
                    } else {
                        Menu.imprimirErro();
                    }
                } catch (NumberFormatException e) {
                    Menu.imprimirErro();
                }
            }
        }
    }

    public static void sacarValor(ArrayList<Conta> contas, Scanner scanner) {
        Conta conta = buscarConta(contas, scanner);
        if (conta == null) {
            Menu.voltarAoMenu(scanner);
            return;
        }

        System.out.println("""
                +------------------------------------------------+
                    Informe o valor a sacar.
                    ou pressione ENTER sem valor para voltar.
                +------------------------------------------------+""");
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.isEmpty()) {
                Menu.exibirMenuPrincipal();
                break;
            } else {
                try {
                    double valor = Double.parseDouble(entrada);
                    if (valor > 0 && valor <= conta.saldo) {
                        conta.saldo -= valor;
                        System.out.printf("R$%.2f sacado com sucesso!%n", valor);
                        Menu.voltarAoMenu(scanner);
                        break;
                    } else {
                        System.out.println("Saldo insuficiente ou valor inválido.");
                    }
                } catch (NumberFormatException e) {
                    Menu.imprimirErro();
                }
            }
        }
    }

    public static void transferirValor(ArrayList<Conta> contas, Scanner scanner){
        System.out.println("--- Dados da conta de ORIGEM ---");
        Conta origem = buscarConta(contas, scanner);
        if (origem == null) {
            Menu.voltarAoMenu(scanner);
            return;
        }

        System.out.println("--- Dados da conta de DESTINO ---");
        Conta destino = buscarConta(contas, scanner);
        if (destino == null) {
            Menu.voltarAoMenu(scanner);
            return;
        }

        if (origem == destino) {
            System.out.println("Erro: A conta de destino não pode ser a mesma de origem.");
            Menu.voltarAoMenu(scanner);
            return;
        }

        System.out.println("""
                +------------------------------------------------+
                    Informe o valor a transferir.
                    ou pressione ENTER sem valor para voltar.
                +------------------------------------------------+""");
        while (true) {
            String entrada = scanner.nextLine();
            if (entrada.isEmpty()){
                Menu.exibirMenuPrincipal();
                break;
            } else {
                try {
                    double valor = Double.parseDouble(entrada);
                    if (valor > 0 && valor <= origem.saldo) {
                        origem.saldo -= valor;
                        destino.saldo += valor;
                        System.out.printf("R$%.2f transferido com sucesso para %s!%n", valor, destino.titular);
                        Menu.voltarAoMenu(scanner);
                        break;
                    } else {
                        System.out.println("Saldo insuficiente na conta de origem.");
                        Menu.imprimirErro();
                    }
                } catch (NumberFormatException e) {
                    Menu.imprimirErro();
                }
            }
        }
    }
}