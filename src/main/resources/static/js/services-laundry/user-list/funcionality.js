 
// Evento para asignar el valor al input usuario, interaccion entre vista index de servicio
function selectUser(userName) {
    document.getElementById('usuario').value = userName;
  
}


// Evento para filtrar - busqueda
document.getElementById('searchInput').addEventListener('input', filterUsers);

function filterUsers() {
    const searchInput = document.getElementById('searchInput');// input de busqueda
    const filter = searchInput.value.toLowerCase().split(" ");
    const userList = document.getElementById('userList'); // lista
    const listItems = userList.querySelectorAll('li'); // elementos de la lista

    // Recorrer los elementos
    listItems.forEach(item => {
        const userName = item.textContent.toLowerCase();
        const incluye = filter.every(letra => userName.includes(letra));

        item.style.display = incluye ? '' : 'none'; // Muestra o esconde segun coincidencia
    });
}