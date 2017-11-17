package src.java8;

import java.util.Optional;

/**
 * <p>Optional 存放null或T类型的值</p>
 * @author xieyw
 * @version OptionalStudy.java, v 0.1 2017/11/15 16:57 谢益文 Exp $
 */
public class OptionalStudy {

    public static void main(String[] args){
        Optional<String> fullName = Optional.of("cool man");
        //ofNullable 如果入参是空，返回null，否则调用of方法
        fullName = Optional.ofNullable(null);
        //isPresent 如果是空，返回false
        System.out.println( "Full Name is set? " + fullName.isPresent() );
        //orElseGet 如果是空执行后面的代码块
        System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) );
        //orElse 如果是空，返回后面的入参
        System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
    }
}
