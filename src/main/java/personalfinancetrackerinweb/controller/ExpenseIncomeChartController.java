package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
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
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.BarChartModel;

import personalfinancetrackerinweb.model.Expense;
import personalfinancetrackerinweb.model.Income;
import personalfinancetrackerinweb.repository.ExpenseRepositoryImpl;
import personalfinancetrackerinweb.repository.IncomeRepositoryImpl;

@Named
@ViewScoped
public class ExpenseIncomeChartController implements Serializable {

    @Inject
    private ExpenseRepositoryImpl expenseRepositoryImpl;

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    private List<Expense> expenseList;
    private List<Income> incomeList;

    private LineChartModel lineChartModel;
    private BarChartModel barChartModel;

    private String chartType; // Weekly or Monthly

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

    public LineChartModel getLineChartModel() {
        return lineChartModel;
    }

    public BarChartModel getBarChartModel() {
        return barChartModel;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    @PostConstruct
    public void init() {
        expenseList = expenseRepositoryImpl.findAll();
        incomeList = incomeRepositoryImpl.findAll();
        createChartModels();
    }

    public void createChartModels() {
        createLineChartModel();
        createBarChartModel();
    }

    private void createLineChartModel() {
        lineChartModel = new LineChartModel();

        if (chartType != null && chartType.equals("weekly")) {
            createWeeklyLineChartModel();
        } else {
            createMonthlyLineChartModel();
        }
    }

    private void createBarChartModel() {
        barChartModel = new BarChartModel();

        if (chartType != null && chartType.equals("weekly")) {
            createWeeklyBarChartModel();
        } else {
            createMonthlyBarChartModel();
        }
    }

    private void createWeeklyLineChartModel() {
        LineChartSeries incomeSeries = new LineChartSeries();
        incomeSeries.setLabel("Income");

        LineChartSeries expenseSeries = new LineChartSeries();
        expenseSeries.setLabel("Expense");

        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        List<BigDecimal> incomeAmounts = new ArrayList<>();
        List<BigDecimal> expenseAmounts = new ArrayList<>();

        for (Income income : incomeList) {
            calendar.setTime(income.getDate());
            int week = calendar.get(Calendar.WEEK_OF_YEAR);

            if (week == currentWeek) {
                incomeAmounts.add(income.getAmount());
            }
        }
        for (Expense expense : expenseList) {
            calendar.setTime(expense.getDate());
            int week = calendar.get(Calendar.WEEK_OF_YEAR);

            if (week == currentWeek) {
                expenseAmounts.add(expense.getAmount());
            }
        }
        for (int i = 0; i < incomeAmounts.size(); i++) {
            BigDecimal amount = incomeAmounts.get(i);
            String label = "Week " + (i + 1);

            incomeSeries.set(label, amount);
        }
        for (int i = 0; i < expenseAmounts.size(); i++) {
            BigDecimal amount = expenseAmounts.get(i);
            String label = "Week " + (i + 1);

            expenseSeries.set(label, amount);
        }
        lineChartModel.addSeries(incomeSeries);
        lineChartModel.addSeries(expenseSeries);
        lineChartModel.setTitle("Income vs Expense Report (Weekly)");
        lineChartModel.setLegendPosition("ne");

        Axis xAxis = new CategoryAxis("Week");
        lineChartModel.getAxes().put(AxisType.X, xAxis);

        Axis yAxis = lineChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Amount");
        yAxis.setMin(0);
    }

    private void createMonthlyLineChartModel() {
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
        lineChartModel.addSeries(incomeSeries);
        lineChartModel.addSeries(expenseSeries);
        lineChartModel.setTitle("Income vs Expense Report (Monthly)");
        lineChartModel.setLegendPosition("ne");

        Axis xAxis = new CategoryAxis("Month");
        lineChartModel.getAxes().put(AxisType.X, xAxis);

        Axis yAxis = lineChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Amount");
        yAxis.setMin(0);
    }

    private void createWeeklyBarChartModel() {
        ChartSeries incomeSeries = new ChartSeries();
        incomeSeries.setLabel("Income");

        ChartSeries expenseSeries = new ChartSeries();
        expenseSeries.setLabel("Expense");

        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        List<BigDecimal> incomeAmounts = new ArrayList<>();
        List<BigDecimal> expenseAmounts = new ArrayList<>();

        for (Income income : incomeList) {
            calendar.setTime(income.getDate());
            int week = calendar.get(Calendar.WEEK_OF_YEAR);

            if (week == currentWeek) {
                incomeAmounts.add(income.getAmount());
            }
        }
        for (Expense expense : expenseList) {
            calendar.setTime(expense.getDate());
            int week = calendar.get(Calendar.WEEK_OF_YEAR);

            if (week == currentWeek) {
                expenseAmounts.add(expense.getAmount());
            }
        }
        for (int i = 0; i < incomeAmounts.size(); i++) {
            BigDecimal amount = incomeAmounts.get(i);
            String label = "Week " + (i + 1);

            incomeSeries.set(label, amount);
        }
        for (int i = 0; i < expenseAmounts.size(); i++) {
            BigDecimal amount = expenseAmounts.get(i);
            String label = "Week " + (i + 1);

            expenseSeries.set(label, amount);
        }
        barChartModel.addSeries(incomeSeries);
        barChartModel.addSeries(expenseSeries);
        barChartModel.setTitle("Income vs Expense Report (Weekly)");
        barChartModel.setLegendPosition("ne");

        Axis xAxis = new CategoryAxis("Week");
        barChartModel.getAxes().put(AxisType.X, xAxis);

        Axis yAxis = barChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Amount");
        yAxis.setMin(0);
    }

    private void createMonthlyBarChartModel() {
        ChartSeries incomeSeries = new ChartSeries();
        incomeSeries.setLabel("Income");

        ChartSeries expenseSeries = new ChartSeries();
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

        barChartModel.addSeries(incomeSeries);
        barChartModel.addSeries(expenseSeries);
        barChartModel.setTitle("Income vs Expense Report (Monthly)");
        barChartModel.setLegendPosition("ne");

        Axis xAxis = new CategoryAxis("Month");
        barChartModel.getAxes().put(AxisType.X, xAxis);

        Axis yAxis = barChartModel.getAxis(AxisType.Y);
        yAxis.setLabel("Amount");
        yAxis.setMin(0);
    }
    private String getMonthFromDate(Date date) {
        if (date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int monthIndex = calendar.get(Calendar.MONTH);
        return new DateFormatSymbols().getMonths()[monthIndex];
    }
}
