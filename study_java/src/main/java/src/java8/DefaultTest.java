package src.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * <p>注释</p>
 * @author xieyw
 * @version DefaultTest.java, v 0.1 2017/11/15 11:22 谢益文 Exp $
 */

public class DefaultTest {
    public static void main (String[] args) {
        //构造器引用  Class::new  或 Class<T>::new
        // 调用car的无参构造器,如果没有无参构造器,则会错误
        Car car = Car.create(Car::new);
        car.setBond("ben");
        List<Car> cars = Arrays.asList(car);
        //静态方法引用  Class::static_method 入参:car
        cars.forEach(Car::collide);
        //类成员方法引用  Class::method   入参:无
        cars.forEach(Car::repair);
        //实例的成员方法引用 instance::method   入参:car
        Car police = Car.create(Car::new);
        police.setBond("police");
        cars.forEach(police::follow);
    }
}

class Car {
    private String bond;

    public Car () {}

    public Car (String bond) {
        this.bond = bond;
    }

    public static Car create (Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide (Car car) {
        System.out.println("collided " + car.toString());
    }

    public void follow (Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair () {
        System.out.println("Repaired " + this.toString());
    }

    public void setBond (String bond) {
        this.bond = bond;
    }

    @Override
    public String toString () {
        return "Car{" + "bond='" + bond + '\'' + '}';
    }
}