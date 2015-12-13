package elisvaldocoelho.com.br.resultadoloteria;

/**
 * Created by elisvaldo on 12/12/15.
 */
public class Concurso {

    public String numero;
    public String cidade;
    public String local;
    public String valor_acumulado;
    public String numeros_sorteados;

    @Override
    public String toString() {
        return numero + ", " + cidade + ", " + local + ", " + valor_acumulado + ", " + numeros_sorteados;
    }
}
