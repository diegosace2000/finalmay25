import java.util.ArrayList;
import java.util.Scanner;

class Ticket {
    String ubicacion;
    double precioBase;
    double descuentoEdad;
    double descuentoSocio;
    double precioFinal;

    public Ticket(String ubicacion, double precioBase, double descuentoEdad, double descuentoSocio, double precioFinal) {
        this.ubicacion = ubicacion;
        this.precioBase = precioBase;
        this.descuentoEdad = descuentoEdad;
        this.descuentoSocio = descuentoSocio;
        this.precioFinal = precioFinal;
    }

    public void mostrarResumen() {
        System.out.println("\n--- Resumen de la compra ---");
        System.out.println("Ubicación del asiento: " + ubicacion);
        System.out.printf("Precio base: $%.2f\n", precioBase);
        System.out.printf("Descuento por edad: %.0f%%\n", descuentoEdad * 100);
        System.out.printf("Descuento adicional por ser socio: %.0f%%\n", descuentoSocio * 100);
        System.out.printf("Precio final a pagar: $%.2f\n", precioFinal);
    }
}

public class TeatroMoroVentas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Ticket> ticketsComprados = new ArrayList<>();

        while (true) {
            System.out.println("\nBienvenido al Teatro Moro");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Ver compras anteriores");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            if (opcion == 3) {
                System.out.println("¡Gracias por visitar el Teatro Moro!");
                break;
            } else if (opcion == 2) {
                if (ticketsComprados.isEmpty()) {
                    System.out.println("\nNo hay compras registradas aún.");
                } else {
                    System.out.println("\n--- Compras anteriores ---");
                    for (int i = 0; i < ticketsComprados.size(); i++) {
                        System.out.println("\nTicket #" + (i + 1));
                        ticketsComprados.get(i).mostrarResumen();
                    }
                }
            } else if (opcion == 1) {
                double precioBase = 0.0;
                double descuentoEdad = 0.0;
                double descuentoSocio = 0.0;
                double precioFinal = 0.0;
                String ubicacionAsiento = "";

                // Selección de zona
                while (true) {
                    System.out.println("Seleccione la ubicación del asiento:");
                    System.out.println("Zona 1 - $30.000");
                    System.out.println("Zona 2 - $15.000");
                    System.out.println("Zona 3 - $10.000");
                    System.out.print("Ingrese la zona (1-3): ");
                    String zona = scanner.nextLine();

                    if (zona.equals("1")) {
                        precioBase = 30000;
                        ubicacionAsiento = "Zona 1";
                        break;
                    } else if (zona.equals("2")) {
                        precioBase = 15000;
                        ubicacionAsiento = "Zona 2";
                        break;
                    } else if (zona.equals("3")) {
                        precioBase = 10000;
                        ubicacionAsiento = "Zona 3";
                        break;
                    } else {
                        System.out.println("Zona inválida. Intente nuevamente.");
                    }
                }

                // Edad
                int edad = 0;
                while (true) {
                    System.out.print("Ingrese su edad: ");
                    if (scanner.hasNextInt()) {
                        edad = scanner.nextInt();
                        scanner.nextLine(); // limpiar buffer
                        if (edad > 4) break;
                        else System.out.println("Edad inválida. Debe ser mayor a 4 años.");
                    } else {
                        System.out.println("Edad no válida.");
                        scanner.nextLine(); // limpiar entrada inválida
                    }
                }

                // Socio
                while (true) {
                    System.out.print("¿Usted es socio? (si/no): ");
                    String socio = scanner.nextLine().trim().toLowerCase();

                    if (socio.equals("si") || socio.equals("s")) {
                        descuentoSocio = 0.50;
                        break;
                    } else if (socio.equals("no") || socio.equals("n")) {
                        descuentoSocio = 0.0;
                        break;
                    } else {
                        System.out.println("Respuesta inválida. Escriba 'si' o 'no'.");
                    }
                }

                // Descuento por edad
                if (edad >= 18 && edad <= 25) {
                    descuentoEdad = 0.10;
                    System.out.println("¡Descuento del 10% por estudiante!");
                } else if (edad >= 60) {
                    descuentoEdad = 0.15;
                    System.out.println("¡Descuento del 15% por tercera edad!");
                } else {
                    System.out.println("No se aplican descuentos por edad.");
                }

                double precioConDescuentoEdad = precioBase * (1 - descuentoEdad);
                precioFinal = precioConDescuentoEdad * (1 - descuentoSocio);

                // Crear y guardar ticket
                Ticket ticket = new Ticket(ubicacionAsiento, precioBase, descuentoEdad, descuentoSocio, precioFinal);
                ticket.mostrarResumen();
                ticketsComprados.add(ticket);

                // ¿Otra compra?
                System.out.print("\n¿Desea realizar otra compra? (s/n): ");
                String otraCompra = scanner.nextLine().trim().toLowerCase();
                if (!otraCompra.equals("s") && !otraCompra.equals("si")) {
                    System.out.println("¡Gracias por su compra!");
                    
                }
            } else {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }

        scanner.close();
    }
}
