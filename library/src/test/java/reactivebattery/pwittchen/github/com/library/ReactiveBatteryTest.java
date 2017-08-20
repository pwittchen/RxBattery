package reactivebattery.pwittchen.github.com.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(RobolectricTestRunner.class) public class ReactiveBatteryTest {

  @Test public void additionShouldBeCorrect() throws Exception {
    assertThat(2 + 2).isEqualTo(4);
  }
}