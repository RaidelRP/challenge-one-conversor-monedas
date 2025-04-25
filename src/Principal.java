import com.alura.challenge.conversor.Cambio;
import com.alura.challenge.conversor.Consulta;
import com.alura.challenge.conversor.Monedas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static Monedas monedas = new Monedas();
    public static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        System.out.println("**********************************");
        System.out.println("Digite una opción para continuar:");
        System.out.println("1. Consultar un cambio");
        System.out.println("2. Consultar listado de monedas disponibles");
        System.out.println("3. Agregar nueva moneda para consultar cambio");
        System.out.println("0. Salir");
    }

    public static void menu1() {
        System.out.println("Esta es la lista de las monedas que se pueden elegir.\nDigite el número correpondiente");
        monedas.listarMonedas();
    }

    public static void menu2() {
        System.out.println("**********************************");
        System.out.println("Esta es la lista de las monedas que se pueden elegir.");
        monedas.listarMonedas();
    }

    public static void main(String[] args) {
        int opcion;

        System.out.println("**********************************");
        System.out.println("Bienvenido al Conversor de monedas");
        menu();
        try {
            opcion = scanner.nextInt();
            while (opcion != 0) {
                switch (opcion) {
                    case 1:
                        convertirMonedas();
                        break;
                    case 2:
                        menu2();
                        break;
                    case 3:
                        System.out.println("**********************************");
                        System.out.println("Introduzca el código de la nueva moneda a consultar: ");
                        String codigo = scanner.next();
                        monedas.agregarMoneda(codigo);
                        break;
                    case 0:
                        System.out.println("**********************************");
                        System.out.println("Finalizando programa");
                        break;
                    default:
                        System.out.println("**********************************");
                        System.out.println("La opción no es correcta");
                        break;
                }
                menu();
                opcion = scanner.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Ha ocurrido un error al introducir los datos.\n\t" + e.getMessage());
        }
        System.out.println("**********************************");
        System.out.println("Finalizando programa");

    }

    private static void convertirMonedas() {
        System.out.println("**********************************");
        System.out.println("Seleccione la moneda que quiere convertir: ");
        menu1();
        int opcionMonedaOrigen, opcionMonedaDestino;
        opcionMonedaOrigen = scanner.nextInt();
        System.out.println("Seleccione la moneda que quiere obtener: ");
        menu1();
        opcionMonedaDestino = scanner.nextInt();
        try {
            String monedaOrigen = monedas.obtenerMoneda(opcionMonedaOrigen - 1);
            String monedaDestino = monedas.obtenerMoneda(opcionMonedaDestino - 1);
            System.out.println("Introduzca la cantidad de " + monedaOrigen + " a convertir: ");
            double cantidad = scanner.nextInt();
            Consulta consulta = new Consulta(monedaOrigen, monedaDestino);
            Cambio cambio = consulta.realizarConsulta();
            if (cambio != null && cambio.result().equals("success")) {
                System.out.println(cambio.resultadoDeConversion(cantidad));
            } else {
                System.out.println("No se ha podido realizar la conversión. \nEs posible que alguna de las monedas a convertir no esté disponible");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Una de las opciones de moneda seleccionada (" + opcionMonedaOrigen + " o " + opcionMonedaDestino + ") no está disponible");
        }
    }
}
