public class MovimientoCaballo {
    public static boolean esValido(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);
        // Movimiento en L
        return (filaDiferencia == 2 && columnaDiferencia == 1) || (filaDiferencia == 1 && columnaDiferencia == 2);
    }
}
