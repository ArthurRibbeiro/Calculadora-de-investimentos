import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String opcao;
        Scanner scan = new Scanner(System.in);

        Usuario user = ler();
        if (user == null){
            System.out.println("nulo");
            // cria usuário
            System.out.println("Informe Seu nome:");
            String apelido = scan.nextLine();
            System.out.println("Informe o salário que será calculado (preferencialmente líquido):");
            double salario = scan.nextDouble();
            user = new Usuario(apelido, salario);

            //salva
        }
        scan.nextLine();


        do{
            limpar();
            System.out.println("""
            Selecione uma opção
            1 - Editar perfil
            2 - Incluir novo plano de contas
            3 - Editar plano de contas
            4 - Visualizar
            0 - Sair""");
            opcao = scan.nextLine();
            limpar(args);

            switch (opcao){
                case "1":
                    System.out.println("Opção 1 em desenvolvimento");                    
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
                case "2" :
                    System.out.println("Opção 2 em desenvolvimento");
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
                case "3":
                    System.out.println("Opção 3 em desenvolvimento");
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
                case "4":
                    System.out.println("Opção 3 em desenvolvimento");
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
                case "0":
                System.out.println("Saindo...");
                break;
                default:
                    System.out.println("Informe uma opção válida");
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
            }
        
        }while (!opcao.equals("0"));
    }

    public void salvar(Usuario user){
        try{
            FileOutputStream arq = new FileOutputStream("usuario.arq");
            ObjectOutputStream obj = new ObjectOutputStream(arq);
            obj.writeObject(user);
            obj.flush();
            System.out.println("Gravado com sucesso!");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public static Usuario ler(){
        try{
            FileInputStream arq = new FileInputStream("usuario.arq");
            ObjectInputStream obj = new ObjectInputStream(arq);
            Usuario user = (Usuario)obj.readObject();    
            return user;                 
            
        }
        catch(Exception e)
        {  
            System.out.println(e);
            return null;
        }
        
    }

    public static void limpar(String... arg) throws IOException, InterruptedException {
        try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }catch(Exception e) {
        System.out.println(e);
    }
    }
}
