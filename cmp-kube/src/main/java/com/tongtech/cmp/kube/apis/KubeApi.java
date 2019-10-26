package com.tongtech.cmp.kube.apis;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tongtech.cmp.common.constant.ProgramInformationConstant;
import com.tongtech.cmp.common.constant.RegexRuleConstant;
import com.tongtech.cmp.kube.exception.KubeResourceNameNotCorrectException;
import com.tongtech.cmp.kube.model.ConfigMap;
import com.tongtech.cmp.kube.model.HttpPatch;
import com.tongtech.cmp.kube.model.Namespace;
import com.tongtech.cmp.kube.util.ObjectTranslateUtil;
import com.tongtech.cmp.kube.util.StringUtil;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.AppsV1Api;
import io.kubernetes.client.apis.BatchV1Api;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.apis.RbacAuthorizationApi;
import io.kubernetes.client.models.*;
import io.kubernetes.client.util.Yaml;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description kubernetes api 封装类
 * <p>
 * version 0.1
 * createDate 2019/10/18 15:35
 * updateDate 2019/10/18 15:35
 *
 * @author wangshaoqi
 */
@Slf4j
public class KubeApi {
    private ApiClient apiClient;
    private volatile AppsV1Api appsV1Api;
    private volatile CoreV1Api coreV1Api;
    private volatile RbacAuthorizationApi rbacAuthorizationApi;
    private volatile BatchV1Api batchV1Api;

    KubeApi(ApiClient apiClient) {
        this.apiClient = apiClient;
        Configuration.setDefaultApiClient(this.apiClient);
    }


    public NamespaceOperateImpl namespaceOperate() {
        coreV1Api();
        return new NamespaceOperateImpl(this.coreV1Api);
    }


    /**
     * 创建命名空间（项目->k8s命名空间）
     *
     * @param namespace 命名空间对象（平台对象）
     * @return String k8s资源对象Yaml字符串
     * @throws ApiException 异常
     */
    public String createNamespace(Namespace namespace) throws ApiException {
        verifyKubeResourceName(namespace.getName());
        coreV1Api();
        V1Namespace v1Namespace = ObjectTranslateUtil.platFormToKube(namespace);
        coreV1Api.createNamespace(v1Namespace, null, null, null);
        return Yaml.dump(v1Namespace);
    }

    /**
     * 删除命名空间
     *
     * @param namespace 平台对象命名空间
     * @throws ApiException 异常
     */
    public void deleteNamespace(Namespace namespace) throws ApiException {
        deleteNamespaceWithName(namespace.getName());
    }

    /**
     * 删除命名空间
     *
     * @param name 命名空间名称
     * @throws ApiException 异常
     */
    public void deleteNamespaceWithName(String name) throws ApiException {
        //这里有一个错误，删除操作返回结果本来是整个资源数据转json，但是传的类型却是V1Status，
        // 无法解析Json，这个问题github有提到，可能都会存在整个操作返回结果json解析错误的问题(不确定)
        // 但是不影响操作结果，仍然成功！
        coreV1Api();
        try {
            coreV1Api.deleteNamespace(name, null, null, null, null, false, null);
        } catch (JsonSyntaxException e) {
            log.warn("JSON解析错误，为API错误，暂时忽略:{}", e.getMessage());
        }

    }

    /**
     * 根据命名空间名称获取命名空间（平台对象）
     *
     * @param name 命名空间名
     * @return Namespace 命名空间（平台对象）
     * @throws ApiException 异常
     */
    public Namespace getNamespace(String name) throws ApiException {
        coreV1Api();
        V1Namespace v1Namespace = coreV1Api.readNamespace(name, null, null, null);
        return ObjectTranslateUtil.kubeToPlatForm(v1Namespace);
    }

    /**
     * 获取命名空间列表（只针对平台创建的命名空间）
     *
     * @return 命名空间列表
     * @throws ApiException 异常
     */
    public List<Namespace> listNamespace() throws ApiException {
        return listNamespaceWithLabelStr(null);
    }

    /**
     * 根据资源标签MAP来获取命名空间列表（只针对平台创建的命名空间）
     *
     * @param labels 标签
     * @return 命名空间列表
     * @throws ApiException 异常
     */
    public List<Namespace> listNamespaceWithLabelMap(Map<String, String> labels) throws ApiException {
        return listNamespaceWithLabelStr(StringUtil.mapToString(labels));
    }

    /**
     * 根据资源标签Str来获取命名空间列表（只针对平台创建的命名空间），其中str格式要求 a=b,c=d
     *
     * @param labels 标签
     * @return 命名空间列表
     * @throws ApiException 异常
     */

    public List<Namespace> listNamespaceWithLabelStr(String labels) throws ApiException {
        coreV1Api();
        String labelStr = ProgramInformationConstant.PROGRAM_DEFAULT_LABELS;
        labelStr = labels == null ? labelStr : labelStr + "," + labels;
        V1NamespaceList v1NamespaceList = coreV1Api.listNamespace(null, null, null, labelStr, null, null, null, false);
        ArrayList<Namespace> namespaces = new ArrayList<>();
        for (V1Namespace v1Namespace : v1NamespaceList.getItems()) {
            V1ObjectMeta metadata = v1Namespace.getMetadata();
            Namespace namespace = new Namespace(metadata.getName());
            namespace.setLabels(metadata.getLabels());
            namespace.setAnnotations(metadata.getAnnotations());
            namespaces.add(namespace);
        }
        return namespaces;
    }

    /**
     * 创建 ConfigMap
     *
     * @param configMap configMap 平台资源对象
     * @return kubernetes资源yaml字符
     * @throws ApiException 异常
     */
    public String createConfigMap(ConfigMap configMap) throws ApiException {
        verifyKubeResourceName(configMap.getName());
        coreV1Api();
        V1ConfigMap v1ConfigMap = ObjectTranslateUtil.platFormToKube(configMap);
        coreV1Api.createNamespacedConfigMap(configMap.getNamespace(), v1ConfigMap, null, null, null);
        return Yaml.dump(v1ConfigMap);
    }

    /**
     * 删除ConfigMap
     *
     * @param configMap configmap平台资源对象
     * @throws ApiException 异常
     */
    public void deleteConfigMap(ConfigMap configMap) throws ApiException {
        deleteConfigMapWithNameAndNameSpace(configMap.getName(), configMap.getNamespace());
    }

    /**
     * 根据名称和所属命名空间获取相应的ConfigMap对象（平台对象）
     *
     * @param name      名称
     * @param namespace 命名空间
     * @return ConfigMap 平台对象
     * @throws ApiException 异常
     */
    public ConfigMap getConfigMap(String name, String namespace) throws ApiException {
        coreV1Api();
        V1ConfigMap v1ConfigMap = coreV1Api.readNamespacedConfigMap(name, namespace, null, null, null);
        return ObjectTranslateUtil.kubeToPlatForm(v1ConfigMap);
    }

    /**
     * 删除ConfigMap
     *
     * @param name      configMap名称
     * @param namespace configMap名字空间名称
     * @throws ApiException 异常
     */
    public void deleteConfigMapWithNameAndNameSpace(String name, String namespace) throws ApiException {
        coreV1Api();
        coreV1Api.deleteNamespacedConfigMap(name, namespace, null, null, null, null, null, null);
    }

    /**
     * 获取所有的命名空间下的ConfigMap列表列表（只针对平台创建的ConfigMap）
     *
     * @return ConfigMap列表
     * @throws ApiException 异常
     */
    public List<ConfigMap> listConfigMapForAllNameSpace() throws ApiException {
        return listConfigMapForAllNameSpaceWithLabelStr(null);
    }

    /**
     * 获取指定的命名空间下的ConfigMap列表列表（只针对平台创建的ConfigMap）
     *
     * @return ConfigMap列表
     * @throws ApiException 异常
     */
    public List<ConfigMap> listNameSpaceConfigMap(String namespace) throws ApiException {
        return listNamespaceConfigMapWithLabelStr(namespace, null);
    }

    /**
     * 根据资源标签String来获取所有命名空间下的ConfigMap列表列表（只针对平台创建的ConfigMap）
     *
     * @param labels 标签
     * @return ConfigMap列表
     * @throws ApiException 异常
     */
    public List<ConfigMap> listConfigMapForAllNameSpaceWithLabelStr(String labels) throws ApiException {
        coreV1Api();
        String labelStr = ProgramInformationConstant.PROGRAM_DEFAULT_LABELS;
        labelStr = labels == null ? labelStr : labelStr + "," + labels;
        V1ConfigMapList v1ConfigMapList = coreV1Api.listConfigMapForAllNamespaces(null, null, labelStr, null, null, null, null, false);
        ArrayList<ConfigMap> configMaps = new ArrayList<>();
        for (V1ConfigMap v1ConfigMap : v1ConfigMapList.getItems()) {
            configMaps.add(ObjectTranslateUtil.kubeToPlatForm(v1ConfigMap));
        }
        return configMaps;

    }

    /**
     * 根据资源标签String来获取指定命名空间下的ConfigMap列表列表（只针对平台创建的ConfigMap）
     *
     * @param namespace 命名空间
     * @param labels    标签 格式 a=b,c=d
     * @return ConfigMap列表
     * @throws ApiException 异常
     */
    public List<ConfigMap> listNamespaceConfigMapWithLabelStr(String namespace, String labels) throws ApiException {
        coreV1Api();
        String labelStr = ProgramInformationConstant.PROGRAM_DEFAULT_LABELS;
        labelStr = labels == null ? labelStr : labelStr + "," + labels;
        V1ConfigMapList v1ConfigMapList = coreV1Api.listNamespacedConfigMap(namespace, null, null, labelStr, null, null, null, null, false);
        ArrayList<ConfigMap> configMaps = new ArrayList<>();
        for (V1ConfigMap v1ConfigMap : v1ConfigMapList.getItems()) {
            configMaps.add(ObjectTranslateUtil.kubeToPlatForm(v1ConfigMap));
        }
        return configMaps;

    }

    /**
     * 根据资源标签Map来获取所有命名空间下的ConfigMap列表列表（只针对平台创建的ConfigMap）
     *
     * @param labels 标签
     * @return ConfigMap列表
     * @throws ApiException 异常
     */

    public List<ConfigMap> listConfigMapForAllNameSpaceWithLabelMap(Map<String, String> labels) throws ApiException {
        return listConfigMapForAllNameSpaceWithLabelStr(StringUtil.mapToString(labels));
    }

    /**
     * 根据资源标签Map来获取指定命名空间下的ConfigMap列表列表（只针对平台创建的ConfigMap）
     *
     * @param namespace 命名空间
     * @param labels    标签
     * @return ConfigMap列表
     * @throws ApiException 异常
     */
    public List<ConfigMap> listNamespaceConfigMapWithLabelMap(String namespace, Map<String, String> labels) throws ApiException {
        return listNamespaceConfigMapWithLabelStr(namespace, StringUtil.mapToString(labels));
    }

    /**
     * 修改ConfigMap资源
     *
     * @param configMap configMap资源(平台资源）
     * @return 修改后yaml资源字符串（kubernetes资源）
     * @throws ApiException 异常
     */
    public String editConfigMapByReplace(ConfigMap configMap) throws ApiException {
        coreV1Api();
        V1ConfigMap v1ConfigMap = ObjectTranslateUtil.platFormToKube(configMap);
        coreV1Api.replaceNamespacedConfigMap(configMap.getName(), configMap.getNamespace(), v1ConfigMap, null, null, null);
        return Yaml.dump(v1ConfigMap);
    }

    public void addOrUpdateConfigMapData(String name, String namespace, Map<String, String> data) throws ApiException {
        coreV1Api();
        List<HttpPatch> httpPatches = new ArrayList<>();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            httpPatches.add(HttpPatch.newAddOp("/data/" + entry.getKey(), entry.getValue()));
        }
        Gson gson = new Gson();
        io.kubernetes.client.custom.V1Patch v1Patch = new io.kubernetes.client.custom.V1Patch(gson.toJson(httpPatches));
        coreV1Api.patchNamespacedConfigMap(name, namespace, v1Patch, null, null, null, null);
    }


    public String editConfigMapData(String configMapName, String namespace, String dataKey, String dataValue) throws ApiException {
        coreV1Api();
        V1ConfigMap v1ConfigMapFromCluster = null;


        return Yaml.dump(v1ConfigMapFromCluster);

    }

    private synchronized void appsV1Api() {
        if (this.appsV1Api == null) {
            this.appsV1Api = new AppsV1Api(this.apiClient);
        }
    }

    private synchronized void batchV1Api() {
        if (this.batchV1Api == null) {
            this.batchV1Api = new BatchV1Api(this.apiClient);
        }
    }

    private synchronized void coreV1Api() {
        if (this.coreV1Api == null) {
            this.coreV1Api = new CoreV1Api(this.apiClient);
        }
    }

    private synchronized void rbacAuthorizationApi() {
        if (this.rbacAuthorizationApi == null) {
            this.rbacAuthorizationApi = new RbacAuthorizationApi(this.apiClient);
        }
    }

    /**
     * 校验kubernetes资源名称 要求名称包含数字、小写字母、- ；且-不能结尾和开头
     *
     * @param name 资源名称
     * @throws KubeResourceNameNotCorrectException 异常
     */
    private void verifyKubeResourceName(String name) throws KubeResourceNameNotCorrectException {
        Pattern compile = Pattern.compile(RegexRuleConstant.KUBE_RESOURCE_NAME_REGEX);
        Matcher matcher = compile.matcher(name);
        boolean result = matcher.matches();
        if (!result) {
            throw new KubeResourceNameNotCorrectException();
        }
    }
}
