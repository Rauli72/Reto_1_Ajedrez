public class MovimientoAlfil {
    public static boolean esValido(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);

        return (filaFin <= 8 && columnaFin <= 8)
                && (Math.abs(filaInicio - filaFin) == Math.abs(columnaInicio - columnaFin))
                && !(filaDiferencia == 0 && columnaDiferencia == 0);
    }
}
