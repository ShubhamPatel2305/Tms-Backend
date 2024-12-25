-- V2__update_location_fields.sql

-- Add new created_at column before making any other changes
ALTER TABLE location ADD COLUMN created_at BIGINT NOT NULL DEFAULT EXTRACT(EPOCH FROM NOW())::BIGINT;

ALTER TABLE location ALTER COLUMN name SET NOT NULL;
ALTER TABLE location ALTER COLUMN district SET NOT NULL;
ALTER TABLE location ALTER COLUMN taluka SET NOT NULL;


ALTER TABLE location
    RENAME COLUMN contact_no TO contact_number;