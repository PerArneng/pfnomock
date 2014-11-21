package com.anyplanet.pfnomock;

public class TransferServiceV1 implements TransferService {

    private final AccountRepository accountRepository;

    public TransferServiceV1(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void transfer(Transfer transfer) {

        Account fromAccount = this.accountRepository.getAccountById(transfer.getFromAccountId());


    }
}
