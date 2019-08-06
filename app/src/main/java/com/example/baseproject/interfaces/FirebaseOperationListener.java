package com.example.baseproject.interfaces;

/**
 * created by Rishika Agarwal on 8/6/2019.
 */
public interface FirebaseOperationListener {

    void firebaseOperationCompleted(Object o , Class c);
    void firebaseOperationFailed(Object o , Class c);
    void firebaseOperationSuccess(Object o , Class c);
//    void firebaseOperationCancel(Object o , Class c);

}
