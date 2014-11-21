package com.anyplanet.pfnomock;

public class TransferServiceV1 implements TransferService {

    private final AccountRepository accountRepository;

    public TransferServiceV1(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void transfer(Transfer transfer) {

        if (transfer.getAmmount() < 1) {
            throw new BankException("transfer amount of less than 1 is not valid");
        }

        if (transfer.getAmmount() > 100000) {
            throw new BankException("transfer amount is to high");
        }

        Account fromAccount = this.accountRepository.getAccountById(transfer.getFromAccountId());
        Account toAccount = this.accountRepository.getAccountById(transfer.getToAccountId());

        if (fromAccount == null) {
            throw new BankException("the from account does not exist");
        }

        if (toAccount == null) {
            throw new BankException("the to account does not exist");
        }

        if (transfer.getAmmount() > fromAccount.getAmount()) {
            throw new BankException("insufficient funds in the from account");
        }

        Account updatedFromAccount = new Account(fromAccount.getId(), fromAccount.getAmount() - transfer.getAmmount());
        Account updatedToAccount = new Account(toAccount.getId(), toAccount.getAmount() + transfer.getAmmount());

        this.accountRepository.updateAccounts(updatedFromAccount, updatedToAccount);
    }
}
