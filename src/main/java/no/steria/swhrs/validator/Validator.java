package no.steria.swhrs.validator;

import no.steria.swhrs.util.RegistrationConstants;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Date: 28.09.12
 * Time: 08:50
 * All rights reserved Steria AS 2012
 *
 * @author chrm@steria.no
 */
public abstract class Validator {

    public boolean isValid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        for (String mandatoryInput : getMandatoryFields()) {
            String input = request.getParameter(mandatoryInput);
            if (StringUtils.isBlank(input) || !ValidationUtility.isValid(mandatoryInput, input)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, RegistrationConstants.TEXT_VALIDATION_ERROR + mandatoryInput);
                return false;
            }
        }
        for (String optionalInput : getOptionalFields()) {
            String input = request.getParameter(optionalInput);
            if (StringUtils.isNotBlank(input) && !ValidationUtility.isValid(optionalInput, input)) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, RegistrationConstants.TEXT_VALIDATION_ERROR + optionalInput);
                return false;
            }
        }
        return true;
    }

    protected abstract List<String> getMandatoryFields();
    protected abstract List<String> getOptionalFields();
}
