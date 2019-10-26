package com.tongtech.cmp.jenkins.jobxml.intend;

import java.util.HashMap;
import java.util.Map;

/**
 * description jenkins 插件版本信息过度位
 * <p>
 * version 0.1
 * createDate 2019/10/16 18:02
 * updateDate 2019/10/16 18:02
 *
 * @author wangshaoqi
 */
public class PluginsNameAndVersion {
    private final static class PluginItem {
        String name;
        String version;

        PluginItem(String name, String version) {
            this.name=name;
            this.version=version;

        }
    }

    private final static Map<String, PluginItem> PLUGIN_ITEM_MAP = new HashMap<String, PluginItem>();

    public static String getPluginInfo(String pluginName) {
        PluginItem pluginItem = PLUGIN_ITEM_MAP.get(pluginName);
        return pluginItem!=null?pluginItem.name + "@" + pluginItem.version:null;
    }

    public static void addPluginInfo(String pluginName, String pluginVersion) {
        PLUGIN_ITEM_MAP.put(pluginName, new PluginItem(pluginName, pluginVersion));
    }
    public static boolean isEmpty(){
        return PLUGIN_ITEM_MAP.isEmpty();
    }
}
