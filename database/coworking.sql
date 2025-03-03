CREATE TABLE Utilisateur(
   id VARCHAR(255) ,
   nom VARCHAR(255) ,
   PRIMARY KEY(id)
);

CREATE TABLE RoleUtilisateur(
   id VARCHAR(255) ,
   libelle VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE Account(
   id VARCHAR(255) ,
   login VARCHAR(255)  NOT NULL,
   password VARCHAR(255)  NOT NULL,
   idUtilisateur VARCHAR(255)  NOT NULL,
   idRole VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idUtilisateur) REFERENCES Utilisateur(id),
   FOREIGN KEY(idRole) REFERENCES RoleUtilisateur(id)
);

CREATE TABLE Option(
   id VARCHAR(255) ,
   option VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE OptionPrix(
   id VARCHAR(255) ,
   datePrix DATE NOT NULL,
   pu NUMERIC(15,2)   NOT NULL,
   idOption VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idOption) REFERENCES Option(id)
);

CREATE TABLE Espace(
   id VARCHAR(255) ,
   nom VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE PrixEspace(
   id VARCHAR(255) ,
   datePrix DATE NOT NULL,
   prixHeure NUMERIC(15,2)   NOT NULL,
   idEspace VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idEspace) REFERENCES Espace(id)
);

CREATE TABLE UtilisateurContact(
   id VARCHAR(255) ,
   contact VARCHAR(255)  NOT NULL,
   idUtilisateur VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idUtilisateur) REFERENCES Utilisateur(id)
);

CREATE TABLE Reservation(
   id VARCHAR(255) ,
   dateReservation DATE NOT NULL,
   duree INTEGER NOT NULL,
   montant NUMERIC(15,2)   NOT NULL,
   idClient VARCHAR(255)  NOT NULL,
   idEspace VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idClient) REFERENCES Utilisateur(id),
   FOREIGN KEY(idEspace) REFERENCES Espace(id)
);

CREATE TABLE ReservationOption(
   id VARCHAR(255) ,
   pu NUMERIC(15,2)   NOT NULL,
   idOption VARCHAR(255)  NOT NULL,
   idReservation VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idOption) REFERENCES Option(id),
   FOREIGN KEY(idReservation) REFERENCES Reservation(id)
);

CREATE TABLE Creneau(
   id VARCHAR(255) ,
   heureDebut INTEGER NOT NULL,
   heureFin INTEGER NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE Paiement(
   id VARCHAR(255) ,
   datePaiement DATE NOT NULL,
   idReservation VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idReservation) REFERENCES Reservation(id)
);

CREATE TABLE ReservationDetails(
   id VARCHAR(255) ,
   pu NUMERIC(15,2)   NOT NULL,
   idCreneau VARCHAR(255)  NOT NULL,
   idReservation VARCHAR(255)  NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(idCreneau) REFERENCES Creneau(id),
   FOREIGN KEY(idReservation) REFERENCES Reservation(id)
);
