// Evento Relacionado al Spinner

const spinner = document.getElementById("spinner");
const btnIncrement = document.getElementById("btnIncrement");
const btnDecrement = document.getElementById("btnDecrement");

btnIncrement.addEventListener('click', () => {

    const value = Number(spinner.value); // Valor del input spinner
    const step = Number(spinner.dataset.step);// El valor del incremento
    const increment = value + step; // resultado

    if (increment <= 20) {
        spinner.value = increment; // Mostramos
    }

});

btnDecrement.addEventListener('click', () => {
    const value = Number(spinner.value); // Valor del input spinner
    const step = Number(spinner.dataset.step);// El valor del incremento
    const decrement = value - step; // resultado
    if (decrement > 0) {
        spinner.value = decrement; // Mostramos
    }
});


//Evento Relacionado ala Busqueda de Servicio

const searchInput = document.getElementById('searchInput');
searchInput.addEventListener('input', filterCards);

// Funcion para busqueda de servicio
function filterCards() {

    const searchInput = document.getElementById('searchInput'); // input de busqueda
    const cards = document.querySelectorAll('.card'); // cards

    cards.forEach((card) => {

        const cardValue = card.dataset.servicio.toLowerCase(); // valor de la tarjeta
        const searchValue = searchInput.value.toLowerCase(); // valor de input de busqueda

        //comparacion
        if (cardValue.includes(searchValue)) {
            card.style.display = 'block';
        } else {
            card.style.display = 'none';
        }

    });
}