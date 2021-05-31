# --- !Ups
ALTER TABLE `patients` DROP PRIMARY KEY;

ALTER TABLE `patients`
    ADD COLUMN `kit_id` INT NOT NULL AFTER `id`,
    ADD PRIMARY KEY  (`id`, `kit_id`);

# --- !Downs

ALTER TABLE `patients`
    DROP PRIMARY KEY,
    DROP COLUMN `kit_id`,
    ADD PRIMARY KEY (`id`);