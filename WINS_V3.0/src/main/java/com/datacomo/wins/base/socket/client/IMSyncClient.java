package com.datacomo.wins.base.socket.client;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datacomo.wins.base.socket.im.IMCodecFactory;
import com.datacomo.wins.base.socket.im.IMDataPacket;
import com.datacomo.wins.base.socket.im.IMMessageDecoder;
import com.datacomo.wins.base.socket.im.IMMessageEncoder;
import com.datacomo.wins.base.socket.im.IMMessageInterface;
import com.datacomo.wins.controller.Config;

/**
 * 
 * @ClassName: IMSyncClient
 * @Description: IM协议同步客户端
 * @author peisong
 * @date 2013-9-9 上午9:39:17
 * 
 */
public class IMSyncClient {
	private static Logger logger = LoggerFactory.getLogger(IMSyncClient.class);
	private IoSession session;
	private int timeOut=30;
	private int port;
	private String host;

	public IMSyncClient(){
		host=Config.message.get("host").toString();
		port=Integer.parseInt(Config.message.get("port")
				.toString());
	}
	public boolean createSession() {
		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000L);
		// 设置过滤器（使用IM协议进行过滤）
		connector.getFilterChain().addLast(
				"IMprotocol",
				new ProtocolCodecFilter(new IMCodecFactory(
						new IMMessageEncoder(), new IMMessageDecoder())));
		SocketSessionConfig cfg = connector.getSessionConfig();
		cfg.setUseReadOperation(true);
		session = connector.connect(new InetSocketAddress(host, port))
				.awaitUninterruptibly().getSession();
		logger.info("createSession success:{}",session);
		return true;
	}

	public boolean closeSession() {
		try {
			if (session != null)
				session.close(true);
		} catch (Exception e) {
			logger.warn("closeSession Exception:{}",e);
			return false;
		}
		logger.info("closeSession success!");
		return true;
	}

	public short sendOperMsg(int iOpCode, IMMessageInterface msg) {
		return sendOperMsg(iOpCode, msg, timeOut);
	}

	public short sendOperMsg(int iOpCode, IMMessageInterface msg, int timeOut) {
		short iFunCode = 0;
		return sendOperMsg(iOpCode, iFunCode, msg, timeOut);
	}

	/**
	 * 
	 * @Title: sendOperMsg
	 * @Description: 
	 * @param @param iOpCode
	 * @param @param iFunCode
	 * @param @param msg
	 * @param @param timeOut （秒）最小值为5秒
	 * @param @return 设定文件
	 * @return short 返回类型
	 * @throws
	 */
	public short sendOperMsg(int iOpCode, short iFunCode,
			IMMessageInterface msg, int timeOut) {
		IMDataPacket data = sendDataMsg(iOpCode, iFunCode, msg, timeOut);
		return data.getFunctionCode();
	}

	/**
	 * 
	 * @Title: sendDataMsg
	 * @Description: 处理有返回包体的操作码
	 * @param @param iOpCode 操作码
	 * @param @return 设定文件
	 * @return IMDataPacket 返回类型
	 * @throws
	 */
	public IMDataPacket sendDataMsg(int iOpCode) {
		return sendDataMsg(iOpCode, null);
	}

	public IMDataPacket sendDataMsg(int iOpCode, IMMessageInterface msg) {
		return sendDataMsg(iOpCode, msg, timeOut);
	}

	public IMDataPacket sendDataMsg(int iOpCode, IMMessageInterface msg,
			int timeOut) {
		short iFunCode = 0;
		return sendDataMsg(iOpCode, iFunCode, msg, timeOut);
	}

	/**
	 * 
	 * @Title: sendDataMsg
	 * @Description: 发送数据包，返回带数据包的消息
	 * @param @param iOpCode
	 * @param @param iFunCode
	 * @param @param msg 要发送的数据包
	 * @param @param timeOut （秒）最小值为5秒
	 * @param @return 设定文件
	 * @return IMDataPacket 返回类型
	 * @throws
	 */
	public IMDataPacket sendDataMsg(int iOpCode, short iFunCode,
			IMMessageInterface msg, int timeOut) {
		short errorNo;
		timeOut = (timeOut < 5 ? 5 : timeOut);
		IMDataPacket respData = null;
		IMDataPacket reqData=null;
		try {
			if (null == msg) {
				reqData=new IMDataPacket(iOpCode, iFunCode, null);
				session.write(reqData).awaitUninterruptibly();
			} else {
				reqData=new IMDataPacket(iOpCode, iFunCode, null, msg);
				session.write(reqData).awaitUninterruptibly();
			}
			logger.debug("reqData:{}",reqData);
			// 接收
			ReadFuture readFuture = session.read();
			if (readFuture.awaitUninterruptibly(timeOut, TimeUnit.SECONDS)) {
				Object reMsg = readFuture.getMessage();
				if (null != reMsg) {
					if (reMsg instanceof IMDataPacket) {
						respData = (IMDataPacket) reMsg;
						if (0 != respData.getFunctionCode()) {
							logger.warn("Failed opcode（{}），error code（{}）", iOpCode,
									respData.getFunctionCode());
						}
						return respData;
					} else {
						logger.warn("undefined packet,responseMsgData:{}", reMsg);
						errorNo = -1;
					}
				} else {
					errorNo = -2;
					logger.warn("response packet is empty!");
				}
			} else {
				errorNo = -3;
				logger.warn("response timeout：{},opcode:{}", timeOut,iOpCode);
			}
		} catch (Exception e) {
			errorNo = -4;
			logger.warn("Exception:{}", e);
		}
		respData = new IMDataPacket();
		respData.setIFunctionCode(errorNo);
		return respData;
	}
}
