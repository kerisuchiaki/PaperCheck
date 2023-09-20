
import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WordSegmentation {
    public static void main(String[] args) throws Exception {

        String input = "d:/text.txt";
        String output = "d:/word.txt";
        WordSegmenter.seg(new File(input), new File(output));
        WordSegmenter.segWithStopWords(new File(input), new File(output));
        List<Word> words = WordSegmenter.seg("杨尚川是APDPlat应用级产品开发平台的作者");
        for (Word word : words) {
            String s=word.getText();
            System.out.println(s);
        }
//        List<Word> words = WordSegmenter.segWithStopWords("杨尚川是APDPlat应用级产品开发平台的作者");
//        System.out.println(words);
//        // 加载文档
//        Document doc = new Document();
//        doc.loadFromFile("input.docx", FileFormat.Docx);
//
//        // 获取文档中所有的文本
//        String text = doc.getText();
//
//        // 用正则表达式去除文本中的换行符和制表符
//        text = text.replaceAll("[\n\t]", "");
//
//        // 执行分词
//        ArrayList<String> words = WordSegmenter.segment(text);
//
//        // 输出结果
//        for (String word : words) {
//            System.out.println(word);
//        }
//    }
    }
}