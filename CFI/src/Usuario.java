import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Usuario implements Serializable {
    String apelido;
    double salario;
    ArrayList<PlanoDeContas> planos = new ArrayList<PlanoDeContas>();
    String[] templates = {"Necessidades", "55", "Educação", "10", "Lazer", "10", "Invest. Curto/Med. Prazo", "10", "Invest. Longo Prazo", "15"};
    
    
    //PlanoDeContas[] planos;
    public Usuario(String apelido, double salario, String[] preferencias) {
        this.apelido = apelido;
        this.salario = salario;
        this.planos.add(new PlanoDeContas("Balanceio", 100));

        

        for (int i = 0; i < preferencias.length; i++){
            if (preferencias[i].equals("S"))
                this.planos.add(new PlanoDeContas(templates[i*2], Integer.parseInt(templates[(i*2)+1])));         

        }

    }
    public String getApelido() {
        return apelido;
    }
    public double getSalario() {
        return salario;
    }
    public ArrayList<PlanoDeContas> getPlanos() {
        return planos;
    }
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public void setPlanos(ArrayList<PlanoDeContas> planos) {
        this.planos = planos;
    }

    public void addPlano(String nome, double porcent) {

        this.planos.add(new PlanoDeContas(nome, porcent));

    }

    public void salvar(){
        Scanner scan = new Scanner(System.in);
        try{
            FileOutputStream arq = new FileOutputStream("usuario.arq");
            ObjectOutputStream obj = new ObjectOutputStream(arq);
            obj.writeObject(this);
            obj.close();
            System.out.println("Gravado com sucesso!");
            System.out.println("Pressione Enter para continuar");
            scan.nextLine();
            App.limpar();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            scan.nextLine();
        }
        //scan.close();
    }

    public static Usuario ler(){
        try{
            FileInputStream arq = new FileInputStream("usuario.arq");
            ObjectInputStream obj = new ObjectInputStream(arq);
            Usuario user = (Usuario)obj.readObject();    
            obj.close();
            return user;                 
            
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    
}
