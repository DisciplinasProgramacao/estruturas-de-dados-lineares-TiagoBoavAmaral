import java.util.function.Function;
import java.util.function.Predicate;

public class Lista<T> {

    private Celula<T> primeiro;
    private Celula<T> ultimo;
    private int tamanho;

    /** Cria uma lista vazia com a célula sentinela */
    public Lista() {
        Celula<T> sentinela = new Celula<>();
        this.primeiro = this.ultimo = sentinela;
        this.tamanho = 0;
    }

    /** Indica se a lista está vazia */
    public boolean vazia() {
        return (this.primeiro == this.ultimo);
    }

    /** Insere um novo elemento na posição indicada */
    public void inserir(T novo, int posicao) {
        if (posicao < 0 || posicao > this.tamanho) {
            throw new IndexOutOfBoundsException(
                "Não foi possível inserir o item na lista: posição inválida!"
            );
        }

        Celula<T> anterior = this.primeiro;
        for (int i = 0; i < posicao; i++) {
            anterior = anterior.getProxima();
        }

        Celula<T> novaCelula = new Celula<>(novo);
        Celula<T> proximaCelula = anterior.getProxima();

        anterior.setProxima(novaCelula);
        novaCelula.setProxima(proximaCelula);

        if (posicao == this.tamanho) {
            this.ultimo = novaCelula;
        }

        this.tamanho++;
    }

    /** Remove o elemento da posição indicada */
    public T remover(int posicao) {
        if (vazia()) {
            throw new IllegalStateException(
                "Não foi possível remover o item da lista: a lista está vazia!"
            );
        }

        if (posicao < 0 || posicao >= this.tamanho) {
            throw new IndexOutOfBoundsException(
                "Não foi possível remover o item da lista: posição inválida!"
            );
        }

        Celula<T> anterior = this.primeiro;
        for (int i = 0; i < posicao; i++) {
            anterior = anterior.getProxima();
        }

        Celula<T> celulaRemovida = anterior.getProxima();
        Celula<T> proximaCelula = celulaRemovida.getProxima();

        anterior.setProxima(proximaCelula);
        celulaRemovida.setProxima(null);

        if (celulaRemovida == this.ultimo) {
            this.ultimo = anterior;
        }

        this.tamanho--;

        return celulaRemovida.getElemento();
    }

    /** Retorna o elemento da posição indicada (sem remover) */
    public T obterElemento(int posicao) {
        if (this.tamanho == 0) {
            throw new IllegalStateException("Lista vazia");
        }

        if (posicao < 0 || posicao >= this.tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }

        Celula<T> atual = primeiro.getProxima();
        for (int i = 0; i < posicao; i++) {
            atual = atual.getProxima();
        }

        return atual.getElemento();
    }

    /** Conta quantos elementos atendem a um predicado */
    public int contar(Predicate<T> condicional) {
        if (condicional == null) {
            throw new IllegalArgumentException("Condicional nulo");
        }

        int contador = 0;
        Celula<T> atual = primeiro.getProxima();

        while (atual != null) {
            if (condicional.test(atual.getElemento())) {
                contador++;
            }
            atual = atual.getProxima();
        }

        return contador;
    }

    /** Calcula a soma de um atributo dos elementos usando uma função extratora */
    public double obterSoma(Function<T, Double> extrator) {
        if (extrator == null) {
            throw new IllegalArgumentException("Extrator nulo");
        }

        double soma = 0.0;
        Celula<T> atual = primeiro.getProxima();

        while (atual != null) {
            Double val = extrator.apply(atual.getElemento());
            if (val != null) {
                soma += val;
            }
            atual = atual.getProxima();
        }

        return soma;
    }

    /** Retorna o tamanho da lista */
    public int tamanho() {
        return tamanho;
    }

    /** Retorna uma representação textual da lista */
    @Override
    public String toString() {
        if (vazia()) {
            return "A lista está vazia!";
        }

        StringBuilder listaString = new StringBuilder();
        Celula<T> aux = primeiro.getProxima();
        int contador = 0;

        while (aux != null) {
            listaString.append(
                String.format("Posição %d: %s%n", contador, aux.getElemento())
            );
            aux = aux.getProxima();
            contador++;
        }

        return listaString.toString();
    }
}
