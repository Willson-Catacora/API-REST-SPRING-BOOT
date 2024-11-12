ALTER TABLE medicos ADD COLUMN activo tinyint;
UPDATE medicos SET  activo = 1;