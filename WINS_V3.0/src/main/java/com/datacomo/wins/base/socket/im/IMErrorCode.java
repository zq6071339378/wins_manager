/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacomo.wins.base.socket.im;

/**
 * 
 * @ClassName: IcsErrorCode
 * @Description: 根据C代码中policy_im.h定义的前置机错误码，返回相应错误信息
 * @author peisong
 * @date 2013-8-19 下午3:56:12
 * 
 */
public class IMErrorCode {

	public static String geterrorMsg(int errorcode) {
		String errorMsg = null;
		switch (errorcode) {
		case -1:
			errorMsg = "TCP连接关闭，请联系管理确认信息配置正确！";
			break;
		case 0:
			errorMsg = "策略操作成功";
			break;
		case 2002:
			errorMsg = "数据库操作出错";
			break;
		case 2003:
			errorMsg = "数据重复";
			break;
		case 2004:
			errorMsg = "数据不存在";
			break;
		case 2005:
			errorMsg = "系统出错";
			break;
		case 2006:
			errorMsg = "内存出错";
			break;
		case 2007:
			errorMsg = "用户不存在";
			break;
		case 2008:
			errorMsg = "用户在线";
			break;
		case 2009:
			errorMsg = "发送数据给ips出错";
			break;
		case 2010:
			errorMsg = "该策略组已经被应用了";
			break;
		case 2011:
			errorMsg = "数据库连接不空闲";
			break;
		case 2012:
			errorMsg = "正在应用组策略或数据库繁忙";
			break;
		case 2014:
			errorMsg = "组没有被应用";
			break;
		case 2015:
			errorMsg = "该组策略已经被应用了";
			break;
		case 2016:
			errorMsg = "不支持该操作";
			break;
		case 2017:
			errorMsg = "特殊用户的ip段非法";
			break;
		case 2018:
			errorMsg = "send_teshu_user_to_ipserror";
			break;
		case 2019:
			errorMsg = "send_teshu_user_policy_to_ipserror";
			break;
		case 2020:
			errorMsg = "文件处理出错";
			break;
		case 2021:
			errorMsg = "该文件已经被应用了";
			break;
		case 2022:
			errorMsg = "文件ID出错";
			break;
		case 2023:
			errorMsg = "用户已经存在";
			break;
		case 2024:
			errorMsg = "get_policy_group_by_name出错";
			break;
		case 2025:
			errorMsg = "策略名非法";
			break;
		case 2026:
			errorMsg = "insertpolicy_group出错";
			break;
		case 2027:
			errorMsg = "apply_policy_user出错";
			break;
		case 2028:
			errorMsg = "deletefrompolicy_group出错";
			break;
		case 2029:
			errorMsg = "取表policy_file的下一个id出错";
			break;
		case 2030:
			errorMsg = "insertpolicy_file出错";
			break;
		case 2031:
			errorMsg = "函数deal_policy_file_user出错";
			break;
		case 2032:
			errorMsg = "取表policy的下一个id出错";
			break;
		case 2033:
			errorMsg = "policyid非法";
			break;
		case 2034:
			errorMsg = "get_user_type_by_name出错";
			break;
		case 2036:
			errorMsg = "insertpolicy出错";
			break;
		case 2037:
			errorMsg = "update_policy_prio出错";
			break;
		case 2038:
			errorMsg = "insertpush_policy出错";
			break;
		case 2039:
			errorMsg = "add_user_push_policy出错";
			break;
		case 2040:
			errorMsg = "selectfrompolicy_group出错";
			break;
		case 2041:
			errorMsg = "策略组不存在";
			break;
		case 2042:
			errorMsg = "updatelog_param出错";
			break;
		case 2043:
			errorMsg = "insertintolog_param出错";
			break;
		case 2044:
			errorMsg = "selectfromuser_online_detail出错";
			break;
		case 2045:
			errorMsg = "selectfromuser_info出错";
			break;
		case 2049:
			errorMsg = "策略id不存在";
			break;
		case 2050:
			errorMsg = "selectfrompolicy出错";
			break;
		case 2051:
			errorMsg = "selectfrompolicy_file出错";
			break;
		case 2052:
			errorMsg = "deletefrompolicy_file_user出错";
			break;
		case 2053:
			errorMsg = "deletefrompolicy_file出错";
			break;
		case 2054:
			errorMsg = "updatepolicy_group出错";
			break;
		case 2055:
			errorMsg = "get_teshu_user_id出错";
			break;
		case 2056:
			errorMsg = "insertintoother_telecom_user出错";
			break;
		case 2057:
			errorMsg = "check_teshu_ip_seg出错";
			break;
		case 2058:
			errorMsg = "deletefrompush_policy出错";
			break;
		case 2059:
			errorMsg = "deletefrompolicy出错";
			break;
		case 2060:
			errorMsg = "updateuser_info出错";
			break;
		case 2061:
			errorMsg = "insertintouser_info出错";
			break;
		case 2062:
			errorMsg = "updatespecial_user_push_times出错";
			break;
		case 2063:
			errorMsg = "updateuser_push_times出错";
			break;
		case 2064:
			errorMsg = "updateother_telecom_ip出错";
			break;
		case 2065:
			errorMsg = "deletefromother_telecom_push出错";
			break;
		case 2066:
			errorMsg = "deletefromother_telecom_ip出错";
			break;
		case 2067:
			errorMsg = "deletefromother_telecom_user出错";
			break;
		case 2068:
			errorMsg = "insertintoother_telecom_ip出错";
			break;
		case 2069:
			errorMsg = "insertintouser_push_times出错";
			break;
		case 2070:
			errorMsg = "selectfrompolicy_file_user出错";
			break;
		case 2071:
			errorMsg = "selectfrompolicy_param出错";
			break;
		case 2072:
			errorMsg = "updatepolicy_param出错";
			break;
		case 2073:
			errorMsg = "insertintopolicy_param出错";
			break;
		case 2074:
			errorMsg = "insertintoother_telecom_push出错";
			break;
		case 2075:
			errorMsg = "file_policy_apply_user出错";
			break;
		case 2076:
			errorMsg = "del_user_push_policy出错";
			break;
		case 2077:
			errorMsg = "del_global_push_policy出错";
			break;
		case 2078:
			errorMsg = "del_group_push_policy出错";
			break;
		case 2079:
			errorMsg = "selectfromother_telecom_user出错";
			break;
		case 2080:
			errorMsg = "个性化策略不存在";
			break;
		case 2081:
			errorMsg = "updatepush_policy出错";
			break;
		case 2082:
			errorMsg = "updatepolicy出错";
			break;
		case 2083:
			errorMsg = "insertintogroup_user_info出错";
			break;
		case 2084:
			errorMsg = "get_group_user_info出错";
			break;
		case 2085:
			errorMsg = "judge_user_group_if_applied出错";
			break;
		case 2086:
			errorMsg = "该用户群组已经被应用了";
			break;
		case 2087:
			errorMsg = "update_group_user_info出错";
			break;
		case 2088:
			errorMsg = "insertintogroup_srv_subscription出错";
			break;
		case 2089:
			errorMsg = "selectfromservice_info出错";
			break;
		case 2090:
			errorMsg = "updategroup_srv_subscription出错";
			break;
		case 2091:
			errorMsg = "updateuser_srv_subscription出错";
			break;
		case 2092:
			errorMsg = "updateuser_prj_subscription出错";
			break;
		case 2093:
			errorMsg = "deletefromgroup_srv_subscription出错";
			break;
		case 2094:
			errorMsg = "deletefromuser_srv_subscription出错";
			break;
		case 2095:
			errorMsg = "deletefromuser_prj_subscription出错";
			break;
		case 2096:
			errorMsg = "insertuser_srv_subscription出错";
			break;
		case 2097:
			errorMsg = "insertuser_prj_subscription出错";
			break;
		case 2098:
			errorMsg = "apply_user_username_srv出错";
			break;
		case 2099:
			errorMsg = "deal_as_user_srv_change_db出错";
			break;
		case 2100:
			errorMsg = "insert_user_srv_prj出错";
			break;
		case 2101:
			errorMsg = "preparecur_user_info_srv出错";
			break;
		case 2102:
			errorMsg = "declarecur_user_info_srv出错";
			break;
		case 2103:
			errorMsg = "opencur_user_info_srv出错";
			break;
		case 2104:
			errorMsg = "fetchcur_user_info_srv出错";
			break;
		case 2105:
			errorMsg = "组服务应用重复";
			break;
		case 2106:
			errorMsg = "send_to_ipserror";
			break;
		case 2107:
			errorMsg = "get_cur_pushtimes_by_policyid";
			break;
		case 2108:
			errorMsg = "apply_global_policy";
			break;
		case 2109:
			errorMsg = "apply_user_policy";
			break;
		case 2110:
			errorMsg = "unapply_user_policy";
			break;
		case 2111:
			errorMsg = "unapply_global_policy";
			break;
		case 2112:
			errorMsg = "apply_push_policy_user_times";
			break;
		case 2113:
			errorMsg = "get_global_info_num";
			break;
		case 2114:
			errorMsg = "get_policy_param_type_by_id";
			break;
		case 2115:
			errorMsg = "del_policy_param_by_id";
			break;
		case 2116:
			errorMsg = "get_fs_policy_info_by_paramid";
			break;
		case 2117:
			errorMsg = "del_user_push_times_by_poicyId";
			break;
		case 2118:
			errorMsg = "deletepolicy出错";
			break;
		case 2119:
			errorMsg = "get_oper_push_num出错";
			break;
		case 2120:
			errorMsg = "用户不在线";
			break;
		case 2121:
			errorMsg = "get_online_starttime出错";
			break;
		case 2122:
			errorMsg = "get_online_info_update出错";
			break;
		case 2123:
			errorMsg = "updateonlineaccount出错";
			break;
		case 2124:
			errorMsg = "updatecurronlineTime出错";
			break;
		case 2125:
			errorMsg = "updateuser_info.startTime出错";
			break;
		case 2126:
			errorMsg = "deletepolicy_param出错";
			break;
		case 2127:
			errorMsg = "策略不存在,或为无效策略";
			break;
		case 2128:
			errorMsg = "selectfromother_telecom_push出错";
			break;
		case 2129:
			errorMsg = "该策略不是响应策略";
			break;
		case 2130:
			errorMsg = "insertintoupdate_file_info出错";
			break;
		case 2131:
			errorMsg = "在该策略分组上应用了太多的push策略";
			break;
		case 2132:
			errorMsg = "insert_mem_policy_group";
			break;
		case 2133:
			errorMsg = "del_mem_policy_group";
			break;
		case 2134:
			errorMsg = "modify_mem_policy_group";
			break;
		case 2135:
			errorMsg = "insert_mem_policy_group_policyid";
			break;
		case 2136:
			errorMsg = "del_mem_policy_group_policyid";
			break;
		case 2137:
			errorMsg = "insertpush_url_limit";
			break;
		case 2138:
			errorMsg = "deletefrompush_url_limit";
			break;
		case 2139:
			errorMsg = "策略数据不完整";
			break;
		case 2140:
			errorMsg = "selectfromenp_user_info出错";
			break;
		case 2141:
			errorMsg = "insertintoubas_policyinfo出错";
			break;
		case 2142:
			errorMsg = "selectubas_policyinfo出错";
			break;
		case 2143:
			errorMsg = "该策略已经被应用了";
			break;
		case 2144:
			errorMsg = "start_real_gather_by_iplisterror";
			break;
		case 2145:
			errorMsg = "start_real_gather_by_urllisterror";
			break;
		case 2146:
			errorMsg = "start_real_gather_by_userfileerror";
			break;
		case 2147:
			errorMsg = "start_real_gather_by_userlisterror";
			break;
		case 2148:
			errorMsg = "selectuser_intrusion出错";
			break;
		case 2149:
			errorMsg = "该策略目前没有被应用";
			break;
		case 2150:
			errorMsg = "stop_real_gather_by_iplisterror";
			break;
		case 2151:
			errorMsg = "stop_real_gather_by_urllisterror";
			break;
		case 2152:
			errorMsg = "stop_real_gather_by_userfileerror";
			break;
		case 2153:
			errorMsg = "stop_real_gather_by_userlisterror";
			break;
		case 2154:
			errorMsg = "start_real_gather_by_urlfileerror";
			break;
		case 2155:
			errorMsg = "deletefromuser_push_timeserror";
			break;
		case 2200:
			errorMsg = "格式错误";
			break;
		case 2201:
			errorMsg = "包格式错误-globalFlag";
			break;
		case 2202:
			errorMsg = "包格式错误-head.length错误";
			break;
		case 2203:
			errorMsg = "包格式错误-起始ip或结束ip为0";
			break;
		case 2204:
			errorMsg = "包格式错误-起始ip大于结束ip";
			break;
		case 2205:
			errorMsg = "包格式错误-username";
			break;
		case 2206:
			errorMsg = "包格式错误-groupname";
			break;
		case 2207:
			errorMsg = "包格式错误-policyname";
			break;
		case 2208:
			errorMsg = "包格式错误-user_info的paramNum";
			break;
		case 2209:
			errorMsg = "包格式错误-policyId";
			break;
		case 2210:
			errorMsg = "包格式错误-minAge大于maxAge";
			break;
		case 2211:
			errorMsg = "包格式错误-dealFlag";
			break;
		case 2212:
			errorMsg = "包格式错误-userId不存在";
			break;
		case 2213:
			errorMsg = "包格式错误-userId当前状态不是正常状态";
			break;
		case 2214:
			errorMsg = "包格式错误-serviceId不存在";
			break;
		case 2215:
			errorMsg = "包格式错误-serviceId当前状态不是正常状态";
			break;
		case 2216:
			errorMsg = "包格式错误-status";
			break;
		case 2217:
			errorMsg = "包格式错误-IP地址段中的ip地址数目过多";
			break;
		case 2218:
			errorMsg = "包格式错误-templetType";
			break;
		case 2219:
			errorMsg = "包格式错误-policy重复";
			break;
		case 2220:
			errorMsg = "包格式错误-url重复";
			break;
		case 2221:
			errorMsg = "包格式错误-生效日期和结束日期错误";
			break;
		case 2222:
			errorMsg = "包格式错误-urlhost";
			break;
		case 2223:
			errorMsg = "包格式错误-udpFlag";
			break;
		case 2224:
			errorMsg = "包格式错误-policy_param重复";
			break;
		case 2225:
			errorMsg = "包格式错误-policyValid";
			break;
		case 2226:
			errorMsg = "包格式错误-pageShowFlag";
			break;
		case 2227:
			errorMsg = "包格式错误-globalFlag";
			break;
		case 2228:
			errorMsg = "包格式错误-policyType";
			break;
		case 2229:
			errorMsg = "包格式错误-policyNamenotpushpolicy";
			break;
		case 2230:
			errorMsg = "包格式错误-paramType";
			break;
		case 2231:
			errorMsg = "太多的缺省推送参数";
			break;
		case 2232:
			errorMsg = "包格式错误-policyParamId";
			break;
		case 2233:
			errorMsg = "包格式错误-fileName";
			break;
		case 2234:
			errorMsg = "包格式错误-太多的运营商策略";
			break;
		case 2235:
			errorMsg = "包格式错误-policyFlag";
			break;
		case 2236:
			errorMsg = "太多的缺省拒绝页面参数";
			break;
		case 2237:
			errorMsg = "太多的二次认证页面参数";
			break;
		case 2238:
			errorMsg = "包格式错误-type";
			break;
		case 2239:
			errorMsg = "包格式错误-mode";
			break;
		case 2240:
			errorMsg = "包格式错误-local_area_num";
			break;
		case 2241:
			errorMsg = "包格式错误-userType";
			break;
		case 2242:
			errorMsg = "包格式错误-adTypeList";
			break;
		case 2243:
			errorMsg = "包格式错误-定向页面对应了太多的定向策略";
			break;
		case 2244:
			errorMsg = "包格式错误-userFlag";
			break;
		case 2245:
			errorMsg = "包格式错误-IPFlag";
			break;
		case 2246:
			errorMsg = "包格式错误-URLFlag";
			break;
		case 2247:
			errorMsg = "包格式错误-factorList";
			break;
		case 2248:
			errorMsg = "包格式错误-factorType";
			break;
		case 2249:
			errorMsg = "包格式错误-factorNum";
			break;
		case 3011:
			errorMsg = "正在处理ips的启动";
			break;
		case 3012:
			errorMsg = "处理线程不空闲（系统繁忙）";
			break;
		case 4011:
			errorMsg = "处理线程不空闲（系统繁忙）";
			break;
		case 4102:
			errorMsg = "db_err";
			break;
		case 4103:
			errorMsg = "用户不存在";
			break;
		case 4104:
			errorMsg = "用户未申请ICS服务";
			break;
		case 4105:
			errorMsg = "取icsFlag出错";
			break;
		case 4106:
			errorMsg = "恢复服务updateuser_ics_info出错";
			break;
		case 4107:
			errorMsg = "暂停服务updateuser_ics_info出错";
			break;
		case 4108:
			errorMsg = "取用户在线update信息错误";
			break;
		case 4109:
			errorMsg = "通知FS失败";
			break;
		case 4120:
			errorMsg = "db_err";
			break;
		case 4121:
			errorMsg = "用户不存在";
			break;
		case 4122:
			errorMsg = "未在本系统内注册";
			break;
		case 4123:
			errorMsg = "申请服务updateuser_ics_info出错";
			break;
		case 4124:
			errorMsg = "申请服务intsertintouser_ics_info出错";
			break;
		case 4125:
			errorMsg = "取消服务updateuser_ics_info出错";
			break;
		case 4126:
			errorMsg = "deletefromuser_ics_info出错";
			break;
		case 4127:
			errorMsg = "deletefromuser_srv_info出错";
			break;
		case 4128:
			errorMsg = "取用户在线update信息错误";
			break;
		case 4129:
			errorMsg = "通知FS失败";
			break;
		default:
			errorMsg = "信息未定义，错误码为:" + errorcode;
			break;
		}
		return errorMsg;
	}
}
