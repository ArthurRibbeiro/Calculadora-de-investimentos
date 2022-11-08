import java.io.Serializable;

public class Usuario implements Serializable {
    String apelido;
    double salario;
    PlanoDeContas[] planos;
    public Usuario(String apelido, double salario) {
        this.apelido = apelido;
        this.salario = salario;
        PlanoDeContas inicial = new PlanoDeContas("Salário", 100);
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

    public PlanoDeContas[] addPlano(PlanoDeContas newPlano) {
        
        PlanoDeContas[] oldPlanos = this.getPlanos();

        PlanoDeContas[] tempPlanos = new PlanoDeContas[(oldPlanos.length + 1)];
        
        for (int i = 0; i<= (oldPlanos.length - 1); i++){
            tempPlanos[i] = oldPlanos[i];
        }
        tempPlanos [oldPlanos.length] = newPlano;

        return tempPlanos;    
    }
    
    
}
