package com.anyplanet.pfnomock;

public interface AccountRepository {

    void createAccount(int id);

    Account getAccountById(int id);

    void updateAccounts(Account... accounts);

}
