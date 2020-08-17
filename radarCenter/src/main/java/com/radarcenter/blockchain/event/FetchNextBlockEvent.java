package com.radarcenter.blockchain.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author Robert Gerard
 */
public class FetchNextBlockEvent extends ApplicationEvent {

    /**
     * @param blockIndex
     */
    public FetchNextBlockEvent(Integer blockIndex) {
        super(blockIndex);
    }
}
