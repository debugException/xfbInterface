package cn.emagsoftware.utils;

import cn.emagsoftware.frame.bean.BaseReqBean;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 校验工具类
 *
 */
public class ValidationUtils {

    /**
     * BEAN类校验方法
     *
     * @param bean
     * @return ValidationBean
     */
    public static ValidationBean validation(BaseReqBean bean) {
        try {
            // 注解校验
            ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
            Validator validator = vf.getValidator();
            Set<ConstraintViolation<BaseReqBean>> set = validator.validate(bean);

            // 注解校验通过的话，再进行逻辑校验
            if (set.isEmpty()) {
                ValidationBean valid = bean.logicValidation();
                if (valid == null) {
                    valid = new ValidationBean();
                    valid.setValid(true);
                }
                return valid;
            } else {
                // 注解校验不通过，返回第一个错误信息
                ValidationBean valid = new ValidationBean();
                valid.setValid(false);
                for (ConstraintViolation<BaseReqBean> constraintViolation : set) {
                    valid.setMessage(constraintViolation.getMessage());
                    break;
                }
                return valid;
            }
        } catch (Exception e) {
            // 校验异常
            ValidationBean valid = new ValidationBean();
            valid.setValid(false);
            valid.setMessage("validation error!");
            return valid;
        }
    }

    /**
     * 校验语句是否为SELECT语句
     *
     * @param sql 语句
     * @return 是否校验成功
     */
    public static boolean verifySelectSql(String sql) {
        if (StringUtils.isNotEmpty(sql)) {
            String regular = "^((?i)select)[^;]*$";
            return Pattern.matches(regular, sql);
        }
        return true;
    }

}
