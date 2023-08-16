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
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

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
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;
import personalfinancetrackerinweb.repository.IncomeRepositoryImpl;
import personalfinancetrackerinweb.repository.UserRepository;

@Named
@ViewScoped
public class ExpenseIncomeChartController implements Serializable {

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @Inject
    private UserRepository userRepository;

    private User user;

    private Income income;

    private List<Income> incomeList;

    private List<Category> categoryList;
    private List<Category> incmList;

    public List<Category> getIncmList() {
        return incmList;
    }

    public void setIncmList(List<Category> incmList) {
        this.incmList = incmList;
    }

    private LineChartModel lineChartModel;
    private BarChartModel barChartModel;

    private String chartType;

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

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    @PostConstruct
    public void init() {
        income = new Income();
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        User incomeUser = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
       

        categoryList = categoryRepositoryImpl.findByCategoryType(incomeUser, CategoryType.EXPENSE);
        incmList = categoryRepositoryImpl.findByCategoryType(incomeUser, INCOME);
        
        incomeList = incomeRepositoryImpl.findByUser(incomeUser.getId());
                

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

        Map<Integer, BigDecimal> incomeWeeklyAmounts = new HashMap<>();
        Map<Integer, BigDecimal> expenseWeeklyAmounts = new HashMap<>();

        for (Category incomeCategory : incmList) {
            for (Income income : incomeList) {
                if (income.getCategory().equals(incomeCategory)) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(income.getDate());
                    int week = calendar.get(Calendar.WEEK_OF_YEAR);

                    BigDecimal amount = incomeWeeklyAmounts.getOrDefault(week, BigDecimal.ZERO);
                    amount = amount.add(income.getAmount());
                    incomeWeeklyAmounts.put(week, amount);
                }
            }
        }

        for (Category expenseCategory : categoryList) {
            for (Income expense : incomeList) {
                if (expense.getCategory().equals(expenseCategory)) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(expense.getDate());
                    int week = calendar.get(Calendar.WEEK_OF_YEAR);

                    BigDecimal amount = expenseWeeklyAmounts.getOrDefault(week, BigDecimal.ZERO);
                    amount = amount.add(expense.getAmount());
                    expenseWeeklyAmounts.put(week, amount);
                }
            }
        }

        // Populate the chart series with calculated amounts
        for (Integer week : incomeWeeklyAmounts.keySet()) {
            incomeSeries.set("Week " + week, incomeWeeklyAmounts.get(week));
        }

        for (Integer week : expenseWeeklyAmounts.keySet()) {
            expenseSeries.set("Week " + week, expenseWeeklyAmounts.get(week));
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

        Map<String, BigDecimal> incomeMonthlyAmounts = new HashMap<>();
        Map<String, BigDecimal> expenseMonthlyAmounts = new HashMap<>();

        for (Category incomeCategory : incmList) {
            for (Income income : incomeList) {
                if (income.getCategory().equals(incomeCategory)) {
                    String month = getMonthFromDate(income.getDate());
                    BigDecimal amount = incomeMonthlyAmounts.getOrDefault(month, BigDecimal.ZERO);
                    amount = amount.add(income.getAmount());
                    incomeMonthlyAmounts.put(month, amount);
                }
            }
        }

        for (Category expenseCategory : categoryList) {
            for (Income expense : incomeList) {
                if (expense.getCategory().equals(expenseCategory)) {
                    String month = getMonthFromDate(expense.getDate());
                    BigDecimal amount = expenseMonthlyAmounts.getOrDefault(month, BigDecimal.ZERO);
                    amount = amount.add(expense.getAmount());
                    expenseMonthlyAmounts.put(month, amount);
                }
            }
        }

        // Populate the chart series with calculated amounts
        for (String month : incomeMonthlyAmounts.keySet()) {
            incomeSeries.set(month, incomeMonthlyAmounts.get(month));
        }

        for (String month : expenseMonthlyAmounts.keySet()) {
            expenseSeries.set(month, expenseMonthlyAmounts.get(month));
        }

        // Add the series to the chart model
        lineChartModel.addSeries(incomeSeries);
        lineChartModel.addSeries(expenseSeries);

        // Configure other chart properties as needed
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

        Map<Integer, BigDecimal> incomeWeeklyAmounts = new HashMap<>();
        Map<Integer, BigDecimal> expenseWeeklyAmounts = new HashMap<>();

        for (Category incomeCategory : incmList) {
            for (Income income : incomeList) {
                if (income.getCategory().equals(incomeCategory)) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(income.getDate());
                    int week = calendar.get(Calendar.WEEK_OF_YEAR);

                    BigDecimal amount = incomeWeeklyAmounts.getOrDefault(week, BigDecimal.ZERO);
                    amount = amount.add(income.getAmount());
                    incomeWeeklyAmounts.put(week, amount);
                }
            }
        }

        for (Category expenseCategory : categoryList) {
            for (Income expense : incomeList) {
                if (expense.getCategory().equals(expenseCategory)) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(expense.getDate());
                    int week = calendar.get(Calendar.WEEK_OF_YEAR);

                    BigDecimal amount = expenseWeeklyAmounts.getOrDefault(week, BigDecimal.ZERO);
                    amount = amount.add(expense.getAmount());
                    expenseWeeklyAmounts.put(week, amount);
                }
            }
        }

        // Populate the chart series with calculated amounts
        for (Integer week : incomeWeeklyAmounts.keySet()) {
            incomeSeries.set("Week " + week, incomeWeeklyAmounts.get(week));
        }

        for (Integer week : expenseWeeklyAmounts.keySet()) {
            expenseSeries.set("Week " + week, expenseWeeklyAmounts.get(week));
        }

        // Add the series to the chart model
        barChartModel.addSeries(incomeSeries);
        barChartModel.addSeries(expenseSeries);

        // Configure other chart properties as needed
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

        Map<String, BigDecimal> incomeMonthlyAmounts = new HashMap<>();
        Map<String, BigDecimal> expenseMonthlyAmounts = new HashMap<>();

        for (Category incomeCategory : incmList) {
            for (Income income : incomeList) {
                if (income.getCategory().equals(incomeCategory)) {
                    String month = getMonthFromDate(income.getDate());
                    BigDecimal amount = incomeMonthlyAmounts.getOrDefault(month, BigDecimal.ZERO);
                    amount = amount.add(income.getAmount());
                    incomeMonthlyAmounts.put(month, amount);
                }
            }
        }

        for (Category expenseCategory : categoryList) {
            for (Income expense : incomeList) {
                if (expense.getCategory().equals(expenseCategory)) {
                    String month = getMonthFromDate(expense.getDate());
                    BigDecimal amount = expenseMonthlyAmounts.getOrDefault(month, BigDecimal.ZERO);
                    amount = amount.add(expense.getAmount());
                    expenseMonthlyAmounts.put(month, amount);
                }
            }
        }

        // Populate the chart series with calculated amounts
        for (String month : incomeMonthlyAmounts.keySet()) {
            incomeSeries.set(month, incomeMonthlyAmounts.get(month));
        }

        for (String month : expenseMonthlyAmounts.keySet()) {
            expenseSeries.set(month, expenseMonthlyAmounts.get(month));
        }

        // Add the series to the chart model
        barChartModel.addSeries(incomeSeries);
        barChartModel.addSeries(expenseSeries);

        // Configure other chart properties as needed
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
