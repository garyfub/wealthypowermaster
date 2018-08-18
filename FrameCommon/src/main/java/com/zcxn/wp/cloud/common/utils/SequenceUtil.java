package com.zcxn.wp.cloud.common.utils;


/**   
 * @Title: SequenceUtil.java 
 * @Package: com.sitech.prm.iease.util 
 * @CopyRright (c)2008-2020: si-tech 
 * @Project: 易企算 iease-util
 * @Description: 生成序列工具类
 * @author 卡尔 guoqs@si-tech.com.cn
 * @date 2016年6月16日 下午1:51:38 
 * @version V2.0   
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: SequenceUtil
 * @Description: 生成序列工具类
 * @author 卡尔 guoqs@si-tech.com.cn
 */
public class SequenceUtil {
	private static final long ONE_STEP = 10;
	private static final Lock LOCK = new ReentrantLock();
	private static long lastTime = System.currentTimeMillis();
	private static short lastCount = 0;
	private static int count = 0;


	@SuppressWarnings("finally")
	public static String nextId() {
		LOCK.lock();
		try {
			if (lastCount == ONE_STEP) {
				boolean done = false;
				while (!done) {
					long now = System.currentTimeMillis();
					if (now == lastTime) {
						try {
							Thread.currentThread();
							Thread.sleep(1);
						} catch (InterruptedException e) {
						}
						continue;
					} else {
						lastTime = now;
						lastCount = 0;
						done = true;
					}
				}
			}
			count = lastCount++;
		} finally {
			LOCK.unlock();
			SimpleDateFormat sf = new SimpleDateFormat( "yyyyMMddhhmmssSSS" );
			return sf.format( new Date( lastTime ) )+ count;
		}
	}
}
