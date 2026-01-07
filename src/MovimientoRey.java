public class MovimientoRey {
    public static boolean esValido(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaInicio - filaFin);
        int columnaDiferencia = Math.abs(columnaInicio - columnaFin);
        return (filaFin <= 8 && columnaFin <= 8)
                && filaDiferencia <= 1 || columnaDiferencia <= 1;
    }
}
