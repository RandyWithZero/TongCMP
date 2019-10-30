package com.tongtech.cmp.kube.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

/**
 * description
 * <p>
 * version 0.1
 * createDate 2019/10/30 9:37
 * updateDate 2019/10/30 9:37
 *
 * @author wangshaoqi
 */
public class Volume {
    public static ConfigMapVolume newConfigMapVolume(String name) {
        return new ConfigMapVolume(name);

    }

    public static ConfigMapVolume newConfigMapVolume(String name, Map<String, String> items) {
        return new ConfigMapVolume(name, items);

    }

    public static EmptyDirVolume newEmptyDirVolume(String name) {
        return new EmptyDirVolume(name);

    }

    public static HostPathVolume newHostPathVolume(String name, String path) {
        return new HostPathVolume(name, path);

    }
    public static PvcVolume newPVCVolume(String name, String claimName) {
        return new PvcVolume(name, claimName);
    }

    @Getter
    @Setter
    private static class EmptyDirVolume extends Volume {
        private String name;

        private EmptyDirVolume(String name) {
            this.name = name;

        }
    }

    @Getter
    @Setter
    private static class ConfigMapVolume extends Volume {
        private String name;
        private Map<String, String> items;

        private ConfigMapVolume(String name, Map<String, String> items) {
            this.name = name;
            this.items = items;
        }

        private ConfigMapVolume(String name) {
            this.name = name;
        }
    }

    @Getter
    @Setter
    private static class HostPathVolume extends Volume {
        private String name;
        private String path;

        private HostPathVolume(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
    @Getter
    @Setter
    private static class PvcVolume extends Volume {
        private String name;
        private String claimName;

        private PvcVolume(String name, String claimName) {
            this.name = name;
            this.claimName = claimName;
        }
    }
}
