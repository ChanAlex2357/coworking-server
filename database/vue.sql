
CREATE or replace view reservation_espace as
SELECT 
    r.*,
    e.nom as espace
FROM espace e 
join reservation r on e.id = r.idespace

CREATE or replace view reservation_details_cpl as
SELECT 
    re.id as idReservation,
    re.idespace as idEspace,
    re.dateReservation,
    re.espace as espace,
    rd.id as idReservationDetails,
    re.etat as etat,
    rd.idcreneau as idCreneau,
    c.heureDebut,
    c.heureFin
from reservationdetails rd
join reservation_espace re on rd.idreservation = re.id
join creneau c on rd.idcreneau = c.id;

CREATE or replace view paiement_reservation as
SELECT 
    p.*,
    r.dateReservation,
    r.espace as espace
from paiement p
join reservation_espace r on p.idreservation = r.id;




SELECT
    c.*,
    rdc.dateReservation,
    rdc.espace as espace,
    rdc.etat as etat
from creneau as c
LEFT JOIN (
    SELECT * from 
    reservation_details_cpl r
    WHERE dateReservation = '2025-01-14' and espace = 'or'
) rdc on rdc.idcreneau = c.id
;

SELECT
    c.id as idcreneau,
    c.heureDebut,
    c.heureFin,
    e.id as idespace,
    e.nom
from creneau c
NATURAl JOIN espace e;