package com.kylin.kylinojbackendjudgeservice.judge.codesandbox;

import com.kylin.kylinojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.kylin.kylinojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 代码沙箱接口定义
 */
public interface CodeSandbox {
    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
