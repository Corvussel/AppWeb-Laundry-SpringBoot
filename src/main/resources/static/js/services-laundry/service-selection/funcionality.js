 //evento cuando se presiona un button-categoria
 const container_btnCategorias = document.querySelector('#categoriasTabsButton');

 container_btnCategorias.addEventListener('click', function (e) {

     const button_presionado = e.target.closest('.nav-button');

     if (button_presionado) {
         const value = button_presionado.value;
        // removerActiveButtons();
         mostrarTabPestaña(value);
       //  button_presionado.classList.add('active'); 

     }
 });

 function removerActiveButtons() {
     document.querySelectorAll('.nav-link').forEach(btn => { btn.classList.remove('active') });
 }

 function mostrarTabPestaña(tabCategoria) {
     // Se Itera por todas las pestañas
     document.querySelectorAll('.tab-pane').forEach(tabPage => {
         const value = tabPage.id; // el Id de la pestaña
         // Mostrar u ocultar la pestaña segun coincida con la categoria
         if (!value.includes(tabCategoria)) {
             tabPage.classList.add('hidden');
         } else {
             tabPage.classList.remove('hidden');
         }
     });
 }


// Evento para la activacion tarjetas de servicios
const cardContainer = document.getElementById('card-container');

cardContainer.addEventListener('click', (event) => {

    // Verificar si el clic ocurrio en una tarjeta
    const card = event.target.closest('.card-service');

    if (card) {

        desmarcarTarjetas();//Desmarcar tarjetas anteriores
        card.classList.add('card-active');//Marcar Tarjeta actual
 
    }

});

// función para desmarcar todas las tarjetas
function desmarcarTarjetas() {
    const cards = document.querySelectorAll('.card-active');
    cards.forEach(card => { card.classList.remove('card-active') });
};

 
//Evento Relacionado a la Busqueda de Servicio

const searchInput = document.getElementById('searchInput');
searchInput.addEventListener('input', filterCards);

// Funcion para busqueda de servicio
function filterCards() {

    const searchInput = document.getElementById('searchInput'); // input de busqueda
    const cards = document.querySelectorAll('.card-service'); // cards

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