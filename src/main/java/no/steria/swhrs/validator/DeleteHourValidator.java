package no.steria.swhrs.validator;

import no.steria.swhrs.RegistrationConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 28.09.12
 * Time: 12:25
 * All rights reserved Steria AS 2012
 *
 * @author chrm@steria.no
 */
public class DeleteHourValidator extends Validator {

    private static final List<String> mandatoryFields = new ArrayList<String>();
    private static final List<String> optionalFields = new ArrayList<String>();

    static {
        mandatoryFields.add(RegistrationConstants.TASK_NUMBER);
    }

    public List<String> getMandatoryFields() {
        return mandatoryFields;
    }

    public List<String> getOptionalFields() {
        return optionalFields;
    }
}
