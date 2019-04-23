package com.antsix.chapter1;

import com.antsix.domain.pri.User;
import com.antsix.domain.pri.UserRepository;
import com.antsix.domain.sec.Message;
import com.antsix.domain.sec.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MultiSourceSpringJpaTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    @Before
    public void setUp() {
    }

    /**
     * ### 隔离级别
     * public enum Isolation {
     *     DEFAULT(-1),
     *     READ_UNCOMMITTED(1),
     *     READ_COMMITTED(2),
     *     REPEATABLE_READ(4),
     *     SERIALIZABLE(8);
     * }
     *
     * DEFAULT：这是默认值，表示使用底层数据库的默认隔离级别。对大部分数据库而言，通常这值就是：READ_COMMITTED。
     * READ_UNCOMMITTED：该隔离级别表示一个事务可以读取另一个事务修改但还没有提交的数据。该级别不能防止脏读和不可重复读，因此很少使用该隔离级别。
     * READ_COMMITTED：该隔离级别表示一个事务只能读取另一个事务已经提交的数据。该级别可以防止脏读，这也是大多数情况下的推荐值。
     * REPEATABLE_READ：该隔离级别表示一个事务在整个过程中可以多次重复执行某个查询，并且每次返回的记录都相同。即使在多次查询之间有新增的数据满足该查询，这些新增的记录也会被忽略。该级别可以防止脏读和不可重复读。
     * SERIALIZABLE：所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止脏读、不可重复读以及幻读。但是这将严重影响程序的性能。通常情况下也不会用到该级别。
     */

    /**
     * ### 传播行为
     * 所谓事务的传播行为是指，如果在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为
     *
     * public enum Propagation {
     *     REQUIRED(0),
     *     SUPPORTS(1),
     *     MANDATORY(2),
     *     REQUIRES_NEW(3),
     *     NOT_SUPPORTED(4),
     *     NEVER(5),
     *     NESTED(6);
     * }
     *
     * REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
     * SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
     * MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
     * REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
     * NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
     * NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
     * NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于REQUIRED
     */


    @Test
    //@Transactional(value="transactionManagerSecondary",isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public void test() throws Exception {

        userRepository.deleteAll();
        messageRepository.deleteAll();
        userRepository.save(new User("aaa", 10));
        userRepository.save(new User("bbb", 20));
        userRepository.save(new User("ccc", 30));
        userRepository.save(new User("ddd", 40));
        userRepository.save(new User("eee", 50));

        //Assert.assertEquals(5, userRepository.findAll().size());


        messageRepository.save(new Message("o1", "aaaaaaaaaa"));
        messageRepository.save(new Message("o2", "bbbbbbbbbb"));
        messageRepository.save(new Message("o3", "cccccccccc"));

        //Assert.assertEquals(3, messageRepository.findAll().size());

    }
}
