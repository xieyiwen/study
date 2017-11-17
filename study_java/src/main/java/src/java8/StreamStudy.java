package src.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>注释</p>
 * @author xieyw
 * @version StreamStudy.java, v 0.1 2017/11/15 17:04 谢益文 Exp $
 */
public class StreamStudy {
    private enum Status{
        OPEN,CLOSE
    }

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task( final Status status, final Integer points ) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }

    public static void main(String[] args){
        List<Task> tasks = Arrays
                .asList(new Task(Status.OPEN, 5), new Task(Status.OPEN, 13), new Task(Status.CLOSE, 8));
        int sum = tasks.stream().filter(task -> task.getStatus() == Status.OPEN).mapToInt(Task::getPoints).sum();
        System.out.println(sum);
        Collections.reverse(tasks);
        tasks.forEach(t -> System.out.println(t));
    }

}
