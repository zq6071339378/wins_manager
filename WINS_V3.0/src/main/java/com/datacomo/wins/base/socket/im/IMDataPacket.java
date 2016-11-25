package com.datacomo.wins.base.socket.im;

import java.util.Arrays;

import com.datacomo.wins.util.DataConvert;
import com.datacomo.wins.util.MD5Encrypt;


/**
 * 
* @ClassName: IMDataPacket 
* @Description: IM协议包构造
* @author peisong
* @date 2013-8-2 上午10:52:39 
*
 */
public class IMDataPacket {
	private int DATAHEADER_LEN = 32;

	private static int iPacketSerialID = 100000;

	private byte cVersionId = (byte) 1; /* 协议版本号 */

	private int lOperationCode = 0; /* 操作码 */

	private short iFunctionCode = 0; /* 应答标志 */

	private byte cReserved1 = (byte)0; /* 保留1 */

	private int lSerialId = 0; /* 协议包的序列号 */

	private short iPacketLength = 0; /* 数据包包体长度 */

	private byte[] cMd5Code = new byte[16]; /* MD5校验码 */

	private byte[] cReserved2 = new byte[2]; /* 保留2 */

	private byte[] cPacketContent = null; /* 数据包的内容 */

	private int lDataLength = 0; /* 数据包总长度 */

	private String sMd5Key = "web_md5"; /* MD5 KEY */

	public IMDataPacket() {
	}

	// 无包体数据包构造函数  com.datacomo.wins.base.socket.client.IMSyncClient
	public IMDataPacket(int lOpCode, short iFCode, String sKey) {
		lOperationCode = lOpCode;
		iFunctionCode = iFCode;
		lSerialId = iPacketSerialID++;
		iPacketLength = (short) 0;
		if (sKey != null)
			sMd5Key = sKey;
	}

	// 含包体数据包构造函数
	public IMDataPacket(int lOpCode, short iFCode, String sKey,
			IMMessageInterface pack) {
		lOperationCode = lOpCode;
		iFunctionCode = iFCode;
		lSerialId = iPacketSerialID++;
		cPacketContent = pack.requestBytes();
		iPacketLength = (short) cPacketContent.length;
		if (sKey != null)
			sMd5Key = sKey;
	}

	// 生成MD5校验码
	private void getMd5Code() {
		byte[] bKey = sMd5Key.getBytes();
		int iKeyLen = bKey.length;
		byte[] b = null;
		if (cPacketContent != null) {
			b = new byte[cPacketContent.length + iKeyLen];
//			b = arraycat(cPacketContent, bKey);
			System.arraycopy(cPacketContent, 0, b, 0, cPacketContent.length);
			System.arraycopy(bKey, 0, b, cPacketContent.length, iKeyLen);

		} else {
			b = new byte[iKeyLen];
			b = bKey;
		}
		cMd5Code = MD5Encrypt.MD5Encode(b);
	}

	private byte[] arraycat(byte[] buf1, byte[] buf2) {
		byte[] bufret = null;
		int len1 = 0;
		int len2 = 0;
		if (buf1 != null)
			len1 = buf1.length;
		if (buf2 != null)
			len2 = buf2.length;
		if (len1 + len2 > 0)
			bufret = new byte[len1 + len2];
		if (len1 > 0)
			System.arraycopy(buf1, 0, bufret, 0, len1);
		if (len2 > 0)
			System.arraycopy(buf2, 0, bufret, len1, len2);
		return bufret;
	}

	// 构造包头字节数组
	public byte[] getHeaderBytes() {
		byte[] cHeader = new byte[DATAHEADER_LEN];
		int ipos = 0;

		cHeader[0] = cVersionId;
		ipos += 1;
		System.arraycopy(DataConvert.convIntTo(lOperationCode), 0, cHeader,
				ipos, 4);

		ipos += 4;

		System.arraycopy(DataConvert.convShortTo(iFunctionCode), 0, cHeader,
				ipos, 2);

		ipos += 2;
		ipos += 1; // 保留1
		System.arraycopy(DataConvert.convIntTo(lSerialId), 0, cHeader, ipos, 4);
		ipos += 4;
		System.arraycopy(DataConvert.convShortTo(iPacketLength), 0, cHeader,
				ipos, 2);
		ipos += 2;
		getMd5Code();
		System.arraycopy(cMd5Code, 0, cHeader, ipos, 16);
		ipos += 16;

		return (cHeader);
	}

	// 根据包头字节数组转换为包头结构
	public void convertHeader(byte[] buff) {
		byte[] b4 = new byte[4];
		byte[] b2 = new byte[2];
		int ipos = 0;
		cVersionId = buff[0];
		ipos += 1;
		System.arraycopy(buff, ipos, b4, 0, 4);
		lOperationCode = DataConvert.convToInt(b4);
		ipos += 4;//5
		System.arraycopy(buff, ipos, b2, 0, 2);
		iFunctionCode = DataConvert.convToShort(b2);
		ipos+=2;//7
		cReserved1=buff[ipos];
		ipos+=1;//8
		System.arraycopy(buff, ipos, b4, 0, 4);
		lSerialId = DataConvert.convToInt(b4);
		ipos+=4;//12
		System.arraycopy(buff, ipos, b2, 0, 2);
		iPacketLength = DataConvert.convToShort(b2);
		ipos+=2;//14
		System.arraycopy(buff, ipos, cMd5Code, 0, 16);
		ipos+=16;//30
		System.arraycopy(buff, ipos, cReserved2, 0, 2);
	}

	// 构造IM数据包：包头 + 包体
	public byte[] getDataPacketBytes() {
		byte[] head = getHeaderBytes();
		lDataLength = head.length;
		if (cPacketContent != null) {
			lDataLength += cPacketContent.length;
		}
		byte[] allbytes = new byte[lDataLength];
		System.arraycopy(head, 0, allbytes, 0, head.length);
		if (cPacketContent != null) {
			System.arraycopy(cPacketContent, 0, allbytes, head.length,
					cPacketContent.length);
		}

		return allbytes;
	}

	// 取数据包总长度（包头 ＋ 包体）
	public int getDataLength() {
		return (lDataLength);
	}

	// // 取数据包包体长度
	public int getContentLength() {
		return (iPacketLength);
	}

	// 设置包体内容(从Socket端口读数据时使用)
	public void setContent(byte[] data) {
		cPacketContent = data;
	}

	// 设置包体内容(从Socket端口读数据时使用)
	public byte[] getContent() {
		return (cPacketContent);
	}

	// 得到返回操作码
	public int getOperationCode() {
		return (lOperationCode);
	}

	// 得到返回功能码（0表示策略服务器处理成功，非0表示处理失败的错误码）
	public short getFunctionCode() {
		return (iFunctionCode);
	}

	// 得返回包体
	public byte[] getRespDataPacketBytes() {
		if (cPacketContent != null) {
			lDataLength = cPacketContent.length;
		}
		byte[] packetContentBytes = new byte[lDataLength];
		if (cPacketContent != null) {
			System.arraycopy(cPacketContent, 0, packetContentBytes, 0,
					cPacketContent.length);
		}
		return packetContentBytes;
	}

	public void setIFunctionCode(short functionCode) {
		iFunctionCode = functionCode;
	}

	public byte getcVersionId() {
		return cVersionId;
	}

	@Override
	public String toString() {
		return "IMDataPacket [DATAHEADER_LEN=" + DATAHEADER_LEN
				+ ", cVersionId=" + cVersionId + ", lOperationCode="
				+ lOperationCode + ", iFunctionCode=" + iFunctionCode
				+ ", cReserved1=" + cReserved1 + ", lSerialId=" + lSerialId
				+ ", iPacketLength=" + iPacketLength + ", cMd5Code="
				+ Arrays.toString(cMd5Code) + ", cReserved2="
				+ Arrays.toString(cReserved2) + ", cPacketContent="
				+ Arrays.toString(cPacketContent) + ", lDataLength="
				+ lDataLength + ", sMd5Key=" + sMd5Key + "]";
	}

}