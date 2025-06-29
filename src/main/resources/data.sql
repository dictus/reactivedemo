INSERT INTO event (id, name, location, cost, duration) VALUES
(1, 'Power Outage', 'antarctica', 100, 5),
(2, 'Generator Test', 'antarctica', 80, 4),
(3, 'Ice Storm', 'antarctica', 90, 3),
(4, 'Solar Flare', 'antarctica', 400, 10),
(5, 'Wind Event', 'antarctica', 500, 5),
(6, 'Blizzard', 'antarctica', 300, 8),
--(7, 'Heat Wave', null, 100, 5),           -- Invalid: null location
--(8, null, 'africa', 200, 10),             -- Invalid: null name
(9, 'Sandstorm', 'africa', 100, 2),
(10, 'Rainstorm', 'africa', 150, 3);
