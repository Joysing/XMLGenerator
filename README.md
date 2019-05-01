2018年5月 随手记给我的笔试题目


**题目全文如下：**

设计一个简单的XML文档生成器，它满足以下功能：

1) 节点能够添加或者删除其它子节点， 操作同时要保证每个节点只能有一个父节点， 一个节点只能从属于一个父节点；
2) 节点能够获取父节点对象；节点能够获取所有子节点对象。
3) 节点有添加或者读取XML属性的操作；
4) 当前节点有方法能打印当前节点和所有子节点（包括子孙节点）的内容。
5) XML的encoding方式能够设置，如：
&lt;?xml version="1.0" encoding="utf-8"?&gt;
或
&lt;?xml version="1.0" encoding="BG2312"?&gt;


要求：
1) 用Java写出完整程序；
2) 主程序测试生成器，它构建下面文档，然后打印出来。

&lt;?xml version="1.0" encoding="utf-8"?&gt;
&lt;CategoryList&gt; 
    &lt;Category ID="01"&gt;   
        &lt;MainCategory&gt;XML&lt;/MainCategory&gt;   
            &lt;Description&gt;This is a list my XML articles.&lt;/Description&gt;                  
            &lt;Active&gt;true&lt;/Active&gt;  
    &lt;/Category&gt;
 
&lt;/CatgoryList&gt;











