package com.xxx.planeap.dao.impl;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.xxx.planeap.dao.GrantDao;

public class GrantDaoImpl implements GrantDao {
	private SqlMapClientTemplate sqlMapClient;

	@Override
	public void savePermissions(String rid, JSONArray permissions)
			throws Exception {
		// TODO Auto-generated method stub
		SqlMapClient smc = this.sqlMapClient.getSqlMapClient();
		smc.startBatch();
		Map<String, String> param = new HashMap<String, String>();
		for (int i = 0; i < permissions.size(); i++) {
			JSONObject perm = permissions.getJSONObject(i);
			smc.delete("Permission.delPermission", perm);
			JSONArray funs = (JSONArray) perm.get("fun");
			param.put("rid", perm.getString("rid"));
			param.put("mid", perm.getString("mid"));
			for (int j = 0; j < funs.size(); j++) {
				param.put("fid", funs.getString(j));
				smc.insert("Permission.savePermission", param);
			}
		}
		smc.executeBatch();
	}

	public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

}
