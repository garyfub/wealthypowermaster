package com.zcxn.wp.cloud.common.rpc;

import com.zcxn.wp.cloud.common.fallbacklog.FallBackLogModel;
import com.zcxn.wp.cloud.common.rpc.impl.FallBackLogServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "iEase-base-server",fallback =FallBackLogServiceImpl.class)
@Component
public interface FallBackLogService {

  @RequestMapping("/fallbacklog")
  public void send(@RequestBody FallBackLogModel logModel);


}
