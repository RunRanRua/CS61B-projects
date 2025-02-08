import ngrams.TimeSeries;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TimeSeriesTest {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertThat(totalPopulation.years()).isEqualTo(expectedYears);

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertThat(totalPopulation.data().get(i)).isWithin(1E-10).of(expectedTotal.get(i));
        }
    }

    @Test
    public void testEmptyBasic() {
        TimeSeries catPopulation = new TimeSeries();
        TimeSeries dogPopulation = new TimeSeries();

        assertThat(catPopulation.years()).isEmpty();
        assertThat(catPopulation.data()).isEmpty();

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);

        assertThat(totalPopulation.years()).isEmpty();
        assertThat(totalPopulation.data()).isEmpty();
    }

    @Test
    public void sumTest_empty(){
        TimeSeries ts1 = new TimeSeries();
        TimeSeries ts2 = new TimeSeries();
        // case 2 empty
        TimeSeries ts_empty = ts1.plus(ts2);
        assertThat(ts_empty).isEmpty();
    }
    @Test
    public void sumTest_emptyCase1(){
        TimeSeries ts1 = new TimeSeries();
        TimeSeries ts2 = new TimeSeries();
        // case 1 empty(param = empty)
        ts1.put(1456,2.2);
        TimeSeries ts_empty_param = ts1.plus(ts2);
        assertThat(ts_empty_param).isEqualTo(ts1);
    }
    @Test
    public void sumTest_emptyCase2(){
        TimeSeries ts1 = new TimeSeries();
        TimeSeries ts2 = new TimeSeries();
        // case 2 empty (variable = empty)
        ts1.put(1456,2.2);
        TimeSeries ts_empty_var = ts2.plus(ts1);
        assertThat(ts_empty_var).isEqualTo(ts1);
    }
    @Test
    public void divideTest(){
        TimeSeries ts1 = new TimeSeries();
        TimeSeries ts2 = new TimeSeries();
        ts1.put(1450,2.2);
        ts1.put(1451,2.2);
        ts1.put(1452,2.2);

        ts2.put(1450,1.1);
        ts2.put(1451,1.1);
        ts2.put(1452,1.);
        ts2.put(1453,1.1);

        TimeSeries dividedTS = ts1.dividedBy(ts2);
    }

} 