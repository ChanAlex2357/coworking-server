package itu.jca.eval.test.coworking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import itu.jca.eval.test.coworking.models.Account;
import java.util.List;


public interface AccountRepository extends JpaRepository<Account,String>{
    public List<Account> findByLogin(String login);
}
