package cc.joysing.xmlgenerator;

/**
 * @Date 2018/5/31 20:17
 * @Author Joysing
 */
public class Main {
    public static void main(String[] args){
        // 主程序测试生成器，它构建下面文档，然后打印出来。
        // 构建的文档已保存在当前项目的根目录。
        // 特别说明：
        // 语言级别：8 - Lambdas, 类型注解等。
        // JDK版本：1.8.0_74
        Node root = new Node("CategoryList");
        Node nodeCategory = new Node("Category");
        Node nodeMainCategory = new Node("MainCategory");
        Node nodeDescription = new Node("Description");
        Node nodeActive = new Node("Active");

        nodeCategory.setAttributes("ID", "01");
        nodeMainCategory.setTextContent("XML");
        nodeDescription.setTextContent("This is a list my XML articles.");
        nodeActive.setTextContent("true");

        root.appendChild(nodeCategory);
        nodeCategory.appendChild(nodeMainCategory);
        nodeCategory.appendChild(nodeDescription);
        nodeCategory.appendChild(nodeActive);

        root.printCurrentNode();
    }
}
