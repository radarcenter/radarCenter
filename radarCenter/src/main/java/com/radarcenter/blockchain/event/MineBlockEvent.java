package com.radarcenter.blockchain.event;

import com.radarcenter.blockchain.core.Block;
import org.springframework.context.ApplicationEvent;

/**
 * @author Robert Gerard
 */
public class MineBlockEvent extends ApplicationEvent {

    public MineBlockEvent(Block block) {
        super(block);
    }
}
