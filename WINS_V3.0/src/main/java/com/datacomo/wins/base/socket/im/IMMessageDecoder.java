package com.datacomo.wins.base.socket.im;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
* @ClassName: IMRequestDecoder 
* @Description: IM协议请求包解码
* @author peisong
* @date 2013-8-2 下午2:49:04 
*
 */
public class IMMessageDecoder implements MessageDecoder {
	
	private static Logger logger = LoggerFactory
			.getLogger(IMMessageDecoder.class);
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {

		// 报头长度==6
		if (in.remaining() < 32) {
			return MessageDecoderResult.NEED_DATA;
		}
		IMDataPacket data = new IMDataPacket();
		// 读包头数据
		byte[] header = new byte[32];
		in.get(header, 0, 32);
		data.convertHeader(header);
		if(data.getcVersionId()!=(byte)1){
			logger.warn("IM protocol version error:{}",data.getcVersionId());
//			session.close(true);
//			return MessageDecoderResult.NOT_OK;
		}
		int iContentLen = data.getContentLength();
		if (in.remaining() < iContentLen) {
			return MessageDecoderResult.NEED_DATA;
		}
		return MessageDecoderResult.OK;
	}

	public MessageDecoderResult decode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		logger.debug("client：{}, message decoding！" ,session);
			byte[] content = null;
			IMDataPacket data = new IMDataPacket();
			// 读包头数据
			byte[] header = new byte[32];
			in.get(header, 0, 32);
			data.convertHeader(header);
//			if(0!=data.getFunctionCode()){
//				logger.debug("data.getContentLength:{}！",data.getContentLength());
//				out.write(data);
//				return MessageDecoderResult.OK;
//			}
			int iContentLen = data.getContentLength();
			if (iContentLen > 0) {
				content = new byte[iContentLen];
				in.get(content, 0, iContentLen);
				data.setContent(content);
			}
			out.write(data);
		return MessageDecoderResult.OK;
	}

	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {
		logger.debug("client：{},message decode success！" ,session);
	}

	

}
