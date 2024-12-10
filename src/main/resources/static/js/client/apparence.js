
// Evento para Los button gender - genero
const button_gender = document.querySelectorAll('.button-outline');
Array.from(button_gender).forEach((btn) => {
    btn.addEventListener('click', function () {

        button_gender.forEach(btn => btn.classList.remove('button-outline-select'));

        this.classList.add('button-outline-select');
    });
});

// Evento para persoanlizacion de color de icono cuando el input esta enfocado
const inputs_icons = document.querySelectorAll('.input-icon');

Array.from(inputs_icons).forEach((input_icon) => {

    const icon = input_icon.querySelector('i');
    const input = input_icon.querySelector('input');

    input.addEventListener('focus', () => {
        icon.style.color = '#2980b9';
    });

    input.addEventListener('blur', () => {
        icon.style.color = '';
    });
});