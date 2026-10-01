public class Producto {
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public static void main(String[] args) {
        Producto p = new Producto("Laptop", 120.0);
        System.out.println("Producto: " + p.getNombre() + " - Precio: " + p.getPrecio());
    }
}