  
// Eventos y interaccion entre vista parcial de usuario
// Evento cuando se muestra la modal 
const openModalButton = document.getElementById('openModalButton');

// Evento para abrir el modal
openModalButton.addEventListener('click', () => {
    $.ajax({
        url: '/services/ListUsers',
        type: 'get',
        success: function (data) {
            // insertarr el html en el contenedor del modal
            $('#userListContainer').html(data);
        },
        error: function () {
            console.error('Error al cargar la vista parcial.');
            $('#userListContainer').html('<p>Error al cargar la vista parcial.</p>');
        }
    });
});


// Eventos y interaccion entre vista seleccion de servicio

document.addEventListener('DOMContentLoaded', function () {

    $.ajax({
        url: '/services/selection',
        type: 'get',
        success: function (data) {
            $('#ContainerServiceSelection').html(data);

            const buttonCancelar = document.getElementById('btnCancelarRegistroServicio');
            const buttonRegistrar = document.getElementById('btnRealizarRegistrarServicio');
            const formRegistroServicio = document.querySelector('.form-validation');


            if (buttonCancelar) {
                buttonCancelar.addEventListener('click', function () {
                    hideServiceSelection();
                });
            }

            if (buttonRegistrar) {
                buttonRegistrar.addEventListener('click', function (e) {
                    // Validación de formulario
                    if (!formRegistroServicio.checkValidity()) {
                        e.preventDefault();
                        e.stopPropagation();
                        formRegistroServicio.classList.add('was-validated');
                    } else {
                        hideServiceSelection();
                        registrarServicio();
                    }
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
    const cantidad = document.getElementById('spinner').value;
    const servicio = document.getElementById('servicio').value;
    const precioUnidad = document.getElementById('precioUnidad').textContent;
    const subTotal = document.getElementById('total').textContent;
    const detalle = document.getElementById('detalles').value;

    // Mostramos el contenedor La tabla
    document.getElementById('tableContainer').classList.remove('hidden');
    // Modificamos el tamaño de button BtnNuevoServicio
    document.getElementById('BtnNuevoServicio').classList.remove('w-100');

    // Referencia a la tabla y al tbody
    const tablaServicio = document.getElementById('tableService').getElementsByTagName("tbody")[0];

    // creacion de una nueva fila
    const Nuevafila = tablaServicio.insertRow();
    Nuevafila.classList.add("table-row");

    // Cantidad
    const celdaCantidad = Nuevafila.insertCell(0);
    const spanCantidad = document.createElement("span");
    spanCantidad.classList.add("precio");
    spanCantidad.textContent = cantidad;
    celdaCantidad.appendChild(spanCantidad);

    // Servicio
    const celdaServicio = Nuevafila.insertCell(1);
    const spanServicio = document.createElement("span");
    spanServicio.classList.add("nombre-servicio");
    spanServicio.textContent = servicio;
    celdaServicio.appendChild(spanServicio);

    // Precio Unitario
    const celdaPrecioUnitario = Nuevafila.insertCell(2);
    const spanPrecioUnitario = document.createElement("span");
    spanPrecioUnitario.classList.add("precio");
    spanPrecioUnitario.textContent = precioUnidad;
    celdaPrecioUnitario.appendChild(spanPrecioUnitario);

    // Subtotal
    const celdaSubTotal = Nuevafila.insertCell(3);
    const spanSubTotal = document.createElement("span");
    spanSubTotal.classList.add("precio");
    spanSubTotal.textContent = subTotal;
    celdaSubTotal.appendChild(spanSubTotal);

    // Detalles
    const celdaDetalles = Nuevafila.insertCell(4);
    const spanDetalles = document.createElement("span");
    spanDetalles.classList.add("detalles");
    spanDetalles.textContent = detalle;
    celdaDetalles.appendChild(spanDetalles);

    //btn de edicion
    const celdaAcciones = Nuevafila.insertCell(5);
    celdaAcciones.classList.add('d-flex');

    // boton de Eliminar
    const btnEliminar = document.createElement('button');
    // Ícono para el boton de eliminar
    const iconEliminar = document.createElement('i');
    iconEliminar.classList.add("fas", "fa-trash");
    btnEliminar.classList.add("btn-table-delete");
    btnEliminar.appendChild(iconEliminar);

    btnEliminar.addEventListener('click', function () {
        tablaServicio.deleteRow(Nuevafila.rowIndex - 1);
    });
    celdaAcciones.appendChild(btnEliminar); // Añadimos el botón a la celda de acciones

    // Boton para Editar
    const btnEditar = document.createElement('button');
    // Ícono para el boton de editar
    const iconEditar = document.createElement('i');
    iconEditar.classList.add("fas", "fa-edit");
    btnEditar.classList.add("btn-table-edit");
    btnEditar.appendChild(iconEditar);

    btnEditar.addEventListener('click', function () {
        const datos = {
            cantidad: spanCantidad.textContent,
            servicio: spanServicio.textContent,
            precioUnidad: spanPrecioUnitario.textContent,
            subTotal: spanSubTotal.textContent,
            detalle: spanDetalles.textContent
        };
        console.log("Datos de la fila: ", datos);
        alert(JSON.stringify(datos, null, 2));
    });
    celdaAcciones.appendChild(btnEditar); // Añadimos el botón a la celda de acciones

}