package com.radarcenter.blockchain.net.base;

import java.io.Serializable;

/**
 * @author Robert Gerard
 * @since 18-4-18
 */
public class Node extends org.tio.core.Node implements Serializable {

	public Node(String ip, int port) {
		super(ip, port);
	}

	public Node() {
		super(null, 0);
	}
}
