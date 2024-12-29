package com.laundry.lavanderia.Model.Spending;

import lombok.Data;
import java.util.List;

@Data
public class SpendingSummary {
    private Double totalAmount;
    private Double materialsAmount;
    private Double servicesAmount;
    private Double othersAmount;
    private List<Double> monthlyTrends;
    private List<Spending> recentSpendings;
}
 
