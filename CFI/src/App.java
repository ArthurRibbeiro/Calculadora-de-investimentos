import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String opcao;
        Scanner scan = new Scanner(System.in);
        do{
            limpar();
            System.out.println("""
            Selecione uma opção
            1 - Incluir novo plano de contas
            2 - Visualizar
            3 - Excluir plano
            0 - Sair""");
            opcao = scan.nextLine();
        
        }while (!opcao.equals("0"));
    }

    public static void limpar(String... arg) throws IOException, InterruptedException {
        try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }catch(Exception e) {
        System.out.println(e);
    }
    }
}
