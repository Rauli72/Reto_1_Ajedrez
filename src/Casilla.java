public class Casilla {
    private int fila;
    private int columna;
    private Pieza pieza;

    public Casilla(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
    }

    public int getColumna() {return columna;}
    public int getFila() {return fila;}

    public Pieza getPieza() {
        return pieza;
    }
    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }
}
