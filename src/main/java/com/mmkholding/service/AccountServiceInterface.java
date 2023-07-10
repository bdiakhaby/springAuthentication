package com.mmkholding.service;

import com.mmkholding.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountServiceInterface {

    List<Account> getAll();
    Account findByEmail(String email);

    Account createAccount(Account account);
}
