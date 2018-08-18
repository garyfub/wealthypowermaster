package com.zcxn.wp.cloud.common.fallbacklog;

import com.zcxn.wp.cloud.common.rpc.FallBackLogService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.LoggerFactory;

/**
 * 用于记录 熔断Hystrix日志
 */
public class FallBackLog extends Thread {

  private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FallBackLog.class);

  private static FallBackLog fallBackLog;

  private FallBackLogService fallBackLogService;

  private static BlockingQueue<FallBackLogModel> logInfoQueue = new LinkedBlockingQueue<FallBackLogModel>(1024);

  private FallBackLog()
  {
    super("FallBackLog Thread");
  }

  public FallBackLog setFallBackLogService(FallBackLogService fallBackLogService)
  {
     this.fallBackLogService = fallBackLogService;
     return this;
  }

  private static synchronized FallBackLog getInstance()
  {
      if(fallBackLog == null)
      {
         fallBackLog = new FallBackLog();
      }
      return fallBackLog;
  }

  public void offerQueue(FallBackLogModel context) {
    try {
      logInfoQueue.offer(context);
    } catch (Exception e) {
      logger.error("日志写入失败", e);
    }
  }

  public void run() {
    List<FallBackLogModel> bufferedLogList = new ArrayList<FallBackLogModel>(); // 缓冲队列
    while (true) {
      try {
        bufferedLogList.add(logInfoQueue.take());
        logInfoQueue.drainTo(bufferedLogList);
        if (bufferedLogList != null && bufferedLogList.size() > 0) {
          // 写入日志
          for(FallBackLogModel context:bufferedLogList){

            fallBackLogService.send( context );
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        // 防止缓冲队列填充数据出现异常时不断刷屏
        try {
          Thread.sleep(1000);
        } catch (Exception eee) {
        }
      } finally {
        if (bufferedLogList != null && bufferedLogList.size() > 0) {
          try {
            bufferedLogList.clear();
          } catch (Exception e) {
          }
        }
      }
    }
  }




}
