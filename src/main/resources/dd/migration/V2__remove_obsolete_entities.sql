DO $$
BEGIN
   IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'predonation_checkup') THEN
      DROP TABLE predonation_checkup CASCADE;
   END IF;

   IF EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'old_unused_table') THEN
      DROP TABLE old_unused_table CASCADE;
   END IF;
END$$;
