package src.java8;

import java.lang.annotation.*;

/**
 * <p>重复注解</p>
 * @author xieyw
 * @version RepeatAnnotation.java, v 0.1 2017/11/15 14:18 谢益文 Exp $
 */
public class RepeatAnnotation {

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters{
        Filter[] value();
    }
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter{
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE_USE,ElementType.TYPE_PARAMETER})
    public @interface NonEmpty{}

    @Filter("filter1")
    @Filter("filter2")
    public interface Filterable{}

    public static void main(String[] args){
        for (Filter filter:Filterable.class.getAnnotationsByType(Filter.class)){
            System.out.println(filter.value());
        }


    }


}
