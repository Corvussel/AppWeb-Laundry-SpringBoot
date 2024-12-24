
// Eventos y interaccion entre vista parcial de usuario
// Evento cuando se muestra la modal 
const openModalButton = document.getElementById('openModalButton');

// Evento para abrir el modal
openModalButton.addEventListener('click', () => {
    $.ajax({
        url: '/serviceLaundry/ListUsers',
        type: 'get',
        success: function (data) {
            // insertar el html en el contenedor del modal
            $('#userListContainer').html(data);
        },
        error: function () {
            console.error('Error al cargar la vista parcial.');
            $('#userListContainer').html('<p>Error al cargar la vista parcial.</p>');
        }
    });
});


// Eventos y interaccion entre vista seleccion de servicio y el index 

document.addEventListener('DOMContentLoaded', function () {

    $.ajax({
        url: '/serviceLaundry/selection',
        type: 'get',
        success: function (data) {
            $('#ContainerServiceSelection').html(data);

            const buttonCancelar = document.getElementById('btnCancelarRegistroServicio');
            const buttonRegistrar = document.getElementById('btnRealizarRegistrarServicio');


            if (buttonCancelar) {
                buttonCancelar.addEventListener('click', function () {
                    hideServiceSelection();
                });
            }

            if (buttonRegistrar) {
                buttonRegistrar.addEventListener('click', function (e) {

                    hideServiceSelection();
                    registrarServicio();
                });
            }

        },
        error: function () {
            console.error('Error al cargar la vista parcial.');
            $('#ContainerServiceSelection').html('<p>Error al cargar la vista.</p>');
        }
    });
});


// Contenedor de la vista Parcial- Servicio seleccion
const serviceSelectionContainer = document.getElementById('ContainerServiceSelection');
// Contenedor de la vista principal - ventas
const registroVentaContainer = document.getElementById('ContainerSalesRegistration');

// Evento para mostrar la vista parcial de selección de servicio
function showServiceSelection() {
    serviceSelectionContainer.classList.remove('hidden');
    registroVentaContainer.classList.add('hidden');
};

// Evento para ocultar la vista parcial de selección de servicio
function hideServiceSelection() {
    serviceSelectionContainer.classList.add('hidden');
    registroVentaContainer.classList.remove('hidden');
};
 
// Funcion Para la realizacion de registro de Servicio
function registrarServicio() {

    // Valores De la vista Parcial Seleccion de Servicios
    const cantidad = document.getElementById('cantidad').textContent;
    const servicio = document.getElementById('servicio').textContent;
    const precioUnidad = document.getElementById('precioUnidad').textContent;
    const subTotal = document.getElementById('total').textContent;
    const detalle = document.getElementById('detalles').value;

    // Mostramos el contenedor La tabla
    document.getElementById('tableContainer').classList.remove('hidden');

    // Referencia a la tabla y al tbody
    const tablaServicio = document.getElementById('tableService').getElementsByTagName("tbody")[0];

    // creacion de una nueva fila
    const Nuevafila = tablaServicio.insertRow();
    Nuevafila.classList.add("hover:bg-gray-50/50", "transition-colors"); // Añadimos las clases de estilo

    // Servicio
    const celdaServicio = Nuevafila.insertCell(0);
    celdaServicio.classList.add("px-6", "py-4");
    celdaServicio.setAttribute("data-servicio", servicio);

    const divServicio = document.createElement("div");
    divServicio.classList.add("text-sm", "font-medium", "text-gray-900");
    divServicio.textContent = servicio;
    const subDivServicio = document.createElement("div");
    subDivServicio.classList.add("text-xs", "text-gray-500");
    subDivServicio.textContent = detalle;
    celdaServicio.appendChild(divServicio);
    celdaServicio.appendChild(subDivServicio);

    // Precio Unitario
    const celdaPrecioUnitario = Nuevafila.insertCell(1);
    celdaPrecioUnitario.classList.add("px-6", "py-4");
    celdaPrecioUnitario.setAttribute("data-precio", precioUnidad);

    const divPrecioUnitario = document.createElement("div");
    divPrecioUnitario.classList.add("text-sm", "text-gray-900");
    divPrecioUnitario.textContent = `S/ ${precioUnidad}`;
    celdaPrecioUnitario.appendChild(divPrecioUnitario);

    // Subtotal
    const celdaSubTotal = Nuevafila.insertCell(2);
    celdaSubTotal.classList.add("px-6", "py-4");
    celdaSubTotal.setAttribute("data-subtotal", subTotal);
    celdaSubTotal.setAttribute("data-cantidad", cantidad);

    const divSubTotal = document.createElement("div");
    divSubTotal.classList.add("text-sm", "font-medium", "text-gray-900");
    divSubTotal.textContent = `S/ ${subTotal}`;
    const subDivSubTotal = document.createElement("div");
    subDivSubTotal.classList.add("text-xs", "text-gray-500");
    subDivSubTotal.textContent = `${cantidad} unidades`;
    celdaSubTotal.appendChild(divSubTotal);
    celdaSubTotal.appendChild(subDivSubTotal);

    // Detalles
    const celdaDetalles = Nuevafila.insertCell(3);
    celdaDetalles.classList.add("px-6", "py-4");
    celdaDetalles.setAttribute("data-detalle", detalle);

    const pDetalles = document.createElement("p");
    pDetalles.classList.add("text-sm", "text-gray-600");
    pDetalles.textContent = detalle;
    celdaDetalles.appendChild(pDetalles);

    // Acciones
    const celdaAcciones = Nuevafila.insertCell(4);
    celdaAcciones.classList.add("px-6", "py-4");

    const divAcciones = document.createElement("div");
    divAcciones.classList.add("flex");

    const botonEliminar = document.createElement("button");
    botonEliminar.classList.add("p-2", "text-red-600", "hover:text-red-800", "rounded-lg");

    const iconoEliminar = document.createElement("i");
    iconoEliminar.classList.add("fas", "fa-trash");

    botonEliminar.appendChild(iconoEliminar);
    divAcciones.appendChild(botonEliminar);

    // Evento para eliminar la fila 
    botonEliminar.addEventListener('click', function () {
        tablaServicio.deleteRow(Nuevafila.rowIndex - 1);
    });
    celdaAcciones.appendChild(divAcciones);
}