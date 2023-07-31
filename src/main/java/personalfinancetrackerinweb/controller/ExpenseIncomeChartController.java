package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import static personalfinancetrackerinweb.model.CategoryType.INCOME;

import personalfinancetrackerinweb.model.Income;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;
import personalfinancetrackerinweb.repository.IncomeRepositoryImpl;

@Named
@ViewScoped
public class ExpenseIncomeChartController implements Serializable {

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    private List<Income> incomeList;
    private List<Category> categoryList;

    private LineChartModel lineChartModel;
    private BarChartModel barChartModel;

    private String chartType;

    // Initialize the maps to store income and expense amounts for each week
    private Map<Integer, BigDecimal> incomeAmountsMap = new HashMap<>();
    private Map<Integer, BigDecimal> expenseAmountsMap = new HashMap<>();

    public IncomeRepositoryImpl getIncomeRepositoryImpl() {
        return incomeRepositoryImpl;
    }

    public void setIncomeRepositoryImpl(IncomeRepositoryImpl incomeRepositoryImpl) {
        this.incomeRepositoryImpl = incomeRepositoryImpl;
    }

    public CategoryRepositoryImpl getCategoryRepositoryImpl() {
        return categoryRepositoryImpl;
    }

    public void setCategoryRepositoryImpl(CategoryRepositoryImpl categoryRepositoryImpl) {
        this.categoryRepositoryImpl = categoryRepositoryImpl;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public LineChartModel getLineChartModel() {
        return lineChartModel;
    }

    public void setLineChartModel(LineChartModel lineChartModel) {
        this.lineChartModel = lineChartModel;
    }

    public BarChartModel getBarChartModel() {
        return barChartModel;
    }

    public void setBarChartModel(BarChartModel barChartModel) {
        this.barChartModel = barChartModel;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    @PostConstruct
    public void init() {
        incomeList = incomeRepositoryImpl.findAll();
        categoryList = categoryRepositoryImpl.findByCategoryType(CategoryType.EXPENSE);
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

        for (Category category : categoryList) {
            for (Income income : incomeList) {
                if (income.getCategory().getType() == CategoryType.INCOME) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(income.getDate());
                    int week = calendar.get(Calendar.WEEK_OF_YEAR);

                    BigDecimal amount = incomeAmountsMap.getOrDefault(week, BigDecimal.ZERO);
                    amount = amount.add(income.getAmount());
                    incomeAmountsMap.put(week, amount);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(income.getDate());
                    int week = calendar.get(Calendar.WEEK_OF_YEAR);

                    BigDecimal amount = expenseAmountsMap.getOrDefault(week, BigDecimal.ZERO);
                    amount = amount.add(income.getAmount());
                    expenseAmountsMap.put(week, amount);
                }
            }
        }
        for (Integer week : incomeAmountsMap.keySet()) {
            BigDecimal amount = incomeAmountsMap.get(week);
            String label = "Week" + week;

            incomeSeries.set(label, amount);
        }

        for (Integer week : expenseAmountsMap.keySet()) {
            BigDecimal amount = expenseAmountsMap.get(week);
            String label = "Week" + week;

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

        for (Category category : categoryList) {
            for (Income income : incomeList) {
                if (income.getCategory().type == INCOME) {
                    String month = getMonthFromDate(income.getDate());
                    BigDecimal amount = income.getAmount();
                    incomeSeries.set(month, amount);

                } else {
                    String month = getMonthFromDate(income.getDate());
                    BigDecimal amount = income.getAmount();
                    expenseSeries.set(month, amount);

                }
            }
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

        if (barChartModel == null) {
            barChartModel = new BarChartModel();
        } else {
            barChartModel.clear();
        }
       //Initialize HashMap object to store the total income amount and total expense amount
        incomeAmountsMap = new HashMap<>();
        expenseAmountsMap = new HashMap<>();
        
        ChartSeries incomeSeries = new ChartSeries();
        incomeSeries.setLabel("Income");
        ChartSeries expenseSeries = new ChartSeries();
        expenseSeries.setLabel("Expense");

       // Create a set to store all unique weeks from both income and expense data
        Set<Integer> allWeeks = new HashSet<>();

        for (Category category : categoryList) {
            for (Income income : incomeList) {
                if (income.getCategory().getType() == CategoryType.INCOME) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(income.getDate());
                    int week = calendar.get(Calendar.WEEK_OF_YEAR);

                    BigDecimal amount = incomeAmountsMap.getOrDefault(week, BigDecimal.ZERO);
                    amount = amount.add(income.getAmount());
                    incomeAmountsMap.put(week, amount);

                    allWeeks.add(week); // Add the week to the set
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(income.getDate());
                    int week = calendar.get(Calendar.WEEK_OF_YEAR);

                    BigDecimal amount = expenseAmountsMap.getOrDefault(week, BigDecimal.ZERO);
                    amount = amount.add(income.getAmount());
                    expenseAmountsMap.put(week, amount);

                    allWeeks.add(week); // Add the week to the set
                }
            }
        }

        // Loop through all weeks in the set and make sure both income and expense maps have an entry for each week
        for (Integer week : allWeeks) {
            BigDecimal incomeAmount = incomeAmountsMap.getOrDefault(week, BigDecimal.ZERO);
            BigDecimal expenseAmount = expenseAmountsMap.getOrDefault(week, BigDecimal.ZERO);

            String label = "Week" + week;
            incomeSeries.set(label, incomeAmount);
            expenseSeries.set(label, expenseAmount);
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

        for (Category category : categoryList) {
            for (Income income : incomeList) {
                if (income.getCategory().type == INCOME) {
                    String month = getMonthFromDate(income.getDate());
                    BigDecimal amount = income.getAmount();
                    incomeSeries.set(month, amount);
                } else {
                    String month = getMonthFromDate(income.getDate());
                    BigDecimal amount = income.getAmount();
                    expenseSeries.set(month, amount);

                }
            }
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
