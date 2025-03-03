package itu.jca.eval.test.coworking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.qos.logback.classic.pattern.Util;
import itu.jca.eval.test.coworking.models.Account;
import itu.jca.eval.test.coworking.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account authentication(String login , String password)throws Exception {
        Account account = null;
        List<Account> accounts  = accountRepository.findByLogin(login);

        if (accounts.size() < 0) {
            throw new Exception("Authentication failed  login is incorect");
        }
        
        account = accounts.get(0);
        boolean auth = account.checkPassword(password);
        if (!auth) {
            throw new Exception("Authentication failed password is incorect");
        }

        return account;
    }
}
