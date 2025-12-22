public class MovimientoAlfil {
    public static boolean esValido(int filaInicio, int columnaInicio, int filaFin, int columnaFin) {
        return (filaFin <= 8 && columnaFin <= 8)
                && (Math.abs(filaInicio - filaFin) == Math.abs(columnaInicio - columnaFin));
    }
}
