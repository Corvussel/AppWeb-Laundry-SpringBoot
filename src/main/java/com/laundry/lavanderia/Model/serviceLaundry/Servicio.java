    package com.laundry.lavanderia.Model.serviceLaundry;

    import lombok.Data;
    import lombok.AllArgsConstructor;
    import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Servicio {
        private String nombre; 
        private double precioUnidad;
        private int cantidad;
        private double subTotal;
        private String detalle;
        private boolean activo ; // Por defecto, activo

        public Servicio(String nombre, String detalle, double precioUnidad, boolean activo) {
            this.nombre = nombre;
            this.detalle = detalle;
            this.precioUnidad = precioUnidad;
            this.activo = activo; // Ahora se respeta el valor proporcionado.
        }

    // Constructor sin el parámetro `activo` (por defecto, activo = true)
    public Servicio(String nombre, String detalle, double precioUnidad) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.precioUnidad = precioUnidad;
        this.activo = true;
    }
}