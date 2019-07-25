package com.demo.zookeeper;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import java.io.IOException;
import java.util.List;
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