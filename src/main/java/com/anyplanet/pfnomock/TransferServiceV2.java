package com.anyplanet.pfnomock;

public class TransferServiceV2 implements TransferService {

    private final AccountRepository accountRepository;

    public TransferServiceV2(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void transfer(Transfer transfer) {
        Account fromAccount = this.accountRepository.getAccountById(transfer.getFromAccountId());
        Account toAccount = this.accountRepository.getAccountById(transfer.getToAccountId());

        Account[] accounts = makeTransfer(fromAccount, toAccount, transfer.getAmmount());

        this.accountRepository.updateAccounts(accounts);

    }

    protected static Account[] makeTransfer(final Account fromAccount,
                                            final Account toAccount, int amount) {

        if (amount < 1) {
            throw new BankException("transfer amount of less than 1 is not valid");
        }

        if (amount > 100000) {
            throw new BankException("transfer amount is to high");
        }

        if (fromAccount == null) {
            throw new BankException("the from account does not exist");
        }

        if (toAccount == null) {
            throw new BankException("the to account does not exist");
        }

        if (amount > fromAccount.getAmount()) {
            throw new BankException("insufficient funds in the from account");
        }

        Account updatedFromAccount = new Account(fromAccount.getId(), fromAccount.getAmount() - amount);
        Account updatedToAccount = new Account(toAccount.getId(), toAccount.getAmount() + amount);

        return new Account[] {updatedFromAccount, updatedToAccount};

    }

}
