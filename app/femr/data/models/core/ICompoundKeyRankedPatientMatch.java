package femr.data.models.core;

import femr.data.models.mysql.PatientEncounter;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

/**
 * ICompoundKeyRankedPatientMatch represents a RankedPatientMatch
 * where the patient is using a compound primary key of id and kit id.
 *
 * This class/interface is being used in the short term to make the sql columnMapping
 * easy/work in patient repository retrievePatientMatchesFromTriageFields. This class is just
 * Patient.java and RankedPatientMatch.java copied into one class. It would be preferable
 * to use RankedPatientMatch which has a Patient Object as an attribute instead of copying
 * all of patient into RankedPatientMatch to create this new class, but I wasn't able to
 * get the column mapping to worked with the compound primary key.
 * So the compound key from the table will be mapped to patientId and kitId which are two
 * separate variables, which will then be used to populate RankedPatientItem and PatientItem.
 */
public interface ICompoundKeyRankedPatientMatch {

    int getId();

    int getKitId();

    void setKitId(int kitId);

    int getUserId();

    void setUserId(int userId);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    Date getAge();

    void setAge(Date age);

    String getSex();

    void setSex(String sex);

    String getAddress();

    void setAddress(String address);

    String getCity();

    void setCity(String city);

    void setId(int id);

    IPhoto getPhoto();

    void setPhoto(IPhoto photo);

    List<PatientEncounter> getPatientEncounters();

    void setPatientEncounters(List<PatientEncounter> patientEncounters);

    DateTime getIsDeleted();

    void setIsDeleted(DateTime isDeleted);

    Integer getDeletedByUserId() ;

    void setDeletedByUserId(Integer userId) ;

    String getReasonDeleted() ;

    void setReasonDeleted(String reason) ;

    Integer getRank();

    void setRank(int rank);
}
