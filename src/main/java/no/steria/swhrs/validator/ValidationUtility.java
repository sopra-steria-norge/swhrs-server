package no.steria.swhrs.validator;

import no.steria.swhrs.util.RegistrationConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 28.09.12
 * Time: 09:04
 * All rights reserved Steria AS 2012
 *
 * @author chrm@steria.no
 */
public class ValidationUtility {

    private final static Map<String, ValidationMethod> validationMethods = new HashMap<String, ValidationMethod>();

    static {
        validationMethods.put(RegistrationConstants.PROJECT_NUMBER, new ProjectNumber());
        validationMethods.put(RegistrationConstants.ACTIVITY_CODE, new ActivityCode());
        validationMethods.put(RegistrationConstants.DESCRIPTION, new Description());
        validationMethods.put(RegistrationConstants.TASK_NUMBER, new TaskNumber());
        validationMethods.put(RegistrationConstants.HOURS, new Hours());
        validationMethods.put(RegistrationConstants.BILLABLE, new Billable());
        validationMethods.put(RegistrationConstants.DATE, new Date());
        validationMethods.put(RegistrationConstants.WORK_TYPE, new WorkType());
        validationMethods.put(RegistrationConstants.CUSTOMER_NAME, new CustomerName());
        validationMethods.put(RegistrationConstants.SEARCH, new Search());
    }

    public static boolean isValid(String inputField, String inputValue) {
        ValidationMethod validationMethod = validationMethods.get(inputField);
        return validationMethod != null && validationMethod.validate(inputValue);
    }

    public static interface ValidationMethod {
        boolean validate(String inputField);
    }

    private static class ProjectNumber implements ValidationMethod {
        @Override
        public boolean validate(String inputValue) {
            return inputValue.matches("^.{1,20}$");
        }
    }

    private static class ActivityCode implements ValidationMethod {
        @Override
        public boolean validate(String inputValue) {
            return inputValue.matches("^.{1,10}$");
        }
    }

    private static class Description implements ValidationMethod {
        @Override
        public boolean validate(String inputField) {
            return inputField.matches("^.{1,250}$");
        }
    }

    private static class TaskNumber implements ValidationMethod {
        @Override
        public boolean validate(String inputValue) {
            try {
                Integer.parseInt(inputValue);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
    }

    private static class Hours implements ValidationMethod {
        @Override
        public boolean validate(String inputValue) {
            try {
                Double.parseDouble(inputValue);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
    }

    private static class Billable implements ValidationMethod {
        @Override
        public boolean validate(String inputValue) {
            Boolean.valueOf(inputValue);
            return true;
        }
    }

    private static class Date implements ValidationMethod {
        @Override
        public boolean validate(String inputValue) {
            try {
                RegistrationConstants.dateTimeFormatter.parseDateTime(inputValue);
            } catch (IllegalArgumentException e) {
                return false;
            }
            return true;
        }
    }

    private static class WorkType implements ValidationMethod {
        @Override
        public boolean validate(String inputValue) {
            return inputValue.matches("^.{1,10}$");
        }
    }

    private static class CustomerName implements ValidationMethod {
        @Override
        public boolean validate(String inputValue) {
            return inputValue.matches("^.{1,10}$");
        }
    }

    private static class Search implements ValidationMethod {
        @Override
        public boolean validate(String inputValue) {
            return inputValue.matches("^.{1,10}$");
        }
    }
}
