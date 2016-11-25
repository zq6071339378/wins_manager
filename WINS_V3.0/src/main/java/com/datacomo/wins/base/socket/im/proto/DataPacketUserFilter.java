package com.datacomo.wins.base.socket.im.proto;

import com.datacomo.wins.base.socket.im.IMMessageInterface;
import com.datacomo.wins.base.socket.im.proto.IMProtobuf.ICS_Web;
import com.datacomo.wins.base.socket.im.proto.IMProtobuf.UpdateInfo;
import com.datacomo.wins.base.socket.im.proto.IMProtobuf.UpdateInfo.Update_Flag;
import com.datacomo.wins.push.bean.UserFilter;

import java.util.List;

/**
 * 
* @ClassName: DataPacketGPolicy 
* @Description: 全局策略包体
* @author peisong
* @date 2013-12-14 下午3:45:08 
*
 */
public class DataPacketUserFilter implements IMMessageInterface {
	private byte[] allbytes;
	private int DATALENGTH;
	public DataPacketUserFilter(UserFilter userFilter, int operCode) {
		IMProtobuf.Filter_User.Builder builder= IMProtobuf.Filter_User.newBuilder();
		builder.setFilterUser(userFilter.getFilterUser());
		builder.setUserStatus(userFilter.getUserStatus());
		builder.setPolicyId(userFilter.getPolicyId());
		builder.setCreateUser(userFilter.getCreateUser());
		builder.setCreateTime(userFilter.getCreateTime());
		UpdateInfo.Builder updateInfo= UpdateInfo.newBuilder();
		if(0==operCode){
			updateInfo.setUpdateflag(Update_Flag.Insert);
		}else if(1==operCode){
			updateInfo.setUpdateflag(Update_Flag.Delete);
		}else if(2==operCode){
			updateInfo.setUpdateflag(Update_Flag.Update);
		}
		updateInfo.addFilterUser(builder);
		ICS_Web.Builder b= ICS_Web.newBuilder();
		b.addUpdateData(updateInfo);
		ICS_Web ic=b.build();
		DATALENGTH=ic.getSerializedSize();
		allbytes=ic.toByteArray();
	}

	public DataPacketUserFilter(List<UserFilter> list, int operCode) {
		UpdateInfo.Builder updateInfo= UpdateInfo.newBuilder();
		if(!list.isEmpty() || list!=null){
			for (UserFilter userFilter:list){
				IMProtobuf.Filter_User.Builder builder= IMProtobuf.Filter_User.newBuilder();
				builder.setFilterUser(userFilter.getFilterUser());
				builder.setUserStatus(userFilter.getUserStatus());
				builder.setPolicyId(userFilter.getPolicyId());
				builder.setCreateUser(userFilter.getCreateUser());
				//builder.setInvalidTime(userFilter.getInvalidTime());
				builder.setCreateTime(userFilter.getCreateTime());
				if(0==operCode){
					updateInfo.setUpdateflag(Update_Flag.Insert);
				}else if(1==operCode){
					updateInfo.setUpdateflag(Update_Flag.Delete);
				}else if(2==operCode){
					updateInfo.setUpdateflag(Update_Flag.Update);
				}
				updateInfo.addFilterUser(builder);
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
		return (T) this.requestBytes();
	}


	
}
