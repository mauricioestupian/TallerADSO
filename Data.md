use taller1;

INSERT INTO cargo (id, cargo) VALUES
(1, 'Desarrollador Backend'),
(2, 'Analista de Datos'),
(3, 'Scrum Master'),
(4, 'Arquitecto de Software');

INSERT INTO oficina (id, nombre) VALUES
(1, '201'),
(2, '202'),
(3, '203'),
(4, 'Presidencia');

INSERT INTO empleado ( nom, ape, dir, tel, cargo_id, oficina_id) VALUES
( 'Mauricio', 'Gómez', 'Cra 45 #12-34', '3101234567', 1, 1),
( 'Laura', 'Martínez', 'Av 68 #45-67', '3209876543', 2, 2),
( 'Carlos', 'Ruiz', 'Calle 10 #20-30', '3004567890', 3, 3);

INSERT INTO proyecto (id, nombre, presupuesto, fecha_inicio, fecha_fin) VALUES
(1, 'Sistema de Inventario', 1500000.00, '2025-01-15', '2025-04-30'),
(2, 'Plataforma de Aprendizaje', 3200000.00, '2025-02-01', '2025-06-15'),
(3, 'App de Gestión de Tareas', 950000.00, '2025-03-10', '2025-05-20'),
(4, 'Migración a Microservicios', 5000000.00, '2025-04-01', '2025-09-30'),
(5, 'Dashboard de KPIs', 1800000.00, '2025-05-05', '2025-07-25');
