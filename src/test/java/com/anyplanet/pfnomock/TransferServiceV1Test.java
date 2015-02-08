package com.anyplanet.pfnomock;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

public class TransferServiceV1Test {

    @Test
    public void testHappy() {
        Account account1 = new Account(1, 100);
        Account account2 = new Account(2, 0);

        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.getAccountById(1)).thenReturn(account1);
        when(accountRepository.getAccountById(2)).thenReturn(account2);

        final Account account1after = accountRepository.getAccountById(1);
        final Account account2after = accountRepository.getAccountById(2);

        doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();

                Account acc1 = (Account) args[0];
                Account acc2 = (Account) args[1];

                account1after.setAmount(acc1.getAmount());
                account2after.setAmount(acc2.getAmount());

                return null;
            }
        }).when(accountRepository).updateAccounts(Matchers.<Account>anyVararg());

        TransferService service = new TransferServiceV1(accountRepository);

        service.transfer(new Transfer(1,2,100));

        Assert.assertEquals(0, account1after.getAmount());
        Assert.assertEquals(100, account2after.getAmount());
    }



}
