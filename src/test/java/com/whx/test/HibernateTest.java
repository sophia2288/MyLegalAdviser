package com.whx.test;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import com.whx.entities.TUser;
import com.whx.hibernate.*;
 
public class HibernateTest {
     
    public static void main(String[] args) {
        //初始化一个POJO对象
        TUser user = new TUser();
        user.setAccount("672310228@sina.com");
        user.setPassword("michael7108367865");
        user.setCategory("公检法人员");
        user.setConcerns("房屋买卖，房地产转让，侵权");
        user.setContributions((short)6);
        user.setPhone("18972256548");
        
        Session session = MyHibernateSessionFactory.getSession();
        
        String hql="from TUser";
        Transaction tx = session.beginTransaction();
         
        //使用HQL语句查询
        Query query = session.createQuery(hql);
        List<TUser> userList = query.list();
        System.out.println(userList.size());
        
        //存入user对象至TUser表中
        session.save(user);
   
        //提交事务，关闭会话
        tx.commit();
        session.close();  
    }
}
