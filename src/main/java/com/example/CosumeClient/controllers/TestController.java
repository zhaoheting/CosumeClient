package com.example.CosumeClient.controllers;

import com.example.GrandWorldMSpec.generated.client.template.RuleManagementApi;
import com.example.GrandWorldMSpec.generated.model.RuleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Arrays;

@RestController
public class TestController {
    @Autowired
    RuleManagementApi ruleManagementApi;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/testBytes")
    public void testString() {
        RuleResponse ruleResponse = ruleManagementApi.downLoadZipInBytesGet();
        byte[] bytes;
        try {
            bytes = ruleResponse.getRuleInBytes();
            File file = new File("c:/des.zip");
            //需要以管理员身份运行编辑器
            file.createNewFile();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            System.out.println(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/testFile")
    public void testFile() throws IOException {
        File zipFile = ruleManagementApi.downLoadZipInFileGet();
        FileInputStream fileInputStream = new FileInputStream(zipFile);
        File desFile = new File("/Users/zhao/gitRepoIntelliJ/qqq.zip");
        FileOutputStream fileOutputStream = new FileOutputStream(desFile);
        Integer by = 0;
        while ((by = fileInputStream.read()) != -1) {
            fileOutputStream.write(by);
        }
        fileInputStream.close();
        fileOutputStream.close();
        System.out.println(zipFile);
    }

    //不使用client发请求写法如下
    @GetMapping("/getZipFile")
    public ResponseEntity<RuleResponse> sendRequestToFile() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<RuleResponse> result = restTemplate.exchange("http://localhost:8080/downLoadZipInBytes",
                HttpMethod.GET, entity, RuleResponse.class);
        RuleResponse ruleResponse = result.getBody();
        byte[] bytes;
        try {
            bytes = ruleResponse.getRuleInBytes();
            File file = new File("c:/des.zip");
            file.createNewFile();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            System.out.println(bytes);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}