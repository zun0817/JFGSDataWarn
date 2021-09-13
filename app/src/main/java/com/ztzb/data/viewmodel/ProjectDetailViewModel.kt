package com.ztzb.data.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.http.rxjava.disposableOnDestroy
import com.ztzb.data.model.data.ProjectDetailBean
import com.ztzb.data.model.data.SectionBean
import com.ztzb.data.model.repository.ProjectDetailRepository

class ProjectDetailViewModel(private val repository: ProjectDetailRepository) : BaseViewModel() {

    private val TAG = ProjectDetailViewModel::class.java.simpleName

    private lateinit var owner: LifecycleOwner

    val sections = MutableLiveData<MutableList<SectionBean>>()

    val projectDetailBean = MutableLiveData<ProjectDetailBean>()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        this.owner = owner
        requestOfProjectDetail()
        requestOfSection()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        dismissLoading()
    }

    fun requestOfProjectDetail() {
        repository.requestOfProjectDetail()
            .disposableOnDestroy(owner)
            .subscribe({
                projectDetailBean.value = it
            }, {
                showToast(it.toString())
            })
    }

    fun requestOfSection() {
        repository.requestOfSection()
            .doOnSubscribe { showLoading() }
            .doAfterTerminate { dismissLoading() }
            .disposableOnDestroy(owner)
            .subscribe({
                sections.value = it
            }, {
                showToast(it.toString())
            })
    }

}