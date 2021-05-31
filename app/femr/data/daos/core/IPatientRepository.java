package femr.data.daos.core;

import femr.data.models.mysql.RankedPatientMatch;
import femr.data.models.core.*;

import java.util.List;

public interface IPatientRepository {

    /**
     * Create a new patient age classification. Be careful with sort order, it needs to be unique
     *
     * @param name name of the classification, not null
     * @param description description of the classification (e.g. 0-1, 2-12), not null
     * @param sortOrder sort order of the classification, not null, unique
     * @return the new patient age classification or null if errors happen
     */
    IPatientAgeClassification createPatientAgeClassification(String name, String description, int sortOrder);

    /**
     * Retrieve list of all patient age classificaitons. Does not take into consideration if
     * they are deleted.
     * Performs ascending sort by SortOrder column.
     *
     * @return list of sorted age classifications or NULL if an error occurs
     */
    List<? extends IPatientAgeClassification> retrieveAllPatientAgeClassifications();

    /**
     * Retrieve list of all patient age classificaitons that are/are not deleted.
     * Performs ascending sort by SortOrder column.
     *
     * @param isDeleted value for isDeleted
     * @return list of sorted age classifications or NULL if an error occurs
     */
    List<? extends IPatientAgeClassification> retrieveAllPatientAgeClassifications(boolean isDeleted);

    /**
     * Retrieve a patient age classification from the database by its name.
     *
     * @param ageClassification name of the patient age classification, not null
     * @return the patient age classification or NULL if not found or if errors occur
     */
    IPatientAgeClassification retrievePatientAgeClassification(String ageClassification);

    /**
     *  Retrieve list of all patients that have not
     *  been deleted.
     *  @return list containing all patients or NULL if an error occurs
     **/
    List<? extends IPatient> retrieveAllPatients();

    /**
     *  Retrieve list of patients in provided country that
     *  have not been deleted.
     *  @param  country to search by
     *  @return list containing patients in country
     **/
    List<? extends IPatient> retrievePatientsInCountry(String country);

    /**
     *  Retrieve patient that is not deleted
     *  @param  id of the patient
     *  @return the patient with the given ID or NULL if no patients are found
     **/
    IPatient retrievePatientById(Integer id);

    /**
     *  Retrieve list of patients with the given first AND last name. Will also
     *  try to match firstName to all available first names & last names
     *
     *  @param  firstName of the patient or a guess
     *  @param  lastName of the patient, can be null
     *  @return the patient with the given name
     **/
    List<? extends IPatient> retrievePatientsByName(String firstName, String lastName);

    List<? extends IRankedPatientMatch> retrievePatientMatchesFromTriageFields(String firstName, String lastName, String phone, String addr, String gender, Long age, String city);

    /**
     * Retrieve a list of patients that are given a ranking based on how similar the patient is to
     * what was fields inputted in triage
     * These patients also use the compound patient key which is really just having an attribute for patient id
     * and patient key
     *
     * CompoundKeyRankedPatientMatch is a class that should be replaced by a better implementation in the future, but it
     * copies all the code in RankedPatientMatch class as well as the Patient class into one new class to get retrieving
     * ranked patients that use the compound primary key to work. Basically the columnMapping done in PatientRepository
     * wasn't mapping to the compound key (PatientKey.java) in patient, so this is a transition class that has all the
     * attributes as basic data types so that the mapping could work.
     *
     * @return a list of CompoundKeyRankedPatientMatch objects
     *
     */
    List<? extends ICompoundKeyRankedPatientMatch> retrieveCompoundPatientMatchesFromTriageFields(String firstName, String lastName, String phone, String addr, String gender, Long age, String city);

    /**
     *  Retrieve list of patients with the given first AND last name. Will also
     *  try to match firstName to all available first names & last names
     *
     *  @param  phoneNumber of the patient
     *  @return the patient with the given Phone Number
     **/
    List<? extends IPatient> retrievePatientsByPhoneNumber(String phoneNumber);

    /**
     *  Creates OR Updates a patient. If you send an existing patient, this will update their
     *  record. (existing = available id)
     *
     *  @param  patient data object to save
     *  @return the new or updated patient
     **/
    IPatient savePatient(IPatient patient);
}

