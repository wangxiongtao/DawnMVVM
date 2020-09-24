package com.example.dawnmvvm.base;

import io.reactivex.disposables.Disposable;

public interface IDisposable {
    void addDisposable(Disposable disposable);
}
