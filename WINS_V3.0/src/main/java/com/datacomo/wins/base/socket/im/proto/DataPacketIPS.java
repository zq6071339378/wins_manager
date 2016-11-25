package com.datacomo.wins.base.socket.im.proto;

import com.datacomo.wins.base.socket.im.IMMessageInterface;

/**
 * Created by yangxiongbin on 2016/9/27.
 */
public class DataPacketIPS implements IMMessageInterface {
    private byte[] allbytes;
    private int DATALENGTH;
    public DataPacketIPS(int ipsNum,int ipsStatus){
        IMProtobuf.ICS_Web.Builder b= IMProtobuf.ICS_Web.newBuilder();
        b.setIpsNum(ipsNum);
        b.setIpsStatus(ipsStatus);
        IMProtobuf.ICS_Web ic=b.build();
        DATALENGTH=ic.getSerializedSize();
        allbytes=ic.toByteArray();
    }

    @Override
    public byte[] requestBytes() {
        return new byte[0];
    }

    @Override
    public int getPacketSize() {
        return 0;
    }

    @Override
    public <T> T rspDecode(byte[] buf) {
        return null;
    }
}
