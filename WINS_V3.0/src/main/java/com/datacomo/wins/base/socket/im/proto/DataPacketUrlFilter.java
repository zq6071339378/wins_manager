package com.datacomo.wins.base.socket.im.proto;

import com.datacomo.wins.base.socket.im.IMMessageInterface;
import com.datacomo.wins.base.socket.im.proto.IMProtobuf.ICS_Web;
import com.datacomo.wins.base.socket.im.proto.IMProtobuf.UpdateInfo;
import com.datacomo.wins.base.socket.im.proto.IMProtobuf.UpdateInfo.Update_Flag;
import com.datacomo.wins.push.bean.UrlFilter;

import java.util.List;

/**
 * 
* @ClassName: DataPacketGPolicy 
* @Description: 全局策略包体
* @author peisong
* @date 2013-12-14 下午3:45:08 
*
 */
public class DataPacketUrlFilter implements IMMessageInterface {
	private byte[] allbytes;
	private int DATALENGTH;
	public DataPacketUrlFilter(UrlFilter urlFilter, int operCode) {
		IMProtobuf.Filter_Url.Builder builder= IMProtobuf.Filter_Url.newBuilder();
		if(urlFilter.getUrlDomain() != null){
			builder.setUrlDomain(urlFilter.getUrlDomain());
		}
		if(urlFilter.getUrlPath() != null){
			builder.setUrlPath(urlFilter.getUrlPath());
		}
		if (urlFilter.getUrlId()!=0){
			builder.setUrlId(urlFilter.getUrlId());
		}
		if(urlFilter.getCreateUser() != 0){
			builder.setCreateUser(urlFilter.getCreateUser());
		}
		if(urlFilter.getCreateTime() != null){
			builder.setCreateTime(urlFilter.getCreateTime());
		}
		/*if(urlFilter.getUrlStatus()!=0){
			builder.set
		}*/
		UpdateInfo.Builder updateInfo= UpdateInfo.newBuilder();
		if(0==operCode){
			updateInfo.setUpdateflag(Update_Flag.Insert);
		}else if(1==operCode){
			updateInfo.setUpdateflag(Update_Flag.Delete);
		}else if(2==operCode){
			updateInfo.setUpdateflag(Update_Flag.Update);
		}
		System.out.println("urlFilter.toString()---------------------------------------------------------------------------"+urlFilter.toString());
		updateInfo.addFilterUrl(builder);
		ICS_Web.Builder b= ICS_Web.newBuilder();
		b.addUpdateData(updateInfo);
		ICS_Web ic=b.build();
		DATALENGTH=ic.getSerializedSize();
		allbytes=ic.toByteArray();
	}

	public DataPacketUrlFilter(List<UrlFilter> list, int operCode) {
		IMProtobuf.Filter_Url.Builder builder= IMProtobuf.Filter_Url.newBuilder();
		UpdateInfo.Builder updateInfo= UpdateInfo.newBuilder();
		if(!list.isEmpty() || list!=null){
			for (UrlFilter urlFilter : list){
				if(urlFilter.getUrlDomain() != null){
					builder.setUrlDomain(urlFilter.getUrlDomain());
				}
				if(urlFilter.getUrlPath() != null){
					builder.setUrlPath(urlFilter.getUrlPath());
				}
				if(urlFilter.getCreateUser() != 0){
					builder.setCreateUser(urlFilter.getCreateUser());
				}
				if(urlFilter.getCreateTime() != null){
					builder.setCreateTime(urlFilter.getCreateTime());
				}
				/*if (urlFilter.getUrlStatus()!=0){
					builder.set
				}*/
				if(0==operCode){
					updateInfo.setUpdateflag(Update_Flag.Insert);
				}else if(1==operCode){
					updateInfo.setUpdateflag(Update_Flag.Delete);
				}else if(2==operCode){
					updateInfo.setUpdateflag(Update_Flag.Update);
				}
				updateInfo.addFilterUrl(builder);
			}
		}
		ICS_Web.Builder b= ICS_Web.newBuilder();
		b.addUpdateData(updateInfo);
		ICS_Web ic=b.build();
		DATALENGTH=ic.getSerializedSize();
		allbytes=ic.toByteArray();
	}

	public byte[] requestBytes() {
		return (allbytes);
	}

	public int getPacketSize() {
		return (DATALENGTH);
	}

	@Override
	public <T> T rspDecode(byte[] buf) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
