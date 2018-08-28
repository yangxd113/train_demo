package com.cucc.microservice.train_demo.domain;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * 服务应答类
 */
public class ServiceResponse {
    private static Logger logger =  LoggerFactory.getLogger(ServiceResponse.class);


    @ApiModelProperty(name = "STATUS", value = "服务请求结果编码", required = true, example = "0000")
    @JsonProperty("STATUS")
    private String status;
    @ApiModelProperty(name = "MSG", value = "服务请求结果描述", required = true, example = "服务调用成功！")
    @JsonProperty("MSG")
    private String msg;
    @ApiModelProperty(name = "TXID", value = "服务流水号(自动生成)", required = true, example = "b393548b3dc5^1504165518997^70")
    @JsonProperty("TXID")
    private String txid;
    @ApiModelProperty(name = "RSP", value = "服务返回业务数据", required = true)
    @JsonProperty("RSP")
    private Rsp rsp;


    /**
     * 默认构造函数
     */
    public ServiceResponse(){
        try{
            this.txid = this.generateTxid();


        }catch (Exception e){
            logger.error("can not get PtxId:", e);
        }
    }

    /**
     * 服务成功调用应答信息
     * @param rsp
     * @return ServiceResponse
     */
    public ServiceResponse getSuccessResponse(Rsp rsp){
        this.setStatus(ServiceConstant.STATUS_SUCCESS);
        this.setMsg(ServiceConstant.MSG_SUCCESS);
        this.setRsp(rsp);
        this.setTxid(this.generateTxid());
        return this;
    }
    /**
     * 服务其他错误返回结果
     * @param errorStatus
     * @param errorMsg
     * @param rsp
     * @return
     */
    public ServiceResponse getErrorResponse(String errorStatus, String errorMsg, Rsp rsp){
        this.setStatus(errorStatus);
        this.setMsg(errorMsg);
        this.setRsp(rsp);
        this.setTxid(this.generateTxid());
        return this;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Rsp getRsp() {
        return rsp;
    }

    public void setRsp(Rsp rsp) {
        this.rsp = rsp;
    }

    /**
     * 请统一使用此方法获取txid
     *
     * 获取Ptxid，截取后作为流水号txid，规则"容器ID后4位-容器创建时间戳-被调用序号"
     * @return (String) txid
     */
    public static String generateTxid(){
        try{
            //实例化一个LoggingEvent对象，触发子线程拷贝MDC
            new LoggingEvent("mdcTrigger", (ch.qos.logback.classic.Logger)logger, null, null, null, null);
            String txid = MDC.get("PtxId");
            if (txid == null){
                logger.error("txid is null");
                return ServiceConstant.TXID_FAIL;
            }
            return txid.substring(8);
        } catch(Exception e){
            logger.error("获取服务调用链txid异常",e);
            return ServiceConstant.TXID_FAIL;
        }
    }

}
