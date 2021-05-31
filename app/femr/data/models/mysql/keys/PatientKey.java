package femr.data.models.mysql.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PatientKey implements Serializable {
    @Column(name = "id", unique = true, nullable = false)
    private int patientId;
    @Column(name = "kit_id", nullable = false)
    private int kitId;

    public PatientKey(int patientId, int kitId) {
        this.patientId = patientId;
        this.kitId = kitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientKey that = (PatientKey) o;
        return patientId == that.patientId &&
                kitId == that.kitId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, kitId);
    }

    /**
     * Getter for patient id.
     *
     * @return int patient id is a non-unique number patients are assigned by the kit
     *             A compound key of (patient id, kit id) is a globally unique identifier for
     *             a patient
     */
    public int getPatientId() {
        return patientId;
    }

    /**
     * Setter for patient id.
     *
     * @param id int patient id is a non-unique number patients are assigned by the kit
     *      *             A compound key of (patient id, kit id) is a globally unique identifier for
     *      *             a patient
     */
    public void setPatientId(int id) {
        patientId = id;
    }

    /**
     * Getter for kit id.
     *
     * @return int kit id is the identifying number of the kit (similar to mission)
     */
    public int getKitId() {
        return kitId;
    }

    /**
     * Setter for kit id.
     *
     * @param kitId int kit id is the identifying number of the kit (similar to mission)
     */
    public void setKitId(int kitId) {
        this.kitId = kitId;
    }
}