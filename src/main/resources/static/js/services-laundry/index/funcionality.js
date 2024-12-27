// Eventos y interaccion entre vista parcial de usuario
// Evento cuando se muestra la modal 
const openModalButton = document.getElementById('openModalButton');

// Evento insertar contenido en la modal
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
                    hideServiceSelection(); // Ocultamos la vista parcial
                });
            }

            if (buttonRegistrar) {
                buttonRegistrar.addEventListener('click', function (e) {

                    hideServiceSelection(); // Ocultamos la vista parcial
                    registrarServicio(); // Registramos el servicio 
                    CalcularPrecioServicios(); // Calculamos el precio total de los servicios registrados en la tabla
                });
            }

        },
        error: function () {
            console.error('Error al cargar la vista parcial.');
            $('#ContainerServiceSelection').html('<p>Error al cargar la vista.</p>');
        }
    });
});



const serviceSelectionContainer = document.getElementById('ContainerServiceSelection');// Contenedor de la vista - Servicio seleccion 
const registroVentaContainer = document.getElementById('ContainerSalesRegistration');// Contenedor de la vista principal - ventas

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
    botonEliminar.setAttribute("type", "button");

    const iconoEliminar = document.createElement("i");
    iconoEliminar.classList.add("fas", "fa-trash");

    botonEliminar.appendChild(iconoEliminar);
    divAcciones.appendChild(botonEliminar);

    // Evento para eliminar la fila 
    botonEliminar.addEventListener('click', function () {
        tablaServicio.deleteRow(Nuevafila.rowIndex - 1);
        CalcularPrecioServicios(); // actualizamos el total
    });
    celdaAcciones.appendChild(divAcciones);

};


// Variables para el descuento y el total de los servicios
let descuento = 0;
let precioTotal = 0;
let totalServicio = 0;

function CalcularPrecioServicios() { // Funcion para calcular el precio total de los servicios registrados en la tabla
    const tabla = document.getElementById('tableService').getElementsByTagName('tbody')[0];
    const filas = tabla.getElementsByTagName('tr');
    let total = 0;
    for (let i = 0; i < filas.length; i++) {
        const fila = filas[i];
        const celdas = fila.getElementsByTagName('td');
        total += parseFloat(celdas[2].getAttribute('data-subtotal'));
    }
    totalServicio = total;
    // Actualizamos el total del servicio
    document.getElementById('totalServicios').value = totalServicio;

    CalcularDescuento(); // Calculamos el descuento
};

// Evento para calcular el descuento
document.getElementById('descuento').addEventListener('input', CalcularDescuento);

// Funcion para calcular el descuento
function CalcularDescuento() {
    // Obtenemos el valor del descuento
    const descuento_input = parseFloat(document.getElementById('descuento').value);
    descuento = descuento_input ? descuento_input : 0;
    // Calculamos el precio total en base al descuento  
    precioTotal = totalServicio - descuento;
    // Actualizamos el precio total    
    document.getElementById('precioTotal').value = precioTotal;
}



// Evento para enviar el formulario de registro

function submitForm(event) {
    event.preventDefault(); // Evitamos que el formulario se envie
    const tabla = document.getElementById('tableService').getElementsByTagName('tbody')[0];
    const filas = tabla.getElementsByTagName('tr');

    // objeto  que contendra la lista de servicios
    const registroData = {
        tipoServicio: "Tienda",
        clienteId: 1,
        nombreCliente: document.getElementById('nombreCliente').value,
        observacion: document.getElementById('observacion').value,
        servicios: [],
        totalServicio: parseFloat(document.getElementById('totalServicios').value),
        totalCobro: parseFloat(document.getElementById('totalCobro').value),
        metodoPago: "Efectivo",
        descuento: parseFloat(document.getElementById('descuento').value),
        precioTotal: parseFloat(document.getElementById('precioTotal').value)
    };

    // Convertimos las filas de la tabla en objetos de servicio
    for (let i = 0; i < filas.length; i++) {
        const fila = filas[i];
        const celdas = fila.getElementsByTagName('td');

        const servicio = {
            nombre: celdas[0].getAttribute('data-servicio'),
            precioUnidad: parseFloat(celdas[1].getAttribute('data-precio')),
            cantidad: parseInt(celdas[2].getAttribute('data-cantidad')),
            subTotal: parseFloat(celdas[2].getAttribute('data-subtotal')),
            detalle: celdas[3].getAttribute('data-detalle')
        };

        registroData.servicios.push(servicio);
    }

    fetch('/serviceLaundry/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(registroData)
    })
        .then(response => response.json())
        .then(result => {
            if (result.status === 'success') {
                // se hable la url en nueva ventana para imprimir la boleta
                window.open(result.boletaUrl, '_blank', 'width=800,height=600');
                // Limpiamos el formulario
                limpiarFormulario();
            } else {
                // Mostramos un mensaje de error
                alert('Error: ' + result.message);
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert('Error al procesar el registro');
        });
}

function limpiarFormulario() {
    document.getElementById('form_Register').reset();
    document.getElementById('tableService').getElementsByTagName('tbody')[0].innerHTML = '';
    document.getElementById('tableContainer').classList.add('hidden');
}

