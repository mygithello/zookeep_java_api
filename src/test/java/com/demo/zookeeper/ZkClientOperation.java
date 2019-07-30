package com.demo.zookeeper;
import org.I0Itec.zkclient.ZkClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.List;
/**
 * @author gaozhy
 * @date 2018/3/5.16:26
 */
public class ZkClientOperation {
    private ZkClient zkClient = null;
    @Before
    public void before(){
        zkClient = new ZkClient("192.168.42.132");
    }
    /**
     * 增删改查
     */
    @Test
    public void zkCRUD(){
        a();
        a3();
    }
    public void a(){
        // 创建持久节点
        zkClient.createPersistent("/tencent",true);
    }
    public void a2(){
        // 创建临时节点
        zkClient.createEphemeral("/zpark","bzjy");
    }
    public void a3(){
        // 创建临时顺序节点
        String e1 = zkClient.createEphemeralSequential("/tencent/league-of- legends", "lol");
        String e2 = zkClient.createEphemeralSequential("/tencent/cross-fire", "cf");
        System.out.println("创建的临时顺序节点e1："+e1);
        System.out.println("创建的临时顺序节点e2："+e2);
    }
    public void a4(){
        // 获取节点列列表
        List<String> list = zkClient.getChildren("/tencent");
        list.forEach(n -> System.out.println(n));
    }

    public void a5(){
        // 删除节点
        boolean result = zkClient.deleteRecursive("/tencent");
        System.out.println("删除结果："+result);
    }

    public void a6(){
        // 获取节点内容
        Object data = zkClient.readData("/zpark");
        System.out.println("/zpark 保存的数据为："+data);
    }
    public void a7(){
        // 更更新节点内容
        zkClient.writeData("/zpark",new Date());
        Object newData = zkClient.readData("/zpark");
        System.out.println("/zpark 更更新后数据为："+newData);
    }



    @After
    public void after(){
        zkClient.close();
    }
}