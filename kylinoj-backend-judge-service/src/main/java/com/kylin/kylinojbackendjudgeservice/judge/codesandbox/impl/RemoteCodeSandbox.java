package com.kylin.kylinojbackendjudgeservice.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.kylin.kylinojbackendcommon.common.ErrorCode;
import com.kylin.kylinojbackendcommon.exception.BusinessException;
import com.kylin.kylinojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.kylin.kylinojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.kylin.kylinojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * 远程代码沙箱（实际调用接口的沙箱）
 */
public class RemoteCodeSandbox implements CodeSandbox {

    //定义鉴权请求头和密钥
    private static final String AUTH_REQUEST_HEADER="auth";
    private static final String AUTH_REQUEST_SECRET="codeSandboxSecretKey";
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:8090/executeCode";
        String jsonStr = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER,AUTH_REQUEST_SECRET)
                .body(jsonStr)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "executeCode remoteCodeSandbox error,message:" + responseStr);
        }
        System.out.println("******************remotecodesandbox.java");
        System.out.println(responseStr);
        ExecuteCodeResponse executeCodeResponse = JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
        System.out.println("远程代码沙箱judgeInfo");
        System.out.println(executeCodeResponse.getJudgeInfo());
        return executeCodeResponse;
    }
}
