package org.javachina.controller;

import java.util.List;
import java.util.Map;

import org.javachina.dto.NodeDto;
import org.javachina.dto.TopicDto;
import org.javachina.model.Node;
import org.javachina.service.CommentService;
import org.javachina.service.NodeService;
import org.javachina.service.TopicService;

import com.blade.ioc.annotation.Inject;
import com.blade.jdbc.Pager;
import com.blade.kit.StringKit;
import com.blade.mvc.annotation.Controller;
import com.blade.mvc.annotation.PathVariable;
import com.blade.mvc.annotation.RequestParam;
import com.blade.mvc.annotation.Route;
import com.blade.mvc.http.HttpMethod;
import com.blade.mvc.view.ModelAndView;

/**
 * Created by oushaku on 16/8/26.
 */
@Controller
public class TopicController extends BaseController{
	
	@Inject
	private NodeService nodeService;
	
	@Inject
	private TopicService topicService;
	
	@Inject
	private CommentService commentService;
	
    /**
     * 首页
     * @return
     */
    @Route(value = "/", method = HttpMethod.GET)
    public ModelAndView index(ModelAndView mav,
    		@RequestParam(value="page", required = false, defaultValue = "1") int page,
    		@RequestParam String tab
    		){
    	
    	Integer nid = null;
    	if(StringKit.isNotBlank(tab)){
    		Node node = nodeService.getNode(tab);
    		if(null != node){
    			nid = node.nid;
    		}
    	}
    	
    	List<Node> hot_nodes = nodeService.getNodes("topics desc", 1, 10).getList();
    	Pager<TopicDto> topicPage = topicService.getTopics(nid, page, 10);
    	List<NodeDto> nodes = nodeService.getNodes();
    	
    	mav.add("hot_nodes", hot_nodes);
    	mav.add("nodes", nodes);
    	mav.add("topicPage", topicPage);
    	mav.add("tab", tab);
    	mav.setView("home");
        return mav;
    }
    
    /**
     * 帖子详情页
     * @return
     */
    @Route(value = "/topic/:tid", method = HttpMethod.GET)
    public ModelAndView topicDetail(ModelAndView mav, @PathVariable int tid,
    		@RequestParam(value="page", required = false, defaultValue = "1") int page){
    	TopicDto topic = topicService.getTopicDetail(tid);
    	Pager<Map<String, Object>> commentPage = commentService.getPageListMap(tid, null, "create_time asc", page, 10);
    	
    	mav.add("topic", topic);
    	mav.add("commentPage", commentPage);
    	mav.setView("topic_detail");
        return mav;
    }
    
}
