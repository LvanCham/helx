package com.cham.alice.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cham.alice.api.ApiService
import com.cham.alice.base.event.AliceMessage
import com.cham.alice.base.event.SingleLiveEvent
import com.cham.alice.base.network.ExceptionHandle
import com.cham.alice.base.network.ResponseThrowable
import com.cham.alice.base.network.RetrofitHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*



/**
 * Hello World
 * Date: 2019/12/5
 * Author: Cham
 */
abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    val mService by lazy { RetrofitHelper.getInstance().create(ApiService::class.java) }
    val defUI: UIChange by lazy { UIChange() }
    protected var mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    fun addDispose(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }
    protected fun unDispose() {
        mCompositeDisposable.clear()
    }
    fun Disposable.addTo(c:CompositeDisposable){
        c.add(this)
    }
    inner class UIChange {
        val showDialog by lazy { SingleLiveEvent<String>() }
        val dismissDialog by lazy { SingleLiveEvent<Void>() }
        val toastEvent by lazy { SingleLiveEvent<String>() }
        val msgEvent by lazy { SingleLiveEvent<AliceMessage>() }
    }
    private fun launchUI(block:suspend  CoroutineScope.() ->Unit){
        viewModelScope.launch {
            block()
        }
    }
    fun launchMain(block:suspend CoroutineScope.()->Unit,
                   error:suspend CoroutineScope.(ResponseThrowable) ->Unit={
                 //  defUI.toastEvent.postValue("${it.code}:${it.errMsg}")
                       if (it.code ==1002) {
                           defUI.msgEvent.postValue(AliceMessage(it.code))
                       }
                   defUI.toastEvent.postValue(it.errMsg)
               },
                   complete: suspend CoroutineScope.() -> Unit = {},
                   isShowDialog: Boolean = true){
        if (isShowDialog) defUI.showDialog.call()
        launchUI {
            handleException(
               withContext(Dispatchers.IO){
                    block
                },{
                    error(it)
                },{
                    defUI.dismissDialog.call()
                    complete()
                }
            )
        }
    }

    private  suspend fun handleException(
            block:suspend CoroutineScope.() ->Unit,
            error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
            complete: suspend CoroutineScope.() -> Unit){
        coroutineScope{
            try {
                block()
            }catch (e:Throwable){
                error(ExceptionHandle.handleException(e))
            }
            finally {
                complete()
            }

        }
    }

    override fun onCleared() {
        unDispose()
        super.onCleared()
    }

}