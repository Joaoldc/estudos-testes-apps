


public class Main {

    public static void main(String[] args) {
        //Calculadora
        System.out.println("Exercício calculadora");
        Calculadora.soma(3, 6);
        Calculadora.subtracao(9, 1.8);
        Calculadora.multiplicacao(7, 8);
        Calculadora.divisao(5, 2.5);

        //Mensagem
        System.out.println("Exercicio Mensagem");
        Mensagem.obterMensagem(hora: 9);
        Mensagem.obterMensagem(hora: 14);
        Mensagem.obterMensagem(hora: 1);
        //Emprestino

        System.out.println("Exercico emprestimo");
        Emprestimo.calcular(valor: 1000, Emprestimo.getDuasParcelas());
        Emprestimo.calcular(valor: 1000, Emprestimo.getTresParcelas());
        Emprestimo.calcular(valor: 1000, parcelas:5);

    }
}