package com.javachina.kit;

import java.util.HashMap;
import java.util.Map;

import com.blade.kit.DateKit;
import com.blade.kit.StringKit;
import com.blade.kit.http.HttpRequest;
import com.blade.kit.json.JSONKit;
import com.javachina.Constant;

public class FamousKit {

	private static final Map<String, FamousDay> pools = new HashMap<String, FamousDay>(2);
	
	public static FamousDay getTodayFamous(){
		String today = DateKit.getToday("yyyy-MM-dd");
		if(null != pools.get(today)){
			return pools.get(today);
		}
		pools.clear();
		FamousDay famousDay = new FamousDay();
		if(StringKit.isNotBlank(Constant.FAMOUS_KEY)){
			String body = HttpRequest.get("http://api.avatardata.cn/MingRenMingYan/Random?key=" + Constant.FAMOUS_KEY).body();
			if(StringKit.isNotBlank(body)){
				String famous_saying = JSONKit.parseObject(body).get("result").asJSONObject().getString("famous_saying");
				String famous_name = JSONKit.parseObject(body).get("result").asJSONObject().getString("famous_name");
				famousDay.setFamous_saying(famous_saying);
				famousDay.setFamous_name(famous_name);
			}
		} else {
			famousDay.setFamous_saying("好奇的目光常常可以看到比他所希望看到的东西更多。");
			famousDay.setFamous_name("莱辛");
		}
		pools.put(today, famousDay);
		return famousDay;
	}
	
}
