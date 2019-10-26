package com.tongtech.cmp.kube.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * description Http Patch
 * <p>
 * 样式：
 * [
 * { "op": "test", "path": "/a/b/c", "value": "foo" },
 * { "op": "remove", "path": "/a/b/c" },
 * { "op": "add", "path": "/a/b/c", "value": [ "foo", "bar" ] },
 * { "op": "replace", "path": "/a/b/c", "value": 42 },
 * { "op": "move", "from": "/a/b/c", "path": "/a/b/d" },
 * { "op": "copy", "from": "/a/b/d", "path": "/a/b/e" }
 * ]
 * </p>
 * version 0.1
 * createDate 2019/10/24 15:09
 * updateDate 2019/10/24 15:09
 *
 * @author wangshaoqi
 */
@Getter
@Setter
public class HttpPatch {
    @SerializedName("op")
    private String operation;
    @SerializedName("path")
    private String path;
    @SerializedName("value")
    private Object value;
    @SerializedName("from")
    private String from;

    private HttpPatch() {
    }

    public static HttpPatch newTestOp(String path, Object value) {
        HttpPatch httpPatch = new HttpPatch();
        httpPatch.path = path;
        httpPatch.operation = "test";
        httpPatch.value = value;
        return httpPatch;
    }

    public static HttpPatch newAddOp(String path, Object value) {
        HttpPatch httpPatch = newTestOp(path, value);
        httpPatch.operation = "add";
        return httpPatch;
    }

    public static HttpPatch newReplaceOp(String path, Object value) {
        HttpPatch httpPatch = newTestOp(path, value);
        httpPatch.operation = "replace";
        return httpPatch;
    }

    public static HttpPatch newMoveOp(String from, String path) {
        HttpPatch httpPatch = new HttpPatch();
        httpPatch.path = path;
        httpPatch.from = from;
        httpPatch.operation = "move";
        return httpPatch;
    }

    public static HttpPatch newCopyOp(String from, String path) {
        HttpPatch httpPatch = newMoveOp(from, path);
        httpPatch.operation = "copy";
        return httpPatch;
    }

    public static HttpPatch newRemoveOp(String path) {
        HttpPatch httpPatch = new HttpPatch();
        httpPatch.path = path;
        httpPatch.operation = "remove";
        return httpPatch;
    }
}
