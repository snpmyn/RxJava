package com.zsp.rxjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @desc: 主页
 * @author: zsp
 * @date: 2021/5/26 5:18 下午
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private MaterialButton mainActivityBtnExampleOne;
    private MaterialButton mainActivityBtnExampleTwo;
    private MaterialButton mainActivityBtnExampleThree;
    private MaterialButton mainActivityBtnExampleFour;
    private MaterialButton mainActivityBtnExampleFive;
    private MaterialButton mainActivityBtnExampleSix;

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
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    /**
     * 示例四
     */
    private void exampleFour() {

    }

    /**
     * 示例五
     */
    private void exampleFive() {

    }

    /**
     * 示例六
     */
    private void exampleSix() {

    }
}