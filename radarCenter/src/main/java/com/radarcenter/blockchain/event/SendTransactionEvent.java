package com.radarcenter.blockchain.event;

import com.radarcenter.blockchain.core.Transaction;
import org.springframework.context.ApplicationEvent;

/**
 * @author Robert Gerard
 */
public class SendTransactionEvent extends ApplicationEvent {

    public SendTransactionEvent(Transaction transaction) {
        super(transaction);
    }

}
