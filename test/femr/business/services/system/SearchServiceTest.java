package femr.business.services.system;

import com.google.inject.name.Named;
import femr.business.helpers.QueryProvider;
import femr.business.services.core.IInventoryService;
import femr.common.IItemModelMapper;
import femr.common.ItemModelMapper;
import femr.common.dtos.ServiceResponse;
import femr.common.models.RankedPatientItem;
import femr.data.daos.IRepository;
import femr.data.daos.core.IEncounterRepository;
import femr.data.daos.core.IPatientRepository;
import femr.data.daos.core.IPrescriptionRepository;
import femr.data.models.core.*;
import femr.data.models.mysql.CompoundKeyRankedPatientMatch;
import femr.data.models.mysql.RankedPatientMatch;
import io.ebean.Query;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.Mockito;
import play.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchServiceTest {

    IRepository<IConceptDiagnosis> diagnosisRepository;
    IRepository<IMissionTrip> missionTripRepository;
    IPatientRepository patientRepository;
    IEncounterRepository patientEncounterRepository;
    IRepository<IPatientEncounterVital> patientEncounterVitalRepository;
    IPrescriptionRepository prescriptionRepository;
    IRepository<ISystemSetting> systemSettingRepository;
    IInventoryService inventoryService;
    IRepository<IMissionCity> cityRepository;
    IItemModelMapper itemModelMapper;

    SearchService searchService;


    @Before
    public void setUp() {

        diagnosisRepository = Mockito.mock(IRepository.class);
        missionTripRepository = Mockito.mock(IRepository.class);
        patientRepository = Mockito.mock(IPatientRepository.class);
        patientEncounterRepository = Mockito.mock(IEncounterRepository.class);
        patientEncounterVitalRepository = Mockito.mock(IRepository.class);
        prescriptionRepository = Mockito.mock(IPrescriptionRepository.class);
        systemSettingRepository = Mockito.mock(IRepository.class);
        inventoryService = Mockito.mock(IInventoryService.class);
        cityRepository = Mockito.mock(IRepository.class);
        itemModelMapper = new ItemModelMapper();

        searchService = new SearchService(
                diagnosisRepository,
                missionTripRepository,
                patientRepository,
                patientEncounterRepository,
                patientEncounterVitalRepository,
                prescriptionRepository,
                systemSettingRepository,
                inventoryService,
                cityRepository,
                itemModelMapper
        );
    }

    @Test
    public void retrievePatientsFromTriageSearchTest() {
        ServiceResponse<List<RankedPatientItem>> response = new ServiceResponse<>();

        ICompoundKeyRankedPatientMatch patient = new CompoundKeyRankedPatientMatch(120);
        patient.setId(1);
        patient.setKitId(1);
        patient.setAddress("CalPoly");
        patient.setAge(new Date(1996, 02, 11));
        patient.setFirstName("Grant");
        patient.setLastName("Ludwig");
        patient.setPhoneNumber("0123456789");
        patient.setCity("San Luis Obispo");

        List<ICompoundKeyRankedPatientMatch> rankedPatients = new ArrayList<>();
        rankedPatients.add(patient);
        Mockito.doReturn(rankedPatients).when(patientRepository).retrieveCompoundPatientMatchesFromTriageFields(
                "Grant",
                "Ludwig",
                "0123456789",
                "CalPoly",
                "Male",
                (long) 25,
                "San Luis Obispo");

        ServiceResponse<List<RankedPatientItem>> answer = searchService.retrievePatientsFromTriageSearch(
                "Grant",
                "Ludwig",
                "0123456789",
                "CalPoly",
                "Male",
                (long) 25,
                "San Luis Obispo");

        assertEquals(answer.getResponseObject().get(0).getPatientItem().getFirstName(), "Grant");
        assertEquals(answer.getResponseObject().get(0).getPatientItem().getLastName(), "Ludwig");
        assertEquals(answer.getResponseObject().get(0).getRank(), new Integer(120));
    }

}