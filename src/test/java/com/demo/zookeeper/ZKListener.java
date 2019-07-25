package com.demo.zookeeper;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import java.io.IOException;
import java.util.List;

/**
 * 注册监听
 * 在ZkClient中客户端可以通过注册相关的事件监听来实现对Zookeeper服务端事件的订阅，其中
 * ZkClient提供的常⽤用监听事件接⼝口有以下⼏几种：
 * 监听接⼝口               订阅⽅方法       取消订阅⽅方法
 * IZkChildListener subscribeChildChanges unsubscribeChildChanges
 * IZkDataListener subscribeDataChanges unsubscribeDataChanges
 */
public class ZKListener {
    public static void main(String[] args) throws IOException {
        ZkClient zkClient = new ZkClient("192.168.42.132:2181");
        // 订阅⼦子节点改变
        zkClient.subscribeChildChanges("/tencent", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String>
                    currentChilds) throws Exception {
                System.out.println("parentPath: "+parentPath);
                currentChilds.forEach( n -> System.out.println("child node: "+n));
                        System.out.println("-----------------------------------");
            }
        });
        // 订阅数据改变
        zkClient.subscribeDataChanges("/tencent", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data)
                    throws Exception {
                System.out.println("data change: "+dataPath + ",change data: "+data);
            }
            @Override
            public void handleDataDeleted(String dataPath) throws Exception
            {
                System.out.println("delete node path: "+dataPath);
            }
        });
        System.in.read();
    }
}