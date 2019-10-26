package com.tongtech.cmp.jenkins.jobxml.model;



import com.tongtech.cmp.jenkins.jobxml.intend.PluginsNameAndVersion;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;

/**
 * description  jenkins流水线任务jobXML 中参数properties 模型
 * <p>
 * version 0.1
 * createDate 2019/10/14 17:47
 * updateDate 2019/10/14 17:47
 *
 * @author wangshaoqi
 */
@XmlRootElement(name = "properties")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkFlowJobProperty {
    @XmlElement(name = "org.jenkinsci.plugins.workflow.job.properties.PipelineTriggersJobProperty")
    private PipelineTriggerJobProperty pipelineTriggerJobProperty;


    public WorkFlowJobProperty enableReverseBuidGTrigger(String projectName, ThresholdEnum thresholdEnum) {
        if (pipelineTriggerJobProperty != null) {
            ReverseBuildTrigger reverseBuildTrigger = new ReverseBuildTrigger();
            reverseBuildTrigger.setSpec("");
            Threshold threshold = new Threshold();
            threshold.setColor(thresholdEnum.getColor());
            threshold.setCompleteBuild(thresholdEnum.isCompleteBuild());
            threshold.setName(thresholdEnum.getName());
            threshold.setOrdinal(thresholdEnum.getOrdinal());
            reverseBuildTrigger.setThreshold(threshold);
            reverseBuildTrigger.setUpstreamProjects(projectName);
            this.pipelineTriggerJobProperty.getTriggerGroup().setReverseBuildTrigger(reverseBuildTrigger);
        }
        return this;
    }

    public WorkFlowJobProperty enableTimerTrigger(String cron) {
        if (pipelineTriggerJobProperty != null) {
            TimerTrigger timerTrigger = new TimerTrigger();
            timerTrigger.setSpec(cron);
            this.pipelineTriggerJobProperty.getTriggerGroup().setTimerTrigger(timerTrigger);

        }
        return this;
    }

    public WorkFlowJobProperty enableGitHubPushTrigger() throws IOException {
        if (pipelineTriggerJobProperty != null) {
            GitHubPushTrigger gitHubPushTrigger = new GitHubPushTrigger();
            gitHubPushTrigger.setSpec("");
            gitHubPushTrigger.setPlugin(PluginsNameAndVersion.getPluginInfo(gitHubPushTrigger.getPluginName()));
            this.pipelineTriggerJobProperty.getTriggerGroup().setGitHubPushTrigger(gitHubPushTrigger);
        }
        return this;
    }

    public WorkFlowJobProperty enableGitLabPushTrigger() throws IOException {
        if (pipelineTriggerJobProperty != null) {
            GitLabPushTrigger gitLabPushTrigger = new GitLabPushTrigger();
            gitLabPushTrigger.setSpec("");
            gitLabPushTrigger.setPlugin(PluginsNameAndVersion.getPluginInfo(gitLabPushTrigger.getPluginName()));
            this.pipelineTriggerJobProperty.getTriggerGroup().setGitLabPushTrigger(gitLabPushTrigger);
        }
        return this;


    }

    public void endProperties() {
    }

}
