public class tablero {

    private Casilla[][] casillas;

    public tablero() {
        this.casillas = new Casilla[8][8];
        inicializar();
    }

    private void inicializar() {
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                casillas[fila][col] = new Casilla(fila, col);
            }
        }
    }

    public void vaciar() {
        inicializar();
    }

    public void colocarPieza(Pieza pieza, int fila, int col) {
        casillas[fila][col].setPieza(pieza);
    }

    public Pieza obtenerPieza(int fila, int col) {
        return casillas[fila][col].getPieza();
    }

    public Casilla[][] getCasillas() {
        return casillas;
    }
}
