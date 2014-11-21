package com.anyplanet.pfnomock;

import java.util.HashMap;
import java.util.Map;

public class InMemmoryAccountRepository implements AccountRepository {

    private final Map<Integer, Account> accountMap = new HashMap<Integer, Account>();

    @Override
    public void createAccount(int id) {
        accountMap.put(id, new Account(id, 0));
    }

    @Override
    public Account getAccountById(int id) {
        return accountMap.get(id);
    }

    @Override
    public void updateAccounts(Account... accounts) {
        for (Account account : accounts) {
            accountMap.put(account.getId(), account);
        }
    }
}
