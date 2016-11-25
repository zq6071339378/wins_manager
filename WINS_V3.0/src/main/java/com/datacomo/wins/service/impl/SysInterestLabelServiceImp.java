package com.datacomo.wins.service.impl;

import com.datacomo.wins.business.PolicyLabelClassifyBusiness;
import com.datacomo.wins.business.SysInterestLabelBusiness;
import com.datacomo.wins.exception.BusinessException;
import com.datacomo.wins.service.SysInterestLabelService;
import com.datacomo.wins.util.ApiConfigUtil;
import com.datacomo.wins.util.ApiRequestUtil;
import com.datacomo.wins.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxiongbin on 2016/9/22.
 */
@Service("sysInterestLabelService")
public class SysInterestLabelServiceImp implements SysInterestLabelService {
    private static Logger logger = Logger.getLogger(SysInterestLabelServiceImp.class.getName());

    @Autowired
    private SysInterestLabelBusiness sysInterestLabelBusiness;
    @Autowired
    private PolicyLabelClassifyBusiness policyLabelClassifyBusiness;

    @Override
    public int updateAllInterestLabel() {
        logger.info("getAllInterestLabel method start");
        String api = String.valueOf(ApiConfigUtil.message.get("searchInterestLabelApi"));
        String parames = null;
        List<Map<String,Object>> listMap = ApiRequestUtil.searchAllCrowdLabel(api,parames);
        int result = -2;
        try {
            if(listMap.size()>0) {
                List<Map<String,Object>> parent = new ArrayList<>(); //分类
                List<Map<String,Object>> childs = new ArrayList<>();  //人群标签
                for (Map<String, Object> map : listMap) {
                    if (String.valueOf(map.get("id")).equals("1")) {
                        continue;
                    }
                    if(String.valueOf(map.get("pid")).equals("1")){
                        parent.add(map);
                    }else{
                        childs.add(map);
                    }
                }
                sysInterestLabelBusiness.deleteAllInfo();  //删除所有人群标签群组 group_type=2
                Map<String,Object> conditionData = new HashMap<>();
                List<Map<String,Object>> list = new ArrayList<>();
                for(Map<String,Object> map_p : parent){
                    String id = String.valueOf(map_p.get("id"));
                    String enNameP = String.valueOf(map_p.get("en_name"));
                    String ynNameP = String.valueOf(map_p.get("yn_name"));
                    String chNameP = String.valueOf(map_p.get("ch_name"));
                    for(Map<String,Object> map_c : childs){
                        String pid = String.valueOf(map_c.get("pid"));
                        if(id.equals(pid)){
                            String enName = String.valueOf(map_c.get("en_name"));
                            String ynName = String.valueOf(map_c.get("yn_name"));
                            String chName = String.valueOf(map_c.get("ch_name"));
                            Map<String,Object> condition = new HashMap<>();
                            condition.put("groupName",enName); //以英文名作为群组名称
                            condition.put("groupType","2");
                            condition.put("createUser","0");
                            condition.put("createTime", DateUtil.getDateString());
                            sysInterestLabelBusiness.insertOneInfo(condition);
                            String labelId = String.valueOf(condition.get("Group_Id"));  //标签Id
                            Map<String,Object> condit = new HashMap<>();
                            condit.put("groupId",labelId);
                            condit.put("enName",enNameP);
                            condit.put("ynName",ynNameP);
                            condit.put("chName",chNameP);
                            list.add(condit);
                        }else{
                            continue;
                        }
                    }
                }
                conditionData.put("list",list);
                conditionData.put("createUser","0");
                conditionData.put("createTime",DateUtil.getDateString());
                policyLabelClassifyBusiness.deleteAllInfo();    //删除所有人群标签分类
                result = policyLabelClassifyBusiness.insertAllInfo(conditionData); //人群标签入库
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return -1;
        }
        logger.info("getAllInterestLabel method end");
        return result;
    }

    @Override
    public int updateAllUserByInterestLabel(){
        logger.info("updateAllUserByInterestLabel method start");
        String usersApi = String.valueOf(ApiConfigUtil.message.get("searchUserInfoApi"));
        List<Map<String,Object>> interset = null;
        int result = -1;
        try {
            List<Map<String,Object>> labels = policyLabelClassifyBusiness.findAllInfo();
            if(labels.size()>0){
                Map<String,Object> condition = new HashMap<>();
                List<Map<String,Object>> list = new ArrayList<>();
                for (Map<String,Object> map : labels){
                    String classify = String.valueOf(map.get("classify"));
                    String label = String.valueOf(map.get("label"));
                    String groupId = String.valueOf(map.get("groupId"));
                    List<String> users = ApiRequestUtil.searchUserPhoneByLabel(usersApi,classify,label);
                    if(users.size()>0){
                        for (String str : users){
                            if(str=="" || str==null){
                                continue;
                            }
                            Map<String,Object> data = new HashMap<>();
                            data.put("groupId",groupId);
                            data.put("userId",str);
                            list.add(data);
                        }
                    }
                }
                condition.put("createUser","0");
                condition.put("createTime",DateUtil.getDateString());
                condition.put("dynamicDes","label");  //添加dynamicDes = "label" 作为人群标签群组用户的标识，以便删除人群标签群组用户
                condition.put("list",list);
                sysInterestLabelBusiness.deleteAll(); //删除旧的数据
                sysInterestLabelBusiness.insertInfo(condition);
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            logger.error(e);
            return -1;
        }
        logger.info("updateAllUserByInterestLabel method end");
        return result;
    }

}
