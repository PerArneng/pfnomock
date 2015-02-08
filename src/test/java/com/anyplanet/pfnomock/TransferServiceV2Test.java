package com.anyplanet.pfnomock;

import org.junit.Assert;
import org.junit.Test;

public class TransferServiceV2Test {

    @Test
    public void testHappy() {

        Account[] accounts = TransferServiceV2.makeTransfer(new Account(1, 100),
                                                            new Account(2, 0), 100);

        Assert.assertEquals(0, accounts[0].getAmount());
        Assert.assertEquals(100, accounts[1].getAmount());
    }



}
