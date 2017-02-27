package cn.tyiti.xfb.utils;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import cn.tyiti.xfb.bojo.QuotaScore;

/**
 * 额度计算工具类
 * User: KELLEN
 * Date: 2015-10-10
 */
public class QuotaComputerUtil {
	
	public static BigDecimal Coefficient = new BigDecimal(550.0/1080.0);
	public static BigDecimal param = new BigDecimal(10);
	/**
	 * 根据分数计算额度
	 * */
	public static int computerCredit(QuotaScore quota,Integer scoreSum){
		if(StringUtils.isEmpty(quota) || StringUtils.isEmpty(scoreSum) || quota.getMaxQuota() < 0) return 0;
		//最小分
		BigDecimal minScore = new BigDecimal(quota.getMinScore());
		//最大分
		BigDecimal maxScore = new BigDecimal(quota.getMaxScore());
	    //最小额度
		BigDecimal minQuota = new BigDecimal(quota.getMinQuota());
	    //最大额度
		BigDecimal maxQuota = new BigDecimal(quota.getMaxQuota());
		
		//额度差
		BigDecimal differenceCredit=maxQuota.subtract(minQuota);
		//分数差
		BigDecimal differenceScore = maxScore.subtract(minScore);
		//实际分数差
		BigDecimal differenceRealScore  = new BigDecimal(scoreSum).subtract(minScore);
		/**
		 * 计算公式((获取的分数*额度差/10)/分数差)*10+最小额度
		 * */
		BigDecimal temp = differenceRealScore.multiply(differenceCredit).divide(param);
		BigDecimal te  = temp.divide(differenceScore, 0, BigDecimal.ROUND_HALF_UP).multiply(param);
		BigDecimal result = te.add(minQuota);
		return  result.intValue();
	}
	
	public static int reComputerCredit(int Rescore){
		BigDecimal newScore = new BigDecimal(Rescore).multiply(Coefficient);
		return newScore.intValue();
	}
	
}
