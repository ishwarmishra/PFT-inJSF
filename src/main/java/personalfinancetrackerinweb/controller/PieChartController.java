package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.PieChartModel;
import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.repository.BudgetRepositoryImpl;

@Named
@ViewScoped
public class PieChartController implements Serializable {

    @Inject
    private BudgetRepositoryImpl budgetRepositoryImpl;

    private PieChartModel pieModel;

    @PostConstruct
    public void init() {
        createPieModel();
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    void createPieModel() {
        pieModel = new PieChartModel();

        List<Budget> budgetList = budgetRepositoryImpl.findAll();
        for (Budget budget : budgetList) {
            pieModel.set(budget.getCategory().getName(), budget.getAmount());
        }

        pieModel.setTitle("Category Budget Distribution");
        pieModel.setLegendPosition("w");
        pieModel.setShowDataLabels(true);
    }
}
