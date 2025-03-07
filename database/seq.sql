-- Pour RoleUtilisateur
CREATE SEQUENCE IF NOT EXISTS role_user_seq;
CREATE OR REPLACE FUNCTION get_role_user_seq()
RETURNS text AS $$
BEGIN
    RETURN 'ROLE_' || nextval('role_user_seq')::text;
END;
$$ LANGUAGE plpgsql;


-- Pour Account
CREATE SEQUENCE IF NOT EXISTS account_seq;
CREATE OR REPLACE FUNCTION get_account_seq()
RETURNS text AS $$
BEGIN
    RETURN 'ACC_' || nextval('account_seq')::text;
END;
$$ LANGUAGE plpgsql;

-- Pour Utilisateur
CREATE SEQUENCE IF NOT EXISTS utilisateur_seq;
CREATE OR REPLACE FUNCTION get_utilisateur_seq()
RETURNS text AS $$
BEGIN
    RETURN 'USR_' || nextval('utilisateur_seq')::text;
END;
$$ LANGUAGE plpgsql;

-- Pour Espace
CREATE SEQUENCE espace_seq;

CREATE OR REPLACE FUNCTION get_espace_seq()
RETURNS text AS $$
BEGIN
    RETURN 'ESP_' || nextval('espace_seq')::text;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION trigger_espace_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_espace_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER espace_id_trigger
    BEFORE INSERT ON espace
    FOR EACH ROW
    EXECUTE FUNCTION trigger_espace_id();

-- Pour Option
CREATE SEQUENCE IF NOT EXISTS option_seq;
CREATE OR REPLACE FUNCTION get_option_seq()
RETURNS text AS $$
BEGIN
    RETURN 'OPT_' || nextval('option_seq')::text;
END;
$$ LANGUAGE plpgsql;

-- Pour Reservation
CREATE SEQUENCE IF NOT EXISTS reservation_seq;
CREATE OR REPLACE FUNCTION get_reservation_seq()
RETURNS text AS $$
BEGIN
    RETURN 'RES_' || nextval('reservation_seq')::text;
END;
$$ LANGUAGE plpgsql;

-- Pour Creneau
CREATE SEQUENCE IF NOT EXISTS creneau_seq;
CREATE OR REPLACE FUNCTION get_creneau_seq()
RETURNS text AS $$
BEGIN
    RETURN 'CRN_' || nextval('creneau_seq')::text;
END;
$$ LANGUAGE plpgsql;

-- Pour Paiement
CREATE SEQUENCE IF NOT EXISTS paiement_seq;
CREATE OR REPLACE FUNCTION get_paiement_seq()
RETURNS text AS $$
BEGIN
    RETURN 'PAY_' || nextval('paiement_seq')::text;
END;
$$ LANGUAGE plpgsql;

-- Pour ReservationDetails
CREATE SEQUENCE IF NOT EXISTS reservation_details_seq;
CREATE OR REPLACE FUNCTION get_reservation_details_seq()
RETURNS text AS $$
BEGIN
    RETURN 'RDT_' || nextval('reservation_details_seq')::text;
END;
$$ LANGUAGE plpgsql;

-- Pour ReservationOption
CREATE SEQUENCE IF NOT EXISTS reservation_option_seq;
CREATE OR REPLACE FUNCTION get_reservation_option_seq()
RETURNS text AS $$
BEGIN
    RETURN 'ROP_' || nextval('reservation_option_seq')::text;
END;
$$ LANGUAGE plpgsql;

-- Pour PrixEspace
CREATE SEQUENCE prix_espace_seq;

CREATE OR REPLACE FUNCTION get_prix_espace_seq()
RETURNS text AS $$
BEGIN
    RETURN 'PES_' || nextval('prix_espace_seq')::text;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION trigger_prix_espace_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_prix_espace_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER prix_espace_id_trigger
    BEFORE INSERT ON prixespace
    FOR EACH ROW
    EXECUTE FUNCTION trigger_prix_espace_id();

-- Pour OptionPrix
CREATE SEQUENCE option_prix_seq;

CREATE OR REPLACE FUNCTION get_option_prix_seq()
RETURNS text AS $$
BEGIN
    RETURN 'OPX_' || nextval('option_prix_seq')::text;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION trigger_option_prix_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_option_prix_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER option_prix_id_trigger
    BEFORE INSERT ON optionprix
    FOR EACH ROW
    EXECUTE FUNCTION trigger_option_prix_id();

-- Pour Account
CREATE OR REPLACE FUNCTION trigger_account_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_account_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER account_id_trigger
    BEFORE INSERT ON account
    FOR EACH ROW
    EXECUTE FUNCTION trigger_account_id();

-- Pour Creneau
CREATE OR REPLACE FUNCTION trigger_creneau_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_creneau_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER creneau_id_trigger
    BEFORE INSERT ON creneau
    FOR EACH ROW
    EXECUTE FUNCTION trigger_creneau_id();

-- Pour Option
CREATE OR REPLACE FUNCTION trigger_option_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_option_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER option_id_trigger
    BEFORE INSERT ON option
    FOR EACH ROW
    EXECUTE FUNCTION trigger_option_id();

-- Pour Paiement
CREATE OR REPLACE FUNCTION trigger_paiement_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_paiement_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER paiement_id_trigger
    BEFORE INSERT ON paiement
    FOR EACH ROW
    EXECUTE FUNCTION trigger_paiement_id();

-- Pour Reservation
CREATE OR REPLACE FUNCTION trigger_reservation_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_reservation_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER reservation_id_trigger
    BEFORE INSERT ON reservation
    FOR EACH ROW
    EXECUTE FUNCTION trigger_reservation_id();

-- Pour ReservationDetails
CREATE OR REPLACE FUNCTION trigger_reservation_details_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_reservation_details_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER reservation_details_id_trigger
    BEFORE INSERT ON reservationdetails
    FOR EACH ROW
    EXECUTE FUNCTION trigger_reservation_details_id();

-- Pour ReservationOption
CREATE OR REPLACE FUNCTION trigger_reservation_option_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_reservation_option_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER reservation_option_id_trigger
    BEFORE INSERT ON reservationoption
    FOR EACH ROW
    EXECUTE FUNCTION trigger_reservation_option_id();

-- Pour RoleUtilisateur
CREATE OR REPLACE FUNCTION trigger_role_utilisateur_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_role_user_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER role_utilisateur_id_trigger
    BEFORE INSERT ON roleutilisateur
    FOR EACH ROW
    EXECUTE FUNCTION trigger_role_utilisateur_id();

-- Pour Utilisateur
CREATE OR REPLACE FUNCTION trigger_utilisateur_id()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.id IS NULL THEN
        NEW.id = get_utilisateur_seq();
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER utilisateur_id_trigger
    BEFORE INSERT ON utilisateur
    FOR EACH ROW
    EXECUTE FUNCTION trigger_utilisateur_id();