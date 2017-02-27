package cn.emagsoftware.frame.bean;

import cn.emagsoftware.utils.ValidationBean;

public abstract class BaseReqBean {

    /**
	 * RequestBean的补充校验
	 * 
	 * 正常情况下可通过注解的方式对字段的规范性进行校验，由于java Bean Validation的局限性，逻辑判断仍需要自己实现
	 * 
	 * @return
	 */
	public abstract ValidationBean logicValidation();

}
