import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class QuickFindDSTest {
    @Test
    public void connectTest(){
        QuickFindDS l = new QuickFindDS(8);
        l.connect(0,1);
        System.out.println(l.toString());
        l.connect( 2,4);
        System.out.println(l.toString());

        l.connect(0,4);

        assertThat(l.toString()).isEqualTo("--- Test Only ---");
    }

    @Test
    public void isConnectTest(){
        QuickFindDS l = new QuickFindDS(8);
        l.connect(0,1);
        l.connect( 2,4);

        assertThat(l.isConnect(0,1)).isTrue();
        assertThat(l.isConnect(0,4)).isFalse();
    }
}
