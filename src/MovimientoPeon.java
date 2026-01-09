public class MovimientoPeon {
    public static boolean esValido(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);
        return (filaFin <= 8 && columnaFin <= 8)
                && filaInicio + 1 == filaFin
                // Trackear color para los movimientos del esValido
                || (((filaInicio == 2 /*&& Pieza.getColor.equalsIgnoreCase("BLANCAS")*/)
                || (filaInicio == 7 /*&& Pieza.getColor.equalsIgnoreCase("NEGRAS")"*/))
                && filaInicio + 2 == filaFin)
                && !(filaDiferencia == 0 && columnaDiferencia == 0);
    }
}
