package no.steria.swhrs.validator;

import no.steria.swhrs.util.RegistrationConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 28.09.12
 * Time: 08:32
 * All rights reserved Steria AS 2012
 *
 * @author chrm@steria.no
 */
public class Validators {

    private final static Map<String,Validator> validators = new HashMap<String, Validator>();

    static {
        validators.put(RegistrationConstants.REQUEST_URL_HOURS_ADD, new AddHourValidator());
        validators.put(RegistrationConstants.REQUEST_URL_HOURS_DELETE, new DeleteHourValidator());
        validators.put(RegistrationConstants.REQUEST_URL_HOURS_UPDATE, new UpdateHourValidator());
        validators.put(RegistrationConstants.REQUEST_URL_HOURS_RETRIEVE_DAY, new GetDayListValidator());
        validators.put(RegistrationConstants.REQUEST_URL_HOURS_RETRIEVE_WEEK, new GetWeekListValidator());
        validators.put(RegistrationConstants.REQUEST_URL_FAVORITE_ADD, new AddFavoriteValidator());
        validators.put(RegistrationConstants.REQUEST_URL_FAVORITE_GET, new GetFavoritesValidator());
        validators.put(RegistrationConstants.REQUEST_URL_FAVORITE_DELETE, new DeleteFavoriteValidator());
        validators.put(RegistrationConstants.REQUEST_URL_FAVORITE_SEARCH, new SearchFavoritesValidator());
        validators.put(RegistrationConstants.REQUEST_URL_REOPEN, new ReopenPeriodValidator());
        validators.put(RegistrationConstants.REQUEST_URL_SUBMIT, new SubmitPeriodValidator());
    }

    public static boolean hasValidationError(String methodToValidate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Validator validator = validators.get(methodToValidate);
        return !validator.isValid(request, response);
    }
}
