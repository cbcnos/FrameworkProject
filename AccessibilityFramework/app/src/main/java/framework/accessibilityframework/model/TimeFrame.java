package framework.accessibilityframework.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Olibario
 * enables a particular time frame to be described as either a point in time or a time interval.
 */
public class TimeFrame {
    private Date startDate; //cannot be NULL
    private Date endDate; //cannot be NULL

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) throws ParseException, NullPointerException {
        if (startDate != null) {
            SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
            this.startDate = dt.parse(dt.format(startDate));
        }
        else throw new NullPointerException();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) throws ParseException, NullPointerException {
        if (endDate != null) {
            SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
            this.endDate = dt.parse(dt.format(endDate));
        }
        else throw new NullPointerException();
    }
}
