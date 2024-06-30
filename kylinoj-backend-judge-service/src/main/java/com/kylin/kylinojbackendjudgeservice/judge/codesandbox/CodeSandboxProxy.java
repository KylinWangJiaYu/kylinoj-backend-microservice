package com.kylin.kylinojbackendjudgeservice.judge.codesandbox;

import com.kylin.kylinojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.kylin.kylinojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSandboxProxy implements CodeSandbox {
    private final CodeSandbox codeSandbox;//只会改变一次 加final

    /**
     * 代理必须传入原始参数
     * @param codeSandbox
     */
    public CodeSandboxProxy(CodeSandbox codeSandbox){
        this.codeSandbox=codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("代码沙箱请求信息：" + executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("代码沙箱响应信息：" + executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
