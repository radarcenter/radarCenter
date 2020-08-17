package com.radarcenter.blockchain.listener;

import com.radarcenter.blockchain.core.Transaction;
import com.radarcenter.blockchain.event.SendTransactionEvent;
import com.radarcenter.blockchain.net.base.MessagePacket;
import com.radarcenter.blockchain.net.base.MessagePacketType;
import com.radarcenter.blockchain.net.client.AppClient;
import com.radarcenter.blockchain.utils.SerializeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Robert Gerard
 * @since 18-4-19
 */
@Component
public class TransactionEventListener {

	@Autowired
	private AppClient appClient;

	/**
	 * @param event
	 */
	@EventListener(SendTransactionEvent.class)
	public void sendTransaction(SendTransactionEvent event) {

		Transaction transaction = (Transaction) event.getSource();
		MessagePacket messagePacket = new MessagePacket();
		messagePacket.setType(MessagePacketType.REQ_CONFIRM_TRANSACTION);
		messagePacket.setBody(SerializeUtils.serialize(transaction));
		appClient.sendGroup(messagePacket);
	}

}
