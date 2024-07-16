import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class List_Sets_Test {

    @Test
    public void connectTest(){
        List_Sets l = new List_Sets(8);

        l.connect(0,1);
        l.connect(1,2);
        assertThat(l.toString()).isEqualTo("[[0, 1, 2], [3], [4], [5], [6], [7]]");
    }

    @Test
    public void isConnectTest(){
        List_Sets l = new List_Sets(8);

        l.connect(0,1);
        l.connect(2,3);

        assertThat(l.isConnect(2,3)).isTrue();
        assertThat(l.isConnect(1,3)).isFalse();
    }
}
