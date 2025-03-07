package itu.jca.eval.test.coworking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import itu.jca.eval.test.coworking.dto.AccountDto;
import itu.jca.eval.test.coworking.models.Account;
import itu.jca.eval.test.coworking.service.AccountService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/accounts")
    public List<Account> getAccounts() {
        try {
            return accountService.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<Account> authentifier(@RequestBody AccountDto accountDto) {
        try {
            Account account = accountService.authentication(accountDto.getLogin(), accountDto.getPassword());
            return ResponseEntity.ok(account);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
