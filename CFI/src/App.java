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
        String subopcao;
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
            salvar(user);
        }
        //scan.nextLine();


        do{
            limpar();
            System.out.println("""       
            1 - Editar perfil
            2 - Incluir novo plano de contas
            3 - Editar plano de contas
            4 - Visualizar
            0 - Sair
            Selecione uma opção
            """);
            opcao = scan.nextLine();
            limpar(args);

            switch (opcao){
                case "1":
                    do{
                        System.out.println("Opção 1 em desenvolvimento");
                        System.out.println("Apelido: " + user.getApelido());                    
                        System.out.println("Salário: R$" + user.getSalario());
                        
                        System.out.println("""
                                1 - Alterar apelido
                                2 - alterar salário
                                3 - Resetar programa (limpa todos os Dados)
                                0 - Voltar
                                Selecione uma opção
                                """);
                        
                        subopcao = scan.nextLine();

                        switch (subopcao){
                            case "1":
                            System.out.println("Informe o novo apelido: ");
                            user.setApelido(scan.nextLine());
                            salvar(user);
                            break;
                            
                            case "2":
                            System.out.println("Informe o novo salário: ");
                            //melhorar verificação de valor inteiro
                            user.setSalario(scan.nextDouble());
                            salvar(user);
                            break;

                            case "3":
                            System.out.println("Opção 3 em desenvolvimento");
                            System.out.println("pressione enter para Continuar");
                            scan.nextLine();
                            break;

                            case "0":
                                System.out.println("Voltando...");
                            break;

                            default:
                                System.out.println("Informe uma opção válida");
                                System.out.println("pressione enter para Continuar");
                                scan.nextLine();
                            break;
                        }
                    }while (!subopcao.equals("0"));

                //fim da opção 1
                break;
                case "2" :
                    System.out.println("Informe o nome do novo plano de contas: ");
                    String tempNome = scan.nextLine();

                    System.out.println("Informe a porcentagem do novo plano de contas: ");
                    Double tempPorc = scan.nextDouble();
                    
                    user.addPlano(tempNome, tempPorc);
                    salvar(user);

                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
                case "3":
                    System.out.println("Opção 3 em desenvolvimento");
                    System.out.println("pressione enter para Continuar");
                    scan.nextLine();
                break;
                case "4":

                    System.out.println("Nome: " + user.getApelido());
                    System.out.println("Salário: R$" + user.getSalario());
                    PlanoDeContas planos[] = user.getPlanos();

                    for (int i = 0; i < planos.length; i++){
                        System.out.println( String.format("""
                            ---------------------
                            %s          %.1f%%            R$%.2f
                                """, planos[i].getNome(), planos[i].getPorcent(), ((planos[i].getPorcent()/100) * user.salario)));
                    }


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

    public static void salvar(Usuario user){
        Scanner scan = new Scanner(System.in);
        try{
            FileOutputStream arq = new FileOutputStream("usuario.arq");
            ObjectOutputStream obj = new ObjectOutputStream(arq);
            obj.writeObject(user);
            obj.flush();
            System.out.println("Gravado com sucesso!");
            scan.nextLine();
        }
        catch(Exception e)
        {
            System.out.println(e);
            scan.nextLine();
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
