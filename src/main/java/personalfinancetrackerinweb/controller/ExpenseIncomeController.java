package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import personalfinancetrackerinweb.model.Expense;
import personalfinancetrackerinweb.model.Income;
import personalfinancetrackerinweb.repository.ExpenseRepositoryImpl;
import personalfinancetrackerinweb.repository.IncomeRepositoryImpl;

@Named
@ViewScoped
public class ExpenseIncomeController implements Serializable {

    @Inject
    private ExpenseRepositoryImpl expenseRepositoryImpl;

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    private List<Expense> expenseList;
    private List<Income> incomeList;

    private LineChartModel chartModel;

    public ExpenseRepositoryImpl getExpenseRepositoryImpl() {
        return expenseRepositoryImpl;
    }

    public void setExpenseRepositoryImpl(ExpenseRepositoryImpl expenseRepositoryImpl) {
        this.expenseRepositoryImpl = expenseRepositoryImpl;
    }

    public IncomeRepositoryImpl getIncomeRepositoryImpl() {
        return incomeRepositoryImpl;
    }

    public void setIncomeRepositoryImpl(IncomeRepositoryImpl incomeRepositoryImpl) {
        this.incomeRepositoryImpl = incomeRepositoryImpl;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public LineChartModel getChartModel() {
        return chartModel;
    }

    @PostConstruct
    public void init() {
        expenseList = expenseRepositoryImpl.findAll();
        incomeList = incomeRepositoryImpl.findAll();
        createChartModel();
    }

    private void createChartModel() {
        chartModel = new LineChartModel();

        LineChartSeries incomeSeries = new LineChartSeries();
        incomeSeries.setLabel("Income");

        LineChartSeries expenseSeries = new LineChartSeries();
        expenseSeries.setLabel("Expense");

        for (Income income : incomeList) {
            String month = getMonthFromDate(income.getDate());
            BigDecimal amount = income.getAmount();

            incomeSeries.set(month, amount);
        }

        for (Expense expense : expenseList) {
            String month = getMonthFromDate(expense.getDate());
            BigDecimal amount = expense.getAmount();

            expenseSeries.set(month, amount);
        }

        chartModel.addSeries(incomeSeries);
        chartModel.addSeries(expenseSeries);
        chartModel.setTitle("Income vs Expense Report");
        chartModel.setLegendPosition("ne");

        Axis xAxis = new CategoryAxis("Month");
        chartModel.getAxes().put(AxisType.X, xAxis);

        Axis yAxis = chartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Amount");
        yAxis.setMin(0);
    }

    private String getMonthFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int monthIndex = calendar.get(Calendar.MONTH);
        return new DateFormatSymbols().getMonths()[monthIndex];
    }
}
