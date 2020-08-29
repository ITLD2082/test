import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class RedisTest {
    //存取普通字符串
    @Test
    public void test1(){
        //单独使用jedis客户端，是redis的Java客户端
        //连接本地的 Redis 服务
        Jedis jedis=new Jedis("localhost",6379);
        //设置密码
        jedis.auth("123456");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        //切换数据库
        jedis.select(15);
        jedis.set("username","张三");
        System.out.println(jedis.get("username"));
        jedis.close();
    }

    //存取普通字符串   登陆验证码过时
    @Test
    public void test2(){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("123456");
        jedis.select(15);
        jedis.setex("aaa",10,"bbb");
        System.out.println(jedis.get("aaa"));
        jedis.close();
    }
    //hash表示哈希类型，等同于Java中的map集合
    @Test
    public void test3(){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.auth("123456");
        jedis.select(15);
        jedis.hset("myhash","user","tom");
        jedis.hset("myhash","address","北京");
        //查询单个
        System.out.println(jedis.hget("myhash","user"));
        //查询所有
        Map<String, String> map = jedis.hgetAll("myhash");
        Set<String> set = map.keySet();
        for (String s:set){
            System.out.println(s+"-----"+map.get(s));
        }
        jedis.close();
    }
    //set 无序不可重复
    @Test
    public void test4(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123456");
        jedis.select(15);   //切换数据库

        jedis.sadd("myset","aa","bb","cc","aa");

        Set<String> set = jedis.smembers("myset");
        for (String s:set){
            System.out.println(s);
        }
        jedis.close();
    }

    @Test
    //list  有序可重复
    public void test5(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123456");
        jedis.select(15);   //切换数据库

        jedis.lpush("mylist","aa","bb","cc");
        jedis.rpush("mylist","aa","bb","cc");

        List<String> list = jedis.lrange("mylist", 0, -1);
        System.out.println(list.toString());
        jedis.close();
    }

    @Test
    //sortset 有序 不重复 集合
    public void test6(){
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("123456");
        jedis.select(15);   //切换数据库

        jedis.zadd("mysortset",89,"tom");
        jedis.zadd("mysortset",70,"jack");
        jedis.zadd("mysortset",99,"lucy");
        jedis.zadd("mysortset",86,"json");

        Set<String> set = jedis.zrange("mysortset", 0, -1);

        System.out.println(set.toString());
        jedis.close();
    }

}
