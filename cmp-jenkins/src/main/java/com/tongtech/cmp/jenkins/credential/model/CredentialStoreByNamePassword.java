package com.tongtech.cmp.jenkins.credential.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * description jenkins用户＋密码保存的凭据
 * <p>
 * version 0.1
 * createDate 2019/9/26 9:20
 * updateDate 2019/9/26 9:20
 *
 * @author wangshaoqi
 */
@Data
public class CredentialStoreByNamePassword implements Serializable {
    @JsonProperty("description")
    @SerializedName("description")
    private String description;
    @JsonProperty("_class")
    @SerializedName("_class")
    private String clazz;
    @JsonProperty("displayName")
    @SerializedName("displayName")
    private String displayName;
    @JsonProperty("id")
    @SerializedName("id")
    private String id;
    @JsonProperty("typeName")
    @SerializedName("typeName")
    private String typeName;

}
