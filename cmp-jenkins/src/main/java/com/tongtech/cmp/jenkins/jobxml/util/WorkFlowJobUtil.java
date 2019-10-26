package com.tongtech.cmp.jenkins.jobxml.util;



import com.tongtech.cmp.jenkins.jobxml.model.WorkFlowJob;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

/**
 * description 构建jenkins流水任务的工具类
 * <p>
 * version 0.1
 * createDate 2019/10/15 11:53
 * updateDate 2019/10/15 11:53
 *
 * @author wangshaoqi
 */
public class WorkFlowJobUtil {

    /**
     * 获取 jenkins流水任务的jobXML配置
     *
     * @param workFlowJob 流水线任务
     * @return String  jobXML字符串
     * @throws Exception 异常
     */
    public static String buildJenkinsJobXml(WorkFlowJob workFlowJob) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(workFlowJob.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //打印
        marshaller.marshal(workFlowJob, System.out);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        marshaller.marshal(workFlowJob, byteArrayOutputStream);
        //生成xml字符串并且返回
        return new String(byteArrayOutputStream.toByteArray());
    }
}
