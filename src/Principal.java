import com.alura.challenge.conversor.Cambio;
import com.alura.challenge.conversor.Consulta;

public class Principal {
    public static void main(String[] args) {
        Consulta consulta = new Consulta("CLP", "USD");
        Cambio cambio = consulta.realizarConsulta();
        System.out.println(cambio);
    }
}
