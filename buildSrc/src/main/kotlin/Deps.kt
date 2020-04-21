
object Versions {
    const val androidx_appCompat = "1.0.0-beta01"
    const val androidx_core = "1.0.0-beta01"
    const val androidx_recyclerView = "1.0.0"
    const val materialDesign = "1.0.0-rc01"
    const val picasso = "2.71828"
    const val androidx_constraintLayout = "2.0.0-alpha2"
    const val retrofit = "2.6.1"
    const val okHttp = "3.10.0"
    const val rx_java = "2.2.0"
    const val rx_android = "2.0.2"
    const val rx_kotlin = "2.3.0"
    const val rx_lifeCycleComponent = "2.2.1"
    const val rx_permissions = "0.10.2"
    const val intuit = "1.0.5"
    const val koin = "2.0.1"
    const val androidx_lifeCycle = "2.0.0-rc01"
    const val sha_formValidator = "2.1.0"
    const val sha_navigator = "1.7.0"
    const val sha_rxRequester = "0.7.0"
    const val sha_modelMapper = "0.1.0"
    const val androidx_coreKtx = "1.1.0"
    const val androidx_paging = "2.0.0-rc01"
    const val androidx_swiperefreshlayout = "1.0.0"
    const val sha_bulletin = "0.1.3"
    const val sha_coroutineRequester = "0.3.0"
    const val androidx_ViewModelExt = "2.2.0"

}

object Deps {
    // intuit
    const val sdp = "com.intuit.sdp:sdp-android:${Versions.intuit}"
    const val ssp = "com.intuit.ssp:ssp-android:${Versions.intuit}"

    // koin
    const val koin_android = "org.koin:koin-android:${Versions.koin}"
    const val koin_scope = "org.koin:koin-android-scope:${Versions.koin}"
    const val koin_viewModel = "org.koin:koin-android-viewmodel:${Versions.koin}"
    const val koin_java = "org.koin:koin-java:${Versions.koin}"
    const val koin_ext = "org.koin:koin-android-ext:${Versions.koin}"

    // androidx
    const val androidx_appCompat   = "androidx.appcompat:appcompat:${Versions.androidx_appCompat}"
    const val androidx_core  = "androidx.core:core:${Versions.androidx_core}"
    const val androidx_recyclerView  = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerView}"

    const val androidx_constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintLayout}"
    const val androidx_lifeCycle = "androidx.lifecycle:lifecycle-extensions:${Versions.androidx_lifeCycle}"
    const val androidx_lifeCycleCommonJava8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.androidx_lifeCycle}"
    const val androidx_coreKtx = "androidx.core:core-ktx:${Versions.androidx_coreKtx}"
    const val androidx_paging = "androidx.paging:paging-runtime:${Versions.androidx_paging}"
    const val androidx_swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.androidx_swiperefreshlayout}"
    const val androidx_ViewModelExt = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_ViewModelExt}"

    // retrofit
    const val retrofit_runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofit_converter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    // squareup
    const val picasso  = "com.squareup.picasso:picasso:${Versions.picasso}"
    const val okHttp_runtime = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttp_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    // rx
    const val rx_java = "io.reactivex.rxjava2:rxjava:${Versions.rx_java}"
    const val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"
    const val rx_kotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rx_kotlin}"
    const val rx_lifeCycleComponent = "com.trello.rxlifecycle2:rxlifecycle-components:${Versions.rx_lifeCycleComponent}"
    const val rx_permissions = "com.github.tbruyelle:rxpermissions:${Versions.rx_permissions}"

    const val materialDesign  = "com.google.android.material:material:${Versions.materialDesign}"

    // Shaban kamel
    const val sha_formValidator  = "com.github.ShabanKamell:FormValidator:${Versions.sha_formValidator}"
    const val sha_formValidator_core  = "com.github.ShabanKamell.FormValidator:core:${Versions.sha_formValidator}"
    const val sha_formValidator_rx  = "com.github.ShabanKamell.FormValidator:rxjava:${Versions.sha_formValidator}"
    const val sha_navigator = "com.github.ShabanKamell:Navigator:${Versions.sha_navigator}"
    const val sha_rxRequester = "com.github.ShabanKamell:RxRequester:${Versions.sha_rxRequester}"
    const val sha_modelMapper = "com.github.ShabanKamell:ModelMapper:${Versions.sha_modelMapper}"
    const val sha_bulletin = "com.github.ShabanKamell:Bulletin:${Versions.sha_bulletin}"
    const val sha_coroutineRequester = "com.github.ShabanKamell:CoroutineRequester:${Versions.sha_coroutineRequester}"

}

object TestVersions {
    const val test_junit = "4.12"
    const val test_jUnitParams = "1.1.1"
    const val test_kotlinTestJunit5 = "3.4.0"
    const val test_kotlinTestExtKoin = "3.4.0"
    const val test_espressoCore = "3.1.0-alpha4"
    const val androidx_junit = "1.1.1"
    const val androidx_espressoCore = "3.2.0"
}

object TestDeps {
    const val junit = "junit:junit:${TestVersions.test_junit}"
    const val jUnitParams = "pl.pragmatists:JUnitParams:${TestVersions.test_jUnitParams}"

    const val kotlinTestJunit5 = "io.kotlintest:kotlintest-runner-junit5:${TestVersions.test_kotlinTestJunit5}"
    const val kotlinTestExtKoin = "io.kotlintest:kotlintest-extensions-koin:${TestVersions.test_kotlinTestExtKoin}"

    const val espressoCore = "androidx.test.espresso:espresso-core:${TestVersions.test_espressoCore}"
    const val koin_test = "org.koin:koin-android-ext:${Versions.koin}"

    const val androidx_espressoCore = "androidx.test.espresso:espresso-core:${TestVersions.androidx_espressoCore}"
    const val androidx_junit = "androidx.test.ext:junit:${TestVersions.androidx_junit}"
}