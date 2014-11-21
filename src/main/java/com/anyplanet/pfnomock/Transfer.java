package com.anyplanet.pfnomock;

public class Transfer {

    private final int fromAccountId;
    private final int toAccountId;
    private final int ammount;

    public Transfer(int fromAccountId, int toAccountId, int ammount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.ammount = ammount;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public int getAmmount() {
        return ammount;
    }
}
