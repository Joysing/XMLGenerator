package cc.joysing.xmlgenerator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Date 2018/5/31 11:13
 * @Author Joysing
 */
public class Node {
    private Node parentNode = null;//父节点
    private List<Node> childrenNodes = null;//子节点（包括子孙节点）
    private String nodeName;//节点名称
    private String textContent = null;//节点的文本内容
    private HashMap<String, String> attributes = null;//节点属性（键值对）
    private int nodeDepth;//节点深度，为了缩进格式而引入，为了方便统计，这里定义根节点深度为0
    private XmlGenerator xmlBuilder=new XmlGenerator();

    public Node(String nodeName) {
        this.setNodeName(nodeName);
        this.setNodeDepth(0);
    }

    /**
    * @description 添加一个子节点
    * @param child 准备添加的子节点
    * @return
    */
    public void appendChild(Node child) {
        if (getTextContent() == null) {//确保子节点没有文本内容，才能增加子节点
            if (child.getParentNode()==null){//子节点没有其他父节点时才能被添加
                if (getChildrenNodes() == null) {//如果当前没有子节点，就创建一个list用于存放子节点
                    setChildrenNodes(new LinkedList<>());
                }
                getChildrenNodes().add(child);
                child.setParentNode(this);
                Node tempNode=child;
                int childNodeDepth=0;//计算当前节点的深度
                while (tempNode.getParentNode()!=null){
                    childNodeDepth++;
                    tempNode=tempNode.getParentNode();
                }
                child.setNodeDepth(childNodeDepth);
            }else{
                System.err.println("一个节点不能有两个父节点！");
            }
        }
    }

    /**
    * @description 删除节点
    * @param child 节点对象
    * @return
    */
    public void deleteChild(Node child) {
        if (getChildrenNodes() != null || child != null || child.getNodeName() != null || !child.getNodeName().equals("")) {
            child.parentNode.getChildrenNodes().remove(child);
        }
    }

    /**
     * @description 打印当前节点和所有子节点（包括子孙节点）的内容
     * @param
     * @return
     */
    public void printCurrentNode() {
        xmlBuilder.from(this)
                .setFileName(this.getNodeName() + ".xml")
                .generatorXml();
    }

    public void setEncoding(String charSet) {
        this.xmlBuilder.setCharSet(charSet);
    }

    public Map<String, String> getAttributes() {
        if (attributes == null) return null;
        return attributes;
    }

    public int getNodeDepth() {
        return nodeDepth;
    }

    public void setNodeDepth(int nodeDepth) {
        this.nodeDepth = nodeDepth;
    }

    public void setAttributes(String key, String value) {
        if (getAttributes() == null) attributes = new HashMap<>();
        if (key != null)
            getAttributes().put(key, value);
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        if (getChildrenNodes() == null)//确保子节点没有元素，才能设置文本内容
            this.textContent = textContent;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parent) {
        this.parentNode = parent;
    }

    public List<Node> getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(List<Node> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String tag) {
        this.nodeName = tag;
    }
}
