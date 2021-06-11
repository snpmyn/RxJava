package com.zsp.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @desc: 主页
 * @author: zsp
 * @date: 2021/5/26 5:18 下午
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    /**
     * 控件
     */
    private MaterialButton mainActivityBtnExampleOne;
    private MaterialButton mainActivityBtnExampleTwo;
    private MaterialButton mainActivityBtnExampleThree;
    private MaterialButton mainActivityBtnExampleFour;
    private MaterialButton mainActivityBtnExampleFive;
    private MaterialButton mainActivityBtnExampleSix;
    private MaterialButton mainActivityBtnExampleSeven;
    /**
     * 魔法值
     */
    private final int MAGIC_VALUE_INT_3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stepUi();
        setListener();
    }

    /**
     * 初始化 UI
     */
    private void stepUi() {
        mainActivityBtnExampleOne = findViewById(R.id.mainActivityBtnExampleOne);
        mainActivityBtnExampleTwo = findViewById(R.id.mainActivityBtnExampleTwo);
        mainActivityBtnExampleThree = findViewById(R.id.mainActivityBtnExampleThree);
        mainActivityBtnExampleFour = findViewById(R.id.mainActivityBtnExampleFour);
        mainActivityBtnExampleFive = findViewById(R.id.mainActivityBtnExampleFive);
        mainActivityBtnExampleSix = findViewById(R.id.mainActivityBtnExampleSix);
        mainActivityBtnExampleSeven = findViewById(R.id.mainActivityBtnExampleSeven);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        mainActivityBtnExampleOne.setOnClickListener(this);
        mainActivityBtnExampleTwo.setOnClickListener(this);
        mainActivityBtnExampleThree.setOnClickListener(this);
        mainActivityBtnExampleFour.setOnClickListener(this);
        mainActivityBtnExampleFive.setOnClickListener(this);
        mainActivityBtnExampleSix.setOnClickListener(this);
        mainActivityBtnExampleSeven.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainActivityBtnExampleOne:
                exampleOne();
                break;
            case R.id.mainActivityBtnExampleTwo:
                exampleTwo();
                break;
            case R.id.mainActivityBtnExampleThree:
                exampleThree();
                break;
            case R.id.mainActivityBtnExampleFour:
                exampleFour();
                break;
            case R.id.mainActivityBtnExampleFive:
                exampleFive();
                break;
            case R.id.mainActivityBtnExampleSix:
                exampleSix();
                break;
            case R.id.mainActivityBtnExampleSeven:
                exampleSeven();
                break;
            default:
                break;
        }
    }

    /**
     * 示例一
     */
    private void exampleOne() {
        Observable<Integer> observable = io.reactivex.rxjava3.core.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.d(TAG, "[发射] emitter 1");
                emitter.onNext(1);
                Log.d(TAG, "[发射] emitter 2");
                emitter.onNext(2);
                Log.d(TAG, "[发射] emitter 3");
                emitter.onNext(3);
                Log.d(TAG, "[发射] emitter onComplete");
                emitter.onComplete();
                Log.d(TAG, "[完成后继续发射] emitter 4");
                emitter.onNext(4);
            }
        });
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "[订阅] onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, "[接收] onNext " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "[异常] onError " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "[完成] onComplete");
            }
        };
        observable.subscribe(observer);
    }

    /**
     * 示例二
     */
    private void exampleTwo() {
        io.reactivex.rxjava3.core.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.d(TAG, "[发射] emitter 1");
                emitter.onNext(1);
                Log.d(TAG, "[发射] emitter 2");
                emitter.onNext(2);
                Log.d(TAG, "[发射] emitter 3");
                emitter.onNext(3);
                Log.d(TAG, "[发射] emitter onComplete");
                emitter.onComplete();
                Log.d(TAG, "[完成后继续发射] emitter 4");
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "[订阅] onSubscribe");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, "[接收] onNext " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "[异常] onError " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "[完成] onComplete");
            }
        });
    }

    /**
     * 示例三
     */
    private void exampleThree() {
        Observable<Integer> observable = io.reactivex.rxjava3.core.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.d(TAG, "上游线程 " + Thread.currentThread().getName());
                Log.d(TAG, "[发射] emitter 1");
                emitter.onNext(1);
            }
        });
        io.reactivex.rxjava3.functions.Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d(TAG, "下游线程 " + Thread.currentThread().getName());
                Log.d(TAG, "[接收] onNext " + integer);
            }
        };
        observable.subscribe(consumer);
    }

    /**
     * 示例四
     */
    private void exampleFour() {
        Observable<Integer> observable = io.reactivex.rxjava3.core.Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.d(TAG, "上游线程 " + Thread.currentThread().getName());
                Log.d(TAG, "[发射] emitter 1");
                emitter.onNext(1);
            }
        });
        io.reactivex.rxjava3.functions.Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Log.d(TAG, "下游线程 " + Thread.currentThread().getName());
                Log.d(TAG, "[接收] onNext " + integer);
            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(consumer);
    }

    /**
     * 示例五
     */
    private void exampleFive() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.d(TAG, "[发射] emitter 1");
                emitter.onNext(1);
                Log.d(TAG, "[发射] emitter 2");
                emitter.onNext(2);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Throwable {
                Log.d(TAG, "[转化] apply " + integer);
                return "apply " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d(TAG, "[接收] accept " + s);
            }
        });
    }

    /**
     * 示例六
     */
    private void exampleSix() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.d(TAG, "[发射] emitter 1");
                emitter.onNext(1);
                Log.d(TAG, "[发射] emitter 2");
                emitter.onNext(2);
                Log.d(TAG, "[发射] emitter 3");
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Throwable {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < MAGIC_VALUE_INT_3; i++) {
                    list.add("apply " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d(TAG, "[接收] accept " + s);
            }
        });
    }

    /**
     * 示例七
     */
    private void exampleSeven() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                Log.d(TAG, "[发射] emitter 1");
                emitter.onNext(1);
                Log.d(TAG, "[发射] emitter 2");
                emitter.onNext(2);
                Log.d(TAG, "[发射] emitter 3");
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Throwable {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < MAGIC_VALUE_INT_3; i++) {
                    list.add("apply " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                Log.d(TAG, "[接收] accept " + s);
            }
        });
    }
}