package com.laundry.lavanderia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.laundry.lavanderia.Model.Spending.Spending;
import java.util.List;

public interface SpendingRepository extends JpaRepository<Spending, Long> {

    /**
     * Obtiene el monto total de los gastos registrados en una categoria
     * determinada.
     * 
     * @param category la categoria para la que se desea obtener el monto
     *                 total de gastos
     * @return el monto total de los gastos en la categoria especificada
     */
    @Query("SELECT SUM(s.amount) FROM Spending s WHERE s.category = ?1")
    Double sumAmountByCategory(String category);

    /**
     * Obtiene el monto total de todos los gastos registrados.
     * 
     * @return El monto total de todos los gastos.
     */
    @Query("SELECT SUM(s.amount) FROM Spending s")
    Double getTotalAmount();

    /**
     * Obtiene los gastos mas recientes.
     * 
     * @return Los gastos mas recientes.
     */

    List<Spending> findTop10ByOrderByDateDesc();
}
