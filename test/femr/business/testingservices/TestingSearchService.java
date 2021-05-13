import femr.business.services;
public class TestingSearchService extends SearchService {
  protected Float findPatientWeight(IRepository<IPatientEncounterVital> patientEncounterVitalRepository, int encounterId){
    return 0.0;
  }

  protected Integer findWeeksPregnant(IRepository<IPatientEncounterVital> patientEncounterVitalRepository, int encounterId){
    return 0;
  }

  protected Integer findPatientHeightFeet(IRepository<IPatientEncounterVital> patientEncounterVitalRepository, int encounterId){
    return 0;
  }

  protected Integer findPatientHeightInches(IRepository<IPatientEncounterVital> patientEncounterVitalRepository, int encounterId) {
    return 0;
  }

  protected Integer findPatientSmoker(IRepository<IPatientEncounterVital> patientEncounterVitalRepository, int encounterId) {
    return 0;
  }

  protected Integer findPatientDiabetic(IRepository<IPatientEncounterVital> patientEncounterVitalRepository, int encounterId){
    return 0;
  }

  protected Integer findPatientAlcohol(IRepository<IPatientEncounterVital> patientEncounterVitalRepository, int encounterId){
    return 0;
  }

  protected Integer findPatientCholesterol(IRepository<IPatientEncounterVital> patientEncounterVitalRepository, int encounterId){
    return 0;
  }

  protected Integer findPatientHypertension(IRepository<IPatientEncounterVital> patientEncounterVitalRepository, int encounterId){
    return 0;
  }
}