public class Movimiento {
    public static boolean caballo(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);
        // Movimiento en L
        return (filaDiferencia == 2 && columnaDiferencia == 1) || (filaDiferencia == 1 && columnaDiferencia == 2);
    }

    public static boolean peon(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);
        return (filaFin <= 8 && columnaFin <= 8)
                && filaInicio + 1 == filaFin
                // Trackear color para los movimientos del peon
                || (((filaInicio == 2 /*&& Pieza.getColor.equalsIgnoreCase("BLANCAS")*/)
                || (filaInicio == 7 /*&& Pieza.getColor.equalsIgnoreCase("NEGRAS")"*/))
                && filaInicio + 2 == filaFin)
                && !(filaDiferencia == 0 && columnaDiferencia == 0);
    }

    public static boolean reina(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);
        // Torre + Alfil
        boolean linea = filaDiferencia == 0 || columnaDiferencia == 0;
        boolean diagonal = filaDiferencia == columnaDiferencia;
        return linea || diagonal
                && (filaFin <= 8 && columnaFin <= 8);
    }

    public static boolean rey(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);
        return (filaFin <= 8 && columnaFin <= 8)
                && filaDiferencia <= 1 || columnaDiferencia <= 1
                && !(filaDiferencia == 0 && columnaDiferencia == 0);
    }

    public static boolean torre(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);
        // Movimiento en línea recta
        return filaDiferencia == 0 || columnaDiferencia == 0;
    }

    public static boolean alfil(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        int filaDiferencia = Math.abs(filaFin - filaInicio);
        int columnaDiferencia = Math.abs(columnaFin - columnaInicio);

        return (filaFin <= 8 && columnaFin <= 8)
                && (Math.abs(filaInicio - filaFin) == Math.abs(columnaInicio - columnaFin))
                && !(filaDiferencia == 0 && columnaDiferencia == 0);
    }
}
