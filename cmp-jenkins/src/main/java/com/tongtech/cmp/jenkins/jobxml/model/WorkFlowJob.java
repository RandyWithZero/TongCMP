package com.tongtech.cmp.jenkins.jobxml.model;


import com.tongtech.cmp.jenkins.jobxml.exception.EmptyJenkinsPluginsInfoException;
import com.tongtech.cmp.jenkins.jobxml.intend.PluginsNameAndVersion;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.io.IOException;

/**
 * description jenkins流水线任务jobXML根 模型
 * <p>
 * version 0.1
 * createDate 2019/10/14 17:44
 * updateDate 2019/10/14 17:44
 *
 * @author wangshaoqi
 */
@XmlRootElement(name = "flow-definition")
@Data
@XmlAccessorType(XmlAccessType.NONE)
public class WorkFlowJob {
    private final String pluginName = "workflow-job";
    @XmlElement(name = "keepDependencies")
    private boolean keepDependencies;
    @XmlElement(name = "actions")
    private String actions;
    @XmlElement(name = "properties")
    private WorkFlowJobProperty workFlowJobProperty;
    @XmlElement(name = "disabled")
    private boolean disabled;
    @XmlElement(name = "definition")
    private Definition definition;
    @XmlElement(name = "triggers")
    private String triggers;
    @XmlAttribute(name = "plugin")
    private String plugin;
    @XmlElement(name = "description")
    private String description;
    private String name;

    public WorkFlowJob() {
    }

    public WorkFlowJob(String jobName, String description) throws EmptyJenkinsPluginsInfoException,IOException {
        if(PluginsNameAndVersion.isEmpty()){
            throw new EmptyJenkinsPluginsInfoException();
        }
        this.name = jobName;
        this.description = description;
        this.actions = "";
        this.triggers = "";
        Definition definition = new Definition();
        definition.setPlugin(PluginsNameAndVersion.getPluginInfo(definition.getPluginName()));
        this.definition = definition;
        this.withWorkFlowJobPluginInfo(PluginsNameAndVersion.getPluginInfo(this.pluginName));
    }

    public WorkFlowJobProperty withProperties() {
        WorkFlowJobProperty workFlowJobProperty = new WorkFlowJobProperty();
        PipelineTriggerJobProperty pipelineTriggerJobProperty = new PipelineTriggerJobProperty();
        TriggerGroup triggerGroup = new TriggerGroup();
        pipelineTriggerJobProperty.setTriggerGroup(triggerGroup);
        workFlowJobProperty.setPipelineTriggerJobProperty(pipelineTriggerJobProperty);
        this.workFlowJobProperty = workFlowJobProperty;
        return this.workFlowJobProperty;
    }


    public void withPipelineScript(String script) throws IOException {
        this.definition.setScript(script);
    }

    private void withWorkFlowJobPluginInfo(String pluginInfo) throws IOException {
        this.setPlugin(pluginInfo);
    }


}
