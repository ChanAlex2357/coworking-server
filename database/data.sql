
-- Insertion des rôles
INSERT INTO RoleUtilisateur (id, libelle) VALUES 
    (get_role_user_seq(), 'ADMIN'),
    (get_role_user_seq(), 'CLIENT');

-- Insertion des utilisateurs
INSERT INTO Utilisateur (id, nom,contact) VALUES 
    (get_utilisateur_seq(), 'Admin Principal','11111111'),    -- admin
    (get_utilisateur_seq(), 'Jean Client','22222222'),        -- client 1
    (get_utilisateur_seq(), 'Marie Cliente','33333333');      -- client 2

-- Insertion des comptes
-- Note: remplacer les références (USR_1, ROLE_1, etc.) par les IDs réels générés
INSERT INTO Account (id, login, password, idUtilisateur, idRole) 
SELECT 
    get_account_seq(),
    'admin',
    'admin',
    u.id,
    r.id
FROM Utilisateur u, RoleUtilisateur r 
WHERE u.nom = 'Admin Principal' AND r.libelle = 'ADMIN';

INSERT INTO Account (id, login, password, idUtilisateur, idRole) 
SELECT 
    get_account_seq(),
    'jean@client.com',
    'client123',
    u.id,
    r.id
FROM Utilisateur u, RoleUtilisateur r 
WHERE u.nom = 'Jean Client' AND r.libelle = 'CLIENT';

INSERT INTO Account (id, login, password, idUtilisateur, idRole) 
SELECT 
    get_account_seq(),
    'marie@client.com',
    'client123',
    u.id,
    r.id
FROM Utilisateur u, RoleUtilisateur r 
WHERE u.nom = 'Marie Cliente' AND r.libelle = 'CLIENT';
