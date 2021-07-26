package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.data.ProjectBean
import com.ztzb.data.model.repository.ProjectRepository

class ProjectViewModel(private val repository: ProjectRepository) : BaseViewModel() {

    private val TAG = ProjectViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val projects = MutableLiveData<MutableList<ProjectBean>>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfProject() {
        repository.requestOfProject()
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                projects.value = it
            }, {
                showToast(it.toString())
            })

    }
}