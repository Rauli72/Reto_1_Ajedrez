public class MovimientoRey {
    public static boolean esValido(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);
        return (filaFin <= 8 && columnaFin <= 8)
                && filaDiferencia <= 1 || columnaDiferencia <= 1
                && !(filaDiferencia == 0 && columnaDiferencia == 0);
    }
}
