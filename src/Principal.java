import com.alura.challenge.conversor.Cambio;
import com.alura.challenge.conversor.Consulta;
import com.alura.challenge.conversor.Monedas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        System.out.println("4. Ver historial de conversiones");
        System.out.println("0. Salir");
    }

    private static String convertirMonedas() {
        System.out.println("**********************************");
        System.out.println("Seleccione la moneda que quiere convertir: ");
        listarMonedas();
        int opcionMonedaOrigen, opcionMonedaDestino;
        opcionMonedaOrigen = scanner.nextInt();
        System.out.println("Seleccione la moneda que quiere obtener: ");
        listarMonedas();
        opcionMonedaDestino = scanner.nextInt();
        try {
            String monedaOrigen = monedas.obtenerMoneda(opcionMonedaOrigen - 1);
            String monedaDestino = monedas.obtenerMoneda(opcionMonedaDestino - 1);
            System.out.println("Introduzca la cantidad de " + monedaOrigen + " a convertir: ");
            double cantidad = scanner.nextInt();
            Consulta consulta = new Consulta(monedaOrigen, monedaDestino);
            Cambio cambio = consulta.realizarConsulta();
            if (cambio != null && cambio.result().equals("success")) {
                String resultado = cambio.resultadoDeConversion(cantidad);
                System.out.println(resultado);
                return resultado;
            } else {
                System.out.println("No se ha podido realizar la conversión. \nEs posible que alguna de las monedas a convertir no esté disponible");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Una de las opciones de moneda seleccionada (" + opcionMonedaOrigen + " o " + opcionMonedaDestino + ") no está disponible");
        }
        return "";
    }

    public static void listarMonedas() {
        System.out.println("Esta es la lista de las monedas que se pueden elegir.\nDigite el número correpondiente");
        monedas.listarMonedas();
    }

    public static void menu2() {
        System.out.println("**********************************");
        System.out.println("Esta es la lista de las monedas disponibles.");
        monedas.listarMonedas();
    }

    public static void main(String[] args) {
        int opcion;
        FileWriter writer;
        BufferedReader reader;

        try {
            System.out.println("**********************************");
            System.out.println("Bienvenido al Conversor de monedas");
            menu();

            opcion = scanner.nextInt();
            while (opcion != 0) {
                switch (opcion) {
                    case 1:
                        String resultado = convertirMonedas();
                        if (!resultado.isEmpty()) {
                            writer = new FileWriter("historial.txt", true);
                            LocalDateTime hora = LocalDateTime.now(); // Obtener fecha actual
                            String horaFormateada = hora.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));  //Dar formato a fecha

                            writer.write("\n" + horaFormateada + " => " + resultado);
                            writer.close();
                        }
                        break;
                    case 2:
                        menu2();
                        break;
                    case 3:
                        System.out.println("**********************************");
                        System.out.println("Introduzca el código de la nueva moneda a convertir: ");
                        String codigo = scanner.next();
                        monedas.agregarMoneda(codigo);
                        break;
                    case 4:
                        System.out.println("**********************************");
                        System.out.println("Historial de conversiones");
                        reader = new BufferedReader(new FileReader("historial.txt"));
                        String linea;
                        while ((linea = reader.readLine()) != null) {
                            System.out.println(linea);
                        }
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
        } catch (IOException e) {
            System.out.println("No ha sido posible acceder al archivo");
        }
        System.out.println("**********************************");
        System.out.println("Finalizando programa");
    }


}
