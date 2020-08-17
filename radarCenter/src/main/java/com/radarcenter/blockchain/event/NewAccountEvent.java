package com.radarcenter.blockchain.event;

import com.radarcenter.blockchain.account.Account;
import org.springframework.context.ApplicationEvent;

/**
 * @author Robert Gerard
 */
public class NewAccountEvent extends ApplicationEvent {

    public NewAccountEvent(Account account) {
        super(account);
    }
}
