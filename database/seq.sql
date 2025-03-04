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
CREATE SEQUENCE IF NOT EXISTS prix_espace_seq;
CREATE OR REPLACE FUNCTION get_prix_espace_seq()
RETURNS text AS $$
BEGIN
    RETURN 'PES_' || nextval('prix_espace_seq')::text;
END;
$$ LANGUAGE plpgsql;

-- Pour OptionPrix
CREATE SEQUENCE IF NOT EXISTS option_prix_seq;
CREATE OR REPLACE FUNCTION get_option_prix_seq()
RETURNS text AS $$
BEGIN
    RETURN 'OPX_' || nextval('option_prix_seq')::text;
END;
$$ LANGUAGE plpgsql;