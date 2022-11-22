import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Usuario implements Serializable {
    String apelido;
    double salario;
    PlanoDeContas[] planos;
    public Usuario(String apelido, double salario) {
        this.apelido = apelido;
        this.salario = salario;
        PlanoDeContas inicial = new PlanoDeContas("Balanceio", 100);
        PlanoDeContas[] array = {inicial};
        this.planos = array; 

        
    }
    public String getApelido() {
        return apelido;
    }
    public double getSalario() {
        return salario;
    }
    public PlanoDeContas[] getPlanos() {
        return planos;
    }
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public void setPlanos(PlanoDeContas[] planos) {
        this.planos = planos;
    }

    public void addPlano(String nome, double porcent) {

        PlanoDeContas newPlano = new PlanoDeContas(nome, porcent);
        
        PlanoDeContas[] oldPlanos = this.getPlanos();

        PlanoDeContas[] tempPlanos = new PlanoDeContas[(oldPlanos.length + 1)];
        
        for (int i = 0; i<= (oldPlanos.length - 1); i++){
            tempPlanos[i] = oldPlanos[i];
        }
        tempPlanos [oldPlanos.length] = newPlano;

        this.setPlanos(tempPlanos);
        

        
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
