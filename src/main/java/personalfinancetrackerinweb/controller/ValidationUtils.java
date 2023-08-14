package personalfinancetrackerinweb.controller; 

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class ValidationUtils {

    public boolean isCurrentDate(Date date) {
        Calendar currentDate = Calendar.getInstance();
        Calendar inputDate = Calendar.getInstance();
        inputDate.setTime(date);
        return currentDate.get(Calendar.YEAR) == inputDate.get(Calendar.YEAR) &&
                currentDate.get(Calendar.DAY_OF_YEAR) == inputDate.get(Calendar.DAY_OF_YEAR);
    }

    public boolean isValidAmount(BigDecimal amount) {
    return amount != null && amount.compareTo(BigDecimal.ZERO) > 0 && amount.toString().matches("\\d+(\\.\\d{1,2})?");
}
}
