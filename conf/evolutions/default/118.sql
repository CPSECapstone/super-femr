# --- !Ups

ALTER TABLE `patient_encounters`
    DROP INDEX `patient_id_idx`,
    DROP CONSTRAINT `fk_patient_encounter_patient_id_patients_id`;

ALTER TABLE `patient_encounters`
    ADD COLUMN `kit_id` INT NOT NULL AFTER `id`,
    ADD INDEX `patient_id_idx` (`patient_id` ASC, `kit_id`),
    ADD CONSTRAINT `fk_patient_encounter_patient_id_patients_id`
        FOREIGN KEY (`patient_id`, `kit_id`)
        REFERENCES `patients` (`id`, `kit_id`);

# --- !Downs

ALTER TABLE `patient_encounters`
    DROP CONSTRAINT `fk_patient_encounter_patient_id_patients_id`,
    DROP INDEX `patient_id_idx`,
    DROP COLUMN `kit_id`;

ALTER TABLE `patient_encounters`
    ADD INDEX `patient_id_idx` (`patient_id` ASC),
    ADD CONSTRAINT `fk_patient_encounter_patient_id_patients_id`
        FOREIGN KEY (`patient_id`)
        REFERENCES `patients` (`id`);
