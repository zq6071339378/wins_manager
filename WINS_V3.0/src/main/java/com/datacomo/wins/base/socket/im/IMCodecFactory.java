package com.datacomo.wins.base.socket.im;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageEncoder;


/**
 * 
* @ClassName: IMCodecFactory 
* @Description: IM协议 
* @author peisong
* @date 2013-8-2 上午9:53:03 
*
 */
public class IMCodecFactory extends DemuxingProtocolCodecFactory {
	public IMCodecFactory(MessageEncoder<IMDataPacket> encoder,MessageDecoder decoder) {
			addMessageEncoder(IMDataPacket.class, encoder);
            addMessageDecoder(decoder);
       }  
	

}
