# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn lucas.momo.designsystem.theme.DimensKt
-dontwarn lucas.momo.designsystem.theme.Dimensions
-dontwarn lucas.momo.designsystem.theme.ThemeKt
-dontwarn lucas.momo.stocks.StocksActivity_GeneratedInjector
-dontwarn lucas.momo.stocks.data.network.TwelveDataApi
-dontwarn lucas.momo.stocks.data.repositories.StockExchangeRepository
-dontwarn lucas.momo.stocks.di.AppModule_ProvideGetStockExchangeUseCaseFactory
-dontwarn lucas.momo.stocks.di.AppModule_ProvideStockExchangeRepositoryFactory
-dontwarn lucas.momo.stocks.di.AppModule_ProvideTwelveDataApiFactory
-dontwarn lucas.momo.stocks.domain.usecases.GetStockExchangeUseCase
-dontwarn lucas.momo.stocks.presentation.viewmodels.StockExchangeViewModel
-dontwarn lucas.momo.stocks.presentation.viewmodels.StockExchangeViewModel_HiltModules$KeyModule
-dontwarn lucas.momo.stocks.presentation.viewmodels.StockExchangeViewModel_HiltModules_BindsModule_Binds_LazyMapKey
-dontwarn lucas.momo.stocks.presentation.viewmodels.StockExchangeViewModel_HiltModules_KeyModule_Provide_LazyMapKey