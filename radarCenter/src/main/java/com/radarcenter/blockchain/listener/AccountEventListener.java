package com.radarcenter.blockchain.listener;

import com.radarcenter.blockchain.account.Account;
import com.radarcenter.blockchain.event.NewAccountEvent;
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
 * @since 2018-04-21 下午12:02.
 */
@Component
public class AccountEventListener {

	private static Logger logger = LoggerFactory.getLogger(AccountEventListener.class);

	@Autowired
	private AppClient appClient;

	@EventListener(NewAccountEvent.class)
	public void newAccount(NewAccountEvent event) {

		Account account = (Account) event.getSource();
		logger.info("Prepare to initiate an account synchronization request， {}", account);
		MessagePacket messagePacket = new MessagePacket();
		messagePacket.setType(MessagePacketType.REQ_NEW_ACCOUNT);
		messagePacket.setBody(SerializeUtils.serialize(account));
		appClient.sendGroup(messagePacket);
	}
}
