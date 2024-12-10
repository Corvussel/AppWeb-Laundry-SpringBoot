// Evento para la activacion y seleccion de servicio de card
const cardContainer = document.getElementById('card-container');

cardContainer.addEventListener('click', (event) => {

    // Verificar si el clic ocurrio en una tarjeta
    const card = event.target.closest('.card');

    if (card) {

        desmarcarTarjetas();//Desmarcar tarjetas anteriores
        card.classList.add('card-active');//Marcar Tarjeta actual

        // Obtener el servicio desde el atributo data-servicio
        const servicio = card.dataset.servicio;
        // Llamar a la función de selección
        seleccionarServicio(servicio);
    }

});

// función para desmarcar todas las tarjetas
function desmarcarTarjetas() {
    const cards = document.querySelectorAll('.card-active');
    cards.forEach(card => { card.classList.remove('card-active') });
};

// función para seleccion de servicio
function seleccionarServicio(servicio) {
    document.getElementById('resumenServicio').classList.remove('d-none');
    document.getElementById('detalleServicio').textContent = servicio;
    document.getElementById('servicio').value = servicio;

}