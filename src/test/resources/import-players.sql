/** STATS **/
INSERT INTO public.stats
(id, attack, defense, stamina)
VALUES
    (1, 20, 30, 25),
    (2, 20, 30, 25),
    (3, 20, 30, 25);

/** PLAYERS **/
INSERT INTO public.player
    (id, first_name, stats_id)
VALUES
    (1, 'Miguel Sánchez', 1),
    (2, 'Alejandro Rodriguez', 2),
    (3, 'Kevin Madriz', 3);


