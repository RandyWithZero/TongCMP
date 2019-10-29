package com.tongtech.cmp.kube.major;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.custom.V1Patch;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
import io.kubernetes.client.util.credentials.AccessTokenAuthentication;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description
 * <p>
 * version 0.1
 * createDate 2019/10/21 14:51
 * updateDate 2019/10/21 14:51
 *
 * @author wangshaoqi
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class KubeApiSupporter {
    private static Map<String, KubeApi> apis = new ConcurrentHashMap<>(4);
    private volatile String currentApi;
    private volatile String currentJsonPatchApi;
    private volatile String currentStrategicMergePatchApi;
    private volatile String currentApplyYamlPatchApi;

    public KubeApi getCurrentApi() {
        return apis.get(this.currentApi);
    }

    public KubeApi getCurrentJsonPatchApi() {
        return apis.get(this.currentJsonPatchApi);
    }

    public KubeApi getCurrentStrategicMergePatchApi() {
        return apis.get(this.currentStrategicMergePatchApi);
    }

    public KubeApi getApplyYamlPatchApi() {
        return apis.get(this.currentApplyYamlPatchApi);
    }

    @Deprecated
    public synchronized void initApiClientFromUrl(String clusterId, String url) throws IOException {
        clusterId = clusterId == null ? "" : clusterId;
        setApiKey(clusterId);
        if (apis.get(currentApi) == null) {
            KubeApi kubeApi = new KubeApi(Config.fromUrl(url));
            apis.put(currentApi, kubeApi);
        }
        if (apis.get(currentJsonPatchApi) == null) {
            patchClientBuild(V1Patch.PATCH_FORMAT_JSON_PATCH, clusterId);
        }
        if (apis.get(currentStrategicMergePatchApi) == null) {
            patchClientBuild(V1Patch.PATCH_FORMAT_STRATEGIC_MERGE_PATCH, clusterId);
        }
        if (apis.get(currentApplyYamlPatchApi) == null) {
            patchClientBuild(V1Patch.PATCH_FORMAT_APPLY_YAML, clusterId);
        }
    }


    public synchronized void initApiClientFromConfigPath(String clusterId, String path) throws IOException {
        clusterId = clusterId == null ? "" : clusterId;
        setApiKey(clusterId);
        if (apis.get(currentApi) == null) {
            ApiClient build = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(path))).build();
            KubeApi kubeApi = new KubeApi(build);
            apis.put(currentApi, kubeApi);
        }
        if (apis.get(currentJsonPatchApi) == null) {
            patchClientBuildWithPath(V1Patch.PATCH_FORMAT_JSON_PATCH, clusterId, path);
        }
        if (apis.get(currentStrategicMergePatchApi) == null) {
            patchClientBuildWithPath(V1Patch.PATCH_FORMAT_STRATEGIC_MERGE_PATCH, clusterId, path);
        }
        if (apis.get(currentApplyYamlPatchApi) == null) {
            patchClientBuildWithPath(V1Patch.PATCH_FORMAT_APPLY_YAML, clusterId, path);
        }
    }

    public synchronized void initApiClientFromToken(String clusterId, String token, String url) throws IOException {
        clusterId = clusterId == null ? "" : clusterId;
        setApiKey(clusterId);
        if (apis.get(currentApi) == null) {
            ClientBuilder clientBuilder = new ClientBuilder().setBasePath(url).setVerifyingSsl(false);
            ApiClient client = clientBuilder.setAuthentication(new AccessTokenAuthentication(token)).build();
            KubeApi kubeApi = new KubeApi(client);
            apis.put(currentApi, kubeApi);
        }
        if (apis.get(currentJsonPatchApi) == null) {
            patchClientBuildWithToken(V1Patch.PATCH_FORMAT_JSON_PATCH, clusterId, token, url);
        }
        if (apis.get(currentStrategicMergePatchApi) == null) {
            patchClientBuildWithToken(V1Patch.PATCH_FORMAT_STRATEGIC_MERGE_PATCH, clusterId, token, url);
        }
        if (apis.get(currentApplyYamlPatchApi) == null) {
            patchClientBuildWithToken(V1Patch.PATCH_FORMAT_APPLY_YAML, clusterId, token, url);
        }
    }


    private void patchClientBuild(String patch, String clusterId) throws IOException {
        ApiClient jsonPatchClient = ClientBuilder.standard().setOverridePatchFormat(patch).build();
        KubeApi kubeApi = new KubeApi(jsonPatchClient);
        String key = UUID.nameUUIDFromBytes((patch + clusterId).getBytes()).toString();
        apis.put(key, kubeApi);

    }

    private void patchClientBuildWithPath(String patch, String clusterId, String path) throws IOException {
        ApiClient build = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(path))).setOverridePatchFormat(patch).build();
        String key = UUID.nameUUIDFromBytes((patch + clusterId).getBytes()).toString();
        KubeApi kubeApi = new KubeApi(build);
        apis.put(key, kubeApi);
    }

    private void patchClientBuildWithToken(String patch, String clusterId, String token, String url) throws IOException {
        ClientBuilder clientBuilder = new ClientBuilder().setBasePath(url).setVerifyingSsl(false);
        ApiClient client = clientBuilder.setAuthentication(new AccessTokenAuthentication(token)).setOverridePatchFormat(patch).build();
        String key = UUID.nameUUIDFromBytes((patch + clusterId).getBytes()).toString();
        KubeApi kubeApi = new KubeApi(client);
        apis.put(key, kubeApi);
    }

    private void setApiKey(String clusterId) {
        this.currentApi = UUID.nameUUIDFromBytes(clusterId.getBytes()).toString();
        this.currentJsonPatchApi = UUID.nameUUIDFromBytes((V1Patch.PATCH_FORMAT_JSON_PATCH + clusterId).getBytes()).toString();
        this.currentStrategicMergePatchApi = UUID.nameUUIDFromBytes((V1Patch.PATCH_FORMAT_STRATEGIC_MERGE_PATCH + clusterId).getBytes()).toString();
        this.currentApplyYamlPatchApi = UUID.nameUUIDFromBytes((V1Patch.PATCH_FORMAT_APPLY_YAML + clusterId).getBytes()).toString();
    }
}
