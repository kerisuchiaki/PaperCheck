package com.yuyonghai;

import com.yuyonghai.Check.PaperCheck;
import com.yuyonghai.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        //检查参数个数
        if (args.length != 3){
            System.out.println("参数或文件路径不正确!\n");
            return;
        }

        //判断参数文件是否以.txt结尾
        for(String index : args){
            if(!index.endsWith(".txt")){
                System.out.println("文件格式错误!\n");
                return;
            }
        }

        //初始化论文查重的接口
        PaperCheck check = new PaperCheck();
        //调用接口
        try {
            double result = check.paperCheck(args[0], args[1], args[2]);
            System.out.printf("%s 文章查重率：%.2f%% \n",args[1],result * 100);
            //将结果result写入args[2]的文件路径中
            writeResultToFile(args[2], result);
        } catch (IOException e){
            LOGGER.error("文件打开失败", e);
        }
    }

    private static void writeResultToFile(String filePath, double result) {
        try {
            String resultString = String.format("%.2f%%", result * 100);
            FileUtils.writeFile(filePath, resultString);
            System.out.println("结果写入文件：" + filePath);
        } catch (IOException e) {
            LOGGER.error("写入文件失败", e);
        }
    }

    @Test
    public void test(){
        String[] args = {"D://origin.txt", "D://target.txt", "D://result.txt"};
        main(args);

        // 验证结果
        try {
            String resultContent = FileUtils.readFile("D://result.txt");
            Assert.assertEquals("结果不一致", "99.86%", resultContent);
        } catch (IOException e) {
            LOGGER.error("读取结果文件失败", e);
        }
    }
}