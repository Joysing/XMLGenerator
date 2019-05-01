package cc.joysing.xmlgenerator;

import java.io.*;
import java.util.Map;

/**
 * @Date 2018/5/31 11:26
 * @Author Joysing
 */
public class XmlGenerator {
    XmlGenerator xmlGenerator;
    Node root = null;
    String charSet;
    private String fileName = null;

    public XmlGenerator() {
        charSet = "utf-8";
        xmlGenerator = this;
    }

    public XmlGenerator from(Node root) {
        xmlGenerator.root = root;
        return xmlGenerator;
    }

    public XmlGenerator setCharSet(String charSet) {
        this.charSet = charSet;
        return xmlGenerator;
    }

    public XmlGenerator setFileName(String fileName) {
        this.fileName = fileName;
        return xmlGenerator;
    }

    /**
    * @description 生成文件保存在电脑硬盘，同时在控制台输出内容
    * @param
    * @return
    */
    public void generatorXml() {
        if (root == null) return;
        if (fileName == null) fileName = root.getNodeName() + ".xml";
        if (!fileName.endsWith(".xml")) fileName += ".xml";
        FileOutputStream fileOutputStream = null;
        PrintStream printStream = null;
        try {
            fileOutputStream = new FileOutputStream(fileName);
            printStream = new PrintStream(fileOutputStream);
            StringBuilder stringBuilder = new StringBuilder();
            String headLine = "<?xml version=\"1.0\" encoding=\"" + charSet + "\"?>\n";
            stringBuilder.append(headLine);
            printXml(root, stringBuilder);
            printStream.println(new String(stringBuilder.toString().getBytes(), charSet));
            System.out.println(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (printStream != null)
                printStream.close();
            if (fileOutputStream != null)
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /**
    * @description 递归当前节点和所有子节点（包括子孙节点）的内容，组合在一起。
    * @param node 节点对象
    * @param stringBuilder 需要被组合的字符串
    * @return
    */
    private void printXml(Node node, StringBuilder stringBuilder) {
        if (node != null) {

            stringBuilder.append("<" + node.getNodeName());
            Map<String,String> map = node.getAttributes();
            if (map != null) {
                for(Map.Entry<String,String> entry:map.entrySet()){
                    stringBuilder.append(" " + entry.getKey() + "=\"" + entry.getValue() + "\"");
                }
            }
            stringBuilder.append(">");

            if (node.getTextContent() != null) {
                stringBuilder.append(node.getTextContent());
            } else if (node.getChildrenNodes() != null) {
                stringBuilder.append("\n");
                for (Node child : node.getChildrenNodes()) {
                    for(int i=0;i<child.getNodeDepth();i++)
                        stringBuilder.append("\t");
                    printXml(child, stringBuilder);//递归打印子节点
                }
            }
            for(int i=0;i<node.getNodeDepth();i++)
                if(node.getChildrenNodes()!=null)
                    stringBuilder.append("\t");
            stringBuilder.append("</" + node.getNodeName() + ">\n");

        }
    }
}