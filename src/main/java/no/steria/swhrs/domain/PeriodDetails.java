package no.steria.swhrs.domain;

import org.joda.time.DateTime;

/**
 * User: chrm@steria.no
 * Date: 19.09.12
 * Time: 11:07
 * All rights reserved Steria AS 2012
 */
public class PeriodDetails {

    private DateTime startDate;
    private DateTime endDate;
    private String periodDescription;
    private Integer normTime;

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setPeriodDescription(String periodDescription) {
        this.periodDescription = periodDescription;
    }

    public String getPeriodDescription() {
        return periodDescription;
    }

    public void setNormTime(Integer normTime) {
        this.normTime = normTime;
    }

    public Integer getNormTime() {
        return normTime;
    }
}
