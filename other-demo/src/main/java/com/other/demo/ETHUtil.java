package com.other.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;

@Component
public class ETHUtil {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ETHUtil.class);

    @Value("${ethnode}")
    private static String ethNode;
    private static org.springframework.web.client.RestTemplate restTemplate;
    

    public @Autowired void setRestTemplate(org.springframework.web.client.RestTemplate restTemplate) {
		ETHUtil.restTemplate = restTemplate;
	}

	public static String getEthNode() {
        return StringUtils.isNotBlank(ethNode)?ethNode:"http://0.wallet.info/eth2";
    }

    public static Web3j getWeb3() {
        Web3j web3j = Web3j.build(new HttpService(getEthNode()));
        return web3j;
    }

    private interface Executor<T>{
        T executor(Web3j web3j) throws IOException;
    }

    private static <T> T executor(Executor<T> executor){
        Web3j web3 = getWeb3();
        try {
            return executor.executor(web3);
        }
        catch (Throwable t) {
            log.error("eth节点出现异常", t);
            return null;
        }
        finally {
            close(web3);
        }
    }

    private static void close(Web3j web3) {
        if (web3 != null) {
            web3.shutdown();
        }
    }

    public static BigInteger getBlockNumber(){
        return executor(web3j -> {
            Request<?, EthBlockNumber> ethBlockNumberRequest = web3j.ethBlockNumber();
            EthBlockNumber send = ethBlockNumberRequest.send();
            BigInteger blockNumber = send.getBlockNumber();
            return blockNumber;
        });
    }

    public static java.util.List<EthBlock.TransactionResult> getTxHashByBlockNumber(BigInteger blockNumber){
        return executor(web3j -> {
            DefaultBlockParameterNumber number = new DefaultBlockParameterNumber(blockNumber);
            Request<?, EthBlock> ethBlockRequest = web3j.ethGetBlockByNumber(number, true);
            EthBlock ethBlock = ethBlockRequest.send();
            java.util.List<EthBlock.TransactionResult> transactions = ethBlock.getBlock().getTransactions();
            return transactions;
        });
    }

    public static  EthBlock getETHBlock(BigInteger blockNumber){
        return executor(web3j -> {
            DefaultBlockParameterNumber number = new DefaultBlockParameterNumber(blockNumber);
            Request<?, EthBlock> ethBlockRequest = web3j.ethGetBlockByNumber(number, true);
            EthBlock ethBlock = ethBlockRequest.send();
            return ethBlock;
        });
    }

    // ============================ 从eth浏览器api获取  ====================================================

    @Value("${apiKey}")
    private static String apiKey;

    public static String getApiKey(){
        return StringUtils.isNotBlank(apiKey)?apiKey:"48SVC9RR9UN5G923V12TWGVD7Q67J635I9";
    }

    /**
     * eth_blockNumber
     * 获取最新的块
     * {"jsonrpc":"2.0","id":83,"result":"0x9a2184"}
     *
     * {
     *   "status": "0",
     *   "message": "NOTOK",
     *   "result": "Max rate limit reached, please use API Key for higher rate limit"
     * }
     */
    public static String getBlockNumberBy() throws Exception {
        String url = "http://api-cn.etherscan.com/api?module=proxy&action=eth_blockNumber&apikey=" + getApiKey() ;

        String post = restTemplate.postForObject(url, null, String.class);

        JSONObject jsonObject = JSON.parseObject(post);

        String status = jsonObject.getString("status");
        if(StringUtils.isNotBlank(status) ){
            log.error("获取最新区块号异常" + post);
//            throw new Exception;
        }

        String result = jsonObject.getString("result");
        return result;
    }

    /**
     * eth_getBlockByNumber
     * 按块号返回有关块的信息
     *
     * {
     *   "jsonrpc": "2.0",
     *   "id": 1,
     *   "result": {
     */
    public static String getBlockByNumberBy() throws Exception {
        String block = getBlockNumberBy();
        String url = "http://api-cn.etherscan.com/api?module=proxy&action=eth_getBlockByNumber&tag=" + block + "&boolean=true&apikey=" + getApiKey() ;

        String post = restTemplate.postForObject(url, null, String.class);
        JSONObject jsonObject = JSON.parseObject(post);
        String status = jsonObject.getString("status");

        if(StringUtils.isNotBlank(status) ){
            log.error("获取区块号信息异常" + post);
//            throw new GlobalException(SystemCode.code_1002);
        }
        return post;
    }




    public static void main(String[] args) {
        ETHUtil.getBlockNumber();
    }

}
