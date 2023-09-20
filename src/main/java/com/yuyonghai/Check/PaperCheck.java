package com.yuyonghai.Check;

import com.yuyonghai.utils.FileUtils;
import com.yuyonghai.utils.MergeSortInversePairs;
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

import java.io.IOException;
import java.util.*;

public class PaperCheck {


    /**
     * 论文分词器并查重
     * @param origFilePath 原论文路径
     * @param targetFilePath 抄袭论文 路径
     * @return 相似度
     * @throws IOException
     */

    public double paperCheck(String origFilePath, String targetFilePath,String result) throws IOException {


        //读取源文件内容和目标文件内容
        String origFile = FileUtils.readFile(origFilePath);
        String targetFile = FileUtils.readFile(targetFilePath);

        //对两个文件去掉特殊符号和标点
        origFile=removeSpecialChars(origFile);
        targetFile=removeSpecialChars(targetFile);


        List<String> originStrings = new ArrayList<>();
        List<String> targetStrings = new ArrayList<>();
        List<Word> word1 = WordSegmenter.segWithStopWords(origFile);
        for (Word word : word1) {
            originStrings.add(word.toString());
        }
        List<Word> word2 = WordSegmenter.segWithStopWords(targetFile);
        for (Word word : word2) {
            targetStrings.add(word.toString());
        }
        System.out.println("-----------------______________________________________>");
        System.out.println(originStrings);
        System.out.println("**********************************************************");
        System.out.println(targetStrings);


        int len2=targetStrings.size();

        //求originStrings和targetStrings的交集，并赋值给自己
        originStrings=intersectionSegmentWords(originStrings,targetStrings,0);
        targetStrings=intersectionSegmentWords(originStrings,targetStrings,1);




//        计算originStrings和targetStrings的相同分词的最小集合，即留下公共的词，并且不改变两个list的顺序

        int len3= targetStrings.size();

        ArrayList<Integer> tarIndex = new ArrayList<>();
        //
        for (int i = 0; i < targetStrings.size(); i++) {
            String s = targetStrings.get(i);
            for (int j = 0; j < originStrings.size(); j++) {
                String s1 = originStrings.get(j);
                if (s.equals(s1)) {
                    tarIndex.add(j);
                }
            }
        }

        //调用归并排序计算tarIndex的逆序对，逆序对越少则越容易判断为相似
        int i = MergeSortInversePairs.mergeSort(tarIndex);
        int num=factorial(len3);
        return ((double)num-i)/num;
    }

    private static String removeSpecialChars(String text) {
        // 定义需要去除的特殊符号和标点的正则表达式
        String regex = "[^a-zA-Z0-9\\u4E00-\\u9FA5\\s]";
        // 使用正则表达式替换特殊字符为空字符串
        return text.replaceAll(regex, "");
    }

    public int factorial(int i){
        int c = 1;
        for (int k = 1; k < i; k++) {
            c = c * k;
        }
        return c;
    }

    /**
     * 求交集
     * @param originStrings
     * @param targetStrings
     * @return
     */
    private static List<String> intersectionSegmentWords(List<String> originStrings, List<String> targetStrings,int p) {
        Set<String> set = new HashSet<>(originStrings);
        set.retainAll(targetStrings);
//        return new ArrayList<>(set);
        int len1 = originStrings.size();
        int len2 = targetStrings.size();
        List<String> strings=new ArrayList<>();
        if(p==0)strings=originStrings;
        else strings=targetStrings;
        if(len1>len2)len1=len2;
            for(int i=0;i<len1;i++) {
                if(i==len1-1){
                    System.out.println("");
                }
                int flag=0;
                for (String s : set) {
                    String str=strings.get(i);
                if(str.equals(s)){
                    flag=1;
                    break;
                };
            };
                if(flag==0){
                    strings.remove(i);
                    i--;
                    len1--;
                }
        };
            strings=removeDuplicates(strings);
        return strings;
    }

    /**
     * 去重
     * @param originStrings
     * @return
     */
    private static List<String> removeDuplicates(List<String> originStrings) {
        Set<String> set = new LinkedHashSet<>(originStrings);
        return new ArrayList<>(set);
    }
}




