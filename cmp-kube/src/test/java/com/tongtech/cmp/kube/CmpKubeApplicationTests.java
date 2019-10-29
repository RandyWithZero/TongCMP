package com.tongtech.cmp.kube;

import com.tongtech.cmp.kube.major.KubeApi;
import com.tongtech.cmp.kube.major.KubeApiSupporter;
import com.tongtech.cmp.kube.model.ConfigMap;
import com.tongtech.cmp.kube.model.Namespace;
import io.kubernetes.client.ApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
public class CmpKubeApplicationTests {
    @Autowired
    private KubeApiSupporter supporter;
    private String path = "C:\\Users\\admin\\.kube\\configx";
    private String url="https://168.1.25.21:6443";
    private String token="eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJ0b25nY21wLWFkbWluLXRva2VuLWx3NTZsIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6InRvbmdjbXAtYWRtaW4iLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiI5NmI5NTM2Yy1mNjA5LTExZTktOWJjMC0wMDBjMjlhMzJlZDgiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZS1zeXN0ZW06dG9uZ2NtcC1hZG1pbiJ9.zC7Y685tXrnIbQOFAARkMp1D0tjV_4fjbQW1RMJKjCv2kBUxZZ8jEgr5CtvAytnGknV2FDXBnd8KlSLx_mG17S83BztR7k6yCmfCEIPeCp1p3aDP4ICpiQt6iPx2fE-oV6WysXVfO29MdyAhDWCZvisa4F-FTuF7VEGPGu8xYwNmVoI7XpgUz1zqAC3MqgSXE-tlGH5e8H6Hjp6U4iDCECPH4-c7Vt61itthFKvU4ZYIXNzxqGbMkxZf9a7M8p7DUg1fPxjBqGqt9jXvKMYotGsdWcu8AORBmMyDQVWVPjob_le5WqENKRqaeR8awtbUCGb0eWY_fP52PKsyU_385w";
    @Test
    public void createNamespace2() throws Exception {
        supporter.initApiClientFromConfigPath("api1", path);
        KubeApi kubeApi = supporter.getCurrentApi();
        try {
            kubeApi.configMapOperate().delete().withNamespace("tongcmp1").addLabel("test","test").doneVoid();

        } catch (ApiException e) {
            System.out.println(e.getResponseBody());
        }

    }







}
