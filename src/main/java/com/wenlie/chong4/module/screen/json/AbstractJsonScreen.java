package com.wenlie.chong4.module.screen.json;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Json 处理
 * </p>
 * User: jc.tao <tantao@aipa.me> Date: 13-9-29 Time: PM3:56
 */
public class AbstractJsonScreen {
	protected static Logger logger = LoggerFactory.getLogger(AbstractJsonScreen.class); // 日志

	@SuppressWarnings ( "SpringJavaAutowiringInspection" )
	@Autowired
	protected HttpServletResponse response;

	/**
	 * json 格式进行相应
	 *
	 * @param object 响应对象
	 */
	public void responseJson(Object... object) throws Exception {
		this.responseJson(response, object);

	}

	/**
	 * @param httpServletResponse 响应
	 * @param object              对象
	 */
	public void responseJson(HttpServletResponse httpServletResponse, Object... object) throws Exception{
			httpServletResponse.setContentType("application/json");
			for (Object obj : object) {
				httpServletResponse.getWriter().write(JSONArray.toJSONString(obj));
			}
	}


}
