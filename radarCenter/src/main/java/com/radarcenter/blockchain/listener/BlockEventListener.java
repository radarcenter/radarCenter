package com.radarcenter.blockchain.listener;

import com.google.common.base.Optional;
import com.radarcenter.blockchain.core.Block;
import com.radarcenter.blockchain.db.DBAccess;
import com.radarcenter.blockchain.event.FetchNextBlockEvent;
import com.radarcenter.blockchain.event.MineBlockEvent;
import com.radarcenter.blockchain.net.base.MessagePacket;
import com.radarcenter.blockchain.net.base.MessagePacketType;
import com.radarcenter.blockchain.net.client.AppClient;
import com.radarcenter.blockchain.utils.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Robert Gerard
 * @since 18-4-19
 */
@Component
public class BlockEventListener {

	@Autowired
	private AppClient appClient;
	@Autowired
	private DBAccess dbAccess;
	private static Logger logger = LoggerFactory.getLogger(BlockEventListener.class);

	/**
	 * @param event
	 */
	@EventListener(MineBlockEvent.class)
	public void mineBlock(MineBlockEvent event) {

		logger.info("++++++++++++++ Start broadcasting the new block +++++++++++++++++++++");
		Block block = (Block) event.getSource();
		MessagePacket messagePacket = new MessagePacket();
		messagePacket.setType(MessagePacketType.REQ_NEW_BLOCK);
		messagePacket.setBody(SerializeUtils.serialize(block));
		appClient.sendGroup(messagePacket);
	}

	/**
	 * @param event
	 */
	@EventListener(FetchNextBlockEvent.class)
	public void fetchNextBlock(FetchNextBlockEvent event) {

		logger.info("++++++++++++++++++++++++++++++ Start sending messages in groups next Block +++++++++++++++++++++++++++++++++");
		Integer blockIndex = (Integer) event.getSource();
		if (blockIndex == 0) {
			Optional<Object> lastBlockIndex = dbAccess.getLastBlockIndex();
			if (lastBlockIndex.isPresent()) {
				blockIndex = (Integer) lastBlockIndex.get();
			}
		}
		MessagePacket messagePacket = new MessagePacket();
		messagePacket.setType(MessagePacketType.REQ_SYNC_NEXT_BLOCK);
		messagePacket.setBody(SerializeUtils.serialize(blockIndex+1));
		appClient.sendGroup(messagePacket);
	}

}
