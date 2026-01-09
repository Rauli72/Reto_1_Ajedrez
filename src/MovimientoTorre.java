public class MovimientoTorre {
    public static boolean esValido(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);
        // Movimiento en línea recta
        return filaDiferencia == 0 || columnaDiferencia == 0;
    }
}
