 // Estilo de buttons de tipo pago
 const paymentButtons = document.getElementsByName('payment-button');

 paymentButtons.forEach((button) => {

     button.addEventListener('click', function () {

         paymentButtons.forEach(btn => btn.classList.remove('btn-outline-selected'));// Remover clases
         this.classList.add('btn-outline-selected'); //Añadir clase
         document.getElementById('observacion').value = this.dataset.value;
     });
 });


 // Estilo de buttons de tipo servicio
 const ServiceButton = document.getElementsByName('button-service');

 ServiceButton.forEach(function (button) {

     button.addEventListener('click', function () {

         ServiceButton.forEach((btn) => btn.classList.remove('btn-outline-selected'));

         this.classList.add('btn-outline-selected');
     });
 });