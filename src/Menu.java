import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class MovimientoRegistro {
    int filaInicio, colInicio, filaFin, colFin;
    Pieza piezaMovida;
    Pieza piezaCapturada;
    String color;

    public MovimientoRegistro(int fi, int ci, int ff, int cf, Pieza movida, Pieza capturada, String color) {
        this.filaInicio = fi;
        this.colInicio = ci;
        this.filaFin = ff;
        this.colFin = cf;
        this.piezaMovida = movida;
        this.piezaCapturada = capturada;
        this.color = color;
    }
}

public class Menu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tablero tablero = new Tablero();

        List<MovimientoRegistro> historial = new ArrayList<>();

        System.out.println("========================================");
        System.out.println("        CONFIGURACIÓN DEL TABLERO");
        System.out.println("========================================\n");

        System.out.println("Introduzca la posición inicial de las piezas (notación algebraica):\n");
        System.out.println("Ejemplo:");
        System.out.println("Blancas: Rg1, Tf1, h2, g2, f2, d4, e4, a4, b3, c2, Ab2, Ta1");
        System.out.println("Negras: Rc8, Td8, a7, b7, c7, Ae6, d5, e5, f7, g6, Ag7, h7, Th8\n");

        String entradaBlancas = "", entradaNegras = "";
        boolean entradaCorrecta = false;

        // ===== Carga inicial de piezas =====
        while (!entradaCorrecta) {
            System.out.print("Blancas: ");
            entradaBlancas = sc.nextLine();

            System.out.print("Negras: ");
            entradaNegras = sc.nextLine();

            tablero = new Tablero(); // reiniciar tablero

            if (!Tablero.cargarLinea(tablero, entradaBlancas, "B")) {
                System.out.println("❌ Error en la entrada de las BLANCAS.\n");
                continue;
            }

            if (!Tablero.cargarLinea(tablero, entradaNegras, "N")) {
                System.out.println("❌ Error en la entrada de las NEGRAS.\n");
                continue;
            }

            if (!Tablero.esPiezaValida()) {
                System.out.println("❌ Error: hay piezas en la misma casilla.\n");
                continue;
            }

            boolean jaqueBlancas = Jaque.reyEnJaque(tablero, "B");
            boolean jaqueNegras = Jaque.reyEnJaque(tablero, "N");

            if (jaqueBlancas && jaqueNegras) {
                System.out.println("❌ Posición ilegal: ambos reyes están en jaque.\n");
                continue;
            }

            entradaCorrecta = true;

            System.out.println("\n========================================");
            System.out.println("            TABLERO FINAL");
            System.out.println("========================================\n");
            tablero.dibujar();

            if (jaqueBlancas) System.out.println("\n⚠️  Las BLANCAS están en jaque.");
            else if (jaqueNegras) System.out.println("\n⚠️  Las NEGRAS están en jaque.");
            else System.out.println("\n✔️  Ningún rey está en jaque.");
        }

        // ===== Juego con menú y turnos =====
        String turno = "B"; // empieza Blancas
        boolean menuActivo = true;

        while (menuActivo) {
            System.out.println("\n===== MENÚ =====");
            System.out.println("Turno actual: " + (turno.equals("B") ? "Blancas" : "Negras"));
            System.out.println("1. Mostrar tablero");
            System.out.println("2. Comprobar jaque");
            System.out.println("3. Mover pieza (ej: e2 e4)");
            System.out.println("4. Reiniciar partida");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    tablero.dibujar();
                    break;

                case "2":
                    System.out.println("Blancas en jaque: " + Jaque.reyEnJaque(tablero, "B"));
                    System.out.println("Negras en jaque: " + Jaque.reyEnJaque(tablero, "N"));
                    break;

                case "3":
                    System.out.print("Ingrese movimiento (ej: e2 e4): ");
                    String movimiento = sc.nextLine();

                    // Procesar el movimiento
                    if (procesarMovimiento(movimiento, tablero, turno)) {
                        // Mover la pieza físicamente en el tablero
                        String[] partes = movimiento.split(" ");
                        int colInicio = Character.toUpperCase(partes[0].charAt(0)) - 'A';
                        int filaInicio = 8 - Character.getNumericValue(partes[0].charAt(1));
                        int colFin = Character.toUpperCase(partes[1].charAt(0)) - 'A';
                        int filaFin = 8 - Character.getNumericValue(partes[1].charAt(1));

                        Pieza pieza = tablero.getCasilla(filaInicio, colInicio).getPieza();

                        // Movimiento definitivo
                        tablero.getCasilla(filaFin, colFin).setPieza(pieza);
                        tablero.getCasilla(filaInicio, colInicio).setPieza(null);

                        // Mostrar el tablero después del movimiento
                        tablero.dibujar();

                        // Cambiar turno
                        turno = turno.equals("B") ? "N" : "B";

                        // Mostrar si hay jaque después del movimiento
                        boolean jaqueBlancas = Jaque.reyEnJaque(tablero, "B");
                        boolean jaqueNegras = Jaque.reyEnJaque(tablero, "N");
                        if (jaqueBlancas) System.out.println("⚠️  Las BLANCAS están en jaque.");
                        else if (jaqueNegras) System.out.println("⚠️  Las NEGRAS están en jaque.");
                        else System.out.println("✔️  Ningún rey está en jaque.");
                    } else {
                        System.out.println("❌ Movimiento inválido.");
                    }
                    break;

                case "4":
                    // 1️⃣ Reiniciar tablero
                    menuActivo = false;
                    tablero = new Tablero();

                    // 2️⃣ Recargar posiciones iniciales de blancas y negras
                    if (!Tablero.cargarLinea(tablero, entradaBlancas, "B")) {
                        System.out.println("❌ Error al recargar las piezas blancas.");
                        break;
                    }
                    if (!Tablero.cargarLinea(tablero, entradaNegras, "N")) {
                        System.out.println("❌ Error al recargar las piezas negras.");
                        break;
                    }

                    // 3️⃣ Limpiar historial de movimientos
                    historial.clear();

                    // 4️⃣ Reiniciar turno
                    turno = "B";

                    // 5️⃣ Mostrar mensaje de confirmación
                    System.out.println("✔️ Partida reiniciada.");

                    // 6️⃣ Mostrar tablero actualizado
                    tablero.dibujar();

                    // 7️⃣ Comprobar jaque inicial
                    boolean jaqueBlancas = Jaque.reyEnJaque(tablero, "B");
                    boolean jaqueNegras = Jaque.reyEnJaque(tablero, "N");

                    if (jaqueBlancas) System.out.println("⚠️  Las BLANCAS están en jaque.");
                    else if (jaqueNegras) System.out.println("⚠️  Las NEGRAS están en jaque.");
                    else System.out.println("✔️  Ningún rey está en jaque.");

                    break;

                case "5":
                    System.out.println("Saliendo del programa...");
                    menuActivo = false;
                    break;

                default:
                    System.out.println("❌ Opción no válida.");
            }
        }
    }

    // ===== Funciones auxiliares simplificadas =====
    private static boolean procesarMovimiento(String mov, Tablero tablero, String turno) {
        String[] partes = mov.split(" ");
        if (partes.length != 2) return false;

        String inicio = partes[0];
        String fin = partes[1];

        int colInicio = Character.toUpperCase(inicio.charAt(0)) - 'A';
        int filaInicio = 8 - Character.getNumericValue(inicio.charAt(1));

        int colFin = Character.toUpperCase(fin.charAt(0)) - 'A';
        int filaFin = 8 - Character.getNumericValue(fin.charAt(1));

        if (!esDentroTablero(filaInicio, colInicio) || !esDentroTablero(filaFin, colFin)) return false;

        Pieza pieza = tablero.getCasilla(filaInicio, colInicio).getPieza();
        if (pieza == null || !pieza.getColor().equals(turno)) return false;

        Pieza destino = tablero.getCasilla(filaFin, colFin).getPieza();

        // Validar movimiento según tipo
        boolean valido = false;
        switch (pieza.getTipo()) {
            case "P": valido = Movimiento.Peon(filaInicio, colInicio, filaFin, colFin); break;
            case "T": valido = Movimiento.Torre(filaInicio, colInicio, filaFin, colFin) &&
                    Movimiento.caminoLibre(filaInicio, colInicio, filaFin, colFin, tablero); break;
            case "A": valido = Movimiento.Alfil(filaInicio, colInicio, filaFin, colFin) &&
                    Movimiento.caminoLibre(filaInicio, colInicio, filaFin, colFin, tablero); break;
            case "D": valido = Movimiento.Reina(filaInicio, colInicio, filaFin, colFin) &&
                    Movimiento.caminoLibre(filaInicio, colInicio, filaFin, colFin, tablero); break;
            case "C": valido = Movimiento.Caballo(filaInicio, colInicio, filaFin, colFin); break;
            case "R": valido = Movimiento.Rey(filaInicio, colInicio, filaFin, colFin); break;
        }

        if (!valido) return false;

        // Verificar que el movimiento no deja al propio rey en jaque
        tablero.getCasilla(filaInicio, colInicio).setPieza(null);
        tablero.getCasilla(filaFin, colFin).setPieza(pieza);

        if (Jaque.reyEnJaque(tablero, turno)) {
            // revertir
            tablero.getCasilla(filaInicio, colInicio).setPieza(pieza);
            tablero.getCasilla(filaFin, colFin).setPieza(destino);
            return false;
        }

        return true;
    }

    private static boolean esDentroTablero(int fila, int col) {
        return fila >= 0 && fila <= 7 && col >= 0 && col <= 7;
    }
}
