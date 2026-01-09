public class MovimientoReina {
    public static boolean esValido(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);
        // Torre + Alfil
        boolean linea = filaDiferencia == 0 || columnaDiferencia == 0;
        boolean diagonal = filaDiferencia == columnaDiferencia;
        return linea || diagonal
                && (filaFin <= 8 && columnaFin <= 8);
    }
}

