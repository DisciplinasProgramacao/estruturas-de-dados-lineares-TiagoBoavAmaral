public class Celula<E> {
    private E elemento;
    private Celula<E> proxima;

    public Celula() {
        this.elemento = null;
        this.proxima = null;
    }

    public Celula(E elemento) {
        this.elemento = elemento;
        this.proxima = null;
    }

    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public Celula<E> getProxima() {
        return proxima;
    }

    public void setProxima(Celula<E> proxima) {
        this.proxima = proxima;
    }
}
