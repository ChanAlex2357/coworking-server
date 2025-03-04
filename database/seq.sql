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