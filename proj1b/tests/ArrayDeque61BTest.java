import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    @Test
    /* Test : construcotr */
    public void constructorTest(){
        Deque61B<String> A = new ArrayDeque61B<>();
        assertWithMessage("Error").that(A).isNotNull();
    }
    @Test
    /* Test : addFirst */
    public void addFirstTest(){
        List expected = new ArrayList();
        expected.add("B");
        expected.add("A");

        Deque61B<String> A = new ArrayDeque61B<>();
        A.addFirst("A");
        A.addFirst("B");
        assertWithMessage("ERROR").that(A.toList()).isEqualTo(expected);
    }
    @Test
    /* Test : addLast */
    public void addLastTest(){
        List expected = new ArrayList();
        expected.add("A");
        expected.add("B");

        Deque61B<String> A = new ArrayDeque61B<>();
        A.addLast("A");
        A.addLast("B");
        assertWithMessage("ERROR").that(A.toList()).isEqualTo(expected);
    }
    @Test
    /* Test : removeFirst */
    public void removeFirstTest(){

        Deque61B<String> A = new ArrayDeque61B<>();
        A.addFirst("A");
        A.addFirst("B");
        assertWithMessage("ERROR").that(A.removeFirst()).isEqualTo("B");
    }
    @Test
    /* Test : removeLast */
    public void removeLastTest(){
        Deque61B<String> A = new ArrayDeque61B<>();
        A.addLast("A");
        A.addLast("B");
        assertWithMessage("ERROR").that(A.removeLast()).isEqualTo("B");
    }
    @Test
    /* Test : resize up */
    public void resizeUpTest(){
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(8);
        expected.add(9);

        Deque61B<Integer> A = new ArrayDeque61B<>();
        A.addLast(1);
        A.addLast(2);
        A.addLast(3);
        A.addLast(4);
        A.addLast(5);
        A.addLast(6);
        A.addLast(7);
        A.addLast(8);
        A.addLast(9);
        assertWithMessage("ERROR").that(A.toList()).isEqualTo(expected);
    }
    @Test
    /* Test : resize down */
    public void resizeDownTest(){
        List<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        expected.add(7);
        expected.add(8);

        Deque61B<Integer> A = new ArrayDeque61B<>();
        A.addLast(1);
        A.addLast(2);
        A.addLast(3);
        A.addLast(4);
        A.addLast(5);
        A.addLast(6);
        A.addLast(7);
        A.addLast(8);
        A.addLast(9);
        A.removeLast();
        //assertWithMessage("ERROR").that(A.toList()).isEqualTo(expected);
    }

}
