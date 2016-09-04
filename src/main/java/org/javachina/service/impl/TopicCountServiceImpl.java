package org.javachina.service.impl;

import com.blade.ioc.annotation.Service;
import com.blade.kit.StringKit;
import org.javachina.model.TopicCount;
import org.javachina.service.TopicCountService;

@Service
public class TopicCountServiceImpl implements TopicCountService {

	@Override
	public boolean update(String type, Integer tid, int count) {
		if (StringKit.isBlank(type) || null == tid) {
			return false;
		}
		TopicCount topicCount = this.getCount(tid);
		if (null != topicCount) {
			String sql = String.format("update t_topiccount set %s = (%s + ?) where tid = ?", type, type);

			TopicCount.db.sql(sql, count, tid).execute();
		}
		return false;
	}

	@Override
	public TopicCount getCount(Integer tid) {
		return TopicCount.db.findByPK(tid, TopicCount.class);
	}

	@Override
	public boolean save(Integer tid, Integer create_time) {
		try {
			if (null == tid || tid < 1) {
				return false;
			}
			TopicCount temp = new TopicCount();
			temp.tid = tid;
			temp.views = 0;
			temp.loves = 0;
			temp.favorites = 0;
			temp.comments = 0;
			temp.sinks = 0;
			temp.create_time = create_time;

			TopicCount.db.insert(temp);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
