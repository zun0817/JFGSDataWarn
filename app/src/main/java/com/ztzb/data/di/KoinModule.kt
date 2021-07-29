package com.ztzb.data.di

import com.ztzb.data.http.http.HttpModule
import com.ztzb.data.model.remote.*
import com.ztzb.data.model.repository.*
import com.ztzb.data.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { MainViewModel(get()) }
    viewModel { MessageViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
    viewModel { SectionViewModel(get()) }
    viewModel { WarnViewModel(get()) }
    viewModel { WarnFormViewModel(get()) }
    viewModel { MonitorViewModel(get()) }
    viewModel { ReportViewModel(get()) }
    viewModel { SetValueViewModel(get()) }
    viewModel { WarnDeviceDetailViewModel(get()) }
}

val repositoryModule = module {
    factory { LoginRepository(get()) }
    factory { MainRepository() }
    factory { MessageRepository(get()) }
    factory { ProjectRepository(get()) }
    factory { SectionRepository(get()) }
    factory { WarnRepository(get()) }
    factory { WarnFormRepository(get()) }
    factory { MonitorRepository(get()) }
    factory { ReportRepository(get()) }
    factory { SetValueRepository(get()) }
    factory { WarnDeviceDetailRepository(get()) }
}

val remoteModule = module {
    single { HttpModule.initRetrofit() }
    single { get<Retrofit>().create(LoginService::class.java) }
    single { get<Retrofit>().create(ProjectService::class.java) }
    single { get<Retrofit>().create(MessageService::class.java) }
    single { get<Retrofit>().create(SectionService::class.java) }
    single { get<Retrofit>().create(WarnFormService::class.java) }
    single { get<Retrofit>().create(WarnService::class.java) }
    single { get<Retrofit>().create(MonitorService::class.java) }
    single { get<Retrofit>().create(ReportService::class.java) }
    single { get<Retrofit>().create(SetValueService::class.java) }
    single { get<Retrofit>().create(WarnDeviceDetailService::class.java) }
}

val appModule = listOf(viewModelModule, repositoryModule, remoteModule)