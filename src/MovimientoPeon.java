public class MovimientoPeon {
    public static boolean esValido(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        return (filaFin <= 8 && columnaFin <= 8)
                && filaInicio + 1 == filaFin
                || ((filaInicio >= 2 && filaInicio <= 6) && filaInicio + 2 == filaFin);
    }
}
