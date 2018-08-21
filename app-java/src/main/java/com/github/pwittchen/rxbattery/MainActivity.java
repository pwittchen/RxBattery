package com.github.pwittchen.rxbattery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.github.pwittchen.rxbattery.library.RxBattery;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

  private Disposable batteryDisposable;
  private TextView textView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override protected void onResume() {
    super.onResume();
    textView = findViewById(R.id.textView);
    batteryDisposable = RxBattery.observe(this)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(state -> {
          textView.setText(String.format(
              "info %s, status: %s, plugged: %s, health: %s",
              state.toString(), state.status(), state.plugged(), state.health()
          ));
        });
  }

  @Override protected void onPause() {
    super.onPause();
    if (batteryDisposable != null && !batteryDisposable.isDisposed()) {
      batteryDisposable.dispose();
    }
  }
}
