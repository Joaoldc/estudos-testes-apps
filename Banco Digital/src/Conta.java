public abstract class Conta implements Iconta {

    protected int agencia;
    protected int numero;
    protected double saldo;

    @Override
    public void sacar(double valor) {
        saldo = saldo - valor;

    }

    @Override
    public void depositar(double valor) {
        saldo += valor;

    }



    public <contaDestino> void transferir(double valor, contaDestino) {
        this.sacar(valor);
        contaDestino.depositar(valor);


    }

    public int getAgencia() {
        return agencia;


    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Agencia: %d, this.agencia"));
        System.out.println(String.format("Numero: %d, this.numero"));
        System.out.println(String.format("Saldo: %.2f, this.saldo"));
    }

    public void imprimirExtrato() {

    }
}
