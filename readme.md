[github仓库链接](https://github.com/kerisuchiaki/PaperCheck)

## 作业要求

|                      |                                                              |
| -------------------- | ------------------------------------------------------------ |
| 这个作业属于哪个课程 | [工程概论](https://edu.cnblogs.com/campus/jmu/ComputerScience21) |
| 这个作业要求在哪里   | [个人项目](https://edu.cnblogs.com/campus/jmu/ComputerScience21/homework/13034) |
| 这个作业的目标       | 学习论文查重算法和工程项目的实现                             |

## 需求

题目：论文查重

描述如下：

设计一个论文查重算法，给出一个原文文件和一个在这份原文上经过了增删改的抄袭版论文的文件，在答案文件中输出其重复率。

原文示例：今天是星期天，天气晴，今天晚上我要去看电影。
抄袭版示例：今天是周天，天气晴朗，我晚上要去看电影。
要求输入输出采用文件输入输出，规范如下：

从命令行参数给出：论文原文的文件的绝对路径。
从命令行参数给出：抄袭版论文的文件的绝对路径。
从命令行参数给出：输出的答案文件的绝对路径。
我们提供一份样例，课堂上下发，上传到班级群，使用方法是：orig.txt是原文，其他orig_add.txt等均为抄袭版论文。

注意：答案文件中输出的答案为浮点型，精确到小数点后两位

## PSP表格

| PSP2.1                                  | Personal Software Process Stages       | 预估耗时（分钟） | 实际耗时（分钟） |
| --------------------------------------- | -------------------------------------- | ---------------- | ---------------- |
| Planning                                | 计划                                   | 50               | 60               |
| Estimate                                | 估计这个任务需要多少时间               | 350              | 400              |
| Development                             | 开发                                   | 400              | 420              |
| Analysis                                | · 需求分析 (包括学习新技术)            | 250              | 280              |
| Design Spec                             | 生成设计文档                           | 140              | 120              |
| Design Review                           | Design Review                          | 35               | 20               |
| Coding Standard                         | 代码规范 (为目前的开发制定合适的规范)  | 25               | 20               |
| Design                                  | 具体设计                               | 150              | 190              |
| Coding                                  | · 具体编码                             | 170              | 280              |
| · Code Review                           | · 代码复审                             | 70               | 65               |
| Test                                    | · 测试（自我测试，修改代码，提交修改） | 70               | 800              |
| Reporting                               | 报告                                   | 70               | 90               |
| Test Repor                              | 测试报告                               | 50               | 65               |
| Size Measurement                        | 计算工作量                             | 15               | 15               |
| · Postmortem & Process Improvement Plan | · 事后总结, 并提出过程改进计划         | 10               | 10               |
|                                         | 合计                                   | 1870             | 2560             |

## 运行环境

语言：Java
版本：java version "1.8.0_362" 
操作系统：Windows10

开发环境： idea2023

## 算法设计

使用word分词器进行精细分词，然后提取两篇文章的公共分词，定义原文章的分词序列为递增的序列，得出抄袭文章的分词序列，对抄袭文章使用归并排序计算逆序对个数，然后得出抄袭文章相对与原文章的上下文关系程度，使用公式 相似度S=(正序对-逆序对）/正序对 来定量计算相似度以实现查重



## 整体项目框架

![image-20230920153820812](img/image-20230920153820812.png)

## 代码分析

### 接口设计

PaperCheck类，调用该类的paperCheck（）方法并传入原文章与抄袭文章得出文章相似度

FileUtils类，调用该类的readFile（）接口，用于读取文件

MergeSortInversePairs类，调用内部方法mergeSort（）内部实现对序列归并排序并计算出逆序对

### 异常处理

开始运行时判断命令行参数是否符合参数

```java
//检查参数个数
if (args.length != 3){
    System.out.println("参数个数错误或文件路径不正确!\n");
    return;
}

//判断参数文件是否以.txt结尾
for(String index : args){
    if(!index.endsWith(".txt")){
        System.out.println("文件格式错误!\n");
        return;
    }
}
```

读取文件时判断文件路径是否正确

```java
try {
    double result = check.paperCheck(args[0], args[1], args[2]);
    System.out.printf("%s 文章查重率：%.2f%% \n",args[1],result * 100);
    //将结果result写入args[2]的文件路径中
    writeResultToFile(args[2], result);
} catch (IOException e){
    LOGGER.error("文件打开失败", e);
}
```

## 性能分析

这里使用java自带的命令行工具用于获取目标进程的内存相关信息，包括 Java 堆各区域的使用情况、堆中对象的统计信息、类加载信息等。

![image-20230920162941607](img/image-20230920162941607.png)

### 源码

https://github.com/kerisuchiaki/PaperCheck