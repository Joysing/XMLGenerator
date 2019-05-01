package cc.joysing.xmlgenerator;

import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Date 2018/5/31 11:02
 * @Author Joysing
 */
public class testMain {
    Node root = new Node("CategoryList");
    Node nodeCategory = new Node("Category");
    Node nodeMainCategory = new Node("MainCategory");
    Node nodeDescription = new Node("Description");
    Node nodeActive = new Node("Active");

    //1-1）节点能够添加或者删除其它子节点
    @Test
    public void testAddAndDelete(){
        initXML();
        //添加和删除其它子节点
        System.out.println("-------------------添加newNode前：-------------------------");
        root.printCurrentNode();
        System.out.println("-------------------添加newNode时：-------------------------");
        Node newNode = new Node("newNode");
        newNode.setTextContent("newNode");
        root.appendChild(newNode);
        root.printCurrentNode();
        System.out.println("-------------------删除newNode后：-------------------------");
        root.deleteChild(newNode);
        root.printCurrentNode();
    }
    //1-2）操作同时要保证每个节点只能有一个父节点， 一个节点只能从属于一个父节点
    @Test
    public void testAddDoubleParent(){
        initXML();
        //在 initXML()方法中nodeMainCategory已经属于nodeCategory了，
        //现在再为nodeMainCategory设一个父节点root，提示错误
        root.appendChild(nodeMainCategory);
    }

    //2）节点能够获取父节点对象；节点能够获取所有子节点对象。
    @Test
    public void testGetNodes(){
        initXML();
        //获取父节点对象并打印
        nodeCategory.getParentNode().printCurrentNode();

        //为了美观性，只打印 child 的子节点对象的名称
        List<Node> childsList=nodeCategory.getChildrenNodes();
        for (Node childNode:childsList){
            System.out.println(childNode.getNodeName());
        }
    }

    //3）节点有添加或者读取XML属性的操作
    @Test
    public void testAttributes(){
        initXML();
        nodeCategory.setAttributes("name","IAmAName");
        Map<String,String> attributes=nodeCategory.getAttributes();
        System.out.println(nodeCategory.getNodeName()+"的属性：");
        for (Map.Entry<String,String> entry:attributes.entrySet()){
            System.out.println(entry.getKey()+" = "+entry.getValue());
        }
    }
    //4）当前节点有方法能打印当前节点和所有子节点（包括子孙节点）的内容。
    @Test
    public void testPrintXML(){
        initXML();
        root.printCurrentNode();
    }

    //5）XML的encoding方式能够设置
    //只能是标准编码，例如utf-8、gb2312、BIG5等等，乱写会抛异常
    @Test
    public void testSetEncoding(){
        initXML();
        root.setEncoding("gb2312");
        root.printCurrentNode();
    }

    /**
    * @description 初始化xml内容
    */
    public void initXML(){
        nodeCategory.setAttributes("ID", "01");
        nodeMainCategory.setTextContent("XML");
        nodeDescription.setTextContent("This is a list my XML articles.");
        nodeActive.setTextContent("true");

        root.appendChild(nodeCategory);
        nodeCategory.appendChild(nodeMainCategory);
        nodeCategory.appendChild(nodeDescription);
        nodeCategory.appendChild(nodeActive);
    }
}