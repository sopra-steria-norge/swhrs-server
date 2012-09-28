package no.steria.swhrs.validator;

import no.steria.swhrs.RegistrationConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 28.09.12
 * Time: 12:15
 * All rights reserved Steria AS 2012
 *
 * @author chrm@steria.no
 */
public class UpdateHourValidator extends Validator {

    private static final List<String> mandatoryFields = new ArrayList<String>();
    private static final List<String> optionalFields = new ArrayList<String>();

    static {
        mandatoryFields.add(RegistrationConstants.TASK_NUMBER);
        mandatoryFields.add(RegistrationConstants.PROJECT_NUMBER);
        mandatoryFields.add(RegistrationConstants.ACTIVITY_CODE);
        mandatoryFields.add(RegistrationConstants.HOURS);
        mandatoryFields.add(RegistrationConstants.DATE);
        mandatoryFields.add(RegistrationConstants.WORK_TYPE);

        optionalFields.add(RegistrationConstants.DESCRIPTION);
        optionalFields.add(RegistrationConstants.BILLABLE);
    }

    public List<String> getMandatoryFields() {
        return mandatoryFields;
    }

    public List<String> getOptionalFields() {
        return optionalFields;
    }
}
